<template>
	<div>
		<el-form :inline="true" :model="formInline" class="user-search">
			<el-form-item label=""><el-input size="small" v-model="formInline.baomingbianhao" placeholder="输入报名编号"></el-input></el-form-item>			<el-form-item label=""><el-input size="small" v-model="formInline.jingsaibianhao" placeholder="输入竞赛编号"></el-input></el-form-item>			
			<el-form-item>
				<el-button size="small" type="warning" icon="el-icon-search" @click="search">搜索</el-button>
				
				<el-button size="small" type="success" icon="el-icon-s-data" @click="daochu()" v-if="userInfo.roles[0] == 'guanliyuan'">导出</el-button>
				<el-button size="small" type="danger" icon="el-icon-delete" @click="handleDeleteList()" v-if="userInfo.roles[0] == 'guanliyuan'">批量删除</el-button>
				
			</el-form-item>
			<el-form-item>
				<el-upload class="upload-demo" style="float: left; padding-right: 10px;" action='http://localhost:9999/zuopinpingfen/upload' :headers='headers' :show-file-list='false' :on-success='handleDaoruSuccess' :before-upload='beforeDaoruUpload' ><el-button size="small" type="info" icon="el-icon-sell" v-if="userInfo.roles[0] == 'guanliyuan'">导入</el-button></el-upload>
			</el-form-item>
		</el-form>
		
		<el-table size="small" :data="listData" highlight-current-row v-loading="loading" border element-loading-text="拼命加载中" @selection-change="handleSelectionChange">
			<el-table-column align="center" type="selection" width="60"></el-table-column>
			<el-table-column sortable prop="baomingbianhao" label="报名编号"></el-table-column>			<el-table-column sortable prop="jingsaibianhao" label="竞赛编号"></el-table-column>			<el-table-column sortable prop="jingsaimingcheng" label="竞赛名称"></el-table-column>			<el-table-column sortable prop="jingsaileixing" label="竞赛类型"></el-table-column>			<el-table-column sortable prop="zhidaolaoshi" label="指导老师"></el-table-column>			<el-table-column sortable prop="tuanduibianhao" label="团队编号"></el-table-column>			<el-table-column sortable prop="tuanduimingcheng" label="团队名称"></el-table-column>			<el-table-column sortable prop="tuanduirenshu" label="团队人数"></el-table-column>			<el-table-column sortable prop="xuexiaoxinxi" label="学校信息"></el-table-column>			<el-table-column sortable prop="zuopinmingcheng" label="作品名称"></el-table-column>			<el-table-column sortable prop="zuopinfenshu" label="作品分数"></el-table-column>			
			<el-table-column sortable prop="addtime" label="添加时间" width="160">
				<template slot-scope="scope">
					<div>{{scope.row.addtime|datePipe('yyyy-MM-dd hh:mm:ss')}}</div>
				</template>
			</el-table-column>
			
			<el-table-column align="center" label="操作" min-width="160">
				<template slot-scope="scope">
					
					<!--liangbuan-->
					<el-button size="mini" type="success" @click="handleEdit(scope.$index, scope.row)" v-if="userInfo.roles[0] == 'guanliyuan'  || userInfo.roles[0] == 'zhidaojiaoshi' || userInfo.roles[0] == 'tuanduifuzeren'">编辑</el-button>
					<el-button size="mini" type="danger" @click="deleteZuopinpingfen(scope.$index, scope.row)" v-if="userInfo.roles[0] == 'guanliyuan'  || userInfo.roles[0] == 'zhidaojiaoshi' || userInfo.roles[0] == 'tuanduifuzeren'">删除</el-button>
					<el-button size='mini' type='primary' @click="handleDetail(scope.$index, scope.row)">详细</el-button>
					<!--zhixfuanniu-->
					
				</template>
			</el-table-column>
		</el-table>
		<Pagination v-bind:child-msg="pageparm" @callFather="callFather"></Pagination>
		
		<el-dialog :title="title" :visible.sync="editFormVisible" width="30%" @click="closeDialog">
			<el-form label-width="120px" :model="editForm" :rules="rules" ref="editForm">
				<el-form-item  label="报名编号" prop="baomingbianhao"><el-input size="small" v-model="editForm.baomingbianhao" auto-complete="off" placeholder="请输入报名编号" style='width:50%' disabled></el-input></el-form-item>		<el-form-item  label="竞赛编号" prop="jingsaibianhao"><el-input size="small" v-model="editForm.jingsaibianhao" auto-complete="off" placeholder="请输入竞赛编号" style='width:50%' disabled></el-input></el-form-item>		<el-form-item  label="竞赛名称" prop="jingsaimingcheng"><el-input size="small" v-model="editForm.jingsaimingcheng" auto-complete="off" placeholder="请输入竞赛名称" style='width:50%' disabled></el-input></el-form-item>		<el-form-item  label="竞赛类型" prop="jingsaileixing"><el-input size="small" v-model="editForm.jingsaileixing" auto-complete="off" placeholder="请输入竞赛类型" style='width:50%' disabled></el-input></el-form-item>		<el-form-item  label="指导老师" prop="zhidaolaoshi"><el-input size="small" v-model="editForm.zhidaolaoshi" auto-complete="off" placeholder="请输入指导老师" style='width:50%' disabled></el-input></el-form-item>		<el-form-item  label="团队编号" prop="tuanduibianhao"><el-input size="small" v-model="editForm.tuanduibianhao" auto-complete="off" placeholder="请输入团队编号" style='width:50%' disabled></el-input></el-form-item>		<el-form-item  label="团队名称" prop="tuanduimingcheng"><el-input size="small" v-model="editForm.tuanduimingcheng" auto-complete="off" placeholder="请输入团队名称" style='width:50%' disabled></el-input></el-form-item>		<el-form-item  label="团队人数" prop="tuanduirenshu"><el-input size="small" v-model="editForm.tuanduirenshu" auto-complete="off" placeholder="请输入团队人数" style='width:50%' disabled></el-input></el-form-item>		<el-form-item  label="学校信息" prop="xuexiaoxinxi"><el-input size="small" v-model="editForm.xuexiaoxinxi" auto-complete="off" placeholder="请输入学校信息" style='width:50%' disabled></el-input></el-form-item>		<el-form-item  label="作品名称" prop="zuopinmingcheng"><el-input size="small" v-model="editForm.zuopinmingcheng" auto-complete="off" placeholder="请输入作品名称" style='width:50%' disabled></el-input></el-form-item>		<el-form-item  label="作品分数" prop="zuopinfenshu"><el-input size="small" v-model="editForm.zuopinfenshu" auto-complete="off" placeholder="请输入作品分数" style='width:50%'></el-input></el-form-item>		
				
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button size="small" @click="closeDialog">取消</el-button>
				<el-button size="small" type="primary" :loading="loading" class="title" @click="submitForm('editForm')">保存</el-button>
			</div>
		</el-dialog>
			
			
			<el-dialog :title="title" :visible.sync="detailFormVisible" width="60%" @click="closeDialogdetail">
			<table width="100%" border="0"  align="center" cellpadding="3" cellspacing="1" class="table table-striped table-bordered table-hover">
								<tr>
									<td width='11%' height='40px'>报名编号：</td><td width='39%'>{{editForm.baomingbianhao}}</td>     <td width='11%' height='40px'>竞赛编号：</td><td width='39%'>{{editForm.jingsaibianhao}}</td></tr><tr>     <td width='11%' height='40px'>竞赛名称：</td><td width='39%'>{{editForm.jingsaimingcheng}}</td>     <td width='11%' height='40px'>竞赛类型：</td><td width='39%'>{{editForm.jingsaileixing}}</td></tr><tr>     <td width='11%' height='40px'>指导老师：</td><td width='39%'>{{editForm.zhidaolaoshi}}</td>     <td width='11%' height='40px'>团队编号：</td><td width='39%'>{{editForm.tuanduibianhao}}</td></tr><tr>     <td width='11%' height='40px'>团队名称：</td><td width='39%'>{{editForm.tuanduimingcheng}}</td>     <td width='11%' height='40px'>团队人数：</td><td width='39%'>{{editForm.tuanduirenshu}}</td></tr><tr>     <td width='11%' height='40px'>学校信息：</td><td width='39%'>{{editForm.xuexiaoxinxi}}</td>     <td width='11%' height='40px'>作品名称：</td><td width='39%'>{{editForm.zuopinmingcheng}}</td></tr><tr>     <td width='11%' height='40px'>作品分数：</td><td width='39%'>{{editForm.zuopinfenshu}}</td>     <td>&nbsp;</td><td>&nbsp;</td></tr>
							</table>
			


			<div slot="footer" class="dialog-footer">
				<el-button size="small" @click="closeDialogdetail">关闭</el-button>

			</div>
		</el-dialog>
			<!--zhixfudhk-->
	</div>
</template>

<script>
import Pagination from "@/layout/pagination/Pagination";
import { zuopinpingfenList, zuopinpingfenSave, zuopinpingfenDelete,zuopinpingfenEdit,zuopinpingfenDeleteList } from '@/api/zuopinpingfen/zuopinpingfenApi';


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
			detailFormVisible: false,
			
			
			
			editForm: {
			},
			user:[],
			username:'',
			cx:'',
			
			rules: {
				
			},
			
			formInline: {
				page: 1,
				limit: 10,
			},
			
			
			listData: [],
			zuopinpingfens:[],
			
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
		if(this.userInfo.roles[0] == 'zhidaojiaoshi'){this.formInline.zhidaolaoshi= this.username;}		if(this.userInfo.roles[0] == 'tuanduifuzeren'){this.formInline.tuanduibianhao= this.username;}		
		this.getdata(this.formInline)
		
	},
	methods: {
		
		//zxhifuff
		handleShenheList(){
				const zuopinpingfens = this.zuopinpingfens;
				if(zuopinpingfens.length == 0){
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
						zuopinpingfens.forEach(item=> {
							let data = {...item, issh: 1}
							zuopinpingfenEdit(data).then(res => {})
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
			zuopinpingfenList(parameter)
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
				this.title = '修改作品评分'
				this.editForm = row
			} else {
				this.title = '添加作品评分'
				this.editForm = {}
				this.editForm.zhidaolaoshi= this.username;
				
				//zixdonxgbianxhao1
			}
		},
		daochu(){
			axios.get('/zuopinpingfen/getExcel', {
				responseType: 'blob',
				headers: { token: Session.get("token"), 'Content-Type': 'application/x-download' }
			}).then((res) => {
				if (res.status === 200) {
					var a = document.createElement('a')
					var blob = new Blob([res.data], { type: 'application/vnd.ms-excel' })
					var href = window.URL.createObjectURL(blob)
					a.href = href
					a.download = '作品评分.xlsx'
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
		
		handleDetail: function (index, row) {
			this.detailFormVisible = true
			if (row != undefined && row != 'undefined') {
				this.title = '作品评分详细'
				this.editForm = row
			}
			
		},
		closeDialogdetail() {
				this.getdata(this.formInline)
				this.detailFormVisible = false
			},
		//liangbuedit
		// 编辑、增加页面保存方法
		submitForm(editData) {
			
			this.$refs[editData].validate(valid => {
				if (valid) {
					if(this.editForm.id){
						zuopinpingfenEdit(this.editForm).then(res => {
							this.editFormVisible = false
							
							this.loading = false
							if (res.code == '0') {
								this.getdata(this.formInline)
								this.$message({
									type: 'success',
									message: '作品评分修改成功！'
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
						zuopinpingfenSave(this.editForm).then(res => {
							this.editFormVisible = false
							this.loading = false
							if (res.code == '0') {
								
								this.getdata(this.formInline)
								this.$message({
									type: 'success',
									message: '作品评分添加成功！'
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
		deleteZuopinpingfen(index, row) {
			this.$confirm('确定要删除吗?', '信息', {
				confirmButtonText: '确定',
				cancelButtonText: '取消',
				type: 'warning'
			}).then(() => {
				zuopinpingfenDelete(row.id).then(res => {
					if (res.code == '0') {
						this.$message({
								type: 'success',
							message: '作品评分已删除!'
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
			this.zuopinpingfens = val;
		},
		handleDeleteList(){
			const zuopinpingfens = this.zuopinpingfens;
			if(zuopinpingfens.length == 0){
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
					zuopinpingfenDeleteList(zuopinpingfens).then(res => {
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
		//xuaxnzeshxifou
		
		
		
		
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
