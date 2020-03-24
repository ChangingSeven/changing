<template>
    <div id="app">

        <img v-show="!(currentPagePath === '/login')" src="./assets/logo.png" />
        <img v-show="currentPagePath === '/login'" src="./assets/images/login-logo.png" style="width: 200px;height: 200px;"/>
        <router-view/>
    </div>
</template>

<script>
    export default {
        name: 'App',
        data() {
            return {
                currentPagePath: ''
            }
        },
        mounted() {
            this.currentPagePath = this.$route.path
        }
    }
</script>

<style>
    #app {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
    }
</style>
