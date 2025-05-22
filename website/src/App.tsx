import {RouterProvider} from "react-router";
import routers from "./routes/routers.tsx";

function App() {
    return (
        <RouterProvider router={routers} />
    )
}

export default App;