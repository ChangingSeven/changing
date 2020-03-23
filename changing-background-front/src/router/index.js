import Vue from 'vue'
import Router from 'vue-router'

import IndexPage from '@/components/IndexPage'
import LoginPage from '@/components/login/LoginPage'
import NotFound from '@/components/framework/NotFound'

Vue.use(Router)

export default new Router({
    mode: 'history',
    base: '/bg-front',
    routes: [
        {
            path: '/index',
            name: 'IndexPage',
            component: IndexPage,
            meta: {
                title: '首页'
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
})
