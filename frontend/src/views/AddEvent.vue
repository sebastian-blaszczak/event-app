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
                                    name="name"
                                    label="Event name"
                                    type="text"
                                    v-model="name"
                                    required
                            >
                            </v-text-field>

                            <v-textarea
                                    name="description"
                                    label="Event Description"
                                    type="text"
                                    v-model="description"
                                    required
                            >
                            </v-textarea>

                            <v-text-field
                                    name="date"
                                    type="date"
                                    v-model="date"
                                    required
                            >
                            </v-text-field>

                            <v-text-field
                                    name="street"
                                    label="Street"
                                    type="text"
                                    v-model="street"
                                    required
                            >
                            </v-text-field>

                            <v-text-field
                                    name="numberOfBuilding"
                                    label="No. building"
                                    type="number"
                                    v-model="numberOfBuilding"
                                    required
                            >
                            </v-text-field>

                            <v-text-field
                                    name="numberOfRoom"
                                    label="No. room"
                                    type="number"
                                    v-model="numberOfRoom"
                                    required
                            >
                            </v-text-field>

                            <v-text-field
                                    name="city"
                                    label="City"
                                    type="text"
                                    v-model="city"
                                    required
                            >
                            </v-text-field>

                            <v-text-field
                                    name="zipCode"
                                    label="Zip Code"
                                    type="text"
                                    v-model="zipCode"
                                    required
                            >
                            </v-text-field>

                            <v-select
                                    ref="accessType"
                                    v-model="accessType"
                                    :rules="[() => !!accessType || 'This field is required']"
                                    :items="accessTypeContent"
                                    label="Access"
                                    placeholder="Select..."
                                    required
                            ></v-select>

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
        name: 'AddEvent',
        data() {
            return {
                accessTypeContent: ['PUBLIC', 'PRIVATE'],
                name: '',
                description: '',
                date: null,
                street: '',
                numberOfBuilding: null,
                numberOfRoom: null,
                city: '',
                zipCode: '',
                accessType: ''
            };
        },
        methods: {
            submit() {
                const username = window.localStorage.getItem('username')
                const password = window.localStorage.getItem('password')

                this.axios.request({
                    method: 'post',
                    url: 'http://localhost:8098/user/events/add',
                    auth: {
                        password: password,
                        username: username
                    },
                    data: {
                        name: this.name,
                        description: this.description,
                        date: this.date,
                        address: {
                            street: this.street,
                            numberOfBuilding: this.numberOfBuilding,
                            numberOfRoom: this.numberOfRoom,
                            city: this.city,
                            zipCode: this.zipCode,
                        },
                        accessType: this.accessType
                    }
                })
                this.$router.push({name: 'user-events'})
            }
        }
    };
</script>

<style scoped></style>
