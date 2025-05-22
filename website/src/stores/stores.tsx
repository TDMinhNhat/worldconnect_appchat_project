import {configureStore} from "@reduxjs/toolkit";
import {languageSlice} from "./slices/languageSlice.ts";

const store = configureStore({
    reducer: {
        language: languageSlice.reducer
    },
})

export default store;
export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;