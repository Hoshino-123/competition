import request from '@/utils/request';

export const login =(params) => {
	if(params.role == '管理员'){return request({url: '/guanliyuan/login',method: 'post',data: {yonghuming:params.username,mima:params.password},});}
	else if(params.role == '大学生'){return request({url: '/daxuesheng/login',method: 'post',data: {xuehao:params.username,mima:params.password},});}else if(params.role == '团队负责人'){return request({url: '/tuanduifuzeren/login',method: 'post',data: {tuanduibianhao:params.username,mima:params.password},});}else if(params.role == '指导教师'){return request({url: '/zhidaojiaoshi/login',method: 'post',data: {gonghao:params.username,mima:params.password},});}
	
	
}
export const register =(params) => {return request({url: '/zhidaojiaoshi/register',method: 'post',data: {gonghao:params.username,mima:params.password},})}