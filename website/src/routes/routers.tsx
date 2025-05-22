import {createBrowserRouter} from "react-router";
import HomePage from "../pages/HomePage.tsx";
import LoginPage from "../pages/LoginPage.tsx";
import RegisterPage from "../pages/RegisterPage.tsx";
import DashboardChatPage from "../pages/DashboardChatPage.tsx";

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
    },
    {
        path: "/chat",
        element: <DashboardChatPage />
    }
])

export default routers;