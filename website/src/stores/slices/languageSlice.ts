import { createSlice } from "@reduxjs/toolkit";
import english from "../../languages/english.json";

export const languageSlice =  createSlice({
    name: 'language',
    initialState: english,
    reducers: {
        setLanguage: (state, action) => {
            state = action.payload;
        }
    }
});

export const { setLanguage } = languageSlice.actions;
export default languageSlice.reducer;