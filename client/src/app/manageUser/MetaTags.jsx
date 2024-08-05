// MetaTags.js
import Head from 'next/head';

const MetaTags = ({ title, description }) => {
  return (
    <Head>
      <title>{title}</title>
      <meta name="description" content={description} />
    </Head>
  );
};

export default MetaTags;
