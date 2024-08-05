/* eslint-disable no-console */
import { join } from 'path';

import next from 'next';
import express from 'express';
import compression from 'express-compression';

const dev = process.env.NODE_ENV !== 'production';
const hostname = 'localhost';
const port = process.env.PORT || 3000;

const app = next({ dev, hostname, port });

const shouldCompress = (req, res) => {
  if (req.headers['x-no-compression']) {
    return false;
  }
  return compression.filter(req, res);
};

app
  .prepare()
  .then(() => {
    const expressApp = express();
    const handle = app.getRequestHandler();

    expressApp.use(compression({ filter: shouldCompress }));
    expressApp.use('/uploads', express.static(join(process.cwd(), 'public/uploads')));

    expressApp.all('*', (req, res) => handle(req, res));

    expressApp.listen(port, () => {
      console.log(`🚀 ~ Server is listening On ~> http://localhost:${port}`);
    });
  })
  .catch(ex => {
    console.error(ex.stack);
    process.exit(1);
  });
