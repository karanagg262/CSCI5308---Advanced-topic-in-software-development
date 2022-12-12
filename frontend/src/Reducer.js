const Reducer = (state, action) => {
    switch (action.type) {
        case 'open_group':
            return {
                ...state,
                group: action.payload.group
            };
        case 'save_username':
            return {
                ...state,
                username: action.payload.username
            };
        default:
            return {
                ...state
            };
    }
};

export default Reducer;