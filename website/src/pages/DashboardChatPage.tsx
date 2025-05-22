import { useSelector } from "react-redux";
import type { RootState } from "../stores/stores";

function DashboardChatPage() {

    const langauge = useSelector((state: RootState) => state.language.dashboard_chat_page);

    return (
        <h1>Dashboard Chat Page</h1>
    )
}

export default DashboardChatPage;