<template>
  <div>



      <div class="homeBox homeIdear">
          <div class="wrap">
              <div class="homeTitle">
                  <h2>学习资料推荐</h2>
                  <h3><span> </span></h3>
                  <div class="line"></div>
              </div>
              <div class="homeIdearWrap" v-if="objssp != null">
                  <ul class="slideHomeIdear clearfloat" style="height: 320px; display: flex; justify-content: space-around; align-items: center;">
                      <li v-for="item in objssp" :key="item.id">
                          <router-link :to="`/jingsaixinxidetail/`+item.id">
                              <img :src="`/api/`+item.jingsaifengmian" alt width="250" height="230"/>
                              <h2 style="font-size: 20px; text-align: center;">{{ item.jingsaimingcheng }}</h2>
                          </router-link>
                      </li>
                  </ul>
              </div>
              <div style="height: 200px; display: flex; justify-content: space-around; align-items: center;" v-else >
                  <h3>请添加收藏，以便能为您个性化推荐！</h3>
              </div>
          </div>
      </div>





      <div class="gywm">
      <div class="wrap">
        <div class="title wow fadeInUp animated" style="visibility: visible; animation-name: fadeInUp;">
<!--          <h2>关于我们</h2>-->
<!--          <span>由国内工程师负责掌握所有制造及品质管制过程</span>-->
        </div>
        <div class="gywmctn wow fadeInUp animated" style="visibility: visible; animation-name: fadeInUp;">
          <div class="gywm-left">
            <span></span>
            <div class="glimg">
              <div class="glimg1 beijing">
                <div class="glimg2"></div>
              </div>
            </div>
          </div>
          <div class="gywm-right">
<!--            <div class="grtit"><b>xxx有限公司</b></div>-->
            <a href="" title="查看详情">
              <div class="grtxt"><p>
                  旨在通过竞赛使大多数青少年在智力上有所发展，并为少数优秀青少年脱颖而出并成为人才创造机遇和条件。该活动已在中国得到了广泛开展，并取得了杰出成果…
              </p>
                <p>
                  <br>
                </p>
                <p>
                  <span> </span>
                </p>
                <p>
                  <span> </span>
                </p></div>
            </a>
          </div>
        </div>
        <div class="lxwm wow fadeInUp animated" style="visibility: visible; animation-name: fadeInUp;">

        </div>
      </div>
    </div>

  </div>
</template>
<script>
import {mapActions} from "vuex";

export default {
  data() {
    return {
      objssp: [],
      objstx: [],
      wbjxwtz1: [],
    };
  },
  watch: {
    '$route'(to, from) {
      this.$router.go(0)
    },
  },
  created() {
    let user = localStorage.getItem("user");
    if (user) {
      this.userInfo = JSON.parse(localStorage.getItem("user"));
    }
    //loadsp1
	//loadtx2



      this.loadTablesp(4);
  },
  methods: {
    ...mapActions(["setUser", "setShowLogin", "setShoppingCart"]),
    goUrl() {
      window.location.href = 'http://localhost:9999'
    },
    //loadsphs
    //loadtxhs


      loadTablesp(num) {
          if (this.userInfo == null) {
              this.$axios
                  .post("/api/front/jingsaixinxi", {})
                  .then(res => {
                      let data = res.data.data.list;
                      let filteredData = [];
                      let i = 0;
                      data.forEach(item => {
                          i++;
                          if (i <= num) {
                              filteredData.push(item);
                          }
                      });
                      this.objssp = filteredData;
                  });
          } else {
              this.$axios.post("/api/front/recommendList", null, {params: {userId: this.userInfo.id, num: num}})
                  .then(res => {
                      this.objssp = res.data.data;
                  });
          }
      },

  }
};
</script>
<style scoped>
@import "../assets/css/index.scss";

.message-item {
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

}

last-child {
  margin-bottom: 0;
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
  flex: 1;
  flex-shrink: 0;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
}

.rizhuti_v2-widget-search-bg > .container {
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
  filter: brightness(100%) contrast(100%) saturate(100%) blur(0px) hue-rotate(0deg);
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

.module.search-bg .popula-search-key > a {
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

.clearfix {
  width: 100%;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.clearfix {
  font-size: 10px;
  line-height: 1.9;
  display: inline-block;
}

.title {
  font-size: 14px;
  letter-spacing: -0.2px;
  margin-bottom: 0;
  overflow: hidden;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  white-space: normal;
  height: 36px;
  line-height: 18px;
}

.clear {
  clear: both;
}

.el-form-item {
  margin-bottom: 0px !important;
}

.section {
  width: 100%;

  overflow: hidden;
  display: flex;
  justify-content: flex-start;
  flex-wrap: wrap;
}

h3 {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.col-md-3 {
  width: 24%;
}


</style>
