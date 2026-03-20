<template>
  <div>


    <div class="header" style="position: fixed; opacity: 0.9; top: 0px;">
      <div class="top hidden-sm hidden-xs">
        <div class="wrap">
          <!--          <div class="greet">欢迎来到xxx页面！</div>-->

          <div class="greet">
            <li v-if="!this.$store.getters.getUser" style="float:left">
              <el-button type="text" @click="login" style="color: #fff;
    display: inline-block;
    text-transform: uppercase;
    text-decoration: none;
    background: #dc9a26;
    text-align: center;
    padding: 6px 30px;
    font-weight: bold;">登录
              </el-button>
              <span class="sep">|</span>
              <el-button type="text" @click="register = true" style="color: #fff;
    display: inline-block;
    text-transform: uppercase;
    text-decoration: none;
   background: #dc9a26;
    text-align: center;
    padding: 6px 30px;
    font-weight: bold;">注册
              </el-button>
            </li>
            <li v-else>
              <font color="black"> 欢迎 {{ username }} [{{ cx }}]</font>&nbsp;
              <el-button type="text" @click="logout" style="color: #fff;
    display: inline-block;
    text-transform: uppercase;
    text-decoration: none;
   background: #dc9a26;
    text-align: center;
    padding: 6px 30px;
    font-weight: bold;">退出
              </el-button>
              <router-link :to="`/Collect/`" class="el-button el-button--text" style="color: #fff;
    display: inline-block;
    text-transform: uppercase;
    text-decoration: none;
     background:#dc9a26;
    text-align: center;
    padding: 6px 30px;
    font-weight: bold;">
                我的收藏
              </router-link>
              <el-popover placement="top" width="180" v-model="visible">
                <p>确定退出登录吗？</p>
                <div style="text-align: right; margin: 10px 0 0">
                  <el-button size="mini" type="text" @click="visible = false">取消</el-button>
                  <el-button type="primary" size="mini" @click="logout">确定</el-button>
                </div>
              </el-popover>
            </li>


          </div>



        </div>
      </div>
      <div class="wrap">
        <div class="Logo" style="padding: 12px 0px;">
          <a href="/">
            <img src="./assets/logo.png" style="height: 35px;">
          </a>
        </div>
        <div class="nav hidden-sm header-menu">
          <ul class="menu">
            <li><a href="/" class="menu-item hover" style="height: 20px;">网站首页</a></li>
			
	<li class="menu-item"><a style="height: 20px; ">团队信息</a>
              <ul class="sub-menu">
			  	<li class="menu-item"><router-link :to="`/tuanduifuzerenlisttp/`" >团队信息</router-link></li>
              </ul>
            </li><li class="menu-item"><a style="height: 20px; ">竞赛信息</a>
              <ul class="sub-menu">
			  	<li class="menu-item"><router-link :to="`/jingsaixinxilisttp/`" >竞赛信息</router-link></li> 
			  	<li class="menu-item"><router-link :to="`/jingsaixinxi/`" >竞赛排名</router-link></li>
              </ul>
            </li><li class="menu-item"><a style="height: 20px; ">公告</a>
              <ul class="sub-menu">
			  	<li class="menu-item"><router-link :to="`/xinwentongzhi/公告/`" >公告</router-link></li>
              </ul>
            </li><!--yoxulixuyaxn-->
            <li class="menu-item"><a style="height: 20px; ">在线留言</a>
              <ul class="sub-menu">
                <li class="menu-item"><router-link :to="`/Liuyan/`" >在线留言</router-link></li>
              </ul>
            </li>
          </ul>
        </div>
		
      </div>
    </div>

    <div class="banner" style="margin-top: 110px;">

      <el-carousel style="z-index: -1; height:460px; .el-carousel--horizontal:overflow-x: inherit;">
        <el-carousel-item v-for="item in carouselItems" :key="item.id" style="height:460px">
          <img :src="'/api/'+item.tupian" style="height: 460px; width: 100%"/>
        </el-carousel-item>
      </el-carousel>
    </div>
    <!--大图 -->

    <!-- 登录模块 -->
    <MyLogin></MyLogin>
    <!-- 注册模块 -->
    <MyRegister :register="register" @fromChild="isRegister"></MyRegister>

    <!-- 主要区域容器 -->
    <el-main>
      <keep-alive>
        <router-view></router-view>
      </keep-alive>
    </el-main>

    <div class="footer">
      <div class="wrap">
        <div class="footer1">

        </div>
        <div class="footer2">
          <div class="ft2">
            <ul>
              <li><a href="/">网站首页</a></li>
<!--              <li><a href="/">公司简介</a></li>-->
<!--              <li><a href="/">产品中心</a></li>-->
<!--              <li><a href="/">工程案例</a></li>-->
              <li><a href="/">新闻资讯</a></li>
              <li><a href="/">联系我们</a></li>
            </ul>
            <div>
              <span>咨询：xxxxxxx</span>
              <span>地址：xxxx</span>
            </div>
          </div>
          <div class="ft3">
            <div class="ewm2">
              <img src="./assets/201809141022032910.png">
            </div>
            <div class="lx2">
              <span>热线电话</span>
              <b>xxxxxxxxxx</b>
              <h6>（欢迎您随时来电）</h6>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <div class="dbbox hidden-sm hidden-md hidden-lg">
      <div class="dbs ewm-box">
        <div class="neirong">
          <img src="./assets/201809141022032910.png" alt="">
        </div>
      </div>
     
    </div>


    <div style="width:300px;margin:0 auto; padding:20px 0;">

    </div>


  </div>
</template>

<script>
import {mapActions} from "vuex";
import {mapGetters} from "vuex";

export default {
  beforeUpdate() {
    this.activeIndex = this.$route.path;
  },
  data() {
    return {
      carouselItems: [], // 存储轮播图数据
      activeIndex: "", // 头部导航栏选中的标签
      search: "", // 搜索条件
      register: false, // 是否显示注册组件
      visible: false, // 是否退出登录
      username: '',
      cx: '',
    };
  },
  created() {
    // 获取浏览器localStorage，判断用户是否已经登录
    if (localStorage.getItem("user")) {
      // 如果已经登录，设置vuex登录状态
      this.setUser(JSON.parse(localStorage.getItem("user")));
      this.username = localStorage.getItem("username");
      this.cx = localStorage.getItem("cx");
    }
    this.fetchCarouselData();
    console.log(this.$store.getters.getUser)
  },
  computed: {
    ...mapGetters(["getUser", "getNum"])
  },
  watch: {},
  methods: {
    fetchCarouselData() {
      this.$axios
          .get("/api/front/bianhuantu", {})
          .then(res => {
            let data = res.data.data;
            let filteredData = [];
            data.forEach(item => {
              filteredData.push(item);
            });
            this.carouselItems = filteredData;
          })
          .catch(err => {
            return Promise.reject(err);
          });
    },
    ...mapActions(["setUser", "setShowLogin", "setShoppingCart"]),
    login() {
      // 点击登录按钮, 通过更改vuex的showLogin值显示登录组件
      this.setShowLogin(true);
    },
    hsghoutai() {
      const w = window.open("about:blank");
      w.location.href = 'http://localhost:9999/#/';
    },
    // 退出登录
    logout() {
      this.visible = false;
      // 清空本地登录信息
      localStorage.setItem("user", "");
      // 清空vuex登录信息
      this.setUser("");
      this.notifySucceed("成功退出登录");
    },
    // 接收注册子组件传过来的数据
    isRegister(val) {
      this.register = val;
    },
  }
};
</script>

<style>
@import "assets/css/index2.css";
@import "assets/css/responsive.css";
@import "assets/css.css";
@import "assets/swiper.min.css";
@import "assets/bootstrap.min.css";
@import "assets/font-awesome.min.css";
@import "assets/animate.min.css";
@import "assets/share_style1_32.css";


/*@import "assets/css/style.css";*/
/* 全局CSS */
* {
  padding: 0;
  margin: 0;
  /*border: 0;*/
  list-style: none;
  font-size: 14px;
}

.beijing {
  background: url(./assets/201811261652448300.jpg) no-repeat 50% 50%;
  background-size: cover;
}

.newsline TD {
  LINE-HEIGHT: 25px;
  BORDER-BOTTOM: #c9c9c9 1px dashed;
  text-align: left;
}

.newsline TD A:link {
  COLOR: #000
}

.newsline TD A:visited {
  COLOR: #000
}

.newsline TD A:hover {
  COLOR: #000
}

.newsline TD A:active {
  COLOR: #000
}

#app .el-header {
  padding: 0;
}

#app .el-main {
  min-height: 300px;
  padding: 20px 0;
}

#app .el-footer {
  padding: 0;
}

a,
a:hover {
  text-decoration: none;
}

/* 全局CSS END */

/* 顶部导航栏CSS */
.topbar {
  height: 40px;
  background-color: #3d3d3d;
  margin-bottom: 20px;
}

.topbar .nav {
  width: 1225px;
  margin: 0 auto;
}

.topbar .nav ul {
  float: right;
}

.topbar .nav li {
  float: left;
  height: 40px;
  color: #b0b0b0;
  font-size: 14px;
  text-align: center;
  line-height: 40px;
  margin-left: 20px;
}

.topbar .nav .sep {
  color: #b0b0b0;
  font-size: 12px;
  margin: 0 5px;
}

.topbar .nav li .el-button {
  color: #b0b0b0;
}

.topbar .nav .el-button:hover {
  color: #fff;
}

.topbar .nav li a {
  color: #b0b0b0;
}

.topbar .nav a:hover {
  color: #fff;
}

.topbar .nav .shopCart {
  width: 120px;
  background: #424242;
}

.topbar .nav .shopCart:hover {
  background: #fff;
}

.topbar .nav .shopCart:hover a {
  color: #ff6700;
}

.topbar .nav .shopCart-full {
  width: 120px;
  background: #ff6700;
}

.topbar .nav .shopCart-full a {
  color: white;
}

/* 顶部导航栏CSS END */

/* 顶栏容器CSS */
.el-header .el-menu {
  max-width: 1225px;
  margin: 0 auto;
  background-color: transparent;
}

.el-header .logo {
  /*height: 60px;*/
  width: 189px;
  float: left;
  margin-right: 100px;
}

.el-header .so {
  /*margin-top: 10px;*/
  width: 300px;
  float: right;
}

/* 顶栏容器CSS END */
.header {
  /*padding: 1em 0;*/
}

.w3layouts_logo {
  float: left;
}

.w3layouts_logo a {
  display: block;
  text-decoration: none;
}

h1, h2, h3, h4, h5, h6, a {
  font-family: 'Roboto Condensed', sans-serif;
  margin: 0;
}

.w3layouts_logo a h1 {
  padding-left: 20px;
  padding-top: 20px;
  font-size: 2.8em;
  color: #212121;
  text-transform: uppercase;

  padding-right: 1.67em;
  font-weight: 900;
  padding-bottom: 0.3em;
}

.w3layouts_logo a h1 span {

  color: #FF8D1B;
  font-size: .3em;
  display: block;
  font-weight: 300;
  letter-spacing: 5px;
  text-align: right;
  line-height: 0;
}

.w3_search input[type="text"] {
  outline: none;
  border: none;
  background: #EFEFEF;
  padding: 11px;
  text-align: center;
  font-size: 14px;
  color: #999;
  width: 71%;
  float: left;
  border-top-left-radius: 25px;
  border-bottom-left-radius: 25px;
}

.w3_search input[type="submit"] {
  outline: none;
  border: none;
  background: #212121;
  padding: 10px 0;
  font-size: 14px;
  color: #fff;
  width: 22%;
  border-top-right-radius: 25px;
  border-bottom-right-radius: 25px;
}

.w3l_sign_in_register {
  float: right;
  margin-top: 1em;
  width: 35%;
}

.w3l_sign_in_register ul li a {
  color: #fff;
  display: inline-block;
  text-transform: uppercase;
  text-decoration: none;
  background: #FF8D1B;
  text-align: center;
  padding: 6px 30px;
  font-weight: bold;
}

.fa-phone:before {
  content: "\f095";
}

.navbar-nav > li {
  float: left;
  border-right: 1px dotted #fff;
  width: 9%;
  text-align: center;
}

.navbar-default .navbar-nav > li > a {
  color: #fff;
  text-transform: uppercase;
}

.navbar-default .navbar-nav > li > a {
  color: #777;
}

.navbar-default .navbar-collapse, .navbar-default .navbar-form {
  border-color: #e7e7e7;
}

.navbar-nav > li > a {
  padding-top: 10px;
  padding-bottom: 10px;
  line-height: 20px;
}

.nav > li {
  position: relative;
  display: block;
}

p, ul li, ol li {
  margin: 0;
  font-size: 14px;
}

article, aside, details, figcaption, figure, footer, header, hgroup, main, menu, nav, section, summary {
  display: block;
}

.movies_nav {


}

.w3l_sign_in_register ul li a {
  color: #fff;
  display: inline-block;
  text-transform: uppercase;
  text-decoration: none;
  background: #FF8D1B;
  text-align: center;
  padding: 6px 30px;
  font-weight: bold;
}

.owl-carousel {
  display: none;
  position: relative;
  width: 100%;
  -ms-touch-action: pan-y;
  background: #fff;
}

.img-responsive, .thumbnail > img, .thumbnail a > img, .carousel-inner > .item > img, .carousel-inner > .item > a > img {
  display: block;
  max-width: 100%;
  height: auto;
}

.w3l-movie-gride-agile {
  text-align: center;
  box-shadow: 0px 0px 10px rgba(255, 255, 255, 0.35);
  margin-right: 0%;
  margin-bottom: 3%;
  position: relative;
  padding-left: 0;
}

.c {
  *zoom: 1;
}

.c:after {
  content: "";
  display: block;
  height: 0;
  clear: both;
}


</style>
