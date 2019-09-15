<template>
    <v-container fill-height>
        <v-layout align-center justify-center>
            <v-flex xs12 sm8 md4>
                <v-card class="elevation-12">
                    <v-toolbar dark color="orange darken-2">
                        <v-toolbar-title>Log in Form</v-toolbar-title>
                    </v-toolbar>
                    <v-card-text>
                        <v-form ref="form" v-model="valid" lazy-validation>
                            <v-text-field
                                    name="username"
                                    label="User Name"
                                    type="username"
                                    v-model="username"
                                    required
                            >
                            </v-text-field>
                            <v-text-field
                                    name="password"
                                    label="Password"
                                    type="password"
                                    required
                                    v-model="password"
                                    :rules="passwordRules"
                                    data-cy="joinPasswordField"
                            >
                            </v-text-field>
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn
                                color="orange darken-2"
                                :disabled="!valid"
                                @click="submit"
                                data-cy="joinSubmitBtn"
                        >Log in
                        </v-btn
                        >
                    </v-card-actions>
                </v-card>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    export default {
        name: 'Join',
        data() {
            return {
                valid: false,
                username: '',
                password: '',
                emailRules: [
                    v => !!v || 'E-mail is required',
                    v => /.+@.+/.test(v) || 'E-mail must be valid'
                ],
                passwordRules: [
                    v => !!v || 'Password is required',
                    v =>
                        v.length >= 6 ||
                        'Password must be greater than 6 characters'
                ]
            };
        },
        methods: {
            submit() {
                window.localStorage.setItem('username', this.username)
                window.localStorage.setItem('password', this.password)
                this.$router.push({name: 'user-events'})
            }
        }
    }
</script>

<style scoped></style>
