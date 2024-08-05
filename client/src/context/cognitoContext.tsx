'use client';

import React, { createContext, useContext } from 'react';

import AwsConfig from '@/config/awsConfig';

type CognitoContextType = {
  AwsConfig: () => void;
} | null; // get around strict typing ts error

export const CognitoContext = createContext<CognitoContextType>(null);

export function useCognito() {
  return useContext(CognitoContext);
}

export function Provider(props: any) {
  React.useEffect(() => {
    AwsConfig();
  }, []);
  return <CognitoContext.Provider value={{ AwsConfig }}>{props.children}</CognitoContext.Provider>;
}
