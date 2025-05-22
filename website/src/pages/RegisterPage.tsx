import { useSelector } from "react-redux";
import type { RootState } from "../stores/stores";

function RegisterPage() {

    const language = useSelector((state: RootState) => state.language.register_page);
    
    return (
        <h1>Register Page</h1>
    )
}

export default RegisterPage;