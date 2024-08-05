import React, { ReactNode } from 'react';
import type { Metadata } from 'next';

export const metadata: Metadata = {
  title: 'DPW Waste Unit Tracking - Waste Unit Batch Approval',
  description: 'DPW Waste Unit Tracking - Waste Unit Batch Approval',
  keywords: ['DPW Waste Tracking', 'Waste Batch Approval'],
  creator: 'localhost:3000',
  publisher: 'localhost:3000',
  robots: {
    index: true,
    follow: true,
  },
  alternates: {
    canonical: 'http://localhost:3000',
  },
};

interface LayoutProps {
  children: ReactNode;
}

const Layout: React.FC<LayoutProps> = ({ children }) => children;

export default Layout;
