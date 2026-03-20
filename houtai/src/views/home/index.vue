<template>
	<div class="home">
		<!-- 用户信息 -->
		<el-row :gutter="15">
			<el-col :md="24" :lg="16" :xl="16" class="mb15">
				<el-card shadow="hover">
					<div slot="header">
						<span>{{ $t('message.card.title1') }}</span>
					</div>
					<div class="user-item">
						<div class="user-item-left">
							<img src="../../../static/images/guanli.jpg" />
						</div>
						<div class="user-item-right overflow">
							<el-row>
								<el-col :span="24" class="right-title mb15 one-text-overflow"
									>{{ currentTime }}，{{ username }}，{{ dailyMessage }}
								</el-col>
								<el-col :span="24">
									<el-col :xs="12" :sm="12" :md="8" class="right-l-v">
										<div class="right-label">昵称：</div>
										<div class="right-value">
											{{ username }}
										</div>
									</el-col>
									<el-col :xs="12" :sm="12" :md="16" class="right-l-v">
										<div class="right-label">身份：</div>
										<div class="right-value">{{cx}}</div>
									</el-col>
								</el-col>
								<el-col :span="24" class="mt5">
									<el-col :xs="12" :sm="12" :md="32" class="right-l-v">
										<div class="right-label one-text-overflow">时间：</div>
										<div class="right-value one-text-overflow">{{ userInfo.time }}</div>
									</el-col>
								</el-col>
							</el-row>
						</div>
					</div>
				</el-card>
			</el-col>
		</el-row>

	</div>
</template>
<script>
import * as echarts from 'echarts';
import Scroll from 'vue-seamless-scroll';
import { CountUp } from 'countup.js';
import { Session } from '@/utils/storage';
import { formatAxis, formatDate } from '@/utils/formatTime';
import { recommendList, chartsRightList, newsInfoList, dailyMessage } from './mock';
export default {
	name: 'home',
	components: { Scroll },
	data() {
		return {
			recommendList,
			userInfo: {},
			username:'',
			cx:'',
			dailyMessage: {},
			charts: {
				theme: '',
				bgColor: '',
			},
			global: {
				homeChartOne: null,
				homeChartTwo: null,
				homeCharThree: null,
				dispose: [null, '', undefined],
			},
		};
	},
	created() {
		this.initUserInfo();
		this.initDailyMessage();
	},
	computed: {
		currentTime() {
			return formatAxis(new Date());
		},
		optionSingleHeight() {
			return {
				singleHeight: 28,
				limitMoveNum: 8,
				waitTime: 2000,
			};
		},
		getUserInfos() {
			return this.$store.state.userInfos.userInfos;
		},
	},
	methods: {
		// 随机语录
		initDailyMessage() {
			this.dailyMessage = dailyMessage[Math.floor(Math.random() * dailyMessage.length)];
		},
		// 初始化登录信息
		initUserInfo() {
			if (!Session.get('userInfo')) return false;
			this.userInfo = Session.get('userInfo');
			this.username=localStorage.getItem("username");
			this.cx=localStorage.getItem("cx");
			this.userInfo.time = formatDate(new Date(this.userInfo.time), 'YYYY-mm-dd HH:MM:SS');
		},
		// 消息通知当前项点击
		onNewsInfoListClick(v) {
			window.open(v.link);
		},
	},
	watch: {
		// 监听 vuex 数据变化
		'$store.state.themeConfig.themeConfig.isIsDark': {
			handler(isIsDark) {
				this.$nextTick(() => {
					this.charts.theme = isIsDark ? 'dark' : '';
					this.charts.bgColor = isIsDark ? 'transparent' : '';
					setTimeout(() => {
						//this.initHomeStock();
					}, 500);
					setTimeout(() => {
						//this.initHomeLaboratory();
					}, 700);
					setTimeout(() => {
						//this.initHomeOvertime();
					}, 1000);
				});
			},
			deep: true,
			immediate: true,
		},
	},
};
</script>

<style scoped lang="scss">
@import './index.scss';
</style>