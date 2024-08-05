'use client';
import React, { useEffect } from 'react';
import { Auth, Hub } from 'aws-amplify';
import { useRouter } from 'next/navigation';
import { Spinner } from 'react-activity';

import { useAuth } from '@/context/authContext';
import styles from '@/app/login/login.module.css';
import 'react-activity/dist/library.css';

export default function Login() {
  const router = useRouter();
  const [loggedIn, setLoggedin] = React.useState(false);

  const { setAuth } = useAuth() || ({} as any);

  useEffect(() => {
    Hub.listen('auth', ({ payload: { event } }) => {
      switch (event) {
        case 'signIn':
          getTokenFromSession();
          break;
        case 'signOut':
          setAuth(null);
          localStorage.removeItem('header_token');
          break;
        case 'tokenRefresh':
          break;
      }
    });
  }, [setAuth]);

  const [idToken, setIdToken] = React.useState<string | null>(null);

  async function checkLoggedIn() {
    try {
      await Auth.currentAuthenticatedUser();
      setLoggedin(true);
    } catch {
      setLoggedin(false);
    }
  }

  async function getTokenFromSession() {
    try {
      const session = await Auth.currentSession();
      const idToken = session.getIdToken().getJwtToken();
      setIdToken(idToken);
      localStorage.setItem('header_token', idToken);
    } catch (error) {
      return error;
    }
  }

  useEffect(() => {
    checkLoggedIn();
    if (loggedIn === true) {
      getTokenFromSession();
      router.push('/dashboard');
    }
  }, [loggedIn, router]);

  useEffect(() => {
    async function getUser() {
      try {
        const response = await fetch(`${(window as any)._env_.BACKEND_URL}/alluser/api/user`, {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${idToken}`,
          },
        });
        const result = await response.json();
        if (Object.prototype.hasOwnProperty.call(result, 'error')) {
          // eslint-disable-next-line no-console
          console.error('Error in user obect.'); // Without this, it sets your user as the error.
        } else {
          localStorage.setItem('current_user', JSON.stringify(result));
        }
        return result;
      } catch (error) {
        return error;
      }
    }
    if (idToken !== null) {
      getUser().then(result => {
        setAuth(result);
      });
    }
  }, [idToken, setAuth]);

  return (
    <main className="gradient-container center">
      <div className={styles.logincontainer}>
        <div className={styles.elementcontainer}>
          <h1 className="title">Waste Tracking</h1>
          <div className={styles.buttoncontainer}>
            <button
              className={styles.loginbutton}
              onClick={() => {
                Auth.federatedSignIn();
              }}>
              {loggedIn ? <Spinner color="white" className={styles.loginspinner} /> : 'Login'}
            </button>
          </div>
        </div>
      </div>
    </main>
  );
}
