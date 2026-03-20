<template>
	<div class="personal">
		<el-form label-width="100px" ref="editForm" :model="editForm" :rules="rules">
			<el-form-item  label="申请编号" prop="shenqingbianhao"><el-input size="small" v-model="editForm.shenqingbianhao" auto-complete="off" placeholder="请输入申请编号" style='width:50%' disabled></el-input></el-form-item>
		<el-form-item  label="团队编号" prop="tuanduibianhao"><el-input size="small" v-model="editForm.tuanduibianhao" auto-complete="off" placeholder="请输入团队编号" style='width:50%' disabled></el-input></el-form-item>
		<el-form-item  label="团队名称" prop="tuanduimingcheng"><el-input size="small" v-model="editForm.tuanduimingcheng" auto-complete="off" placeholder="请输入团队名称" style='width:50%' disabled></el-input></el-form-item>
		<el-form-item  label="团队类型" prop="tuanduileixing"><el-input size="small" v-model="editForm.tuanduileixing" auto-complete="off" placeholder="请输入团队类型" style='width:50%' disabled></el-input></el-form-item>
		<el-form-item  label="学生" prop="xuesheng"><el-input size="small" v-model="editForm.xuesheng" auto-complete="off" placeholder="请输入学生" style='width:50%' disabled></el-input></el-form-item>
		
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button size="small" type="primary" :loading="loading" class="title" @click="submitForm('editForm')">添加</el-button>
		</div>
	</div>
</template>

<script>
	import {tuanyuanEdit, tuanyuanSave,tuanyuanDetail} from "@/api/tuanyuan/tuanyuanApi";
	import {Session} from "@/utils/storage";
import {jiarutuanduiDetail,jiarutuanduiEdit} from "../../api/jiarutuandui/jiarutuanduiApi";

	import{tuanduifuzerenEdit,tuanduifuzerenList,tuanduifuzerenDetail,tuanduifuzerenAllList} from "../../api/tuanduifuzeren/tuanduifuzerenApi";
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
				shenqingbianhao:'',
				tuanduibianhao:'',
				tuanduimingcheng:'',
				tuanduileixing:'',
				xuesheng:'',
				
			},
			username:'',
			cx:'',
			//xiabiaoduan2
			tuanduifuzerens:[],
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
		this.getAlltuanduifuzeren();
	},
	computed: {
		headers(){
			return {"token":Session.get("token")}
		},
	},
	methods:{
		getUserInfo(id){
			jiarutuanduiDetail(id).then(res=>{
				if(res.code == '0'){
					this.jiarutuandui = res.data
					this.editForm.shenqingbianhao = this.jiarutuandui.shenqingbianhao;
					this.editForm.tuanduibianhao = this.jiarutuandui.tuanduibianhao;
					this.editForm.tuanduimingcheng = this.jiarutuandui.tuanduimingcheng;
					this.editForm.tuanduileixing = this.jiarutuandui.tuanduileixing;
					this.editForm.xuesheng = this.jiarutuandui.xuesheng;
				
					this.editForm.tuanduibianhao= this.userInfo.tuanduibianhao;
					
					
				}
			})
		},


		getAlltuanduifuzeren(){tuanduifuzerenAllList().then(res=>{if(res.code == '0'){this.tuanduifuzerens = res.data}})},
		//qixuzhijixsuan3
		//xia2sui1
		// 编辑、增加页面保存方法
		submitForm(editData) {
			
			this.$refs[editData].validate(valid => {
				if (valid) {
					if(this.editForm.id){
						
					}else {
						tuanyuanSave(this.editForm).then(res => {
							if (res.code == '0') {
								let tuanduibianhao = this.editForm.tuanduibianhao;
								this.tuanduifuzerens.forEach(item=>{
									if(item.tuanduibianhao == tuanduibianhao){

										this.tuanduifuzeren = item;
									}
								})
								// this.tuanduifuzeren.issh=0;
								this.tuanduifuzeren.tuanduirenshu = parseInt(this.tuanduifuzeren.tuanduirenshu)+1;
								tuanduifuzerenEdit(this.tuanduifuzeren).then(res => {
									this.editFormVisible = false
									this.loading = false
									if (res.code == '0') {
										this.getdata(this.formInline)

									}
								})
								this.jiarutuandui.issh=1;
								jiarutuanduiEdit(this.jiarutuandui).then(res => {
									this.editFormVisible = false
									this.loading = false
									if (res.code == '0') {
										this.getdata(this.formInline)

									}
								})

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
		
		//youscwexnjiaxn
		//xiabiaoduan4
	}
};
</script>

<style scoped lang="scss">
	@import './index.scss';
</style>
