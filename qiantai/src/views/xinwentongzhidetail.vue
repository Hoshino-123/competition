<template>
  <div id="container" style="width:1225px;margin: 5px auto">
    <el-row :gutter="20">
      <el-col span="24">
        <div class="info">
          <el-row>
            <el-card>
              <div slot="header" class="clearfix" style="text-align: center">
                <span>{{item.biaoti}}</span>
              </div>
              <div v-html="item.neirong">
              </div>
            </el-card>
          </el-row>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
 // import { mapActions } from "vuex";
  export default {
    data() {
      return {
        dis: false, // 控制“加入购物车按钮是否可用”
        productID: "", // 商品id
        comment:{},
        comments:[],
        item: {
          user:{},
          shezhang:{},
          shetuan:{}
        }, // 商品详细信息
        otherTiezi:[],
        productPicture: "", // 商品图片
        userInfo:{},
      };
    },
    watch: {
      '$route'(to, from) {
        this.$router.go(0)
      }
    },
    created() {
      // let user = localStorage.getItem("user");
      // if(!user){
      //   this.notifyError("请先登录");
      // }
      // this.userInfo = JSON.parse(localStorage.getItem("user"))

      let id = this.$route.params.id;
      this.id = id;
      this.getDetails(id);
    },
    methods: {
     // ...mapActions(["unshiftShoppingCart", "addShoppingCartNum"]),
      // 获取商品详细信息
      getDetails(val) {
        this.$axios

                .get("/api/front/getxinwentongzhi/"+val,)
                .then(res => {

                  this.item = res.data.data;
                })
                .catch(err => {
                  return Promise.reject(err);
                });
      },
    }
  };
</script>
<style>
  /* 头部CSS */
  #details .page-header {
    height: 64px;
    margin-top: -20px;
    z-index: 4;
    background: #fff;
    border-bottom: 1px solid #e0e0e0;
    -webkit-box-shadow: 0px 5px 5px rgba(0, 0, 0, 0.07);
    box-shadow: 0px 5px 5px rgba(0, 0, 0, 0.07);
  }
  #details .page-header .title {
    width: 1225px;
    height: 64px;
    line-height: 64px;
    font-size: 18px;
    font-weight: 400;
    color: #212121;
    margin: 0 auto;
  }
  #details .page-header .title p {
    float: left;
  }
  #details .page-header .title .list {
    height: 64px;
    float: right;
  }
  #details .page-header .title .list li {
    float: left;
    margin-left: 20px;
  }
  #details .page-header .title .list li a {
    font-size: 14px;
    color: #616161;
  }
  #details .page-header .title .list li a:hover {
    font-size: 14px;
    color: #ff6700;
  }
  /* 头部CSS END */

  /* 主要内容CSS */
  #details .main {
    width: 1225px;
    height: 500px;
    padding-top: 30px;
    margin: 0 auto;
  }
  #details .main .block {
    float: left;
    width: 560px;
    height: 500px;
  }
  #details .el-carousel .el-carousel__indicator .el-carousel__button {
    background-color: rgba(163, 163, 163, 0.8);
  }
  #details .main .content {
    float: left;
    margin-left: 25px;
    width: 640px;
  }
  #details .main .content .name {
    height: 30px;
    line-height: 30px;
    font-size: 24px;
    font-weight: normal;
    color: #212121;
  }
  #details .main .content .intro {
    color: #b0b0b0;
    padding-top: 10px;
  }
  #details .main .content .store {
    color: #ff6700;
    padding-top: 10px;
  }
  #details .main .content .price {
    display: block;
    font-size: 18px;
    color: #ff6700;
    border-bottom: 1px solid #e0e0e0;
    padding: 25px 0 25px;
  }
  #details .main .content .price .del {
    font-size: 14px;
    margin-left: 10px;
    color: #b0b0b0;
    text-decoration: line-through;
  }
  #details .main .content .pro-list {
    background: #f9f9fa;
    padding: 30px 60px;
    margin: 50px 0 50px;
  }
  #details .main .content .pro-list span {
    line-height: 30px;
    color: #616161;
  }
  #details .main .content .pro-list .pro-price {
    float: right;
  }
  #details .main .content .pro-list .pro-price .pro-del {
    margin-left: 10px;
    text-decoration: line-through;
  }
  #details .main .content .pro-list .price-sum {
    color: #ff6700;
    font-size: 24px;
    padding-top: 20px;
  }
  #details .main .content .button {
    height: 55px;
    margin: 10px 0 20px 0;
  }
  #details .main .content .button .el-button {
    float: left;
    height: 55px;
    font-size: 16px;
    color: #fff;
    border: none;
    text-align: center;
  }
  #details .main .content .button .shop-cart {
    width: 340px;
    background-color: #ff6700;
  }
  #details .main .content .button .shop-cart:hover {
    background-color: #f25807;
  }

  #details .main .content .button .like {
    width: 260px;
    margin-left: 40px;
    background-color: #b0b0b0;
  }
  #details .main .content .button .like:hover {
    background-color: #757575;
  }
  #details .main .content .pro-policy li {
    float: left;
    margin-right: 20px;
    color: #b0b0b0;
  }

  .v-section-tit {
    font-size: 18px;
    line-height: 30px;
    font-weight: 500;
    padding: 0 5px;
    color: #3e3b3f;
    border-bottom: 1px solid #dedede;
    margin-bottom: 6px;
    margin-top: 20px;
  }
  .daoyou-item{
    margin-top:10px;
    margin-bottom: 10px;
  }
  .paper-item{
    width: 260px;
    color: #4f4f4f;
    text-overflow:ellipsis;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-box-pack: center;
    -webkit-box-align: center;
    -webkit-line-clamp:3;
    overflow: hidden;
    border: 1px solid #dedede;
    padding: 12px 16px 12px 16px;
  }
  .hotArticle-list a{
    text-decoration: none;
    cursor: pointer;
  }
  .hotArticle-list a:hover {
    color:#fc5531;
    text-decoration: none;
  }
</style>
