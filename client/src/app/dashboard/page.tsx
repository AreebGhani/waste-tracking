import React from 'react';

import DbTable from '@/components/ui/dashboard/dbtable/dbtable';
import styles from '@/components/ui/dashboard/dashboard.module.css';

// def as functional component with react.fc
const Dashboard: React.FC = () => (
  //   const router = useRouter();
  //   const [loggedIn, setLoggedin] = React.useState(false);
  //   const { setAuth } = useAuth() || ({} as any);

  //   async function checkLoggedIn() {
  //     try {
  //       await Auth.currentAuthenticatedUser();
  //       setLoggedin(true);
  //     } catch {
  //       setLoggedin(false);
  //     }
  //   }

  //   useEffect(() => {
  //     checkLoggedIn();
  //     if (loggedIn === false) {
  //       router.replace("/login");
  //     }
  //   }, []);

  <div className={styles.wrapper}>
    <div className={styles.main}>
      <div className={styles.cards}>
        {/* {cards.map((item) => (
                        <Card item={item} key={item.id} />
                    ))} */}
      </div>

      <DbTable />
    </div>
  </div>
);
export default Dashboard;
