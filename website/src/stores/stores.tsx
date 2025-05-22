import {configureStore} from "@reduxjs/toolkit";
import {languageSlice} from "./slices/languageSlice.ts";

export default configureStore({
    reducer: {
        language: languageSlice
    },
})