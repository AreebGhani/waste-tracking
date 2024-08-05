import React from 'react';
import { MdOutlinePendingActions } from 'react-icons/md';

import styles from './card.module.css';

// Define the props interface
interface CardProps {
  item: {
    title: string;
    number: number;
  };
}

// Defined Card as a functional component with React.FC and added props type
const Card: React.FC<CardProps> = ({ item }) => (
  <div className={styles.container}>
    <MdOutlinePendingActions size={24} />
    <div className={styles.texts}>
      <span className={styles.title}>{item.title}</span>
      <span className={styles.number}>{item.number}</span>
      <span className={styles.detail}></span>
    </div>
  </div>
);

export default Card;
