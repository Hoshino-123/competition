<template>
	<div>
		<el-form :inline="true" :model="formInline" class="user-search">
			<el-form-item label=""><el-input size="small" v-model="formInline.tuanduimingcheng" placeholder="输入团队名称"></el-input></el-form-item>
			<el-form-item   prop="tuanduileixing"><el-select size="small" v-model="formInline.tuanduileixing" placeholder="请选择"><el-option selected label="请选择" value=""></el-option><el-option v-for="item in jingsaileixings" :key="item.leixing" :label="item.leixing" :value="item.leixing"></el-option></el-select></el-form-item>
			
			<el-form-item>
				<el-button size="small" type="warning" icon="el-icon-search" @click="search">搜索</el-button>
				<el-button size="small" type="primary" icon="el-icon-plus" @click="handleEdit()" >添加</el-button>
				<el-button size="small" type="success" icon="el-icon-s-data" @click="daochu()" v-if="userInfo.roles[0] == 'guanliyuan'">导出</el-button>
				<el-button size="small" type="danger" icon="el-icon-delete" @click="handleDeleteList()" v-if="userInfo.roles[0] == 'guanliyuan'">批量删除</el-button>
				
			</el-form-item>
			<el-form-item>
				<el-upload class="upload-demo" style="float: left; padding-right: 10px;" action='http://localhost:9999/shenqingtuandui/upload' :headers='headers' :show-file-list='false' :on-success='handleDaoruSuccess' :before-upload='beforeDaoruUpload' ><el-button size="small" type="info" icon="el-icon-sell" v-if="userInfo.roles[0] == 'guanliyuan'">导入</el-button></el-upload>
			</el-form-item>
		</el-form>
		
		<el-table size="small" :data="listData" highlight-current-row v-loading="loading" border element-loading-text="拼命加载中" @selection-change="handleSelectionChange">
			<el-table-column align="center" type="selection" width="60"></el-table-column>
			<el-table-column sortable prop="tuanduibianhao" label="团队编号"></el-table-column>
			<el-table-column sortable prop="tuanduimingcheng" label="团队名称"></el-table-column>
			<el-table-column sortable prop="tuanduileixing" label="团队类型"></el-table-column>
			<el-table-column sortable prop="tuanduidizhi" label="团队地址"></el-table-column>
			<el-table-column sortable prop="xuehao" label="学号"></el-table-column>
			<el-table-column sortable prop="xuexiaoxinxi" label="学校信息"></el-table-column>
			<el-table-column sortable prop="lianxidianhua" label="联系电话"></el-table-column>
			<el-table-column sortable prop="issh" label="是否审核"></el-table-column>
			<el-table-column sortable prop="shhf" label="审核回复"></el-table-column>
			
			<el-table-column sortable prop="addtime" label="添加时间" width="160">
				<template slot-scope="scope">
					<div>{{scope.row.addtime|datePipe('yyyy-MM-dd hh:mm:ss')}}</div>
				</template>
			</el-table-column>
			
			<el-table-column align="center" label="操作" min-width="160">
				<template slot-scope="scope">
					<el-button size='mini' type='primary' @click='dd(scope.$index, scope.row)' v-if="userInfo.roles[0] == 'guanliyuan'">团队负责人</el-button>
					<el-button size='mini' type='primary' @click='hsgshenhe(scope.$index, scope.row)' v-if="userInfo.roles[0] == 'guanliyuan'">审核</el-button>
					<!--liangbuan-->
					<el-button size="mini" type="success" @click="handleEdit(scope.$index, scope.row)" v-if="userInfo.roles[0] == 'guanliyuan'  || userInfo.roles[0] == 'daxuesheng'">编辑</el-button>
					<el-button size="mini" type="danger" @click="deleteShenqingtuandui(scope.$index, scope.row)" v-if="userInfo.roles[0] == 'guanliyuan'  || userInfo.roles[0] == 'daxuesheng'">删除</el-button>
					<el-button size='mini' type='primary' @click="handleDetail(scope.$index, scope.row)">详细</el-button>
					<!--zhixfuanniu-->
					
				</template>
			</el-table-column>
		</el-table>
		<Pagination v-bind:child-msg="pageparm" @callFather="callFather"></Pagination>
		
		<el-dialog :title="title" :visible.sync="editFormVisible" width="30%" @click="closeDialog">
			<el-form label-width="120px" :model="editForm" :rules="rules" ref="editForm">
				<el-form-item  label="团队编号" prop="tuanduibianhao"><el-input size="small" v-model="editForm.tuanduibianhao" auto-complete="off" placeholder="请输入团队编号" style='width:50%'></el-input></el-form-item>
		<el-form-item  label="团队名称" prop="tuanduimingcheng"><el-input size="small" v-model="editForm.tuanduimingcheng" auto-complete="off" placeholder="请输入团队名称" style='width:50%'></el-input></el-form-item>
		<el-form-item  label='团队类型' prop="tuanduileixing"><el-select size="small" v-model="editForm.tuanduileixing" placeholder="请选择"><el-option selected label="请选择" value=""></el-option><el-option v-for="item in jingsaileixings" :key="item.leixing" :label="item.leixing" :value="item.leixing"></el-option></el-select></el-form-item>
		<el-form-item  label="团队介绍" prop="tuanduijieshao"><el-input type="textarea" size="small" v-model="editForm.tuanduijieshao" auto-complete="off" placeholder="请输入团队介绍" style='width:100%'></el-input></el-form-item>
		<el-form-item  label="团队地址" prop="tuanduidizhi"><el-input size="small" v-model="editForm.tuanduidizhi" auto-complete="off" placeholder="请输入团队地址" style='width:50%'></el-input></el-form-item>
		<el-form-item  label="学号" prop="xuehao"><el-input size="small" v-model="editForm.xuehao" auto-complete="off" placeholder="请输入学号" style='width:50%' disabled></el-input></el-form-item>
		<el-form-item  label="学校信息" prop="xuexiaoxinxi"><el-input size="small" v-model="editForm.xuexiaoxinxi" auto-complete="off" placeholder="请输入学校信息" style='width:50%'></el-input></el-form-item>
		<el-form-item  label="联系电话" prop="lianxidianhua"><el-input size="small" v-model="editForm.lianxidianhua" auto-complete="off" placeholder="请输入联系电话" style='width:50%'></el-input></el-form-item>
		
				
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button size="small" @click="closeDialog">取消</el-button>
				<el-button size="small" type="primary" :loading="loading" class="title" @click="submitForm('editForm')">保存</el-button>
			</div>
		</el-dialog>
			<el-dialog :title="titleissh" :visible.sync="isshdialog" width="30%" @click="closeisshdialog">
			<el-form label-width="120px" :model="editForm" :rules="rulesissh" ref="editFormissh">
				<el-form-item label="审核状态" prop="shenqingStatus">
					<el-radio v-model="editForm.issh" label="待审核">待审核</el-radio>
					<el-radio v-model="editForm.issh" label="已通过">已通过</el-radio>
					<el-radio v-model="editForm.issh" label="未通过">未通过</el-radio>
				</el-form-item>
				<el-form-item label="审核回复" prop="shhf"><el-input type="textarea" size="small" v-model="editForm.shhf" auto-complete="off" placeholder="请输入审核回复"></el-input></el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button size="small" @click="closeisshdialog">取消</el-button>
				<el-button size="small" type="primary" :loading="loading" class="title" @click="shenhe('editFormissh')">保存</el-button>
			</div>
		</el-dialog>
			
			<!--xiangxidhk-->
			<!--zhixfudhk-->
	</div>
</template>

<script>
import Pagination from "@/layout/pagination/Pagination";
import { shenqingtuanduiList, shenqingtuanduiSave, shenqingtuanduiDelete,shenqingtuanduiEdit,shenqingtuanduiDeleteList } from '@/api/shenqingtuandui/shenqingtuanduiApi';
import {jingsaileixingAllList,jingsaileixingList} from "@/api/jingsaileixing/jingsaileixingApi";
				

import {Session} from "@/utils/storage";
import axios from 'axios';
export default {
	name: 'user',
	data() {
		return {
			loading: false, //是显示加载
			title: '',
			
			editFormVisible: false, //控制编辑页面显示与隐藏
			payVisible: false,
			tempdingdanxinxi: '',
			id: '',
			radio: '',
			//detaitFormVsisisble
			
			isshdialog: false, 
			
			editForm: {
			},
			user:[],
			username:'',
			cx:'',
			jingsaileixings:[],
				
			rules: {
				tuanduibianhao: [{ required: true, message: '请输入团队编号', trigger: 'blur' },
				],
				tuanduimingcheng: [{ required: true, message: '请输入团队名称', trigger: 'blur' },
				],
				
			},
			rulesissh: {shhf: [{ required: true, message: '请填写回复原因', trigger: 'blur' }],},
			formInline: {
				page: 1,
				limit: 10,
			},
			
			
			listData: [],
			shenqingtuanduis:[],
			
			checkmenu: [],
			pageparm: {
				currentPage: 1,
				pageSize: 10,
				total: 0
			}
		};
	},
	computed:{
		headers(){
			return {"token":Session.get("token")}
		}
	},
	watch: {
			'$route' (to, from) {
				// 路由发生变化页面刷新
				this.$router.go(0);
			}
		},
	components: {
		Pagination
	},
	created() {
		
		if (!Session.get('userInfo')) return false;
		this.userInfo = Session.get('userInfo');
		this.username=localStorage.getItem("username");
		this.cx=localStorage.getItem("cx");
		if(this.userInfo.roles[0] == 'daxuesheng'){this.formInline.xuehao= this.username;}
		
		this.getdata(this.formInline)
		this.getAlljingsaileixing({});
				
	},
	methods: {
		
		//zxhifuff
		handleShenheList(){
				const shenqingtuanduis = this.shenqingtuanduis;
				if(shenqingtuanduis.length == 0){
					this.$message({
						type: 'error',
						message: '请至少选择一项'
					})
				}else {
					this.$confirm('确定要批量审核吗?', '信息', {
						confirmButtonText: '确定',
						cancelButtonText: '取消',
						type: 'warning'
					}).then(() => {
						shenqingtuanduis.forEach(item=> {
							let data = {...item, issh: 1}
							shenqingtuanduiEdit(data).then(res => {})
						})
						this.getdata(this.formInline)
						this.$message({
							type: 'success',
							message: '批量审核成功！'
						})
					}).catch(() => {
						this.$message({
							type: 'info',
							message: '已取消批量审核'
						})
					})
				}
			},
		getdata(parameter) {
			shenqingtuanduiList(parameter)
					.then(res => {
						this.loading = false
						if (res.success == false) {
							this.$message({
								type: 'info',
								message: res.msg
							})
						} else {
							this.listData = res.data.list
							
							
							
							// 分页赋值
							this.pageparm.currentPage = this.formInline.current
							this.pageparm.pageSize = this.formInline.currentNum
							this.pageparm.total = res.data.total
						}
					})
					.catch(err => {
						this.loading = false
						this.$message.error('菜单加载失败，请稍后再试！')
					})
		},
		
		// 分页插件事件
		callFather(parm) {
			this.formInline.current = parm.currentPage
			this.getdata(this.formInline)
		},
		
		// 搜索事件
		search() {
			this.getdata(this.formInline)
		},
		//显示编辑界面
		handleEdit: function (index, row) {
			this.editFormVisible = true
			if (row != undefined && row != 'undefined') {
				this.title = '修改申请团队'
				this.editForm = row
			} else {
				this.title = '添加申请团队'
				this.editForm = {}
				this.editForm.xuehao= this.username;
				this.editForm.xuehao= this.userInfo.xuehao;
				this.editForm.xuexiaoxinxi= this.userInfo.xuexiaoxinxi;
				this.editForm.lianxidianhua= this.userInfo.lianxidianhua;
				
				//zixdonxgbianxhao1
			}
		},
		daochu(){
			axios.get('/shenqingtuandui/getExcel', {
				responseType: 'blob',
				headers: { token: Session.get("token"), 'Content-Type': 'application/x-download' }
			}).then((res) => {
				if (res.status === 200) {
					var a = document.createElement('a')
					var blob = new Blob([res.data], { type: 'application/vnd.ms-excel' })
					var href = window.URL.createObjectURL(blob)
					a.href = href
					a.download = '申请团队.xlsx'
					document.body.appendChild(a)
					a.click() 
					document.body.removeChild(a) 
					window.URL.revokeObjectURL(href)
				}
			}).catch((err) => {
				console.log(err)
			})
		},
		handleDaoruSuccess(res, file) {
				if(res.code == "0") {
					this.$message({
						type: 'success',
						message: '导入成功!'
					})
					this.getdata(this.formInline);
				}
			},
		beforeDaoruUpload(file) {
			const isJPG = file.type === 'application/vnd.ms-excel';
			if (!isJPG) {
				this.$message.error('上传只能是 excel 格式!');
			}
			return isJPG;
		},
		//zixdonxgbianxhao2
		
		handleDetail(index, row) {const w = window.open("about:blank");w.location.href = 'http://localhost:8080/#/shenqingtuanduidetail/'+row.id;},
		//liangbuedit
		// 编辑、增加页面保存方法
		submitForm(editData) {
			
			this.$refs[editData].validate(valid => {
				if (valid) {
					if(this.editForm.id){
						shenqingtuanduiEdit(this.editForm).then(res => {
							this.editFormVisible = false
							
							this.loading = false
							if (res.code == '0') {
								this.getdata(this.formInline)
								this.$message({
									type: 'success',
									message: '申请团队修改成功！'
								})
							} else {
								this.$message({
									type: 'info',
									message: res.msg
								})
							}
						}).catch(err => {
							this.editFormVisible = false
							this.loading = false
							this.getdata(this.formInline)
						if(err){
								this.$message.error(err)
							}else {
								this.$message.error('操作失败，请稍后再试！')
							}
						})
					}else {
						shenqingtuanduiSave(this.editForm).then(res => {
							this.editFormVisible = false
							this.loading = false
							if (res.code == '0') {
								
								this.getdata(this.formInline)
								this.$message({
									type: 'success',
									message: '申请团队添加成功！'
								})
							} else {
								this.$message({
									type: 'info',
									message: res.msg
								})
							}
						}).catch(err => {
							this.editFormVisible = false
							this.loading = false
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
		// 删除公司
		deleteShenqingtuandui(index, row) {
			this.$confirm('确定要删除吗?', '信息', {
				confirmButtonText: '确定',
				cancelButtonText: '取消',
				type: 'warning'
			}).then(() => {
				shenqingtuanduiDelete(row.id).then(res => {
					if (res.code == '0') {
						this.$message({
								type: 'success',
							message: '申请团队已删除!'
						})
						this.getdata(this.formInline)
					} else {
						this.$message({
							type: 'info',
							message: res.msg
						})
					}
				}).catch(err => {
					this.loading = false
					this.$message.error('操作失败，请稍后再试')
				})
			}).catch(() => {
				this.$message({
					type: 'info',
					message: '已取消删除'
				})
			})
		},
		handleSelectionChange(val){
			this.shenqingtuanduis = val;
		},
		handleDeleteList(){
			const shenqingtuanduis = this.shenqingtuanduis;
			if(shenqingtuanduis.length == 0){
				this.$message({
					type: 'error',
					message: '请至少选择一项进行删除'
				})
			}else {
				this.$confirm('确定要批量删除吗?', '信息', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					shenqingtuanduiDeleteList(shenqingtuanduis).then(res => {
						if (res.code == '0') {
							this.$message({
								type: 'success',
								message: '批量删除成功!'
							})
							this.getdata(this.formInline)
						} else {
							this.$message({
								type: 'info',
								message: res.msg
							})
						}
					}).catch(err => {
						this.loading = false
						this.$message.error('操作失败，请稍后再试')
					})
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消删除'
					})
				})
			}
		},

		dd(index,row){
			this.$router.push({
				name:'tuanduifuzerenAdd',
				params:{
					id:row.id
				}
			})
		},
		//xuaxnzeshxifou
		hsgshenhe(index,row){
			this.isshdialog = true
			if (row != undefined && row != 'undefined') {
				this.titleissh = '审核'
				this.editForm = row
			} else {
				this.titleissh = '审核'
				this.editForm = {}
			}
			if(this.userInfo.roles[0] == 'user') {
				this.editForm.userId = this.userInfo.id;
			}
			//this.editForm.type = '审核'
		},
		closeisshdialog() {
			this.getdata(this.formInline)
			this.isshdialog = false
		},
		shenhe(editData) {
			this.$refs[editData].validate(valid => {
				if (valid) {
					if(this.editForm.id){
						shenqingtuanduiEdit(this.editForm).then(res => {
							this.isshdialog = false
							this.loading = false
							if (res.code == '0') {
								this.getdata(this.formInline)
								this.$message({
									type: 'success',
									message: '审核操作成功！'
								})
							} else {
								this.$message({
									type: 'info',
									message: res.msg
								})
							}
						}).catch(err => {
							this.isshdialog = false
							this.loading = false
							this.getdata(this.formInline)
							if(err){
								this.$message.error(err)
							}else {
								this.$message.error('操作失败，请 稍后再试！')
							}
						})
					}else {
						shenqingtuanduiSave(this.editForm).then(res => {
							this.isshdialog = false
							this.loading = false
							if (res.code == '0') {
								this.getdata(this.formInline)
								this.$message({
									type: 'success',
									message: '申请添加成功！'
								})
							} else {
								this.$message({
									type: 'info',
									message: res.msg
								})
							}
						}).catch(err => {
							this.isshdialog = false
							this.loading = false
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
		
		getAlljingsaileixing(parameter){jingsaileixingList(parameter).then(res=>{if(res.code == '0'){this.jingsaileixings = res.data.list}})},
				
		
		//youscwexnjiaxn
		// 关闭编辑、增加弹出框
		closeDialog() {
			this.getdata(this.formInline)
			this.editFormVisible = false
		}
	},
};
</script>

<style scoped lang="scss">
	@import './index.scss';
</style>
