<template>
    <v-container fluid fill-height class="home-hero" style="max-height: 100vh;">
        <v-layout justify-center align-center column pa-5>
            <div class="display-4 font-weight-black white--text text-xs-center">
                Make it big
            </div>
            <div
                class="display-4 font-weight-black white--text text-xs-center mb-3"
            >
                event app
            </div>
            <div class="display-1 font-weight-bold white--text text-xs-center">
                Finally great events with wonderful people at your fingertips.
            </div>

            <v-btn
                    large
                    color="orange darken-2"
                    dark
                    @click="forwardToLogIn"
            >Log in
            </v-btn>

            <v-btn
                    large
                    color="orange darken-4"
                    dark
                    @click="forwardToSignIn"
            >Sign up
            </v-btn>

        </v-layout>
    </v-container>
</template>

<script>
export default {
    name: 'HomeHero',

    methods: {
        forwardToLogIn(){
            this.$router.push({name: 'login'})
        },
        forwardToSignIn(){
            this.$router.push({name: 'signin'})
        }
    }
}
</script>

<style scoped>
.home-hero {
    background: url('https://cdn.pixabay.com/photo/2018/05/10/11/34/concert-3387324_960_720.jpg');
    background-size: cover;
    width: 100%;
    height: 100%;
}
</style>
