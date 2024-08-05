// Probably handy later, if we want to use, wrap layout.tsx children.

'use client';

import { ChakraProvider } from '@chakra-ui/react';

export function Providers({ children }: { children: React.ReactNode }) {
  return <ChakraProvider>{children}</ChakraProvider>;
}
