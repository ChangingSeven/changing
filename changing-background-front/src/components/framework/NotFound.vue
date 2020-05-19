<template>
    <div class="hello background-404">
        <img :src="imgSrc" width="100%" height="100%" alt="" />
        <a :href="indexPageUrl">
            <el-button class="back-to-index-btn" type="primary" round>返回首页</el-button>
        </a>
    </div>
</template>

<script>
    export default {
        name: 'NotFound',
        data() {
            return {
                msg: 'Request is not found！',
                imgSrc: require('../../assets/images/404-background.png'),
                indexPageUrl: '/index'
            }
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    .back-to-index-btn {
        position: absolute;
        z-index: 1;
        left: 48%;
        bottom: 10%;
    }

    .background-404 {
        width: 98%;
        height: 85%;
        z-index:-1;
        position: absolute;
    }

</style>
