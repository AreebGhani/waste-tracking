'use client';

import { usePathname } from 'next/navigation';
import { Auth } from 'aws-amplify';
import React from 'react';

export const AuthContext = React.createContext(null);

// This hook can be used to access the user info.
export function useAuth() {
  return React.useContext(AuthContext);
}

// This hook will protect the route access based on user authentication.
function useProtectedRoute(user: any) {
  const pathname = usePathname();

  // React.useEffect(() => {
  //   // TODO: Stored user stuff? (In dean's code)
  //   const inAuthGroup = pathname.includes("/login");
  //   if (!user && !inAuthGroup) {
  //     router.push('/login');
  //     console.log("Auth is logging out");
  //   } else if (user && inAuthGroup) {
  //     router.push('/dashboard');
  //     console.log("Auth is logging in");
  //   }
  // }, [user, pathname, router]);

  if (!user && pathname !== '/') {
    return null;
  }
  return true;
}

// async function isTokenExpired() {
//   try {
//     const session = await Auth.currentSession();
//     const tokenExpiration = session.getIdToken().getExpiration();
//     console.log("now: ", Date.now());
//     console.log("Token expiry: ", tokenExpiration * 1000);
//     if (Date.now() <= tokenExpiration * 1000) {
//       console.log("Token is not expired");
//       return false;
//     } else {
//       console.log("Token is expired");
//       return true;
//     }
//   } catch (err) {
//     console.log(err);
//   }
// }

// async function refreshSession() {
//   if (isTokenExpired) {
//     await Auth.currentSession()
//       .then((data) => {
//         // localStorage.removeItem("header_token")
//         // localStorage.setItem("header_token",)
//         console.log("Session Refreshed: ", data);
//       })
//       .catch((err) => console.log(err));
//   } else {
//     console.log("Token is still valid");
//     return null;
//   }
// }
// async function extractToken() {
//   const session = await Auth.currentSession();
//   const idToken = session.getIdToken().getJwtToken();
//   return idToken;
// }

/*
This is so hacky, I know there has to be a better way to do this.
I'm manually removing te cognito ID cookie to force an auth error leading to the header and user
values being removed from local storage. For some reason the signOut function on it's own logs us
back in, as if the cookie is not clearing right.
*/
function deleteTokenCookie() {
  const cookieList = document.cookie.split(';');
  const idToken = cookieList.filter(
    c => c.match(/.accessToken/g) && c.match(/CognitoIdentityServiceProvider/g)
  );
  const date = new Date();
  // Set it expire in -1 days
  date.setTime(date.getTime() + -1 * 24 * 60 * 60 * 1000);
  document.cookie = idToken[0] + '=; expires=' + date.toUTCString() + '; path=/';
}

export function Provider(props: any) {
  const [user, setAuth] = React.useState(null);

  useProtectedRoute(user);

  const value: any = {
    signOut: () => {
      // console.log("Logging Out");
      deleteTokenCookie();
      Auth.signOut({ global: true });
      localStorage.removeItem('header_token');
      localStorage.removeItem('current_user');
      // console.log(`Auth: ${user}`);
      setAuth(null);
    },
    setAuth,
    user,
  };

  return <AuthContext.Provider value={value}>{props.children}</AuthContext.Provider>;
}
