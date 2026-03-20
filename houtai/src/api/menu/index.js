import request from '@/utils/request';

export function useMenuApi() {
	return {
		getMenuGuanliyuan: (params) => {
			// 本地数据，路径：`/public/xxx.json`
			return request({
				url: './guanliyuan.json',
				method: 'get',
				params,
			});

		},
		getMenuDaxuesheng: (params) => {
			return request({
				url: './daxuesheng.json',
				method: 'get',
				params,
			});
		},getMenuTuanduifuzeren: (params) => {
			return request({
				url: './tuanduifuzeren.json',
				method: 'get',
				params,
			});
		},getMenuZhidaojiaoshi: (params) => {
			return request({
				url: './zhidaojiaoshi.json',
				method: 'get',
				params,
			});
		},
		
	};
}
