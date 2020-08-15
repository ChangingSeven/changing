import Vue from 'vue'
import Router from 'vue-router'
import Vuex from 'vuex'

import IndexPage from '@/components/IndexPage'
import CookBookRandomPage from '@/components/CookBookRandomPage'
import LoginPage from '@/views/login/LoginPage'
import NotFound from '@/components/framework/NotFound'

Vue.use(Router);
Vue.use(Vuex);

// vuex store start
const frameStore = new Vuex.Store({
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
// 把 store 添加到 vue 扩展方法中
Vue.prototype.$frameStore = frameStore;
// vuex store end


// vue router start
// 请求页面跳转
const router = new Router({
    mode: 'history',
    base: '/bg-front',
    routes: [
        {
            path: '/',
            name: 'IndexPage',
            component: IndexPage,
            meta: {
                title: '首页'
            }
        },
        {
            path: '/index',
            name: 'IndexPage',
            component: IndexPage,
            meta: {
                title: '首页',
                requireAuth: true
            }
        }, {
            path: '/login',
            name: 'LoginPage',
            component: LoginPage,
            meta: {
                title: '登录'
            }
        }, {
            path: '/cookbook/random',
            name: 'CookBookRandomPage',
            component: CookBookRandomPage,
            meta: {
                title: '生成菜品'
            }
        },
        {
            path: '*',
            name: 'NotFound',
            component: NotFound,
            meta: {
                title: '404 - Not Found'
            }
        }
    ]
});

// 请求拦截
router.beforeEach((to, from, next) => {
    // 1、路由发生变化修改页面title
    if (to.meta.title) {
        document.title = to.meta.title;
    }

    // 2、判断该路由是否需要登录权限
    // 通过vuex state获取当前的token是否存在
    const token = frameStore.getters.getToken;
    if (to.meta.requireAuth) {
        if (token) {
            next();
        } else {
            next({
                path: '/login',
                // 将跳转的路由path作为参数，登录成功后跳转到该路由
                query: {redirectUrl: to.fullPath}
            })
        }
    } else {
        next();
    }
});
// vue router end

export default router;