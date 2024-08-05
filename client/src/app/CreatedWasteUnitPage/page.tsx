'use client';

import { Suspense, useEffect, useState } from 'react';
import { useRouter, useSearchParams } from 'next/navigation';
import axios from 'axios';

import 'bootstrap/dist/css/bootstrap.min.css';
import NewAttachment from '@/components/FileUpload';

const CreatedWasteUnit = () => {
  const router = useRouter();
  const params = useSearchParams();
  // const [batchId, setBatchId] = useState<string | null>(null)
  // const [batchType, setBatchType] = useState<string | null>(null)
  const [batchId, setBatchId] = useState<string>('');
  const [batchType, setBatchType] = useState<string>('');
  useEffect(() => {
    if (params.keys()) {
      //const { batchId, batchType } = router.query; // Destructure query from router object
      if (batchId && batchType) {
        setBatchId(params.get('batchId')!.toString());
        setBatchType(params.get('batchType')!.toString());
      }
    }
  }, [batchId, batchType, params]);

  //if (!router.isReady) {
  //return null; // Or some loading indicator if necessary
  //}
  const [msg, setMsg] = useState<string | null>(null);
  const [batch, setBatch] = useState<any>(null);
  const [accounts, setAccounts] = useState<any[]>([]);
  const [account, setAccount] = useState<any>(null);
  const [showModal, setShowModal] = useState(false);
  const [toastMsg, setToastMsg] = useState<string | null>(null);
  const [documentationFile, setDocumentationFile] = useState<any>(null);

  const fetchAccountData = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/batches/accounthistory/view/allbyid`,
        { params: { batchId: batchId } }
      );
      setAccounts(response.data);
    } catch (error: any) {
      if (
        error.response &&
        typeof error.response.data.message === 'string' &&
        error.response.data.message.includes('account history')
      ) {
        alert('No account history found for this batch. Please create an account history entry.');
      } else {
        // eslint-disable-next-line no-console
        console.error('An error occurred while fetching account data.');
      }
    }
  };

  const handleFileUpload = async (file: File) => {
    const formData = new FormData();
    formData.append('file', file);

    const response = await axios.post(`/upload`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });
    return response.data.filePath;
  };

  const onAccountSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    if (!(event.target instanceof HTMLFormElement)) {
      return; // Handle the case where event.target is not an HTMLFormElement
    }
    const data = new FormData(event.target);
    try {
      const documentationPath = documentationFile ? await handleFileUpload(documentationFile) : '';
      const wasteUnitChange = data.get('wasteUnitChange');
      const wasteUnit = wasteUnitChange ? parseInt(wasteUnitChange as string, 10) : 0;
      const currentUserString = localStorage.getItem('current_user');
      const userId = currentUserString ? JSON.parse(currentUserString).adid : '';
      await axios.post(`http://localhost:8080/batches/accounthistory/create`, {
        accountNo: data.get('accountNo'),
        batchId: batchId,
        wasteUnit: wasteUnit,
        // effectiveDate: convertToSqlTimestamp(data.get('effectiveDate')),
        userId: userId,
        comments: data.get('comments'),
        documentNotes: documentationPath,
        serviceArea: data.get('serviceArea'),
      });
      await fetchAccountData();
      setMsg('Account added!');
      setShowModal(false);
    } catch (error) {
      setToastMsg('Account creation failed. Please try again later.');
    }
  };

  const updateAccount = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const data = new FormData(event.target as HTMLFormElement);
    try {
      let documentationPath = '';
      if (documentationFile) {
        documentationPath = await handleFileUpload(documentationFile);
        //	 } else if (account && 'documentNotes' in account) {
      } else if (account && 'ahSeqNo' in account) {
        const wasteUnitChange = data.get('wasteUnitChange');
        let wasteUnit: number;
        if (typeof wasteUnitChange === 'string') {
          wasteUnit = parseInt(wasteUnitChange || '0', 10);
        } else {
          // Handle file case or any other scenario
          // eslint-disable-next-line no-console
          console.error('Unexpected data type for wasteUnitChange:', typeof wasteUnitChange);
          return;
        }
        documentationPath = (account as { documentNotes: string }).documentNotes || '';
        const currentUserJson = localStorage.getItem('current_user');
        if (!currentUserJson) {
          throw new Error('current_user not found in localStorage');
        }

        const currentUser = JSON.parse(currentUserJson);
        if (!currentUser || !currentUser.adid) {
          throw new Error('current_user.adid is missing or invalid');
        }

        //const documentationPath = documentationFile ? await handleFileUpload(documentationFile) : account.documentNotes;

        await axios.post(`http://localhost:8080/batches/accounthistory/updatestatus`, {
          seqNo: (account as { ahSeqNo: number }).ahSeqNo,
          accountNo: data.get('accountNo'),
          batchId: batchId,
          //   wasteUnit: parseInt(data.get('wasteUnitChange') || '0', 10),
          wasteUnit: wasteUnit,
          // effectiveDate: convertToSqlTimestamp(data.get('effectiveDate')),
          userId: currentUser.adid,
          comments: data.get('comments'),
          documentNotes: documentationPath,
          serviceArea: data.get('serviceArea'),
        });
        await fetchAccountData();
        setMsg('Account updated!');
        setShowModal(false);
      } else {
        throw new Error('Account is invalid or missing documentNotes');
      }
    } catch (error: any) {
      // eslint-disable-next-line no-console
      console.error(error);
      if (error.response && error.response.data.message.includes('account history')) {
        alert('No account history found for this batch. Please create an account history entry.');
      } else {
        // eslint-disable-next-line no-console
        console.error('An error occurred while fetching account data.');
      }
      setToastMsg('Account update failed. Please try again later.');
    }
  };

  useEffect(() => {
    const fetchBatchData = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/batches/view/mostrecentid`, {
          params: { id: batchId },
        });
        setBatch(response.data);
      } catch (error: any) {
        // eslint-disable-next-line no-console
        console.error(error);
        if (error.response && error.response.data.message.includes('account history')) {
          alert('No account history found for this batch. Please create an account history entry.');
        } else {
          // eslint-disable-next-line no-console
          console.error('An error occurred while fetching account data.');
        }
      }
    };

    if (batchId) {
      fetchBatchData();
      fetchAccountData();
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [batchId]);

  useEffect(() => {
    if (batch != null) {
      // TODO: correct below
      // setMsg(
      //   `Batch #${batch.batchId}, Batch type: ${verboseBatchType(batch.batchType)}, Status: ${verboseStatus(batch.status)}${accounts.length !== 0 ? '.' : '.\nNo account history to report!'}`
      // );
    } else {
      setMsg('No entries found, please add matching account history');
    }
  }, [batch, accounts]);

  const deleteBatch = async () => {
    if (window.confirm('Are you sure you want to delete this batch?')) {
      try {
        await axios.delete(`http://localhost:8080/batches/delete/`, {
          params: { batchId: batchId },
        });
        // TODO: fix below
        // router.push({ pathname: '/', query: { message: 'Batch deleted!' } });
      } catch (error) {
        setToastMsg('Batch deletion failed. Please try again later.');
      }
    }
  };

  const suspensionUpdate = async (event: any) => {
    event.preventDefault();
    try {
      await axios.post(`http://localhost:8080/batches/updateDT`, {
        batchId: batchId,
        // effectiveDate: convertToSqlTimestamp(data.get('effectiveDate')),
        // followUpDate: convertToSqlTimestamp(data.get('followUpDate')),
        // reason: abbreviateSuspensionReason(data.get('reason'))
      });
      setMsg('Suspension info updated!');
    } catch (error) {
      setToastMsg('Suspension update failed. Please try again later.');
    }
  };

  const batchSubmit = async () => {
    if (!batchId) return;
    if (window.confirm('Are you sure you want to submit this batch to the approval queue?')) {
      try {
        await axios.post(`http://localhost:8080/batches/updatestatus`, {
          batchId: parseInt(batchId),
          userId: JSON.parse(localStorage.getItem('current_user') ?? '').adid,
          status: 'SU',
        });
        // TODO: fix below
        // router.push({ pathname: '/', query: { message: 'Batch submitted!' } });
      } catch (error: any) {
        setToastMsg(error.message);
      }
    }
  };

  return (
    <div className="container py-5">
      <h2>{msg}</h2>
      <div className="mb-4">
        <button className="btn btn-danger me-2" onClick={deleteBatch}>
          Delete
        </button>
        <button className="btn btn-secondary" onClick={() => router.push('/')}>
          Cancel
        </button>
      </div>
      {batchType === 'S' && (
        <form onSubmit={suspensionUpdate} className="mb-4">
          <div className="mb-3">
            <label htmlFor="effectiveDate" className="form-label">
              Suspension Effective Date:
            </label>
            <input type="date" name="effectiveDate" className="form-control" required />
          </div>
          <div className="mb-3">
            <label htmlFor="followUpDate" className="form-label">
              Suspension Follow Up Date:
            </label>
            <input type="date" name="followUpDate" className="form-control" required />
          </div>
          <div className="mb-3">
            <label htmlFor="reason" className="form-label">
              Suspension Reason:
            </label>
            <select name="reason" className="form-select" required>
              <option value="Military Deployment">Military Deployment</option>
              <option value="Vacant Property">Vacant Property</option>
            </select>
          </div>
          <button type="submit" className="btn btn-primary">
            Update
          </button>
        </form>
      )}
      <table className="table table-striped table-hover">
        <thead>
          <tr>
            <th>Account Number</th>
            <th>Current Waste Unit Allocation</th>
            <th>Waste Unit Change</th>
            <th>Effective Date</th>
            <th>User ID</th>
            <th>Comments</th>
            <th>Attached Documents</th>
          </tr>
        </thead>
        <tbody>
          {accounts.map((account, key) => (
            <tr key={key}>
              <td>
                <a
                  href="#"
                  onClick={() => {
                    setAccount(account);
                    setShowModal(true);
                  }}>
                  {account.accountNo}
                </a>
              </td>
              <td>{account.currWasteUnitAlloc}</td>
              <td>{account.wasteUnitChange}</td>
              <td>{new Date(account.effectiveDate).toLocaleDateString()}</td>
              <td>{account.userId}</td>
              <td>{account.comments}</td>
              <td>{account.documentNotes}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <div className="mt-4">
        <button className="btn btn-primary me-2" onClick={() => setShowModal(true)}>
          Add Account
        </button>
        <button className="btn btn-success" onClick={batchSubmit}>
          Submit
        </button>
        <NewAttachment
          file=""
          batchId={batchId ?? ''}
          userId={account != null ? account.userId : null}
        />
      </div>
      {showModal && (
        <div className="modal fade show d-block" tabIndex={-1}>
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <h5 className="modal-title">Account Information</h5>
                <button
                  type="button"
                  className="btn-close"
                  onClick={() => setShowModal(false)}></button>
              </div>
              <div className="modal-body">
                <form onSubmit={account ? updateAccount : onAccountSubmit}>
                  <div className="mb-3">
                    <label htmlFor="accountNo" className="form-label">
                      Account Number:
                    </label>
                    <input
                      type="text"
                      name="accountNo"
                      className="form-control"
                      defaultValue={account ? account.accountNo : ''}
                      required
                    />
                  </div>
                  <div className="mb-3">
                    <label htmlFor="wasteUnitChange" className="form-label">
                      Waste Unit Change:
                    </label>
                    <input
                      type="number"
                      name="wasteUnitChange"
                      className="form-control"
                      defaultValue={account ? account.wasteUnitChange : ''}
                      required
                    />
                  </div>
                  <div className="mb-3">
                    <label htmlFor="effectiveDate" className="form-label">
                      Effective Date:
                    </label>
                    <input
                      type="date"
                      name="effectiveDate"
                      className="form-control"
                      defaultValue={
                        account ? new Date(account.effectiveDate).toISOString().split('T')[0] : ''
                      }
                      required
                    />
                  </div>
                  <div className="mb-3">
                    <label htmlFor="comments" className="form-label">
                      Comments:
                    </label>
                    <textarea
                      name="comments"
                      className="form-control"
                      defaultValue={account ? account.comments : ''}
                      required></textarea>
                  </div>
                  <div className="mb-3">
                    <label htmlFor="documentation" className="form-label">
                      Documentation:
                    </label>
                    <textarea
                      name="documentation"
                      className="form-control"
                      defaultValue={account ? account.documentNotes : ''}></textarea>
                  </div>
                  <div className="mb-3">
                    <label htmlFor="documentationFile" className="form-label">
                      Upload Documentation:
                    </label>
                    <input
                      type="file"
                      name="documentationFile"
                      className="form-control"
                      onChange={e => setDocumentationFile(e?.target?.files?.[0])}
                    />
                  </div>
                  <div className="mb-3">
                    <label htmlFor="serviceArea" className="form-label">
                      Service Area:
                    </label>
                    <input
                      type="text"
                      name="serviceArea"
                      className="form-control"
                      defaultValue={account ? account.serviceArea : ''}
                      required
                    />
                  </div>
                  <button type="submit" className="btn btn-primary">
                    {account ? 'Update Account' : 'Add Account'}
                  </button>
                </form>
              </div>
            </div>
          </div>
        </div>
      )}
      {toastMsg && (
        <div className="toast show position-fixed bottom-0 end-0 p-3">
          <div className="toast-header">
            <strong className="me-auto">Notification</strong>
            <button type="button" className="btn-close" onClick={() => setToastMsg(null)}></button>
          </div>
          <div className="toast-body">{toastMsg}</div>
        </div>
      )}
    </div>
  );
};

function CreatedWasteUnitFallback() {
  return <>placeholder</>;
}

export default function CreatedWasteUnitPage() {
  return (
    <Suspense fallback={<CreatedWasteUnitFallback />}>
      <CreatedWasteUnit />
    </Suspense>
  );
}
