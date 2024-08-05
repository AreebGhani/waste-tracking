'use client';

import { useEffect, useState } from 'react';
import axios from 'axios';
import { useRouter } from 'next/navigation';
import { MdOutlineMenu } from 'react-icons/md';

import 'bootstrap/dist/css/bootstrap.min.css';

import styles from './register.module.css';

export default function Register() {
  const router = useRouter();
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [departments, setDepartments] = useState([]);
  const [roles, setRoles] = useState([]);
  const [selectedRole, setSelectedRole] = useState('');
  const [selectedDepartment, setSelectedDepartment] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState('');
  // const router = useRouter();
  const [formData, setFormData] = useState({
    IsActive: false,
    sendMail: false,
    DefaultRole: false,
  });

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

  function validateFields() {
    if (!username.trim() || !email.trim() || !selectedDepartment || !selectedRole) {
      setError('Please fill all the fields.');
      return false;
    }
    return true;
  }

  async function handleRegisterUser() {
    if (!validateFields()) {
      setIsLoading(false);
      return;
    }
    setIsLoading(true);
    try {
      const userData = {
        adid: username,
        createdBy: username,
        emailAddress: email,
        theme: 'default',
        updatedBy: username,
        name: username,
        active: formData.IsActive,
        sendEmail: formData.sendMail,
        isSupervisor: 'false',
        departmentId: selectedDepartment,
        defaultrole: formData.DefaultRole,
        roleid: selectedRole,
      };

      const response = await axios.post(`http://localhost:8080/applicationuser/register`, userData);

      if (response.status === 200) {
        alert('Registration succeeded.');
        router.push('/manageUser');
      } else {
        setError('Registration failed.');
      }
      setIsLoading(false);
    } catch (error: any) {
      setError(error.message);
      setIsLoading(false);
    }
  }

  return (
    <div className={styles.registerPage}>
      <header className={`${styles.header} navbar`}>
        <MdOutlineMenu className="fs-3 ms-2" />
        <h1 onClick={() => router.push('/')} style={{ fontSize: '20px' }}>
          DPWWasteTracking
        </h1>
        <p style={{ fontSize: '20px' }}>{new Date().toLocaleString()}</p>
        <div>
          {' '}
          <button className="btn " style={{ color: 'white' }}>
            Login
          </button>
        </div>
      </header>
      <main className={styles.main}>
        <div className={styles.register}>
          <div className={styles.register_box}>
            <div className={styles.formContainer}>
              <h2
                className={styles.formHeading}
                style={{ marginBottom: '20px', textAlign: 'center' }}>
                Registration
              </h2>
              {error && <div className="alert alert-danger">{error}</div>}
              <div className="mb-3">
                <label htmlFor="username" className="form-label form_label">
                  Username
                </label>
                <input
                  type="text"
                  className="form-control"
                  id="username"
                  value={username}
                  onChange={e => setUsername(e.target.value)}
                  placeholder="Enter your username"
                />
              </div>
              <div className="mb-3">
                <label htmlFor="email" className="form-label form_label">
                  Email
                </label>
                <input
                  type="email"
                  className="form-control"
                  id="email"
                  value={email}
                  onChange={e => setEmail(e.target.value)}
                  placeholder="Enter your email"
                />
              </div>
              <div className="mb-3">
                <label htmlFor="department" className="form-label form_label">
                  Department
                </label>
                <select
                  className="form-select"
                  id="department"
                  value={selectedDepartment}
                  onChange={e => setSelectedDepartment(e.target.value)}>
                  <option value="">Select department</option>
                  {departments.map((department: any) => (
                    <option key={department.id} value={department.id}>
                      {department.name}
                    </option>
                  ))}
                </select>
              </div>
              <div className="mb-3">
                <label htmlFor="role" className="form-label form_label">
                  Role
                </label>
                <select
                  className="form-select"
                  id="role"
                  value={selectedRole}
                  onChange={e => setSelectedRole(e.target.value)}>
                  <option value="">Select role</option>
                  {roles.map((role: any) => (
                    <option key={role.id} value={role.id}>
                      {role.roleType}
                    </option>
                  ))}
                </select>
              </div>
              <div className={`${styles.formCheckInline} `}>
                <input
                  type="checkbox"
                  className="form-check-input me-4"
                  id="isActive"
                  checked={formData.IsActive}
                  onChange={e => setFormData({ ...formData, IsActive: e.target.checked })}
                />
                <label className="form-check-label" htmlFor="isActive">
                  IsActive
                </label>
                <input
                  type="checkbox"
                  className="form-check-input ms-3 me-2"
                  id="isActive"
                  checked={formData.DefaultRole}
                  onChange={e => setFormData({ ...formData, DefaultRole: e.target.checked })}
                />
                <label className="form-check-label" htmlFor="defaultRole">
                  DefaultRole
                </label>
              </div>
              <div className="form-check mb-3 mt-4">
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
              <button
                type="button"
                className="btn btn-primary mt-4"
                onClick={handleRegisterUser}
                disabled={isLoading}
                style={{ width: '100%' }}>
                {isLoading ? 'Registering ...' : 'Register'}
              </button>
            </div>
          </div>
        </div>
      </main>
    </div>
  );
}
