<template>
    <v-container grid-list-lg>
        <v-layout row>
            <v-flex xs12 class="text-xs-center display-1 font-weight-black my-5"
            >Event that You participate
            </v-flex>
            <v-btn
                    color="orange darken-2"
                    @click="addEvent"
            >Add event
            </v-btn>
        </v-layout>
        <v-layout column>
            <v-card hover v-for="event in events">

                <v-card-media
                        src="https://raw.githubusercontent.com/ijklim/simon-game/gh-pages/assets/img/bg--game-pad.jpg"
                        height="150px"
                >
                </v-card-media>

                <v-card-title>
                    <h2>{{ event.name }}</h2>
                </v-card-title>

                <v-card-text>
                    <h3>Description</h3>
                    {{ event.description }}
                    <h3>Address</h3>
                    City: {{ event.address.city}}<br>
                    Street : {{event.address.street}}
                    <h3>Organizer</h3>
                    {{event.organizer.nickName}}
                </v-card-text>

                <v-card-actions v-if="!isOrganizer(event.organizer.userName)">
                    <v-btn
                            color=success
                            @click="participateToEvent(event.id)"
                    >Join
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-layout>
        <v-layout row>
            <v-flex xs12 class="text-xs-center display-1 font-weight-black my-5"
            >Available Events
            </v-flex>
        </v-layout>
        <v-layout column>
            <v-card hover v-for="event in eventsToParticipate">

                <v-card-media
                        src="https://raw.githubusercontent.com/ijklim/simon-game/gh-pages/assets/img/bg--game-pad.jpg"
                        height="150px"
                >
                </v-card-media>

                <v-card-title>
                    <h2>{{ event.name }}</h2>
                </v-card-title>

                <v-card-text>
                    <h3>Description</h3>
                    {{ event.description }}
                    <h3>Address</h3>
                    City: {{ event.address.city}}<br>
                    Street : {{event.address.street}}
                    <h3>Organizer</h3>
                    {{event.organizer.nickName}}
                </v-card-text>

                <v-card-actions v-if="!isOrganizer(event.organizer.userName)">
                    <v-btn
                            color=success
                            @click="participateToEvent(event.id)"
                    >Join
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-layout>
    </v-container>
</template>

<script>
    export default {
        name: "Event",
        data() {
            return {
                events: null,
                eventsToParticipate: null,
                // isOrganizer: true
            };
        },
        methods: {
            addEvent() {
                this.$router.push({name: 'add-event'});
            },

            isOrganizer(user) {
                const username = window.localStorage.getItem('username')
                return username === user
            },

            participateToEvent(eventId) {
                const username = window.localStorage.getItem('username')
                const password = window.localStorage.getItem('password')

                this.axios.request({
                    headers : {
                        'Content-Type' : 'application/json'
                    },
                    method: 'post',
                    url: 'http://localhost:8098/user/participate',
                    auth: {
                        password: password,
                        username: username
                    },
                    data : eventId
                })
            }

        },
        created() {
            const username = window.localStorage.getItem('username')
            const password = window.localStorage.getItem('password')

            this.axios.request({
                url: 'http://localhost:8098/user/events',
                method: "get",
                auth: {
                    password: password,
                    username: username
                }
            }).then(response =>
                this.events = response.data
            )

            this.axios.request({
                url: 'http://localhost:8098/user/events/not-participate',
                method: "get",
                auth: {
                    password: password,
                    username: username
                }
            }).then(response =>
                this.eventsToParticipate = response.data
            )
        }
    }
</script>

<style scoped>

</style>