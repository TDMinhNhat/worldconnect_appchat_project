import english from "../../languages/english.json";

export const languageSlice=  {
    name: 'language',
    initialState: english,
    reducers: {
        setLanguage: (state, action) => {
            state = action.payload;
        }
    }
}

export const { setLanguage } = languageSlice.reducers;
export default languageSlice.reducers;