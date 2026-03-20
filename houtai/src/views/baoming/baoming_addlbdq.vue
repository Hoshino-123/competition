<template>
	<div class="personal">
		<el-form label-width="100px" ref="editForm" :model="editForm" :rules="rules">
			<el-form-item  label="报名编号" prop="baomingbianhao"><el-input size="small" v-model="editForm.baomingbianhao" auto-complete="off" placeholder="请输入报名编号" style='width:50%'></el-input></el-form-item>
		<el-form-item  label="竞赛编号" prop="jingsaibianhao"><el-input size="small" v-model="editForm.jingsaibianhao" auto-complete="off" placeholder="请输入竞赛编号" style='width:50%' disabled></el-input></el-form-item>
		<el-form-item  label="竞赛名称" prop="jingsaimingcheng"><el-input size="small" v-model="editForm.jingsaimingcheng" auto-complete="off" placeholder="请输入竞赛名称" style='width:50%' disabled></el-input></el-form-item>
		<el-form-item  label="竞赛类型" prop="jingsaileixing"><el-input size="small" v-model="editForm.jingsaileixing" auto-complete="off" placeholder="请输入竞赛类型" style='width:50%' disabled></el-input></el-form-item>
		<el-form-item  label="竞赛地点" prop="jingsaididian"><el-input size="small" v-model="editForm.jingsaididian" auto-complete="off" placeholder="请输入竞赛地点" style='width:50%' disabled></el-input></el-form-item>
		<el-form-item  label="指导老师" prop="zhidaolaoshi"><el-input size="small" v-model="editForm.zhidaolaoshi" auto-complete="off" placeholder="请输入指导老师" style='width:50%'></el-input></el-form-item>
		<el-form-item  label="团队编号" prop="tuanduibianhao"><el-input size="small" v-model="editForm.tuanduibianhao" auto-complete="off" placeholder="请输入团队编号" style='width:50%' disabled></el-input></el-form-item>
		<el-form-item  label="团队名称" prop="tuanduimingcheng"><el-input size="small" v-model="editForm.tuanduimingcheng" auto-complete="off" placeholder="请输入团队名称" style='width:50%'></el-input></el-form-item>
		<el-form-item  label="团队人数" prop="tuanduirenshu"><el-input size="small" v-model="editForm.tuanduirenshu" auto-complete="off" placeholder="请输入团队人数" style='width:50%'></el-input></el-form-item>
		<el-form-item  label="学校信息" prop="xuexiaoxinxi"><el-input size="small" v-model="editForm.xuexiaoxinxi" auto-complete="off" placeholder="请输入学校信息" style='width:50%'></el-input></el-form-item>
		<el-form-item style='display:none' label="拒绝回复" prop="jujuehuifu"><el-input size="small" v-model="editForm.jujuehuifu" auto-complete="off" placeholder="请输入拒绝回复" style='width:50%'></el-input></el-form-item>
		
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button size="small" type="primary" :loading="loading" class="title" @click="submitForm('editForm')">添加</el-button>
		</div>
	</div>
</template>

<script>
	import {baomingEdit, baomingSave,baomingDetail} from "@/api/baoming/baomingApi";
	import {Session} from "@/utils/storage";
import {jingsaixinxiDetail,jingsaixinxiEdit} from "../../api/jingsaixinxi/jingsaixinxiApi";
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
				jingsaibianhao:'',
				jingsaimingcheng:'',
				jingsaileixing:'',
				jingsaididian:'',
				
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
			jingsaixinxiDetail(id).then(res=>{
				if(res.code == '0'){
					this.jingsaixinxi = res.data
					this.editForm.jingsaibianhao = this.jingsaixinxi.jingsaibianhao;
					this.editForm.jingsaimingcheng = this.jingsaixinxi.jingsaimingcheng;
					this.editForm.jingsaileixing = this.jingsaixinxi.jingsaileixing;
					this.editForm.jingsaididian = this.jingsaixinxi.jingsaididian;
				
					// this.editForm.tuanduibianhao= this.userInfo.;
					this.editForm.baomingbianhao = this.getProjectNum() + Math.floor(Math.random() * 10000);
					this.editForm.tuanduibianhao= this.userInfo.tuanduibianhao;
				this.editForm.tuanduimingcheng= this.userInfo.tuanduimingcheng;
				this.editForm.tuanduirenshu= this.userInfo.tuanduirenshu;
				this.editForm.xuexiaoxinxi= this.userInfo.xuexiaoxinxi;
				
				}
			})
		},
		getProjectNum () {
			const projectTime = new Date() // 当前中国标准时间
			const Year = projectTime.getFullYear() // 获取当前年份 支持IE和火狐浏览器.
			const Month = projectTime.getMonth() + 1 // 获取中国区月份
			const Day = projectTime.getDate() // 获取几号
			var CurrentDate = Year
			if (Month >= 10) { // 判断月份和几号是否大于10或者小于10
				CurrentDate += Month
			} else {
				CurrentDate += '0' + Month
			}
			if (Day >= 10) {
				CurrentDate += Day
			} else {
				CurrentDate += '0' + Day
			}
			return CurrentDate
		},
		//qixuzhijixsuan3
		//xia2sui1
		// 编辑、增加页面保存方法
		submitForm(editData) {
			
			this.$refs[editData].validate(valid => {
				if (valid) {
					if(this.editForm.id){
						
					}else {
						baomingSave(this.editForm).then(res => {
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
		
		//youscwexnjiaxn
		//xiabiaoduan4
	}
};
</script>

<style scoped lang="scss">
	@import './index.scss';
</style>
