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
        {
            path: '/about',
            name: 'about',
            component: () => import('./views/About.vue'),
            meta: {
                requiresAuth: true
            }
        },

        // otherwise redirect to home
        {path: '*', redirect: '/'}
    ]
});

export default router;
