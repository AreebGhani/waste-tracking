// 'use client';

// // import { authenticate } from "@/app/lib/actions";
// import styles from "./loginForm.module.css";
// import { useFormState, FormState } from "react-dom";

// const LoginForm = () => {
//   const [state, formAction] = useFormState<FormState | undefined>(authenticate, undefined);

//   return (
//     <form action={formAction} className={styles.form}>
//       <h1>Login</h1>
//       <input type="text" placeholder="username" name="username" />
//       <input type="password" placeholder="password" name="password" />
//       <button>Login</button>
//       {state && <div>{state}</div>}
//     </form>
//   );
// };

// export default LoginForm;
