<template>
	<div class="personal">
		<el-form label-width="80px" ref="editForm" :model="editForm" :rules="rules">
			<el-form-item  label="学号" prop="xuehao"><el-input size="small" v-model="editForm.xuehao" auto-complete="off" placeholder="请输入学号" style='width:25%'></el-input></el-form-item>
		
		<el-form-item  label="姓名" prop="xingming"><el-input size="small" v-model="editForm.xingming" auto-complete="off" placeholder="请输入姓名" style='width:25%'></el-input></el-form-item>
		<el-form-item  label="性别" prop="xingbie"><el-radio v-model="editForm.xingbie" :label=true>男</el-radio><el-radio v-model="editForm.xingbie" :label=false>女</el-radio></el-form-item>
		<el-form-item  label='学校信息' prop="xuexiaoxinxi"><el-select size="small" v-model="editForm.xuexiaoxinxi" placeholder="请选择"><el-option selected label="请选择" value=""></el-option><el-option v-for="item in xuexiaoxinxis" :key="item.xuexiaomingcheng" :label="item.xuexiaomingcheng" :value="item.xuexiaomingcheng"></el-option></el-select></el-form-item>
		<el-form-item  label='专业' prop="zhuanye"><el-select size="small" v-model="editForm.zhuanye" placeholder="请选择专业"><el-option selected label="请选择专业" value=""></el-option><el-option label="计算机" value="计算机"></el-option><el-option label="外贸" value="外贸"></el-option><el-option label="土建" value="土建"></el-option><el-option label="外语" value="外语"></el-option><el-option label="电子工程" value="电子工程"></el-option><el-option label="人文" value="人文"></el-option></el-select></el-form-item>
		<el-form-item  label="联系电话" prop="lianxidianhua"><el-input size="small" v-model="editForm.lianxidianhua" auto-complete="off" placeholder="请输入联系电话" style='width:25%'></el-input></el-form-item>
		<el-form-item  label='照片' prop='zhaopian'><el-upload class='avatar-uploader' action='http://localhost:9999/files/upload' :headers='headers' :show-file-list='false' :on-success='handleZhaopianSuccess' :before-upload='beforeZhaopianUpload'><img v-if='editForm.zhaopian' :src='editForm.zhaopian' class='avatar'><i v-else class='el-icon-plus avatar-uploader-icon'></i></el-upload></el-form-item>
		<el-form-item  label="团队人数" prop="tuanduirenshu" style="display: none"><el-input-number size="small" v-model="editForm.tuanduirenshu" auto-complete="off" placeholder="请输入团队人数" style='width:25%'></el-input-number></el-form-item>
		
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button size="small" type="primary" :loading="loading" class="title" @click="submitForm('editForm')">更新</el-button>
		</div>
	</div>
</template>

<script>
	import {daxueshengEdit, daxueshengSave,daxueshengDetail} from "@/api/daxuesheng/daxueshengApi";
	import {Session} from "@/utils/storage";
	import{xuexiaoxinxiEdit,xuexiaoxinxiList,xuexiaoxinxiDetail,xuexiaoxinxiAllList} from  "@/api/xuexiaoxinxi/xuexiaoxinxiApi";

	export default {
	name: 'personal',
	data() {
		return {
			rules: {
				xuehao: [{ required: true, message: '请输入学号', trigger: 'blur' },
				],
				mima: [{ required: true, message: '请输入密码', trigger: 'blur' },
				],
				lianxidianhua: [{ pattern: /^[1][3,4,5,7,8][0-9]{9}$/,required: true,message: '请输入正确的手机号',trigger: 'blur'}
				],
				tuanduirenshu: [{ type: 'number', message: '团队人数必须为数字'},
				],
				
			},
			editForm: {
				avater:''
			},
			xuexiaoxinxis:[],
		};
	},
	created() {
		this.getDetail()
		this.getAllxuexiaoxinxi();
	},
	computed: {
		headers(){
			return {"token":Session.get("token")}
		},
	},
	methods:{
		getDetail(){
			if (!Session.get('userInfo')) return false;
			this.userInfo = Session.get('userInfo');
			console.log(this.userInfo);
			daxueshengDetail(this.userInfo.id).then(res=>{
				if (res.code == '0') {
					this.editForm = res.data;
				}
			}).catch(err => {
				if(err){
					this.$message.error(err)
				}else {
					this.$message.error('操作失败，请稍后再试！')
				}
			})
		},

		getAllxuexiaoxinxi(){xuexiaoxinxiAllList().then(res=>{if(res.code == '0'){this.xuexiaoxinxis = res.data}})},
		// 编辑、增加页面保存方法
		submitForm(editData) {
			this.$refs[editData].validate(valid => {
				if (valid) {
					if(this.editForm.id){
						daxueshengEdit(this.editForm).then(res => {
							if (res.code == '0') {
								this.getDetail()
								this.$message({
									type: 'success',
									message: '个人信息修改成功！'
								})
								this.$store.dispatch('userInfos/setUserInfos',this.editForm);
							} else {
								this.$message({
									type: 'info',
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
					}else {
						daxueshengSave(this.editForm).then(res => {
							if (res.code == '0') {
								this.getDetail()
								this.$message({
									type: 'success',
									message: '个人信息添加成功！'
								})
							} else {
								this.$message({
									type: 'info',
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
					}
				} else {
					return false
				}
			})
		},
		handleZhaopianSuccess(res, file) {
				if(res.code == "0") {
					this.editForm.zhaopian = "/files/download/"+res.data.id
				}
			},
			beforeZhaopianUpload(file) {
				const isJPG = file.type === 'image/jpeg';
				const isLt2M = file.size / 1024 / 1024 < 2;

				if (!isJPG) {
					this.$message.error('上传图片只能是 JPG 格式!');
				}
				if (!isLt2M) {
					this.$message.error('上传图片大小不能超过 2MB!');
				}
				return isJPG && isLt2M;
			},
				
	}
};
</script>

<style scoped lang="scss">
	@import './index.scss';
</style>
