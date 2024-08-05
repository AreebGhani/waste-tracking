'use client';

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Link from 'next/link';
import Head from 'next/head';

import 'bootstrap/dist/css/bootstrap.min.css';

const UserListPage = () => {
  const [users, setUsers] = useState([]);
  const [departments, setDepartments] = useState([]);
  const [roles, setRoles] = useState([]);
  const [showEditModal, setShowEditModal] = useState(false);
  const [formData, setFormData] = useState<any>({
    name: '',
    email: '',
    password: '',
    department: '',
    role: '',
    IsActive: false,
    sendMail: false,
    DefaultRole: false,
  });

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/alluser/details`);
        setUsers(response.data);
      } catch (error) {
        // eslint-disable-next-line no-console
        console.error('Error fetching users:', error);
      }
    };

    fetchUsers();
  }, []);

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/departments`)
      .then(response => {
        setDepartments(response.data);
      })
      // eslint-disable-next-line no-console
      .catch(error => console.error('Error fetching departments:', error));
  }, []);

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/role`)
      .then(response => {
        setRoles(response.data);
      })
      // eslint-disable-next-line no-console
      .catch(error => console.error('Error fetching roles:', error));
  }, []);

  const handleEditClick = (user: any) => {
    setShowEditModal(true);
    setFormData({
      userId: user.user_id,
      name: user.auName,
      email: user.auEmailAddress,
      password: '',
      department: user.departmentId,
      role: user.roleId,
      IsActive: user.auActive,
      sendMail: user.auSendEmail,
      DefaultRole: user.defaultrole,
    });
  };

  const handleUpdate = async () => {
    try {
      const userData = {
        id: formData.userId,
        adid: formData.name,
        createdBy: formData.name,
        emailAddress: formData.email,
        theme: 'default',
        updatedBy: formData.name,
        name: formData.name,
        departmentId: formData.department,
        roleid: formData.role,
        active: formData.IsActive,
        sendEmail: formData.sendMail,
        defaultrole: formData.DefaultRole,
      };

      const response = await axios.post(`http://localhost:8080/applicationuser/update`, userData);

      if (response.status === 200) {
        const fetchUsers = async () => {
          try {
            const response = await axios.get(`http://localhost:8080/alluser/details`);
            setUsers(response.data);
          } catch (error) {
            // eslint-disable-next-line no-console
            console.error('Error fetching users:', error);
          }
        };

        fetchUsers();
      } else {
        // eslint-disable-next-line no-console
        console.error('Registration failed.');
      }
      setShowEditModal(false);
    } catch (error) {
      // eslint-disable-next-line no-console
      console.error('Error updating user:', error);
    }
  };

  return (
    <>
      <Head>
        <title>User List</title>
        <meta name="description" content="List of users page" />
      </Head>
      <div className="container mt-5">
        <div className="text-center mb-4">
          <h2>User List</h2>
        </div>
        <table className="table table-striped">
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Email Address</th>
              <th>Role</th>
              <th>Department</th>
              <th>IsActive</th>
              <th>SendMail</th>
              <th>Default Role</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {users.map((user: any) => (
              <tr key={user.aurId}>
                <td>{user.aurId}</td>
                <td>{user.auName}</td>
                <td>{user.auEmailAddress}</td>
                <td>{user.roleName}</td>
                <td>{user.departmentName}</td>
                <td>
                  <input type="checkbox" checked={user.auActive} disabled />
                </td>
                <td>
                  <input type="checkbox" checked={user.auSendEmail} disabled />
                </td>
                <td>
                  <input type="checkbox" checked={user.defaultrole} disabled />
                </td>
                <td>
                  <button className="btn btn-primary btn-sm" onClick={() => handleEditClick(user)}>
                    Edit
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>

        {/* Edit Modal */}
        {showEditModal && (
          <div className="modal show d-block" tabIndex={-1}>
            <div className="modal-dialog">
              <div className="modal-content">
                <div className="modal-header">
                  <h5 className="modal-title">Edit User</h5>
                  <button
                    type="button"
                    className="btn-close"
                    onClick={() => setShowEditModal(false)}></button>
                </div>
                <div className="modal-body">
                  <form>
                    <div className="mb-3">
                      <label htmlFor="name" className="form-label">
                        Name
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        id="name"
                        value={formData.name}
                        onChange={e => setFormData({ ...formData, name: e.target.value })}
                      />
                    </div>
                    <div className="mb-3">
                      <label htmlFor="email" className="form-label">
                        Email
                      </label>
                      <input
                        type="email"
                        className="form-control"
                        id="email"
                        value={formData.email}
                        onChange={e => setFormData({ ...formData, email: e.target.value })}
                      />
                    </div>
                    <div className="mb-3">
                      <label htmlFor="department" className="form-label">
                        Department
                      </label>
                      <select
                        className="form-select"
                        id="department"
                        value={formData.department}
                        onChange={e => setFormData({ ...formData, department: e.target.value })}>
                        {departments.map((department: any) => (
                          <option key={department.id} value={department.id}>
                            {department.name}
                          </option>
                        ))}
                      </select>
                    </div>
                    <div className="mb-3">
                      <label htmlFor="role" className="form-label">
                        Role
                      </label>
                      <select
                        className="form-select"
                        id="role"
                        value={formData.role}
                        onChange={e => setFormData({ ...formData, role: e.target.value })}>
                        {roles.map((role: any) => (
                          <option key={role.id} value={role.id}>
                            {role.roleType}
                          </option>
                        ))}
                      </select>
                    </div>
                    <div className="mb-3 form-check">
                      <input
                        type="checkbox"
                        className="form-check-input"
                        id="isActive"
                        checked={formData.IsActive}
                        onChange={e => setFormData({ ...formData, IsActive: e.target.checked })}
                      />
                      <label className="form-check-label" htmlFor="isActive">
                        IsActive
                      </label>
                    </div>
                    <div className="mb-3 form-check">
                      <input
                        type="checkbox"
                        className="form-check-input"
                        id="defaultRole"
                        checked={formData.DefaultRole}
                        onChange={e => setFormData({ ...formData, DefaultRole: e.target.checked })}
                      />
                      <label className="form-check-label" htmlFor="defaultRole">
                        DefaultRole
                      </label>
                    </div>
                    <div className="mb-3 form-check">
                      <input
                        type="checkbox"
                        className="form-check-input"
                        id="sendMail"
                        checked={formData.sendMail}
                        onChange={e => setFormData({ ...formData, sendMail: e.target.checked })}
                      />
                      <label className="form-check-label" htmlFor="sendMail">
                        Send Mail
                      </label>
                    </div>
                  </form>
                </div>
                <div className="modal-footer">
                  <button type="button" className="btn btn-primary" onClick={handleUpdate}>
                    Update
                  </button>
                  <button
                    type="button"
                    className="btn btn-secondary"
                    onClick={() => setShowEditModal(false)}>
                    Cancel
                  </button>
                </div>
              </div>
            </div>
          </div>
        )}
        <div className="text-center mt-4">
          <Link href="/manageUser/RegistrationPage" legacyBehavior>
            <p className="btn btn-success">Registration</p>
          </Link>
        </div>
      </div>
    </>
  );
};

export default UserListPage;
