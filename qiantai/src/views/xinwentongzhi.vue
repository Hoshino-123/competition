<template>
  <div class="goods" id="goods" name="goods">
    <!-- 分类标签 -->
    <el-row>
      <h3 class="huodong-title">{{lb}}</h3>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="24">
        <el-card shadow="hover">

        <div class="info" >
          <ul class="info-ul" >
            <li  class="info-item" style="border-bottom: #c9c9c9 1px dashed" v-for="item in xinwentongzhis" :key="item.id">
              <router-link :to="`/xinwentongzhidetail/`+item.id" style="display: flex;justify-content: space-between;width: 100%">
                <div class="info-item-left" v-text="item.biaoti"></div>
                <div class="info-item-right" >{{item.addtime | dateFormat}}</div>
              </router-link>
            </li>
          </ul>
        </div>
      </el-card>
      </el-col>
    </el-row>

  </div>
</template>
<script>
export default {
  data() {
    return {
      userInfo:{},
      // categoryList: "", //分类列表
      // categoryId: [], // 分类id
      // product: "", // 商品列表
      // productList: "",
      total: 0, // 商品总量
      currentNum: 15, // 每页显示的商品数量
      current: 1, //当前页码
      activeName: "-1", // 分类列表当前选中的id
     // productName: "",// 搜索条件,
      lb:"",
      xinwentongzhistemp:[],
      xinwentongzhis:[],
    //  fuli2s:[],
    };
  },
  created() {
    let lb = this.$route.params.lb;
    this.lb=lb;
   // this.userInfo = JSON.parse(localStorage.getItem("user"));
    this.getxinwentongzhi(lb);
    //this.getGonggao2();
  },
  activated() {
    this.activeName = "-1"; // 初始化分类列表当前选中的id为-1
    this.total = 0; // 初始化商品总量为0
    this.current = 1; //初始化当前页码为1

  },

  watch: {
    // 监听路由变化，更新路由传递了搜索条件
    $route: function(val) {
      if (val.path == "/goods") {
        if (val.query.productName != undefined) {
          this.activeName = "-1";
          this.current = 1;
          this.total = 0;
        //  this.productName = val.query.productName;
        }
      }
    }
  },
  beforeRouteUpdate(to, from, next) {
    // 处理路由参数的变化
    const lb = to.params.lb;
    this.getxinwentongzhi(lb);
    this.lb=lb;
    next();
  },
  methods: {
    goUrl() {
      window.location.href = 'http://localhost:9999'
    },
    // 返回顶部
    backtop() {
      const timer = setInterval(function() {
        const top = document.documentElement.scrollTop || document.body.scrollTop;
        const speed = Math.floor(-top / 5);
        document.documentElement.scrollTop = document.body.scrollTop =
          top + speed;

        if (top === 0) {
          clearInterval(timer);
        }
      }, 20);
    },
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
    // 向后端请求分类列表数据
    getxinwentongzhi(lb) {
      this.$axios
              .get("/api/front/xinwentongzhi", {})
              .then(res => {
                let data = res.data.data;
                let filteredData = []; // 用于存储满足条件的数据

                // 遍历data数组
                data.forEach(item => {
                  if (item.leibie === lb) {
                  filteredData.push(item);

                  }
                });
                this.xinwentongzhis = filteredData;
              })
              .catch(err => {
                return Promise.reject(err);
              });
    },
    //
    // getxinwentongzhi(lb) {
    //   this.$axios
    //           .get("/api/front/xinwentongzhi", {})
    //           .then(res => {
    //
    //             this.xinwentongzhistemp = res.data.data;
    //
    //
    //                     xinwentongzhistemp.forEach(item => {
    //                      if (item.leibie === lb) {
    //
    //                        xinwentongzhis.push(item);
    //
    //                        }
    //
    //           })
    //           .catch(err => {
    //             return Promise.reject(err);
    //           });
    // },

    getData(){
      // 如果分类列表为空则请求全部商品数据，否则请求分类商品数据
      const api =
        this.categoryId.length == 0
          ? "/api/front/getAllProduct"
          : "/api/front/getAllProduct";
      this.$axios
        .post(api, {
          categoryId: this.categoryId[0],
          current: this.current,
          currentNum: this.currentNum
        })
        .then(res => {
          this.product = res.data.data.list;
          this.total = res.data.data.total;
        })
        .catch(err => {
          return Promise.reject(err);
        });
    },
    // 通过搜索条件向后端请求商品数据
    getProductBySearch() {
      this.$axios
        .post("/api/product/getAllProduct", {
          productName: this.productName,
          current: this.current,
          currentNum: this.currentNum
        })
        .then(res => {
          this.product = res.data.Product;
          this.total = res.data.total;
        })
        .catch(err => {
          return Promise.reject(err);
        });
    }
  }
};
</script>

<style scoped>
  @import "../assets/css/index.scss";
* {
  background: #fff;
  color: #444;
  font-family: "Open Sans", sans-serif;
}
  .huodong-title{
    font-size: 16px;
    color: #333;
    line-height: 40px;
    height: 40px;
    margin: 0;
    padding: 0;
    text-decoration: none;
    display: block;
    position: relative;
    transition: .15s ease-out;
    font-weight: 700;
    text-align: center;
  }
.goods {
  background-color: #f5f5f5;
  max-width: 1225px;
  margin: 0 auto;
}
/* 面包屑CSS */
.el-tabs--card .el-tabs__header {
  border-bottom: none;
}
.goods .breadcrumb {
  height: 50px;
  background-color: white;
}
.goods .breadcrumb .el-breadcrumb {
  width: 1225px;
  line-height: 30px;
  font-size: 16px;
  margin: 0 auto;
}
/* 面包屑CSS END */

/* 分类标签CSS */
.goods .nav {
  background-color: white;
}
.goods .nav .product-nav {
  width: 1225px;
  height: 40px;
  line-height: 40px;
  margin: 0 auto;
}
.nav .product-nav .title {
  width: 50px;
  font-size: 16px;
  font-weight: 700;
  float: left;
}
/* 分类标签CSS END */

/* 主要内容区CSS */
.goods .main {
  margin: 0 auto;
  max-width: 1225px;
}
.goods .main .list {
  min-height: 650px;
  padding-top: 14.5px;
  margin-left: -13.7px;
  overflow: auto;
}
.goods .main .pagination {
  height: 50px;
  text-align: center;
}
.goods .main .none-product {
  color: #333;
  margin-left: 13.7px;
}
  .message-item{
    margin-bottom: 20px;
  }
  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    display: flex;
    justify-content: center;
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    width: 100%;
    text-align: center;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
  .el-row {
    margin-bottom: 20px;
  &:last-child {
     margin-bottom: 0;
   }
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
  .info-item {
    display: flex;
    font-size: 13px;
    color: #606266;
    height: 28px;
    line-height: 28px;
  }
  .info-item :hover {
    color: #303133;
    cursor: pointer;
  }
  .info-item-left {
    flex-shrink: 0;
    text-overflow: ellipsis;
    white-space: nowrap;
    overflow: hidden;
  }
  .rizhuti_v2-widget-search-bg>.container {
    max-width: none !important;
    padding: 0 !important;
  }
  .container, .container-fluid, .container-lg, .container-md, .container-sm, .container-xl {
    width: 100%;
    padding-right: 15px;
    padding-left: 15px;
    margin-right: auto;
    margin-left: auto;
  }
  .module.search-bg {
    z-index: 2 !important;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    min-height: calc(100vh - 70vh);
    position: relative;
    text-align: center;
    background-repeat: no-repeat;
    background-position: 50%;
    background-size: cover;
  }
  .module.search-bg .search-bg-overlay {
    height: 100%;
    width: 100%;
    top: 0;
    left: 0;
    position: absolute;
    filter: brightness( 100%) contrast( 100%) saturate( 100%) blur( 0px) hue-rotate( 0deg);
    background-color: #191e4b;
    opacity: 0.5;
    transition: background 0.3s, border-radius 0.3s, opacity 0.3s;
  }
  .module.search-bg .container {
    z-index: 1;
    margin: auto;
    padding-bottom: 140px;
    padding-top: 140px;
    max-width: 820px;
  }
  .module.search-bg .search-title {
    color: #fff;
    letter-spacing: 2px;
    font-size: 24px;
  }
  .module.search-bg .search-desc {
    color: #fff;
    margin-bottom: 25px;
  }
  .module.search-bg .search-form {
    max-width: 100%;
  }
  .search-form form {
    display: flex;
    -ms-flex-flow: row nowrap;
    flex-flow: row nowrap;
    -ms-flex-align: center;
    align-items: center;
    -ms-flex-pack: justify;
    justify-content: space-between;
    width: 100%;
    border-radius: 50px;
  }
  .search-form .search-fields {
    background-color: #ffffff;
    position: relative;
    display: inline-block;
    flex: 1;
    vertical-align: top;
    background: #fff;
    border-radius: 30px;
    opacity: 0.8;
    overflow: hidden;
  }
  .search-form .search-fields input {
    display: block;
    height: 50px;
    line-height: 20px;
    width: 100%;
    border: 2px solid #fff;
    position: relative;
    z-index: 100;
    margin: 0;
    font-size: 16px;
    outline: 0;
    -webkit-appearance: none;
  }
  .search-form .search-fields button {
    position: absolute;
    height: 54px;
    width: 50px;
    right: 0;
    top: 0;
    z-index: 1000;
    border: none !important;
    background: 0 0 !important;
    max-height: 50px;
  }
  .module.search-bg .popula-search-key {
    text-align: left;
    color: #fff;
    margin-top: 10px;
    opacity: 0.5;
  }
  .module.search-bg .popula-search-key>a {
    color: #fff;
  }
  .modular-title-1 .section-title {
    font-size: 24px;
    letter-spacing: .5px;
    margin-bottom: 30px;
    text-transform: uppercase;
  }
  .image {
    width: 100%;
    display: block;
    max-height: 120px;
    min-height: 120px;
  }
  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
  .clearfix{
    font-size: 10px;
    line-height: 1.9;
    display: inline-block;
  }
  .title{
    font-size: 15px;
    letter-spacing: -0.2px;
    margin-bottom: 0;
    overflow: hidden;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    white-space: normal;
    height: 36px;
    line-height: 18px;
    font-weight: 700;
  }
  .el-form-item {
    margin-bottom: 0px !important;
  }

</style>
