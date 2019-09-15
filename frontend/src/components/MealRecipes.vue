<template>
    <v-container grid-list-lg>
        <v-layout row wrap>
            <v-flex xs12 sm6 md6 lg4 v-for="(item, idx) in recipes" :key="idx">
                <v-card data-cy="recipeEntry">
                    <v-responsive>
                        <v-img
                                src="https://picsum.photos/id/11/500/300"
                                lazy-src="https://picsum.photos/id/11/10/6"
                                aspect-ratio="1"
                                class="grey lighten-2"
                                max-width="500"
                                max-height="300"
                        ></v-img>
                    </v-responsive>

<!--                    <v-card-text>-->
<!--&lt;!&ndash;                        <div class="title my-5">{{ item.recipe.label }}</div>&ndash;&gt;-->

<!--                        <div class="subheading">Ingredients</div>-->
<!--                        <ul>-->
<!--                            &lt;!&ndash;                            <li&ndash;&gt;-->
<!--                            &lt;!&ndash;                                    v-for="(ingredient, i) in item.recipe&ndash;&gt;-->
<!--                            &lt;!&ndash;                                    .ingredientLines"&ndash;&gt;-->
<!--                            &lt;!&ndash;                                    :key="i"&ndash;&gt;-->
<!--                            &lt;!&ndash;                            >&ndash;&gt;-->
<!--                            &lt;!&ndash;                                {{ ingredient }}&ndash;&gt;-->
<!--                            &lt;!&ndash;                            </li>&ndash;&gt;-->
<!--                        </ul>-->
<!--                    </v-card-text>-->

                    <v-card-actions>
                        <v-btn color="green" dark @click="orderRecipe(item)"
                        >Order
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
        name: 'MealRecipes',
        computed: {
            recipes() {
                return 'asdawdwwadw';
            },
        },
        methods: {
            orderRecipe(item) {
                // if (this.isAuthenticated) {
                //     this.$store.dispatch('addRecipe', item);
                // } else {
                //     this.$router.push('/sign-in');
                // }
            }
        }
    };
</script>

<style scoped></style>
