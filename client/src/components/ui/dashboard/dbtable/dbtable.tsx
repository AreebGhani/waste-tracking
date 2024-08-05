'use client';
import React, { useEffect, useState } from 'react';

import styles from './dbtable.module.css';
import getAxiosInstance from '@/config/axiosInstance';
import DbFilter, { FilterData } from './dbfilter/dbfilter';

export const BASE_ENTRIES_PER_PAGE: number = 25;

export type BatchData = {
  wbhSeqNo: number;
  batchId: number;
  status: string;
  statusDt: string;
  userId: string;
  wbhComments: string;
  batchType: string;
};

const processSearchResults = (results: BatchData[]) => {
  const filteredResults = new Map<number, BatchData>();

  results.forEach(result => {
    const existingEntry = filteredResults.get(result.batchId);
    if (!existingEntry || new Date(result.statusDt) > new Date(existingEntry.statusDt)) {
      filteredResults.set(result.batchId, result);
    }
  });

  return Array.from(filteredResults.values());
};

const DbTable = () => {
  const [searchResults, setSearchResults] = useState<BatchData[]>([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [serviceAreas, setServiceAreas] = useState<string[]>([]);
  const [filterVisible, setFilterVisible] = useState(true); // State to control filter visibility
  const totalEntries = searchResults.length;
  const totalPages = Math.ceil(totalEntries / BASE_ENTRIES_PER_PAGE);

  const fetchServiceAreas = async () => {
    try {
      const axiosInstance = getAxiosInstance();
      const response = await axiosInstance.get('/serviceareas');
      setServiceAreas(response.data);
    } catch (error) {
      // eslint-disable-next-line no-console
      console.error('Error fetching service areas:', error);
    }
  };

  const handleFilterSubmit = async (data: FilterData) => {
    try {
      const axiosInstance = getAxiosInstance();
      const response = await axiosInstance.post('/batches/view/search', data);
      setSearchResults(data.onlyLastStatus ? processSearchResults(response.data) : response.data);
      setCurrentPage(1);
    } catch (error) {
      // eslint-disable-next-line no-console
      console.error('Error fetching search results:', error);
    }
  };

  const handlePageChange = (newPage: number) => {
    if (newPage >= 1 && newPage <= totalPages) {
      setCurrentPage(newPage);
    }
  };

  const dataSlice = searchResults.slice(
    (currentPage - 1) * BASE_ENTRIES_PER_PAGE,
    currentPage * BASE_ENTRIES_PER_PAGE
  );

  useEffect(() => {
    fetchServiceAreas();
    handleFilterSubmit({});
  }, []);

  const toggleFilterVisibility = () => {
    setFilterVisible(!filterVisible);
  };

  return (
    <div className={styles.container}>
      <button className={styles.toggleButton} onClick={toggleFilterVisibility}>
        {filterVisible ? 'Hide Filter' : 'Show Filter'}
      </button>
      <div className={filterVisible ? '' : styles.hidden}>
        <DbFilter onSubmit={handleFilterSubmit} serviceAreas={serviceAreas} />
      </div>

      <div className={styles.pagination}>
        <button onClick={() => handlePageChange(1)} disabled={currentPage === 1}>
          First
        </button>
        <button onClick={() => handlePageChange(currentPage - 1)} disabled={currentPage === 1}>
          Prev
        </button>
        <span>
          Page {currentPage} of {totalPages}
        </span>
        <button
          onClick={() => handlePageChange(currentPage + 1)}
          disabled={currentPage === totalPages}>
          Next
        </button>
        <button onClick={() => handlePageChange(totalPages)} disabled={currentPage === totalPages}>
          Last
        </button>
      </div>

      <table className={styles.table}>
        <thead>
          <tr>
            <th>WBH SeqNo</th>
            <th>Batch ID</th>
            <th>Status</th>
            <th>Status Date</th>
            <th>User ID</th>
            <th>Comments</th>
            <th>Batch Type</th>
          </tr>
        </thead>
        <tbody>
          {dataSlice.map((result, index) => (
            <tr key={index}>
              <td>{result.wbhSeqNo}</td>
              <td>{result.batchId}</td>
              <td>{result.status}</td>
              <td>{result.statusDt}</td>
              <td>{result.userId}</td>
              <td>{result.wbhComments}</td>
              <td>{result.batchType}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default DbTable;
