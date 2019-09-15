<template>
        <span>
        <event :key="$route.fullPath"></event>
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
