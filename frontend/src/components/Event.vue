<template>
    <v-flex xs12 sm12 md4>
        <v-card>
            <v-responsive>
                <v-img
                        src="http://source.unsplash.com/6S27S6pZ6o0"
                        height="500px"
                >
                    <v-container fill-height fluid>
                        <v-layout fill-height>
                            <v-flex xs12 align-end flexbox>
                                        <span class="headline white--text"
                                        >PALEO</span
                                        >
                            </v-flex>
                        </v-layout>
                    </v-container>
                </v-img>
            </v-responsive>

            <v-card-text>
                <div>
                    <h3 class="headline mb-0">Paleo</h3>
                    <div>
                        The Paleo diet requires the predominant
                        consumption of foods presumed to have been the
                        only foods available to or consumed by humans
                        during the Paleolithic era.
                    </div>
                </div>
            </v-card-text>

            <v-card-actions v-if="['menu'].includes($route.name)">
                <v-btn
                        outline
                        block
                        color="green"
                        @click="showRecipes('paleo')"
                        data-cy="plansPaleoBtn"
                >
                    Select This Plan
                </v-btn
                >
            </v-card-actions>
        </v-card>
    </v-flex>
</template>

<script>
    export default {
        name: "Event"
    }
</script>

<style scoped>

</style>