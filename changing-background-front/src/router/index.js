import Vue from 'vue'
import Router from 'vue-router'

import IndexPage from '@/components/IndexPage'
import LoginPage from '@/components/login/LoginPage'
import NotFound from '@/components/framework/NotFound'

Vue.use(Router)

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
    if (to.meta.requireAuth) {
        // 通过vuex state获取当前的token是否存在
        // const token = store.getters.getToken;
        const token = localStorage.token;
        const a = this.$store.getters.getToken;
        debugger
        if (token) {
            next();
        } else {
            next({
                path: '/login',
                // 将跳转的路由path作为参数，登录成功后跳转到该路由
                query: {redirect: to.fullPath}
            })
        }
    } else {
        next();
    }
});

export default router;