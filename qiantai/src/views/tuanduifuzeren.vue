
<template>
  <div class="order">
    <div class="order-header">
      <div class="order-header-content">
        <p>
          <i class="el-icon-s-order" style="font-size: 30px;color: #ff6700;"></i>
          团队负责人
        </p>
      </div>
    </div>

    <div class="order-content">
      <div class="content">
	  <el-form :inline="true" :model="formInline" class="user-search">
          <el-form-item label=""><el-input size="small" v-model="formInline.tuanduibianhao" placeholder="输入团队编号"></el-input></el-form-item>			<el-form-item label=""><el-input size="small" v-model="formInline.tuanduileixing" placeholder="输入团队类型"></el-input></el-form-item>			<el-form-item label=""><el-input size="small" v-model="formInline.xuexiaoxinxi" placeholder="输入学校信息"></el-input></el-form-item>			
          <el-form-item>
            <el-button size="small" type="primary" icon="el-icon-search" @click="search">搜索</el-button>
          </el-form-item>
        </el-form>
		
		
        <ul>

          <li class="header">
		  	<div class='pro-img'> 团队编号</div>    <div class='pro-img'> 密码</div>    <div class='pro-img'> 团队名称</div>    <div class='pro-img'> 团队类型</div>    <div class='pro-img'> 团队地址</div>                    
          
          </li>

          <li class="product-list" v-for="item in tuanduifuzeren" :key="item.id">
            <router-link :to="`/tuanduifuzerendetail/`+item.id" >
				<div class='pro-img'>{{item.tuanduibianhao}}</div>    <div class='pro-img'>{{item.mima}}</div>    <div class='pro-img'>{{item.tuanduimingcheng}}</div>    <div class='pro-img'>{{item.tuanduileixing}}</div>    <div class='pro-img'>{{item.tuanduidizhi}}</div>                    
             
            </router-link>
          </li>
        </ul>
        <div class="order-bar">
        </div>
      </div>
      <div style="margin-top:-40px;"></div>
    </div>

  </div>
</template>

<script>
  export default {
    data() {
      return {
	  	tuanduifuzeren: "", // 数据列表
        userInfo:{},
        total: 0, // 商品总量
        currentPage:0,
        currentNum: 10, // 每页显示的商品数量
        current: 1, //当前页码
        activeName: "-1", // 分类列表当前选中的id
        formInline: {
          page: 1,
          limit: 10,
        },
      };
    },
    created() {
      this.gettuanduifuzeren();
    },
    activated() {
      this.activeName = "-1"; // 初始化分类列表当前选中的id为-1
      this.total = 0; // 初始化商品总量为0
      this.current = 1; //初始化当前页码为1
    },

    methods: {
      // 页码变化调用currentChange方法
      currentChange(current) {
        this.current = current;
        if (this.productName != "") {
          this.getProductBySearch();
        } else {
          this.getData();
        }
        this.backtop();
      },
	  search() {
        this.gettuanduifuzeren(this.formInline)
      },
      // 向后端请求分类列表数据
      gettuanduifuzeren(parameter) {
        this.$axios
               .post("/api/front/tuanduifuzeren", {...parameter})
                .then(res => {
                  debugger
                  this.tuanduifuzeren = res.data.data.list;
                  this.currentPage = this.formInline.current
                  this.pageSize = this.formInline.currentNum
                  this.total = res.data.data.total
                })
                .catch(err => {
                  return Promise.reject(err);
                });
      },



    }
  };
</script>
<style scoped>
  * {
    background: #fff;
    color: #444;
    font-family: "Open Sans", sans-serif;
  }
  .order {
    background-color: #f5f5f5;
    padding-bottom: 20px;
  }
  /* 我的订单头部CSS */
  .order .order-header {
    height: 64px;
    border-bottom: 2px solid #ff6700;
    background-color: #fff;
    margin-bottom: 20px;
  }
  .order .order-header .order-header-content {
    width: 1225px;
    margin: 0 auto;
  }
  .order .order-header p {
    font-size: 28px;
    line-height: 58px;
    float: left;
    font-weight: normal;
    color: #424242;
  }
  /* 我的订单头部CSS END */
  .order .content {
    width: 1225px;
    margin: 0 auto;
    background-color: #fff;
    margin-bottom: 50px;
  }

  .order .content ul {
    background-color: #fff;
    color: #424242;
    line-height: 85px;
  }
  /* 我的订单表头CSS */
  .order .content ul .order-info {
    height: 60px;
    line-height: 60px;
    padding: 0 26px;
    color: #424242;
    border-bottom: 1px solid #ff6700;
  }
  .order .content ul .order-info .order-id {
    float: left;
    color: #ff6700;
  }
  .order .content ul .order-info .order-time {
    float: right;
  }

  .order .content ul .header {
    height: 85px;
    padding-right: 26px;
    color: #424242;
  }
  /* 我的订单表头CSS END */

  /* 订单列表CSS */
  .order .content ul .product-list {
    height: 85px;
    padding: 15px 26px 15px 0;
    border-top: 1px solid #e0e0e0;
  }
  .order .content ul .pro-img {
    float: left;
    height: 45px;
    width: 120px;
    padding-left: 80px;
  }
  .order .content ul .pro-img img {
    height: 80px;
    width: 80px;
  }
  .order .content ul .pro-name {
    float: left;
    width: 380px;
  }
  .order .content ul .pro-name a {
    color: #424242;
  }
  .order .content ul .pro-name a:hover {
    color: #ff6700;
  }
  .order .content ul .pro-price {
    float: left;
    width: 160px;
    padding-right: 18px;
    text-align: center;
  }
  .order .content ul .pro-num {
    float: left;
    width: 190px;
    text-align: center;
  }
  .order .content ul .pro-total {
    float: left;
    width: 160px;
    padding-right: 81px;
    text-align: right;
  }
  .order .content ul .pro-total-in {
    color: #ff6700;
  }

  .order .order-bar {
    width: 1185px;
    padding: 0 20px;
    border-top: 1px solid #ff6700;
    height: 50px;
    line-height: 50px;
    background-color: #fff;
  }
  .order .order-bar .order-bar-left {
    float: left;
  }
  .order .order-bar .order-bar-left .order-total {
    color: #757575;
  }
  .order .order-bar .order-bar-left .order-total-num {
    color: #ff6700;
  }
  .order .order-bar .order-bar-right {
    float: right;
  }
  .order .order-bar .order-bar-right .total-price-title {
    color: #ff6700;
    font-size: 14px;
  }
  .order .order-bar .order-bar-right .total-price {
    color: #ff6700;
    font-size: 30px;
  }
  /* 订单列表CSS END */

  /* 订单为空的时候显示的内容CSS */
  .order .order-empty {
    width: 1225px;
    margin: 0 auto;
  }
  .order .order-empty .empty {
    height: 300px;
    padding: 0 0 130px 558px;
    margin: 65px 0 0;
    background: url(../assets/imgs/cart-empty.png) no-repeat 124px 0;
    color: #b0b0b0;
    overflow: hidden;
  }
  .order .order-empty .empty h2 {
    margin: 70px 0 15px;
    font-size: 36px;
  }
  .order .order-empty .empty p {
    margin: 0 0 20px;
    font-size: 20px;
  }
  /* 订单为空的时候显示的内容CSS END */
</style>
