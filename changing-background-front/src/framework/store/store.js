import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

const store = new Vuex.Store({
    state: {
        token: ''
    },
    mutations: {
        setToken(state, value) {
            state.token = value;
            localStorage.token = value;
        }
    },
    getters: {
        getToken(state) {
            var token = state.token;
            if (token) {
                return token;
            } else {
                return localStorage.token;
            }
        }
    }
});

export default store;