<template>
	<div class="home">
		<el-row :gutter="15">
			<el-col :md="24" :lg="12" :xl="12" class="home-lg">
				<el-card shadow="hover">
					<div slot="header">
						<span>作品分数统计（饼状图）</span>
					</div>
					<div class="charts">
						<div class="charts-left mr7">
							<div id="ref1" class="h100"></div>
						</div>
					</div>
				</el-card>
			</el-col>
			<el-col :md="24" :lg="12" :xl="12" class="home-lg">
				<el-card shadow="hover">
					<div slot="header">
						<span>作品分数统计（柱形图）</span>
					</div>
					<div class="charts">
						<div class="charts-left mr7">
							<div id="ref2" class="h100"></div>
						</div>
					</div>
				</el-card>
			</el-col>
			<el-col :md="24" :lg="12" :xl="12" class="home-lg">
				<el-card shadow="hover">
					<div slot="header">
						<span>竞赛人数统计（柱形图）</span>
					</div>
					<div class="charts">
						<div class="charts-left mr7">
							<div id="ref3" class="h100"></div>
						</div>
					</div>
				</el-card>
			</el-col>
			<el-col :md="24" :lg="12" :xl="12" class="home-lg">
				<el-card shadow="hover">
					<div slot="header">
						<span>竞赛人数统计（柱形图）</span>
					</div>
					<div class="charts">
						<div class="charts-left mr7">
							<div id="ref4" class="h100"></div>
						</div>
					</div>
				</el-card>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	import * as echarts from "echarts";
	import Pagination from "@/layout/pagination/Pagination";
	import {Session} from "@/utils/storage";
	import {zuopinpingfen_tj_zuopinmingcheng} from "@/api/zuopinpingfen/zuopinpingfenApi";
	import {jingsaixinxi_tj_cansairenshu} from "@/api/jingsaixinxi/jingsaixinxiApi";

	export default {
		name: 'user',
		data() {
			return {
				loading: false, //是显示加载
				user:[],
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
			this.initChart();
		},
		methods: {
			initChart() {
				zuopinpingfen_tj_zuopinmingcheng().then(res=>{
					let data = res.data
					let value = []
					data.forEach(item=>{
						value.push({name:item.aa,value:item.bb})
					})
					let xData = []
					let yData = []
					data.forEach(item=>{
						xData.push(item.aa)
						yData.push(item.bb)
					})
					let chart = echarts.init(document.getElementById("ref1"));
					const option = {
						title: {
							text: '作品分数统计',
							left: 'center'
						},
						tooltip: {
							trigger: 'item'
						},
						legend: {
							orient: 'vertical',
							left: 'left'
						},
						series: [
							{
								name: '作品分数',
								type: 'pie',
								radius: '50%',
								data: value,
								emphasis: {
									itemStyle: {
										shadowBlur: 10,
										shadowOffsetX: 0,
										shadowColor: 'rgba(0, 0, 0, 0.5)'
									}
								}
							}
						]
					};
					chart.setOption(option);
					window.addEventListener('resize', () => {
						chart.resize();
					});
					let chart2 = echarts.init(document.getElementById("ref2"));
					const option2 = {
						title: {
							text: '作品分数统计',
							left: 'center'
						},
						grid: {
							left: '3%',
							right: '4%',
							bottom: '3%',
							containLabel: true
						},
						tooltip: {
							trigger: 'axis',
							axisPointer: {
								type: 'shadow'
							}
						},
						xAxis: {
							type: 'category',
							data: xData
						},
						yAxis: {
							type: 'value'
						},
						series: [
							{
								type: 'bar',
								data: yData,
							}
						]
					};
					chart2.setOption(option2);
					window.addEventListener('resize', () => {
						chart2.resize();
					});
				})

				jingsaixinxi_tj_cansairenshu().then(res=>{
					let data = res.data
					let value = []
					data.forEach(item=>{
						value.push({name:item.aa,value:item.bb})
					})
					let xData = []
					let yData = []
					data.forEach(item=>{
						xData.push(item.aa)
						yData.push(item.bb)
					})
					let chart3 = echarts.init(document.getElementById("ref3"));
					const option3 = {
						title: {
							text: '竞赛人数统计',
							left: 'center'
						},
						tooltip: {
							trigger: 'item'
						},
						legend: {
							orient: 'vertical',
							left: 'left'
						},
						series: [
							{
								name: '竞赛人数',
								type: 'pie',
								radius: '50%',
								data: value,
								emphasis: {
									itemStyle: {
										shadowBlur: 10,
										shadowOffsetX: 0,
										shadowColor: 'rgba(0, 0, 0, 0.5)'
									}
								}
							}
						]
					};
					chart3.setOption(option3);
					window.addEventListener('resize', () => {
						chart3.resize();
					});
					let chart4 = echarts.init(document.getElementById("ref4"));
					const option4 = {
						title: {
							text: '竞赛人数统计',
							left: 'center'
						},
						grid: {
							left: '3%',
							right: '4%',
							bottom: '3%',
							containLabel: true
						},
						tooltip: {
							trigger: 'axis',
							axisPointer: {
								type: 'shadow'
							}
						},
						xAxis: {
							type: 'category',
							data: xData
						},
						yAxis: {
							type: 'value'
						},
						series: [
							{
								type: 'bar',
								data: yData,
							}
						]
					};
					chart4.setOption(option4);
					window.addEventListener('resize', () => {
						chart4.resize();
					});
				})
			},
// initChart2() {
//
// 		},

		},
	};
</script>

<style scoped lang="scss">
	@import './index.scss';
</style>
