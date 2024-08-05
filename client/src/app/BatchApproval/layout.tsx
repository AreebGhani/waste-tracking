import React, { ReactNode } from 'react';
import type { Metadata } from 'next';

export const metadata: Metadata = {
  title: 'AAC DPW Waste Tracking - Waste Unit Batch Approval Queue',
  description: 'AAC DPW Waste Tracking - Waste Unit Batch Approval Queue',
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
