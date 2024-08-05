'use client';

import { useState, useEffect } from 'react';
import axios from 'axios';
import { useRouter } from 'next/navigation';
import Head from 'next/head';

const CreateWasteUnitPage = () => {
  const [batchType, setBatchType] = useState('');
  const [showToast, setShowToast] = useState(false);
  const [toastMessage, setToastMessage] = useState({ title: '', description: '', status: '' });
  const [userId, setUserId] = useState(null);
  const [isMounted, setIsMounted] = useState(false);
  const router = useRouter(); // Call useRouter at the top level

  useEffect(() => {
    setIsMounted(true);
    const user = JSON.parse(localStorage.getItem('current_user') || '{}');
    if (user) {
      setUserId(user.adid);
    }
  }, []);

  const handleSubmit = async (event: any) => {
    event.preventDefault();
    const batchTypeFormatted = deboseBatchType(batchType);
    try {
      const response = await axios.post(`http://localhost:8080/batches/create`, {
        batchType: batchTypeFormatted,
        userId: userId,
      });

      const batchId = response.data.batchId;
      // var batchId = "";

      router.replace(`/CreatedWasteUnitPage?batchId=${batchId}&batchType=${batchTypeFormatted}`);

      setToastMessage({
        title: 'Batch Created',
        description: `Batch ${batchId} of type ${batchTypeFormatted} has been created.`,
        status: 'success',
      });
      setShowToast(true);
    } catch (error) {
      setToastMessage({
        title: 'Error',
        description: 'An error occurred while creating the batch.',
        status: 'error',
      });
      setShowToast(true);
    }
  };

  if (!isMounted) {
    return null; // Render nothing if not mounted
  }

  return (
    <div className="d-flex justify-content-center align-items-center vh-100">
      <Head>
        <title>Create Waste Unit</title>
        <meta name="description" content="Create Waste Unit page" />
      </Head>
      <div>
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label htmlFor="batchType" className="form-label">
              Batch type:
            </label>
            <select
              id="batchType"
              name="batchType"
              className="form-select"
              value={batchType}
              onChange={e => setBatchType(e.target.value)}
              required>
              <option value="">Select...</option>
              <option value="Add">Add</option>
              <option value="Delete">Delete</option>
              <option value="Occupancy">Occupancy</option>
              <option value="Suspension">Suspension</option>
            </select>
          </div>
          <button type="submit" className="btn btn-primary">
            Create
          </button>
        </form>
        {showToast && (
          <div
            className={`toast align-items-center text-white ${toastMessage.status === 'success' ? 'bg-success' : 'bg-danger'}`}
            role="alert">
            <div className="d-flex">
              <div className="toast-body">
                <strong>{toastMessage.title}</strong>: {toastMessage.description}
              </div>
              <button
                type="button"
                className="btn-close btn-close-white me-2 m-auto"
                onClick={() => setShowToast(false)}
                aria-label="Close"></button>
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default CreateWasteUnitPage;

function deboseBatchType(type: string) {
  // Your deboseBatchType function implementation here
  return type;
}
