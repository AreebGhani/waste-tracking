'use client';
import React from 'react';
import Link from 'next/link';
import { usePathname } from 'next/navigation';

import styles from './menuLink.module.css';

interface MenuLinkProps {
  item: {
    title: string;
    path: string;
    icon: React.ReactNode;
  };
}

// proptypes needed
const MenuLink: React.FC<MenuLinkProps> = ({ item }) => (
  <Link
    href={item.path}
    className={`${styles.container} ${usePathname() === item.path ? styles.active : ''}`}>
    {item.icon}
    {item.title}
  </Link>
);

export default MenuLink;
