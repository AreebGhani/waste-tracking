'use client';
import { useEffect, useState } from 'react';
import axios from 'axios';
import Head from 'next/head';

import 'bootstrap/dist/css/bootstrap.min.css';

const base64 = Buffer.from('CrewFlute:ClimpFlee').toString('base64');

export type SuspendedBatchesData = {
  batchId: number;
  suspensionEffectDt: string;
  suspensionFollowDt: string;
  accountNo: number;
  wasteUnit: number;
};

const SuspendedBatches = () => {
  const [suspendedBatchesList, setSuspendedBatchesList] = useState<SuspendedBatchesData[] | null>([
    {
      batchId: 1,
      accountNo: 1,
      suspensionEffectDt: 'dcsd',
      suspensionFollowDt: '123',
      wasteUnit: 123,
    },
  ]);
  const storedUser = typeof window !== 'undefined' ? localStorage.getItem('current_user') : null;
  const user = storedUser ? JSON.parse(storedUser).auAdid : null;

  useEffect(() => {
    axios
      .get(`http://localhost:8080/batches/suspendedbatches`)
      .then(response => {
        setSuspendedBatchesList(response.data);
      })
      .catch(error => {
        // eslint-disable-next-line no-console
        console.error('Error fetching suspended batches:', error);
      });
  }, []);

  const SendSuspendEmail = async (from: string, to: string, suspendedBatchesList: any[]) => {
    try {
      const html1 = `
      <div>
        <p>The following suspensions are within one week of expiration or beyond the expiration date. </p><br/>`;
      let html2 = ``;
      suspendedBatchesList.forEach(suspendedBatches => {
        html2 += `<p>Batch Id: ${suspendedBatches?.batchId} </p>
        <p>Account Number: ${suspendedBatches?.accountNo}</p>
        <p>Suspension Effect Date: ${suspendedBatches?.suspensionEffectDt}</p>
        <p>Suspension Follow Date: ${suspendedBatches?.suspensionFollowDt}</p>
        <p>Waste Unit: ${suspendedBatches?.wasteUnit}</p>
        <br/>`;
      });
      const html3 = `</div>`;
      const html = html1 + html2 + html3;
      //       const proxyUrl = 'http://localhost:3000';
      // const targetUrl = 'https://postdrop.aacounty.org/email';

      const response = await fetch(`https://postdrop.aacounty.org/email`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Basic ${base64}`,
        },
        body: JSON.stringify({
          from: from,
          to: to,
          subject: `DPW - Suspension Expiration Email Report`,
          html: html,
        }),
      });
      const result = response.status;
      return result;
    } catch (error) {
      // eslint-disable-next-line no-console
      console.log(error);
    }
  };

  return (
    <div className="container my-5">
      <Head>
        <title>Suspended Batches</title>
        <meta name="description" content="Suspended Batches page" />
      </Head>
      <div className="table-responsive">
        <table className="table table-striped table-bordered table-hover">
          <thead className="table-dark">
            <tr>
              <th>Batch Id</th>
              <th>Suspension Effect Date</th>
              <th>Suspension Follow Date</th>
              <th>Account Number</th>
              <th>Waste Unit</th>
            </tr>
          </thead>
          <tbody>
            {suspendedBatchesList ? (
              suspendedBatchesList.map((batch, index) => (
                <tr key={index}>
                  <td>{batch.batchId}</td>
                  <td>{batch.suspensionEffectDt}</td>
                  <td>{batch.suspensionFollowDt}</td>
                  <td>{batch.accountNo}</td>
                  <td>{batch.wasteUnit}</td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan={5} className="text-center">
                  No suspended batches found
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
      <div className="mt-3 p-3 bg-light rounded">
        <button
          onClick={() => {
            // console.log(storedUser);
            // console.log(`User: ${user}`);
            // console.log(suspendedBatchesList);
            // Check if suspendedBatchesList is not null before calling SendSuspendEmail
            if (suspendedBatchesList) {
              SendSuspendEmail(
                `${user}@aacounty.org`,
                `${user}@aacounty.org`,
                suspendedBatchesList
              ).then(result => {
                if (result === 200) {
                  alert('Email sent');
                } else {
                  alert('Something went wrong when sending emails.');
                }
              });
            } else {
              alert('No suspended batches to send.'); // Alert if suspendedBatchesList is null
            }
          }}
          className="btn btn-secondary">
          Send Email
        </button>
      </div>
    </div>
  );
};

export default SuspendedBatches;
