<template>
    <div class="markdown-container">
        <div class="container">
            <div class="title">富文本编辑器</div>
        <tinymce
                ref="editor"
                v-model="data.neirong"
                :disabled="disabled"
        />
        <row>
            <el-col :span="24" style="text-align: center;margin-top: 20px">
                <el-button type="danger" @click="clear">清空内容</el-button>
                <el-button type="primary"  @click="save">保存</el-button>
                <el-button type="primary"  @click="back">返回</el-button>
            </el-col>
        </row>
        </div>
    </div>
</template>

<script>
    import tinymce from '@/components/tinymce.vue'
    import {xinwentongzhiDetail} from "@/api/xinwentongzhi/xinwentongzhiApi";
    import {xinwentongzhiEdit} from "@/api/xinwentongzhi/xinwentongzhiApi";
    export default {
        name: 'tinymceEditor',
        components: {
            tinymce
        },
        created() {
           let id = this.$route.params.id;
           this.getArticleDetail(id);
        },
        data(){
            return{
                disabled: false,
                data:{},
            }
        },
        methods: {
            back(){
                if (this.$route.query.goindex === 'true') {
                    this.$router.push('/')
                } else {
                    this.$router.back(-1)
                }
            },
            getArticleDetail(id){
                xinwentongzhiDetail(id).then(res=>{
                    if(res.code == '0'){
                        const data = res.data
                        this.data = data;
                    }
                })
            },
            // 清空内容
            clear () {
                this.$refs.editor.clear()
            },
            save(){
                xinwentongzhiEdit(this.data).then(res => {
                    if (res.code == '0') {
                        this.$message({
                            type: 'success',
                            message: '内容编辑成功！'
                        })
                    } else {
                        this.$message({
                            type: 'error',
                            message: res.msg
                        })
                    }
                }).catch(err => {
                    if(err){
                        this.$message.error(err)
                    }else {
                        this.$message.error('操作失败，请稍后再试！')
                    }
                })
            },
        }
    }
</script>

<style scoped>
    .title {
        padding-bottom: 30px;
        text-align: center;
        font-size: 20px;
        letter-spacing: 1px;
        color: #333;
        font-weight: 600;
    }

</style>
