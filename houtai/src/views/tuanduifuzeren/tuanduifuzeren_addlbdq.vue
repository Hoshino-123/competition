<template>
	<div class="personal">
		<el-form label-width="100px" ref="editForm" :model="editForm" :rules="rules">
			<el-form-item  label="团队编号" prop="tuanduibianhao"><el-input size="small" v-model="editForm.tuanduibianhao" auto-complete="off" placeholder="请输入团队编号" style='width:50%' disabled></el-input></el-form-item>
		
		<el-form-item  label="团队名称" prop="tuanduimingcheng"><el-input size="small" v-model="editForm.tuanduimingcheng" auto-complete="off" placeholder="请输入团队名称" style='width:50%' disabled></el-input></el-form-item>
		<el-form-item  label="团队类型" prop="tuanduileixing"><el-input size="small" v-model="editForm.tuanduileixing" auto-complete="off" placeholder="请输入团队类型" style='width:50%' disabled></el-input></el-form-item>
		<el-form-item  label="团队地址" prop="tuanduidizhi"><el-input size="small" v-model="editForm.tuanduidizhi" auto-complete="off" placeholder="请输入团队地址" style='width:50%' disabled></el-input></el-form-item>
		<el-form-item  label="联系电话" prop="lianxidianhua"><el-input size="small" v-model="editForm.lianxidianhua" auto-complete="off" placeholder="请输入联系电话" style='width:50%' disabled></el-input></el-form-item>
		<el-form-item  label="学校信息" prop="xuexiaoxinxi"><el-input size="small" v-model="editForm.xuexiaoxinxi" auto-complete="off" placeholder="请输入学校信息" style='width:50%' disabled></el-input></el-form-item>
		<el-form-item  label="团队人数" prop="tuanduirenshu"><el-input-number size="small" v-model="editForm.tuanduirenshu" auto-complete="off" placeholder="请输入团队人数" style='width:50%'></el-input-number></el-form-item>
		<el-form-item  label='队微' prop='duiwei'><el-upload class='avatar-uploader' action='http://localhost:9999/files/upload' :headers='headers' :show-file-list='false' :on-success='handleDuiweiSuccess' :before-upload='beforeDuiweiUpload'><img v-if='editForm.duiwei' :src='editForm.duiwei' class='avatar'><i v-else class='el-icon-plus avatar-uploader-icon'></i></el-upload></el-form-item>
		
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button size="small" type="primary" :loading="loading" class="title" @click="submitForm('editForm')">添加</el-button>
		</div>
	</div>
</template>

<script>
	import {tuanduifuzerenEdit, tuanduifuzerenSave,tuanduifuzerenDetail} from "@/api/tuanduifuzeren/tuanduifuzerenApi";
	import {Session} from "@/utils/storage";
import {shenqingtuanduiDetail,shenqingtuanduiEdit} from "../../api/shenqingtuandui/shenqingtuanduiApi";
//xiabiaoduan1
export default {
	name: 'personal',
	data() {
		return {
			rules: {
				//yztssssss1
			},
			editForm: {
				avater:'',
				tuanduibianhao:'',
				tuanduimingcheng:'',
				tuanduileixing:'',
				tuanduidizhi:'',
				lianxidianhua:'',
				xuexiaoxinxi:'',
				
			},
			username:'',
			cx:'',
			//xiabiaoduan2
		};
	},
	created() {
		if (!Session.get('userInfo')) return false;
		this.userInfo = Session.get('userInfo');
		this.username=localStorage.getItem("username");
		this.cx=localStorage.getItem("cx");
		let id = this.$route.params.id;
		//xiabiaoduan3
		this.getUserInfo(id);
	},
	computed: {
		headers(){
			return {"token":Session.get("token")}
		},
	},
	methods:{
		getUserInfo(id){
			shenqingtuanduiDetail(id).then(res=>{
				if(res.code == '0'){
					this.shenqingtuandui = res.data
					this.editForm.tuanduibianhao = this.shenqingtuandui.tuanduibianhao;
					this.editForm.tuanduimingcheng = this.shenqingtuandui.tuanduimingcheng;
					this.editForm.tuanduileixing = this.shenqingtuandui.tuanduileixing;
					this.editForm.tuanduidizhi = this.shenqingtuandui.tuanduidizhi;
					this.editForm.lianxidianhua = this.shenqingtuandui.lianxidianhua;
					this.editForm.xuexiaoxinxi = this.shenqingtuandui.xuexiaoxinxi;
				
					// this.editForm.tuanduibianhao= this.userInfo.;
					
					
				}
			})
		},
		
		//qixuzhijixsuan3
		//xia2sui1
		// 编辑、增加页面保存方法
		submitForm(editData) {
			
			this.$refs[editData].validate(valid => {
				if (valid) {
					if(this.editForm.id){
						
					}else {
						tuanduifuzerenSave(this.editForm).then(res => {
							if (res.code == '0') {
								
								this.$message({
									type: 'success',
									message: '添加成功！'
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
		handleDuiweiSuccess(res, file) {
				if(res.code == "0") {
					this.editForm.duiwei = "/files/download/"+res.data.id
				}
			},
			beforeDuiweiUpload(file) {
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
				
		//youscwexnjiaxn
		//xiabiaoduan4
	}
};
</script>

<style scoped lang="scss">
	@import './index.scss';
</style>
