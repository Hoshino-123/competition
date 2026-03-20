<template>
  <div id="container" style="width:80%;margin: 5px auto">
    <el-row :gutter="20">
      <el-col span="24">
        <div class="comment">
          <div class="comment-item">
            <el-row v-for="item in comments" :key="item.id" style="margin-top: 20px">
              <el-col span="4" style="text-align: center">{{item.cheng}}  发送：</el-col>
              <el-col span="18">
                <el-row>
                  <el-col span="12" style="text-align: justify;">{{item.biaoti}}</el-col>
                  <el-col span="12" style="text-align: justify;">{{item.addtime|datePipe('yyyy-MM-dd hh:mm:ss')}}</el-col>
                  <div style="color: #888;margin-left: -150px;padding-top: 30px" v-for="child in item.children" :key="child.id">
                    <el-col span="4" style="text-align: center">{{child.cheng}}  回复：</el-col>
                    <el-col span="18">
                      <el-row>
                        <el-col span="12">{{child.biaoti}}</el-col>
                        <el-col span="12" style="text-align: right">{{child.addtime|datePipe('yyyy-MM-dd hh:mm:ss')}}</el-col>
                      </el-row>
                    </el-col>
                  </div>
                  <el-col span="12" style="margin-top: 20px">
                    <el-input type="text" class="form-control" style="width: 70%; display: inline-block" v-model="item.replayText"/>
                    <el-button type="primary" @click="reply(item.id, item.replayText)">回复</el-button>
                  </el-col>
                </el-row>
              </el-col>
            </el-row>
          </div>
        </div>
        <div class="row">
          <div class="col-md-8 col-md-offset-2">
            <h2>留言</h2>
          </div>
        </div>
        <div class="comment-item">
          <el-row>
            <el-input type="textarea" rows="3" v-model="comment.biaoti"></el-input>
          </el-row>
          <el-row>
            <el-button type="primary"  @click="add()">发送</el-button>
          </el-row>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  import { mapActions } from "vuex";
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
      let user = localStorage.getItem("user");
      if(!user){
        this.notifyError("请先登录");
      }
      this.userInfo = JSON.parse(localStorage.getItem("user"))
      this.getComment();
    },
    methods: {
      ...mapActions(["unshiftShoppingCart", "addShoppingCartNum"]),
      // 获取商品详细信息
      getComment() {
        this.$axios
                .get("/api/front/getLiuyan",)
                .then(res => {
                  this.comments = res.data.data;
                }).catch(err => {
                  return Promise.reject(err);
                });
      },
      add(){
        this.comment.parentId = 0;
        this.comment.zhanghao = this.userInfo.zhanghao;
        this.comment.cheng = this.userInfo.xingming;
        this.$axios.post('/api/front/addLiuyan', this.comment).then(res => {
          if (res.data.code === '0') {
            this.getComment(this.id);
            this.comment.biaoti = ""
          } else {
            alert(res.data.msg);
          }
        })
      },
      reply(id, content) {
        if (!content) {
          this.notifyError("请填写回复内容");
          return;
        }
        let data = {biaoti: content,zhanghao:this.userInfo.zhanghao,cheng:this.userInfo.xingming,parent: id};
        this.$axios.post('/api/front/addLiuyan', data).then(res => {
          this.getComment();
        })
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
