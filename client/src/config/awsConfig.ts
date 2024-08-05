'use client';

import { Amplify, Auth } from 'aws-amplify';

export default function AwsConfig() {
  const env = {
    BACKEND_URL: 'http://localhost:8080',
    REDIRECT_SIGN_IN: 'http://localhost:3000',
    REDIRECT_SIGN_OUT: 'http://localhost:3000',
    COGNITO_DOMAIN: 'http://localhost:3000',
    MSG: '',
  };
  const redirectSignIn = env.REDIRECT_SIGN_IN;
  const redirectSignOut = env.REDIRECT_SIGN_OUT;
  const cognitoDomain = env.COGNITO_DOMAIN;

  const awsAuth = {
    domain: 'aacounty-login.auth.us-east-1.amazoncognito.com',
    scope: ['phone', 'email', 'openid'],
    redirectSignIn: redirectSignIn,
    redirectSignOut: redirectSignOut,
    responseType: 'code',
  };

  const awsconfig = {
    Auth: {
      region: 'us-east-1',
      userPoolId: 'us-east-1_EqSnZA5qg',
      userPoolWebClientId: '5ghsfd30dte1nd1vm0l5f8rcqk',
      mandatorySignIn: false,
      cookieStorage: {
        domain: cognitoDomain,
        path: '/',
        expires: 365,
        secure: true,
      },
    },
  };

  Amplify.configure(awsconfig);
  Auth.configure({ oauth: awsAuth });
}
