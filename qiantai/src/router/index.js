/*
 * @Description: 路由配置
 * @Author: hai-27
 * @Date: 2020-02-07 16:23:00
 * @LastEditors: hai-27
 * @LastEditTime: 2020-02-27 13:58:48
 */
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const routes = [

  {
    path: '/',
    name: 'Home',
    component: () => import('../views/Home.vue')
  },
  {
    path: '/collect',
    name: 'Collect',
    component: () => import('../views/Collect.vue'),
    meta: {
      requireAuth: true // 需要验证登录状态
    }
  },
  {
    path: '/error',
    name: 'Error',
    component: () => import('../components/Error.vue')
  },
  {
    path: '/xinwentongzhidetail/:id',
    name: 'xinwentongzhidetail',
    component: () => import('../views/xinwentongzhidetail.vue')
  },
  {
    path: '/xinwentongzhi/:lb',
    name: 'xinwentongzhi',
    component: () => import('../views/xinwentongzhi.vue')
  },
  
  {
path: '/daxueshengdetail/:id',
name: 'daxueshengdetail',
component: () => import('../views/daxueshengdetail.vue')
},
{
path: '/jiarutuanduidetail/:id',
name: 'jiarutuanduidetail',
component: () => import('../views/jiarutuanduidetail.vue')
},
{
path: '/jiarutuanduiadd/:id',
name: 'jiarutuanduiadd',
component: () => import('../views/jiarutuanduiadd.vue'),
meta: {
requireAuth: true // 需要验证登录状态
}
},
{
path: '/tuanduifuzerendetail/:id',
name: 'tuanduifuzerendetail',
component: () => import('../views/tuanduifuzerendetail.vue')
},
{
path: '/tuanduifuzerenlisttp',
name: 'tuanduifuzerenlisttp',
component: () => import('../views/tuanduifuzerenlisttp.vue')
},{
path: '/jingsaixinxi',
name: 'jingsaixinxi',
component: () => import('../views/jingsaixinxi.vue')
},
{
path: '/baomingcansaidetail/:id',
name: 'baomingcansaidetail',
component: () => import('../views/baomingcansaidetail.vue')
},
{
path: '/baomingcansaiadd/:id',
name: 'baomingcansaiadd',
component: () => import('../views/baomingcansaiadd.vue'),
meta: {
requireAuth: true // 需要验证登录状态
}
},
{
path: '/jingsaixinxidetail/:id',
name: 'jingsaixinxidetail',
component: () => import('../views/jingsaixinxidetail.vue')
},
{
path: '/jingsaixinxilisttp',
name: 'jingsaixinxilisttp',
component: () => import('../views/jingsaixinxilisttp.vue')
},
{
path: '/shenqingtuanduidetail/:id',
name: 'shenqingtuanduidetail',
component: () => import('../views/shenqingtuanduidetail.vue')
},

  
  {
    path: '/liuyan',
    name: 'Liuyan',
    component: () => import('../views/Liuyan.vue')
  },


]

const router = new Router({
  // base: '/dist',
  // mode: 'history',
  routes
})

/* 由于Vue-router在3.1之后把$router.push()方法改为了Promise。所以假如没有回调函数，错误信息就会交给全局的路由错误处理。
vue-router先报了一个Uncaught(in promise)的错误(因为push没加回调) ，然后再点击路由的时候才会触发NavigationDuplicated的错误(路由出现的错误，全局错误处理打印了出来)。*/
// 禁止全局路由错误处理打印
const originalPush = Router.prototype.push;
Router.prototype.push = function push (location, onResolve, onReject) {
  if (onResolve || onReject)
    return originalPush.call(this, location, onResolve, onReject)
  return originalPush.call(this, location).catch(err => err)
}

export default router
