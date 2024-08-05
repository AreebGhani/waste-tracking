'use client';

import React, { useEffect } from 'react';
import Image from 'next/image';
import Link from 'next/link';
import axios from 'axios';

import '../BatchApproval.css';

type Props = {
  params: {
    id: string;
  };
};

const ViewApproval: React.FC<Props> = ({ params: { id } }) => {
  // eslint-disable-next-line no-console
  console.log('🚀 ~ Batch id:', id);
  // useEffect(() => {
  //   axios
  //     .get(`http://localhost:8080/batches/viewBatchApproval`)
  //     .then(response => {
  //       const data: {
  //         seqNo: number;
  //         batchId: number;
  //         statusDt: string;
  //         status: string;
  //         userId: string;
  //         comments: string;
  //       }[] = response.data;
  //       const batchData: Batch[] = data.map(item => ({
  //         no: item.seqNo,
  //         type: item.status,
  //         created_at: new Date(item.statusDt).toLocaleString(),
  //         created_by: item.userId,
  //         status: item.status,
  //       }));
  //       setBatch(batchData);
  //     })
  //     // eslint-disable-next-line no-console
  //     .catch(error => console.error('Error fetching Batch:', error));
  // }, []);

  useEffect(() => {
    const scriptUrls = [
      '/js/jquery-1.7.2.min.js',
      '/js/AJAXRequest.js',
      '/js/AutoComplete.js',
      '/js/Format_Text-min.js',
      '/js/Load_Calendar-min.js',
      '/js/Miscellaneous-min.js',
      '/js/Validation_Form-min.js',
      '/js/BrowserDetection.js',
      '/js/ActiveDirectory.js',
      '/js/ajax.js',
      '/js/DataTables-1.9.4/media/js/jquery.dataTables.js',
      '/js/DataTables-1.9.4/media/js/jquery.dataTables.jeditable.js',
      '/js/jquery-ui/development-bundle/ui/jquery.ui.core.js',
      '/js/jquery-ui/development-bundle/ui/jquery.ui.widget.js',
      '/js/jquery-ui/development-bundle/ui/jquery.ui.autocomplete.js',
      '/js/jquery-ui/development-bundle/ui/jquery.ui.accordion.js',
      '/js/jquery-ui/development-bundle/ui/jquery.ui.datepicker.js',
      '/js/jquery-ui/development-bundle/ui/jquery.ui.core.js',
      '/js/jquery-ui/development-bundle/ui/jquery.ui.widget.js',
      '/js/jquery-ui/development-bundle/ui/jquery.ui.button.js',
      '/js/jquery-ui/development-bundle/ui/jquery.ui.mouse.js',
      '/js/jquery-ui/development-bundle/ui/jquery.ui.tabs.js',
      '/js/jquery-ui/development-bundle/ui/jquery.ui.draggable.js',
      '/js/jquery-ui/development-bundle/ui/jquery.ui.position.js',
      '/js/jquery-ui/development-bundle/ui/jquery.ui.resizable.js',
      '/js/jquery-ui/development-bundle/ui/jquery.ui.dialog.js',
      '/js/jquery-ui/development-bundle/ui/jquery.effects.core.js',
      '/js/jquery-ui/development-bundle/ui/jquery.effects.blind.js',
      '/js/jquery-ui/development-bundle/ui/jquery.effects.explode.js',
      '/js/batchapproval.js',
      '/js/footer.js',
    ];

    const loadScript = (url: string): Promise<void> =>
      new Promise((resolve, reject) => {
        const script = document.createElement('script');
        script.src = url;
        script.type = 'text/javascript';
        script.async = true;
        script.onload = () => resolve();
        script.onerror = () => reject(new Error(`Script load error for ${url}`));
        document.body.appendChild(script);
      });

    // Load scripts sequentially to ensure order
    const loadAllScripts = async () => {
      try {
        for (const url of scriptUrls) {
          await loadScript(url);
        }
      } catch (error) {
        // eslint-disable-next-line no-console
        console.error('Error loading scripts:', error);
      }
    };

    loadAllScripts();

    return () => {
      scriptUrls.forEach(url => {
        const script = document.querySelector(`script[src="${url}"]`);
        if (script) {
          document.body.removeChild(script);
        }
      });
    };
  }, []);

  const logout = () => {};

  const processBatch = (status: 'approve' | 'reject' | 'cancel') => {
    if (!confirm(`Are you absolutely sure you want to perform ${status} action?`)) {
      return;
    }

    const url = `/batches/${status}`;
    const formData = {
      warning: 'Test warning',
      warning2: 'Test warning2',
      warning3: 'Test warning3',
      dialogOpen: 'Test dialogOpen',
      batchId: id,
      batchTypeSel: 'Test batchTypeSel',
      batchTypeDesc: 'Test batchTypeDesc',
      notes: 'Test notes',
      suspReason: 'Test suspReason',
      batchStatusDesc: 'Test batchStatusDesc',
      suspEffectiveDt_: '2024-08-01',
      suspFollowUpDt_: '2024-08-10',
      userId: 'testUser',
      toDate: '2024-08-10',
      fromDate: '2024-08-01',
      comments: 'Test comments',
      accountNbr: 'Test accountNbr',
      streetAddr: 'Test streetAddr',
      streetName: 'Test streetName',
      streetType: 'Test streetType',
      city: 'Test city',
      state: 'Test state',
      zip: 'Test zip',
      wasteUnit: 'Test wasteUnit',
      curWasteUnit: 'Test curWasteUnit',
      serviceArea: 'Test serviceArea',
      seqNo: 'Test seqNo',
      wasteUnitTotal: 100,
      totalAdds: '10',
      totalOccupancys: '20',
      totalDeletes: '5',
      totalNetChange: '25',
    };

    axios
      .post(`http://localhost:8080${url}`, formData)
      .then(response => {
        // eslint-disable-next-line no-console
        console.log(`${status} action successful:`, response.data);
      })
      .catch(error => {
        // eslint-disable-next-line no-console
        console.error(`${status} action failed:`, error);
      });
  };

  return (
    <div id="wrapper" className="wrapper">
      <table className="table-layout">
        <tbody>
          <tr>
            <td className="spacer"></td>
            <td className="image-cell" rowSpan={2}>
              <Image
                src="/images/trash.jpg"
                className="image"
                alt="Trash"
                width={81}
                height={126}
              />
            </td>
            <td className="header-text" rowSpan={2}>
              <span className="bannerTitle">Anne Arundel County Department of Public Works</span>
              <br />
              <span className="bannerSubTitle">Waste Unit Tracking System</span>
            </td>
          </tr>
          <tr>
            <td></td>
            <td className="email-cell">
              <Link href="mailto:itdise77@aacounty.org">
                <Image
                  src="/images/request.jpg"
                  className="request-image"
                  alt="Request"
                  width={200}
                  height={37}
                />
              </Link>
            </td>
          </tr>
        </tbody>
      </table>
      <table className="separator-table">
        <tbody>
          <tr>
            <td colSpan={5}>
              <hr className="separator" />
            </td>
          </tr>
        </tbody>
      </table>
      <table className="main-table">
        <tbody>
          <tr>
            <td className="spacer"></td>
            <td className="form-container">
              <table className="logout-table">
                <tbody>
                  <tr>
                    <td className="breadcrumb">
                      <Link href="/">Main Menu</Link>
                    </td>
                    <td className="rightAlign">
                      <input type="hidden" name="logoutExitPage" value="../login.jsp" />
                      <span onClick={logout}>Logout</span>
                    </td>
                  </tr>
                </tbody>
              </table>
              <table className="info-table">
                <tbody>
                  <tr>
                    <td>
                      <table className="info-inner-table">
                        <tbody>
                          <tr>
                            <td>
                              <table className="blueDashTable">
                                <tbody>
                                  <tr>
                                    <td>
                                      Welcome to the DPW Waste Unit Tracking System. Click on batch
                                      id to display waste unit batch approval screen.
                                    </td>
                                  </tr>
                                </tbody>
                              </table>
                              <br />
                              <br />
                              <table>
                                <tr>
                                  <td align="left" width="13%">
                                    <strong>Batch Number:</strong>&nbsp;&nbsp;
                                  </td>
                                  <td align="left" width="13%">
                                    <strong>Batch Type:</strong>&nbsp;&nbsp;
                                  </td>
                                  <td align="left" width="13%">
                                    <strong>Batch Status:</strong>&nbsp;&nbsp;
                                  </td>
                                </tr>
                              </table>
                              <br />
                              <hr />
                            </td>
                          </tr>
                          <tr>
                            <td>&nbsp;</td>
                          </tr>
                        </tbody>
                      </table>
                      <table id="batchApproval" className="data-table">
                        <thead>
                          <tr>
                            <th align="right" colSpan={7} className="date-header">
                              {new Date().toLocaleString()}
                            </th>
                          </tr>
                          <tr>
                            <th colSpan={7} className="table-title">
                              Waste Batch Approval
                            </th>
                          </tr>
                          <tr>
                            <td>
                              <br />
                            </td>
                          </tr>
                          <tr>
                            <th className="table-header">
                              Account
                              <br />
                              Number
                            </th>
                            <th className="table-header">
                              Current Waste
                              <br />
                              Unit Allocation
                            </th>
                            <th className="table-header">
                              Waste Unit
                              <br />
                              Change
                            </th>
                            <th className="table-header">
                              Effective
                              <br />
                              Date
                            </th>
                            <th className="table-header">
                              User
                              <br />
                              ID
                            </th>
                            <th className="table-header">Comments</th>
                            <th className="table-header">Attached Documents</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr>
                            <td className="table-cell">
                              <span>item.no</span>
                            </td>
                            <td className="table-cell">item.type</td>
                            <td className="table-cell">item.created_at</td>
                            <td className="table-cell">item.created_by</td>
                            <td className="table-cell">item.status</td>
                            <td className="table-cell">item.status</td>
                            <td className="table-cell">item.status</td>
                          </tr>
                        </tbody>
                      </table>
                      <table>
                        <tr>
                          <td>&nbsp;</td>
                        </tr>
                        <tr>
                          <td>
                            <strong>
                              Comments:
                              <br />
                              <i className="required">(Required)</i>
                            </strong>
                          </td>
                          <td>&nbsp;</td>
                          <td>
                            <textarea rows={5} cols={60} />
                          </td>
                        </tr>
                      </table>
                      <table>
                        <tr>
                          <td>&nbsp;</td>
                        </tr>
                        <tr>
                          <td>
                            <button onClick={() => processBatch('approve')}>
                              &nbsp; &nbsp;Approve Batch &nbsp;&nbsp;
                            </button>{' '}
                            &nbsp; &nbsp; &nbsp;
                            <button onClick={() => processBatch('reject')}>
                              &nbsp;&nbsp;Reject Batch &nbsp; &nbsp;
                            </button>{' '}
                            &nbsp; &nbsp; &nbsp;
                            <button onClick={() => processBatch('cancel')}>
                              &nbsp;&nbsp;Cancel Batch &nbsp; &nbsp;
                            </button>
                          </td>
                        </tr>
                        <tr>
                          <td>&nbsp;</td>
                        </tr>
                      </table>
                    </td>
                  </tr>
                </tbody>
              </table>
            </td>
          </tr>
        </tbody>
      </table>
      <table className="footer-table">
        <tbody>
          <tr>
            <td className="footer">
              <div className="welcome-text">Welcome username</div>
              <span id="footerStatusMsg" className="footer-status"></span>
              <div id="todays_date_time" className="date-time"></div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

export default ViewApproval;
