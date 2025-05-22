import {createBrowserRouter} from "react-router";
import HomePage from "../pages/HomePage.tsx";
import LoginPage from "../pages/LoginPage.tsx";
import RegisterPage from "../pages/RegisterPage.tsx";

const routers = createBrowserRouter([
    {
        path: "/",
        element: <HomePage />
    },
    {
        path: "/login",
        element: <LoginPage />
    },
    {
        path: "/register",
        element: <RegisterPage />
    }
])

export default routers;