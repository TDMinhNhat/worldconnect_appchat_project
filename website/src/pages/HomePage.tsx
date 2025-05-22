import { useSelector } from "react-redux";
import type { RootState } from "../stores/stores";

function HomePage() {

    const language = useSelector((state: RootState) => state.language.home_page);

    return (
        <h1>Home Page</h1>
    )
}

export default HomePage;