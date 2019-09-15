<template>
    <v-container fill-height>
        <v-layout align-center justify-center>
            <v-flex xs12 sm8 md4>
                <v-card class="elevation-12">
                    <v-toolbar dark color="orange darken-2">
                        <v-toolbar-title>Sign in Form</v-toolbar-title>
                    </v-toolbar>
                    <v-card-text>
                        <v-form ref="form" v-model="valid">
                            <v-text-field
                                    name="username"
                                    label="User Name"
                                    type="username"
                                    v-model="userName"
                                    required
                            >
                            </v-text-field>
                            <v-text-field
                                    name="nickName"
                                    label="Nick name"
                                    type="nickName"
                                    v-model="nickName"
                                    required
                            >
                            </v-text-field>
                            <v-text-field
                                    name="email"
                                    label="E-mail"
                                    type="email"
                                    v-model="email"
                                    :rules="emailRules"
                                    required
                                    data-cy="signinEmailField"
                            >
                            </v-text-field>
                            <v-text-field
                                    name="password"
                                    label="Password"
                                    type="password"
                                    data-cy="signinPasswordField"
                                    v-model="password"
                                    :rules="passwordRules"
                                    required
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
                                data-cy="signinSubmitBtn"
                        >Sign up
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    export default {
        name: 'Signin',
        data() {
            return {
                userName: '',
                password: '',
                nickName: '',
                email: '',
                userRole: 0,
                enabled: true,
                accountNonLocked: true,
                credentialNonExpired: true,
                accountNonExpired: true,
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
                let currentObj = this;

                this.axios.post('http://localhost:8098/signup-user', {
                    userName: this.userName,
                    password: this.password,
                    nickName: this.nickName,
                    email: this.email,
                    userRole: this.userRole,
                    enabled: this.enabled,
                    accountNonLocked: this.accountNonLocked,
                    credentialNonExpired: this.credentialNonExpired,
                    accountNonExpired: this.accountNonExpired
                })
                    .then(function (response) {
                        currentObj.output = response.data;
                    })
                    .catch(function (error) {
                        currentObj.output = error;
                    });
                this.$router.push({name: 'user-events'})
            }
        }
    };
</script>

<style scoped></style>
