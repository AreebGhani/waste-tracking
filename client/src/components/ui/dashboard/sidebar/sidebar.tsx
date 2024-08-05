'use client';

import React, { useEffect } from 'react';
import Image from 'next/image';
import { MdDashboard, MdAssignment, MdSupervisedUserCircle, MdLogout } from 'react-icons/md';
import { PiTrashSimpleFill } from 'react-icons/pi';

import MenuLink from './menuLink/menuLink';
import styles from './sidebar.module.css';
import { useAuth } from '@/context/authContext';

interface MenuItem {
  title: string;
  path: string;
  icon: React.ReactNode;
}

const menuItems: { title: string; list: MenuItem[] } = {
  title: 'Pages',
  list: [
    {
      title: 'Dashboard',
      path: '/dashboard',
      icon: <MdDashboard />,
    },
    {
      title: 'Batches',
      path: '/dashboard/batches',
      icon: <PiTrashSimpleFill />,
    },
    {
      title: 'Accounts',
      path: '/dashboard/accounts',
      icon: <MdAssignment />,
    },
    {
      title: 'Users',
      path: '/dashboard/users',
      icon: <MdSupervisedUserCircle />,
    },
    {
      title: 'Manager Users',
      path: '/manageUser',
      icon: <MdSupervisedUserCircle />,
    },
    {
      title: 'Create Waste Unit',
      path: '/CreateWasteUnitPage',
      icon: <MdSupervisedUserCircle />,
    },
    {
      title: 'Suspended Batches',
      path: '/SuspendedBatches',
      icon: <MdSupervisedUserCircle />,
    },
  ],
};

// Defined Sidebar as a functional component with React.FC
const Sidebar: React.FC = () => {
  const { signOut }: any = useAuth();
  const [user, setAuth] = React.useState<any>(null);

  useEffect(() => {
    if (!user && localStorage.getItem('current_user')) {
      const currentUser = JSON.parse(localStorage.getItem('current_user') ?? '');
      setAuth(currentUser);
    }
  }, [user]);

  return (
    <div className={styles.container}>
      <div className={styles.user}>
        <Image
          className={styles.userImage}
          src="/defaultavatar.png"
          alt="Avatar"
          width="50"
          height="50"
        />
        <div className={styles.userDetails}>
          <span className={styles.username}>{user?.auName ?? ''}</span>
          <span className={styles.userTitle}>{user?.roleName ?? ''}</span>
        </div>
      </div>
      <ul className={styles.list}>
        {menuItems.list.map(item => (
          <li key={item.title}>
            <MenuLink item={item} />
          </li>
        ))}
      </ul>
      <button
        className={styles.logout}
        onClick={async () => {
          await signOut();
        }}>
        <MdLogout />
        Logout
      </button>
    </div>
  );
};

export default Sidebar;
