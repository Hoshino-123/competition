<template>
	<div class="personal">
		<el-form label-width="100px" ref="editForm" :model="editForm" :rules="rules">
			<el-form-item  label="报名编号" prop="baomingbianhao"><el-input size="small" v-model="editForm.baomingbianhao" auto-complete="off" placeholder="请输入报名编号" style='width:50%' disabled></el-input></el-form-item>
		<el-form-item  label="竞赛编号" prop="jingsaibianhao"><el-input size="small" v-model="editForm.jingsaibianhao" auto-complete="off" placeholder="请输入竞赛编号" style='width:50%' disabled></el-input></el-form-item>
		<el-form-item  label="竞赛名称" prop="jingsaimingcheng"><el-input size="small" v-model="editForm.jingsaimingcheng" auto-complete="off" placeholder="请输入竞赛名称" style='width:50%' disabled></el-input></el-form-item>
		<el-form-item  label="竞赛类型" prop="jingsaileixing"><el-input size="small" v-model="editForm.jingsaileixing" auto-complete="off" placeholder="请输入竞赛类型" style='width:50%' disabled></el-input></el-form-item>
		<el-form-item  label="指导老师" prop="zhidaolaoshi"><el-input size="small" v-model="editForm.zhidaolaoshi" auto-complete="off" placeholder="请输入指导老师" style='width:50%' disabled></el-input></el-form-item>
		<el-form-item  label="团队编号" prop="tuanduibianhao"><el-input size="small" v-model="editForm.tuanduibianhao" auto-complete="off" placeholder="请输入团队编号" style='width:50%' disabled></el-input></el-form-item>
		<el-form-item  label="团队名称" prop="tuanduimingcheng"><el-input size="small" v-model="editForm.tuanduimingcheng" auto-complete="off" placeholder="请输入团队名称" style='width:50%' disabled></el-input></el-form-item>
		<el-form-item  label="团队人数" prop="tuanduirenshu"><el-input size="small" v-model="editForm.tuanduirenshu" auto-complete="off" placeholder="请输入团队人数" style='width:50%' disabled></el-input></el-form-item>
		<el-form-item  label="学校信息" prop="xuexiaoxinxi"><el-input size="small" v-model="editForm.xuexiaoxinxi" auto-complete="off" placeholder="请输入学校信息" style='width:50%' disabled></el-input></el-form-item>
		<el-form-item  label="作品名称" prop="zuopinmingcheng"><el-input size="small" v-model="editForm.zuopinmingcheng" auto-complete="off" placeholder="请输入作品名称" style='width:50%' disabled></el-input></el-form-item>
		<el-form-item  label="作品分数" prop="zuopinfenshu"><el-input size="small" v-model="editForm.zuopinfenshu" auto-complete="off" placeholder="请输入作品分数" style='width:50%'></el-input></el-form-item>
		
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button size="small" type="primary" :loading="loading" class="title" @click="submitForm('editForm')">添加</el-button>
		</div>
	</div>
</template>

<script>
	import {zuopinpingfenEdit, zuopinpingfenSave,zuopinpingfenDetail} from "@/api/zuopinpingfen/zuopinpingfenApi";
	import {Session} from "@/utils/storage";
	import {
		baomingcansaiDelete,
		baomingcansaiDetail,
		baomingcansaiEdit
	} from "../../api/baomingcansai/baomingcansaiApi";

	import{jingsaixinxiEdit,jingsaixinxiList,jingsaixinxiDetail,jingsaixinxiAllList} from "../../api/jingsaixinxi/jingsaixinxiApi";
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
				baomingbianhao:'',
				jingsaibianhao:'',
				jingsaimingcheng:'',
				jingsaileixing:'',
				zhidaolaoshi:'',
				tuanduibianhao:'',
				tuanduimingcheng:'',
				tuanduirenshu:'',
				xuexiaoxinxi:'',
				zuopinmingcheng:'',
				
			},
			jingsaixinxis:[],
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
		this.getAlljingsaixinxi();
	},
	computed: {
		headers(){
			return {"token":Session.get("token")}
		},
	},
	methods:{
		getUserInfo(id){
			baomingcansaiDetail(id).then(res=>{
				if(res.code == '0'){
					this.baomingcansai = res.data
					this.editForm.baomingbianhao = this.baomingcansai.baomingbianhao;
					this.editForm.jingsaibianhao = this.baomingcansai.jingsaibianhao;
					this.editForm.jingsaimingcheng = this.baomingcansai.jingsaimingcheng;
					this.editForm.jingsaileixing = this.baomingcansai.jingsaileixing;
					this.editForm.zhidaolaoshi = this.baomingcansai.zhidaolaoshi;
					this.editForm.tuanduibianhao = this.baomingcansai.tuanduibianhao;
					this.editForm.tuanduimingcheng = this.baomingcansai.tuanduimingcheng;
					this.editForm.tuanduirenshu = this.baomingcansai.tuanduirenshu;
					this.editForm.xuexiaoxinxi = this.baomingcansai.xuexiaoxinxi;
					this.editForm.zuopinmingcheng = this.baomingcansai.zuopinmingcheng;
				
					this.editForm.zhidaolaoshi= this.userInfo.gonghao;
					
					
				}
			})
		},


		getAlljingsaixinxi(){jingsaixinxiAllList().then(res=>{if(res.code == '0'){this.jingsaixinxis = res.data}})},
		//qixuzhijixsuan3
		//xia2sui1
		// 编辑、增加页面保存方法
		submitForm(editData) {
			
			this.$refs[editData].validate(valid => {
				if (valid) {
					if(this.editForm.id){
						
					}else {
						zuopinpingfenSave(this.editForm).then(res => {
							if (res.code == '0') {

								let id = this.$route.params.id;
								baomingcansaiDelete(id).then(res => {
									if (res.code == '0') {
										// this.$message({
										// 	type: 'success',
										// 	message: '添加成功!'
										// })
										this.getdata(this.formInline)
									}
								})

								let jingsaibianhao = this.editForm.jingsaibianhao;
								this.jingsaixinxis.forEach(item=>{
									if(item.jingsaibianhao == jingsaibianhao){

										this.jingsaixinxi = item;
									}
								})
								// this.jingsaixinxi.issh=0;
								this.jingsaixinxi.cansairenshu = parseInt(this.jingsaixinxi.cansairenshu)+parseInt(this.editForm.tuanduirenshu);
								jingsaixinxiEdit(this.jingsaixinxi).then(res => {
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
