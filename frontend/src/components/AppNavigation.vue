<template>
    <span>
        <v-navigation-drawer
                app
                v-model="drawer"
                class="orange darken-2"
                dark
                disable-resize-watcher
        >
            <v-list>
                <template v-for="(item, index) in items">
                    <v-list-tile :key="index" :to="item.url">
                        <v-list-tile-content>
                            {{ item.title }}
                        </v-list-tile-content>
                    </v-list-tile>
                    <v-divider :key="`divider-${index}`"></v-divider>
                </template>
            </v-list>
        </v-navigation-drawer>
        <v-toolbar app color="orange accent-2" dark>
            <v-toolbar-side-icon
                    class="hidden-md-and-up"
                    @click="drawer = !drawer"
            ></v-toolbar-side-icon>
            <v-spacer class="hidden-md-and-up"></v-spacer>
            <router-link to="/">
                <v-toolbar-title data-cy="titleBtn">{{
                    appTitle
                }}</v-toolbar-title>
            </router-link>
            <v-spacer class="hidden-sm-and-down"></v-spacer>
        </v-toolbar>
    </span>
</template>

<script>
    export default {
        name: 'AppNavigation',
        data() {
            return {
                appTitle: 'Make it big event app',
                drawer: false,
                items: [
                    {title: 'Profile', url: '/about'},
                    {title: 'Sign In', url: '/sign-in'},
                    {title: 'Log In', url: '/log-in'}
                ]
            };
        },
    };
</script>

<style scoped>
    a {
        color: white;
        text-decoration: none;
    }
</style>
