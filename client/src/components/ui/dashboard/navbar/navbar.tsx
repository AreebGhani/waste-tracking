'use client';

import React from 'react';
import { usePathname } from 'next/navigation';

import styles from './navbar.module.css';

// Defined Navbar as a functional component with React.FC
const Navbar: React.FC = () => {
  const pathname = usePathname();

  return (
    <div className={styles.container}>
      <div className={styles.title}>{pathname.split('/').pop()}</div>
      <div className={styles.menu}>
        <div className={styles.search}>
          {/* <input type='text' placeholder='Search...' className={styles.input}></input> */}
        </div>
        <div className={styles.icons}>{/* Work in progress */}</div>
      </div>
    </div>
  );
};

export default Navbar;
