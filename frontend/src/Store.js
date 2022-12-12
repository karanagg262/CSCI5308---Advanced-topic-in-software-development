import React, { createContext, useReducer } from 'react';
import Reducer from './Reducer';

const initialState = {
    group: null,
    username: null
};

export const context = createContext(initialState);

const Store = ({ children }) => {
    const [state, dispatch] = useReducer(Reducer, initialState);
    return (
        <context.Provider value={[state, dispatch]} >
            {children}
        </context.Provider>
    )
};

export default Store;