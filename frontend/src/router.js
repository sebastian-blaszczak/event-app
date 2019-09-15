import Vue from 'vue';
import Router from 'vue-router';
import Home from './views/Home.vue';

Vue.use(Router);

const router = new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/',
            name: 'home',
            component: Home
        },
        {
            path: '/menu',
            name: 'menu',
            component: () => import('./views/Menu.vue')
        },
        {
            path: '/sign-in',
            name: 'signin',
            component: () => import('./views/Signin.vue')
        },
        {
            path: '/log-in',
            name: 'login',
            component: () => import('./views/LogIn.vue'),
            meta: {
                requiresAuth: true
            }
        },
        {
            path: '/user/events',
            name: 'user-events',
            component: () => import('./views/Events.vue'),
            meta: {
                requiresAuth: true
            }
        },
        {
            path: '/user/events/add-event',
            name: 'add-event',
            component: () => import('./views/AddEvent.vue'),
            meta: {
                requiresAuth: true
            }
        },

        // otherwise redirect to home
        { path: '*', redirect: '/' }
    ]
});

router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.authRequired)) {
        if (!store.state.isAuthenticated) {
            next({
                path: '/sign-in'
            });
        } else {
            next();
        }
    } else {
        next();
    }
});

export default router;
