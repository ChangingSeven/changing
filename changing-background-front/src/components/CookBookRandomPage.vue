<template>
    <div class="hello">
        <h1>{{ msg }}</h1>

        <el-row type="flex" justify="end">
            <el-col :span="1">
                <el-button v-on:click="loadTableData" type="primary" round size="small">再看看</el-button>
            </el-col>
            <el-col :span="2">
                <el-button type="success" round size="small">就它了</el-button>
            </el-col>
        </el-row>

        <el-table :data="tableData" :span-method="tableSpanMethod" style="width: 100%">
            <el-table-column prop="timeType" label="午餐/晚餐" width="100"></el-table-column>
            <el-table-column prop="monday" label="星期一" width="180"></el-table-column>
            <el-table-column prop="tuesday" label="星期二"></el-table-column>
            <el-table-column prop="wednesday" label="星期三"></el-table-column>
            <el-table-column prop="thursday" label="星期四"></el-table-column>
            <el-table-column prop="friday" label="星期五"></el-table-column>
            <el-table-column prop="saturday" label="星期六"></el-table-column>
            <el-table-column prop="sunday" label="星期天"></el-table-column>
        </el-table>

    </div>
</template>

<script>
    export default {
        name: 'IndexPage',
        data() {
            return {
                msg: '秀秀你怎么看',
                tableData: []
            }
        },
        mounted: function () {
            this.loadTableData();
        },
        methods: {
            loadTableData: function () {
                let vueObj = this;
                vueObj.$frameAxios.get('http://127.0.0.1:9090/cookbook/random')
                    .then(function (response) {
                        let data = response.data.data;
                        vueObj.tableData = data;
                    });
            },
            tableSpanMethod: function ({row, column, rowIndex, columnIndex}) {
                debugger
                if (columnIndex === 0) {
                    if (rowIndex % 3 === 0) {
                        return {
                            rowspan: 3,
                            colspan: 1
                        };
                    } else {
                        return {
                            rowspan: 0,
                            colspan: 0
                        };
                    }
                }
            }
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    h1, h2 {
        font-weight: normal;
    }

    ul {
        list-style-type: none;
        padding: 0;
    }

    li {
        display: inline-block;
        margin: 0 10px;
    }

    a {
        color: #42b983;
    }
</style>
