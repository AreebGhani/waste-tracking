import type { Metadata } from 'next';
import { Inter } from 'next/font/google';

import '../components/ui/globals.css';
import { Provider as AuthContext } from '../context/authContext';
import { Provider as CognitoContext } from '../context/cognitoContext';
// import { Providers } from './providers'

const inter = Inter({ subsets: ['latin'] });

export const metadata: Metadata = {
  title: 'AAC DPW Waste Tracking',
  description: 'Employee dashboard for waste tracking',
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <CognitoContext>
      <AuthContext>
        <html lang="en">
          <body className={inter.className}>
            <script src="./env-config.js" async></script>
            {children}
          </body>
        </html>
      </AuthContext>
    </CognitoContext>
  );
}
