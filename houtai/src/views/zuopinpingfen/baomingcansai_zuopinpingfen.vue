<template>
	<div>
		<el-form :inline="true" :model="formInline" class="user-search">
			<el-form-item label=""><el-input size="small" v-model="formInline.baomingbianhao" placeholder="输入报名编号"></el-input></el-form-item>			<el-form-item label=""><el-input size="small" v-model="formInline.jingsaibianhao" placeholder="输入竞赛编号"></el-input></el-form-item>			
			<el-form-item>
				<el-button size="small" type="primary" icon="el-icon-search" @click="search">搜索</el-button>
			</el-form-item>
		</el-form>
		<el-table size="small" :data="listData" highlight-current-row v-loading="loading" border element-loading-text="拼命加载中" @selection-change="handleSelectionChange">
			<el-table-column align="center" type="selection" width="60"></el-table-column>
			<el-table-column sortable prop="baomingbianhao" label="报名编号"></el-table-column>			<el-table-column sortable prop="jingsaibianhao" label="竞赛编号"></el-table-column>			<el-table-column sortable prop="jingsaimingcheng" label="竞赛名称"></el-table-column>			<el-table-column sortable prop="jingsaileixing" label="竞赛类型"></el-table-column>			<el-table-column sortable prop="jingsaididian" label="竞赛地点"></el-table-column>			<el-table-column sortable prop="zhidaolaoshi" label="指导老师"></el-table-column>			<el-table-column sortable prop="tuanduibianhao" label="团队编号"></el-table-column>			<el-table-column sortable prop="tuanduimingcheng" label="团队名称"></el-table-column>			<el-table-column sortable prop="tuanduirenshu" label="团队人数"></el-table-column>			<el-table-column sortable prop="xuexiaoxinxi" label="学校信息"></el-table-column>			<el-table-column sortable prop="zuopinmingcheng" label="作品名称"></el-table-column>			
			<el-table-column sortable prop="addtime" label="添加时间" width="160">
				<template slot-scope="scope">
					<div>{{scope.row.addtime|datePipe('yyyy-MM-dd hh:mm:ss')}}</div>
				</template>
			</el-table-column>
			
			<el-table-column align="center" label="操作" min-width="160">
				<template slot-scope="scope">
					<el-button size="mini"  type="danger" @click="addSalary(scope.$index, scope.row)">添加作品评分</el-button>
				</template>
			</el-table-column>
		</el-table>
		<Pagination v-bind:child-msg="pageparm" @callFather="callFather"></Pagination>
		
	</div>
</template>

<script>
import Pagination from "@/layout/pagination/Pagination";
import { baomingcansaiList, baomingcansaiSave, baomingcansaiDelete,baomingcansaiEdit,baomingcansaiDeleteList } from '@/api/baomingcansai/baomingcansaiApi';


import {Session} from "@/utils/storage";
export default {
	name: 'user',
	data() {
		return {
			loading: false, //是显示加载
			title: '',
			editFormVisible: false, //控制编辑页面显示与隐藏
			
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
			baomingcansais:[],
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
		if(this.userInfo.roles[0] == 'tuanduifuzeren'){this.formInline.tuanduibianhao= this.username;}		
		this.getdata(this.formInline)
		
	},
	methods: {
		getdata(parameter) {
			baomingcansaiList(parameter)
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
		//qixuzhijixsuan3
		// 分页插件事件
		callFather(parm) {
			this.formInline.current = parm.currentPage
			this.getdata(this.formInline)
		},
		//xia2sui1
		// 搜索事件
		search() {
			this.getdata(this.formInline)
		},
		//显示编辑界面
		
		addSalary(index,row){
				this.$router.push({
					name:'zuopinpingfenAdd',
					params:{
						id:row.id
					}
				})
			},
		// 编辑、增加页面保存方法
		
		// 删除公司
		
	
		
		
		
		
		// 关闭编辑、增加弹出框
		
	},
};
</script>

<style scoped lang="scss">
	@import './index.scss';
</style>
