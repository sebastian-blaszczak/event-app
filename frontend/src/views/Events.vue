<template>
        <span>
        <event><router-view :key="$route.fullPath"></router-view></event>
    </span>
</template>

<script>
    import Event from '@/components/Event';

    export default {
        name: 'Events',
        components: {
            Event,
        }
    };
</script>
