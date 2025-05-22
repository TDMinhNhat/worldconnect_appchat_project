import { useSelector } from "react-redux";
import type { RootState } from "../stores/stores";

function LoginPage() {

    const language = useSelector((state: RootState) => state.language.login_page);

    return (
        <h1>Login Page</h1>
    )
}

export default LoginPage;