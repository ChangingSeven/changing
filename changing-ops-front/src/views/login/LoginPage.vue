<template>
    <div class="hello">
        <img src="../../assets/images/login-logo.png" style="width: 200px;height: 200px;"/>
        <div style="margin-left: 25%;margin-right: 35%;">
            <el-form ref="loginForm" status-icon :model="form" :rules="rules" label-width="80px">
                <el-form-item label="Account: " label-width="180px" prop="userName">
                    <el-input v-model="form.userName" placeholder="input your account"></el-input>
                </el-form-item>
                <el-form-item label="Password: " label-width="180px" prop="passwd">
                    <el-input v-model="form.passwd" placeholder="input your password" show-password></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="success" size="small" icon="el-icon-success" @click="submitForm('loginForm')">
                        Login
                    </el-button>
                    <el-button type="warning" size="small" icon="el-icon-refresh" @click="resetForm('loginForm')">
                        Reset
                    </el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'LoginPage',
        data() {
            var validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('password is empty'));
                } else {
                    callback();
                }
            };

            return {
                msg: 'This is login page',
                form: {
                    userName: '',
                    passwd: ''
                },
                rules: {
                    userName: [
                        {required: true, message: 'account is empty', trigger: 'blur'}
                    ],
                    passwd: [
                        {required: true, message: 'password is empty', trigger: 'blur'},
                        {validator: validatePass, trigger: blur}
                    ]
                }
            }
        },
        methods: {
            submitForm(formName) {
                let vueObj = this;
                vueObj.$refs[formName].validate((valid) => {
                    if (valid) {
                        let postParam = {
                            'userName': this.form.userName,
                            'password': this.form.passwd,
                        };
                        vueObj.$frameAxios.post('http://127.0.0.1:9090/login', postParam)
                            .then(function (response) {
                                let token = response.data.data.accessToken;
                                vueObj.$frameStore.commit('setToken', token);

                                // 跳转首页
                                let redirectUrl = vueObj.$route.query.redirectUrl;
                                if (redirectUrl) {
                                    vueObj.$router.push({path: redirectUrl});
                                } else {
                                    vueObj.$router.push({path: 'index'});
                                }
                            }).catch(function (error) {
                                alert('request error');
                            });
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
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

    .el-form {
        text-align: center;
    }
</style>
