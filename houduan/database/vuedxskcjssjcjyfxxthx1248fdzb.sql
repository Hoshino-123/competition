/*
Navicat MySQL Data Transfer

Source Server         : localhost_3307
Source Server Version : 50562
Source Host           : localhost:3307
Source Database       : vuedxskcjssjcjyfxxthx1248fdzb

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2024-04-11 16:52:44
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `baomingcansai`
-- ----------------------------
DROP TABLE IF EXISTS `baomingcansai`;
CREATE TABLE `baomingcansai` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `baomingbianhao` varchar(50) DEFAULT NULL,
  `jingsaibianhao` varchar(50) DEFAULT NULL,
  `jingsaimingcheng` varchar(50) DEFAULT NULL,
  `jingsaileixing` varchar(50) DEFAULT NULL,
  `jingsaididian` varchar(50) DEFAULT NULL,
  `zhidaolaoshi` varchar(50) DEFAULT NULL,
  `tuanduibianhao` varchar(50) DEFAULT NULL,
  `tuanduimingcheng` varchar(50) DEFAULT NULL,
  `tuanduirenshu` varchar(50) DEFAULT NULL,
  `xuexiaoxinxi` varchar(50) DEFAULT NULL,
  `zuopinmingcheng` varchar(50) DEFAULT NULL,
  `cansaizuopin` varchar(50) DEFAULT NULL,
  `issh` varchar(10) DEFAULT '待审核',
  `shhf` varchar(200) DEFAULT NULL,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of baomingcansai
-- ----------------------------
INSERT INTO `baomingcansai` VALUES ('5', '202404086366', '202404089874', '测试', '数学', 'xxx', '001', '111', '测试', '0', 'J学校', '3131', '/files/download/47', '已通过', ' ', '2024-04-08 23:54:23');

-- ----------------------------
-- Table structure for `bianhuantu`
-- ----------------------------
DROP TABLE IF EXISTS `bianhuantu`;
CREATE TABLE `bianhuantu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mingcheng` varchar(50) DEFAULT NULL,
  `tupian` varchar(50) DEFAULT NULL,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of bianhuantu
-- ----------------------------
INSERT INTO `bianhuantu` VALUES ('1', 'P名称', '/files/download/61', '2024-04-08 22:44:54');
INSERT INTO `bianhuantu` VALUES ('2', 'C名称', '/files/download/62', '2024-04-08 22:44:54');
INSERT INTO `bianhuantu` VALUES ('3', 'N名称', '/files/download/63', '2024-04-08 22:44:54');
INSERT INTO `bianhuantu` VALUES ('4', 'Q名称', '/files/download/64', '2024-04-08 22:44:54');
INSERT INTO `bianhuantu` VALUES ('5', 'H名称', '/files/download/65', '2024-04-08 22:44:54');

-- ----------------------------
-- Table structure for `collect`
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `shangpinxinxiID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES ('22', '12', '2');
INSERT INTO `collect` VALUES ('23', '11', '2');
INSERT INTO `collect` VALUES ('24', '11', '3');
INSERT INTO `collect` VALUES ('25', '11', '4');
INSERT INTO `collect` VALUES ('26', '11', '5');
INSERT INTO `collect` VALUES ('27', '11', '6');
INSERT INTO `collect` VALUES ('28', '10', '3');
INSERT INTO `collect` VALUES ('29', '13', '2');

-- ----------------------------
-- Table structure for `daxuesheng`
-- ----------------------------
DROP TABLE IF EXISTS `daxuesheng`;
CREATE TABLE `daxuesheng` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `xuehao` varchar(50) DEFAULT NULL,
  `mima` varchar(50) DEFAULT NULL,
  `xingming` varchar(50) DEFAULT NULL,
  `xingbie` varchar(50) DEFAULT NULL,
  `xuexiaoxinxi` varchar(50) DEFAULT NULL,
  `zhuanye` varchar(50) DEFAULT NULL,
  `lianxidianhua` varchar(50) DEFAULT NULL,
  `zhaopian` varchar(50) DEFAULT NULL,
  `tuanduirenshu` varchar(50) DEFAULT NULL,
  `level` varchar(10) DEFAULT '大学生',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of daxuesheng
-- ----------------------------
INSERT INTO `daxuesheng` VALUES ('2', '011', 'dc5c7986daef50c1e02ab09b442ee34f', '方之明', '0', 'G学校', '农学', '13052154877', '', '36', '大学生', '2024-04-08 22:44:54');
INSERT INTO `daxuesheng` VALUES ('3', '006', 'dc5c7986daef50c1e02ab09b442ee34f', '姚明', '1', 'P学校', '历史学', '17505772420', '', '11', '大学生', '2024-04-08 22:44:54');
INSERT INTO `daxuesheng` VALUES ('4', '005', 'dc5c7986daef50c1e02ab09b442ee34f', '黄行阳', '0', 'G学校', '医学', '13748589658', '', '47', '大学生', '2024-04-08 22:44:54');
INSERT INTO `daxuesheng` VALUES ('5', '032', 'dc5c7986daef50c1e02ab09b442ee34f', '吴官本', '1', 'E学校', '军事学', '13025896548', '', '62', '大学生', '2024-04-08 22:44:54');
INSERT INTO `daxuesheng` VALUES ('6', '017', 'dc5c7986daef50c1e02ab09b442ee34f', '陈小巧', '1', 'E学校', '法学', '18759640002', '', '11', '大学生', '2024-04-08 22:44:54');
INSERT INTO `daxuesheng` VALUES ('7', '025', 'dc5c7986daef50c1e02ab09b442ee34f', '詹姆斯', '0', 'D学校', '文学', '13544655202', '', '47', '大学生', '2024-04-08 22:44:54');
INSERT INTO `daxuesheng` VALUES ('8', '003', 'dc5c7986daef50c1e02ab09b442ee34f', '范冰冰', '1', 'P学校', '经济学', '17706641413', '', '18', '大学生', '2024-04-08 22:44:54');
INSERT INTO `daxuesheng` VALUES ('9', '013', 'dc5c7986daef50c1e02ab09b442ee34f', '司宪策', '0', 'K学校', '工学', '13623256544', '', '62', '大学生', '2024-04-08 22:44:54');
INSERT INTO `daxuesheng` VALUES ('10', '010', 'dc5c7986daef50c1e02ab09b442ee34f', '梅西', '0', 'P学校', '艺术学', '13184865998', '', '11', '大学生', '2024-04-08 22:44:54');
INSERT INTO `daxuesheng` VALUES ('11', '001', 'dc5c7986daef50c1e02ab09b442ee34f', '张燕', '1', 'J学校', '土建', '13769548711', '', '0', '大学生', '2024-04-08 22:44:54');
INSERT INTO `daxuesheng` VALUES ('12', 'admin1', 'e00cf25ad42683b3df678c61f42c6bda', '大学生', '1', 'G学校', '土建', '18000000000', '/files/download/58', '0', '大学生', '2024-04-09 09:57:50');
INSERT INTO `daxuesheng` VALUES ('13', 'admin11', 'e020590f0e18cd6053d7ae0e0a507609', null, null, null, null, null, null, null, '大学生', '2024-04-09 12:10:56');

-- ----------------------------
-- Table structure for `file`
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `originName` varchar(255) DEFAULT NULL,
  `fileName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES ('1', 'bht1.jpg', 'bht1.jpg');
INSERT INTO `file` VALUES ('2', 'bht2.jpg', 'bht2.jpg');
INSERT INTO `file` VALUES ('3', 'bht3.jpg', 'bht3.jpg');
INSERT INTO `file` VALUES ('4', 'bht4.jpg', 'bht4.jpg');
INSERT INTO `file` VALUES ('5', 'bht5.jpg', 'bht5.jpg');
INSERT INTO `file` VALUES ('6', 'tuanduifuzeren1.jpg', 'tuanduifuzeren1.jpg');
INSERT INTO `file` VALUES ('7', 'tuanduifuzeren2.jpg', 'tuanduifuzeren2.jpg');
INSERT INTO `file` VALUES ('8', 'tuanduifuzeren3.jpg', 'tuanduifuzeren3.jpg');
INSERT INTO `file` VALUES ('9', 'tuanduifuzeren4.jpg', 'tuanduifuzeren4.jpg');
INSERT INTO `file` VALUES ('10', 'tuanduifuzeren5.jpg', 'tuanduifuzeren5.jpg');
INSERT INTO `file` VALUES ('11', 'tuanduifuzeren6.jpg', 'tuanduifuzeren6.jpg');
INSERT INTO `file` VALUES ('12', 'tuanduifuzeren7.jpg', 'tuanduifuzeren7.jpg');
INSERT INTO `file` VALUES ('13', 'tuanduifuzeren8.jpg', 'tuanduifuzeren8.jpg');
INSERT INTO `file` VALUES ('14', 'tuanduifuzeren9.jpg', 'tuanduifuzeren9.jpg');
INSERT INTO `file` VALUES ('15', 'tuanduifuzeren10.jpg', 'tuanduifuzeren10.jpg');
INSERT INTO `file` VALUES ('16', 'daxuesheng1.jpg', 'daxuesheng1.jpg');
INSERT INTO `file` VALUES ('17', 'daxuesheng2.jpg', 'daxuesheng2.jpg');
INSERT INTO `file` VALUES ('18', 'daxuesheng3.jpg', 'daxuesheng3.jpg');
INSERT INTO `file` VALUES ('19', 'daxuesheng4.jpg', 'daxuesheng4.jpg');
INSERT INTO `file` VALUES ('20', 'daxuesheng5.jpg', 'daxuesheng5.jpg');
INSERT INTO `file` VALUES ('21', 'daxuesheng6.jpg', 'daxuesheng6.jpg');
INSERT INTO `file` VALUES ('22', 'daxuesheng7.jpg', 'daxuesheng7.jpg');
INSERT INTO `file` VALUES ('23', 'daxuesheng8.jpg', 'daxuesheng8.jpg');
INSERT INTO `file` VALUES ('24', 'daxuesheng9.jpg', 'daxuesheng9.jpg');
INSERT INTO `file` VALUES ('25', 'daxuesheng10.jpg', 'daxuesheng10.jpg');
INSERT INTO `file` VALUES ('26', 'jingsaixinxi1.jpg', 'jingsaixinxi1.jpg');
INSERT INTO `file` VALUES ('27', 'jingsaixinxi2.jpg', 'jingsaixinxi2.jpg');
INSERT INTO `file` VALUES ('28', 'jingsaixinxi3.jpg', 'jingsaixinxi3.jpg');
INSERT INTO `file` VALUES ('29', 'jingsaixinxi4.jpg', 'jingsaixinxi4.jpg');
INSERT INTO `file` VALUES ('30', 'jingsaixinxi5.jpg', 'jingsaixinxi5.jpg');
INSERT INTO `file` VALUES ('31', 'jingsaixinxi6.jpg', 'jingsaixinxi6.jpg');
INSERT INTO `file` VALUES ('32', 'jingsaixinxi7.jpg', 'jingsaixinxi7.jpg');
INSERT INTO `file` VALUES ('33', 'jingsaixinxi8.jpg', 'jingsaixinxi8.jpg');
INSERT INTO `file` VALUES ('34', 'jingsaixinxi9.jpg', 'jingsaixinxi9.jpg');
INSERT INTO `file` VALUES ('35', 'jingsaixinxi10.jpg', 'jingsaixinxi10.jpg');
INSERT INTO `file` VALUES ('36', 'zhidaojiaoshi1.jpg', 'zhidaojiaoshi1.jpg');
INSERT INTO `file` VALUES ('37', 'zhidaojiaoshi2.jpg', 'zhidaojiaoshi2.jpg');
INSERT INTO `file` VALUES ('38', 'zhidaojiaoshi3.jpg', 'zhidaojiaoshi3.jpg');
INSERT INTO `file` VALUES ('39', 'zhidaojiaoshi4.jpg', 'zhidaojiaoshi4.jpg');
INSERT INTO `file` VALUES ('40', 'zhidaojiaoshi5.jpg', 'zhidaojiaoshi5.jpg');
INSERT INTO `file` VALUES ('41', 'zhidaojiaoshi6.jpg', 'zhidaojiaoshi6.jpg');
INSERT INTO `file` VALUES ('42', 'zhidaojiaoshi7.jpg', 'zhidaojiaoshi7.jpg');
INSERT INTO `file` VALUES ('43', 'zhidaojiaoshi8.jpg', 'zhidaojiaoshi8.jpg');
INSERT INTO `file` VALUES ('44', 'zhidaojiaoshi9.jpg', 'zhidaojiaoshi9.jpg');
INSERT INTO `file` VALUES ('45', 'zhidaojiaoshi10.jpg', 'zhidaojiaoshi10.jpg');
INSERT INTO `file` VALUES ('46', '90.jpg', '901712588585670.jpg');
INSERT INTO `file` VALUES ('47', '8ce1258089fd462c926e4eae4ba2450e.jpeg', '8ce1258089fd462c926e4eae4ba2450e1712591650700.jpeg');
INSERT INTO `file` VALUES ('48', 't016a6e9ced7c071da6.jpg', 't016a6e9ced7c071da61712592983898.jpg');
INSERT INTO `file` VALUES ('49', 'c5aef93ed9004069a83932b27eaef402.jpg', 'c5aef93ed9004069a83932b27eaef4021712592987512.jpg');
INSERT INTO `file` VALUES ('50', '965F1677457F3DA8368B4F3E409_9CCB58B7_30A4BE.jpg', '965F1677457F3DA8368B4F3E409_9CCB58B7_30A4BE1712592991410.jpg');
INSERT INTO `file` VALUES ('51', '3.jpg', '31712592995997.jpg');
INSERT INTO `file` VALUES ('52', '4.jpg', '41712593000604.jpg');
INSERT INTO `file` VALUES ('53', '5.jpg', '51712627000393.jpg');
INSERT INTO `file` VALUES ('54', 'c5aef93ed9004069a83932b27eaef402.jpg', 'c5aef93ed9004069a83932b27eaef4021712627056732.jpg');
INSERT INTO `file` VALUES ('55', '4.jpg', '41712627074776.jpg');
INSERT INTO `file` VALUES ('56', '3.jpg', '31712627112738.jpg');
INSERT INTO `file` VALUES ('57', '8ce1258089fd462c926e4eae4ba2450e.jpeg', '8ce1258089fd462c926e4eae4ba2450e1712628059458.jpeg');
INSERT INTO `file` VALUES ('58', '8ce1258089fd462c926e4eae4ba2450e.jpeg', '8ce1258089fd462c926e4eae4ba2450e1712628107513.jpeg');
INSERT INTO `file` VALUES ('59', '93fbf25caf646905ca69ab6798cd91e1.jpg', '93fbf25caf646905ca69ab6798cd91e11712628346646.jpg');
INSERT INTO `file` VALUES ('60', '1bc3a79a2173469c85612cb5977e45ae.jpeg', '1bc3a79a2173469c85612cb5977e45ae1712632300075.jpeg');
INSERT INTO `file` VALUES ('61', 'c5aef93ed9004069a83932b27eaef402.jpg', 'c5aef93ed9004069a83932b27eaef4021712632539681.jpg');
INSERT INTO `file` VALUES ('62', 't016a6e9ced7c071da6.jpg', 't016a6e9ced7c071da61712632543836.jpg');
INSERT INTO `file` VALUES ('63', '5.jpg', '51712632547844.jpg');
INSERT INTO `file` VALUES ('64', '4.jpg', '41712632552043.jpg');
INSERT INTO `file` VALUES ('65', '965F1677457F3DA8368B4F3E409_9CCB58B7_30A4BE.jpg', '965F1677457F3DA8368B4F3E409_9CCB58B7_30A4BE1712632555865.jpg');
INSERT INTO `file` VALUES ('66', '1.jpg', '11712632563357.jpg');
INSERT INTO `file` VALUES ('67', '2.jpg', '21712632567176.jpg');
INSERT INTO `file` VALUES ('68', 't016a6e9ced7c071da6.jpg', 't016a6e9ced7c071da61712632571826.jpg');
INSERT INTO `file` VALUES ('69', 'c5aef93ed9004069a83932b27eaef402.jpg', 'c5aef93ed9004069a83932b27eaef4021712632575214.jpg');
INSERT INTO `file` VALUES ('70', '965F1677457F3DA8368B4F3E409_9CCB58B7_30A4BE.jpg', '965F1677457F3DA8368B4F3E409_9CCB58B7_30A4BE1712632578654.jpg');
INSERT INTO `file` VALUES ('71', '93fbf25caf646905ca69ab6798cd91e1.jpg', '93fbf25caf646905ca69ab6798cd91e11712632586088.jpg');
INSERT INTO `file` VALUES ('72', '8ce1258089fd462c926e4eae4ba2450e.jpeg', '8ce1258089fd462c926e4eae4ba2450e1712632725207.jpeg');
INSERT INTO `file` VALUES ('73', '8ce1258089fd462c926e4eae4ba2450e.jpeg', '8ce1258089fd462c926e4eae4ba2450e1712633132582.jpeg');

-- ----------------------------
-- Table structure for `guanliyuan`
-- ----------------------------
DROP TABLE IF EXISTS `guanliyuan`;
CREATE TABLE `guanliyuan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `yonghuming` varchar(50) DEFAULT NULL,
  `mima` varchar(50) DEFAULT NULL,
  `status` varchar(10) DEFAULT '0',
  `level` varchar(10) DEFAULT '管理员',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of guanliyuan
-- ----------------------------
INSERT INTO `guanliyuan` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '0', '管理员', '2024-04-08 22:44:54');

-- ----------------------------
-- Table structure for `jiarutuandui`
-- ----------------------------
DROP TABLE IF EXISTS `jiarutuandui`;
CREATE TABLE `jiarutuandui` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shenqingbianhao` varchar(50) DEFAULT NULL,
  `tuanduibianhao` varchar(50) DEFAULT NULL,
  `tuanduimingcheng` varchar(50) DEFAULT NULL,
  `tuanduileixing` varchar(50) DEFAULT NULL,
  `xuexiaoxinxi` varchar(50) DEFAULT NULL,
  `xuesheng` varchar(50) DEFAULT NULL,
  `issh` varchar(10) DEFAULT '0',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of jiarutuandui
-- ----------------------------
INSERT INTO `jiarutuandui` VALUES ('2', '202404098664', '123', '团队测试', '数学', 'G学校', '001', '1', '2024-04-09 11:23:03');
INSERT INTO `jiarutuandui` VALUES ('3', '202404092561', '123', '团队测试', '数学', 'G学校', 'admin1', '1', '2024-04-09 11:24:36');

-- ----------------------------
-- Table structure for `jingsaileixing`
-- ----------------------------
DROP TABLE IF EXISTS `jingsaileixing`;
CREATE TABLE `jingsaileixing` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `leixing` varchar(50) DEFAULT NULL,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of jingsaileixing
-- ----------------------------
INSERT INTO `jingsaileixing` VALUES ('12', '数学', '2024-04-08 23:02:31');
INSERT INTO `jingsaileixing` VALUES ('13', 'A类', '2024-04-08 23:02:45');
INSERT INTO `jingsaileixing` VALUES ('14', '文艺', '2024-04-09 10:05:24');

-- ----------------------------
-- Table structure for `jingsaixinxi`
-- ----------------------------
DROP TABLE IF EXISTS `jingsaixinxi`;
CREATE TABLE `jingsaixinxi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jingsaibianhao` varchar(50) DEFAULT NULL,
  `jingsaimingcheng` varchar(50) DEFAULT NULL,
  `jingsaileixing` varchar(50) DEFAULT NULL,
  `jingsaididian` varchar(50) DEFAULT NULL,
  `jingsaishijian` date DEFAULT NULL,
  `jingsaineirong` varchar(500) DEFAULT NULL,
  `jingsaifengmian` varchar(50) DEFAULT NULL,
  `cansairenshu` int(50) DEFAULT '0',
  `zhidaolaoshi` varchar(50) DEFAULT NULL,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of jingsaixinxi
-- ----------------------------
INSERT INTO `jingsaixinxi` VALUES ('2', '202404089874', '测试', '数学', 'xxx', '2024-04-17', 'xxxx', '/files/download/66', '2', '001', '2024-04-08 23:03:10');
INSERT INTO `jingsaixinxi` VALUES ('3', '202404091339', 'A竞赛', 'A类', 'xxxx', '2024-04-09', 'xxxx', '/files/download/67', '0', '011', '2024-04-09 09:43:26');
INSERT INTO `jingsaixinxi` VALUES ('4', '202404097842', 'B竞赛', 'A类', null, '2024-04-10', null, '/files/download/68', '0', '013', '2024-04-09 09:44:21');
INSERT INTO `jingsaixinxi` VALUES ('5', '202404096273', 'C竞赛', 'A类', null, null, null, '/files/download/70', '0', '006', '2024-04-09 09:44:39');
INSERT INTO `jingsaixinxi` VALUES ('6', '202404099356', 'D竞赛', 'A类', 'xxxx', '2024-04-09', 'xxx', '/files/download/69', '0', '008', '2024-04-09 09:45:22');
INSERT INTO `jingsaixinxi` VALUES ('7', '202404091819', '文艺', '文艺', 'xxxx', '2024-04-17', 'xxxxx', '/files/download/71', '0', '001', '2024-04-09 10:05:52');

-- ----------------------------
-- Table structure for `liuyanban`
-- ----------------------------
DROP TABLE IF EXISTS `liuyanban`;
CREATE TABLE `liuyanban` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `yonghuming` varchar(50) DEFAULT NULL,
  `cheng` varchar(50) DEFAULT NULL,
  `touxiang` varchar(50) DEFAULT NULL,
  `biaoti` varchar(50) DEFAULT NULL,
  `neirong` varchar(500) DEFAULT NULL,
  `huifu` varchar(500) DEFAULT NULL,
  `status` varchar(10) DEFAULT '0',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `parent` int(11) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of liuyanban
-- ----------------------------

-- ----------------------------
-- Table structure for `pinglun`
-- ----------------------------
DROP TABLE IF EXISTS `pinglun`;
CREATE TABLE `pinglun` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pinglunneirong` varchar(500) DEFAULT NULL,
  `pingfen` varchar(50) DEFAULT NULL,
  `biao` varchar(50) DEFAULT NULL,
  `pinglunren` varchar(50) DEFAULT NULL,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `shujuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of pinglun
-- ----------------------------

-- ----------------------------
-- Table structure for `shenqingtuandui`
-- ----------------------------
DROP TABLE IF EXISTS `shenqingtuandui`;
CREATE TABLE `shenqingtuandui` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tuanduibianhao` varchar(50) DEFAULT NULL,
  `tuanduimingcheng` varchar(50) DEFAULT NULL,
  `tuanduileixing` varchar(50) DEFAULT NULL,
  `tuanduijieshao` varchar(500) DEFAULT NULL,
  `tuanduidizhi` varchar(50) DEFAULT NULL,
  `xuehao` varchar(50) DEFAULT NULL,
  `xuexiaoxinxi` varchar(50) DEFAULT NULL,
  `lianxidianhua` varchar(50) DEFAULT NULL,
  `issh` varchar(10) DEFAULT '待审核',
  `shhf` varchar(200) DEFAULT NULL,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of shenqingtuandui
-- ----------------------------
INSERT INTO `shenqingtuandui` VALUES ('2', '025', 'Y团队', '', '', '东城路99号', '007', '', '13587835380', '待审核', null, '2024-04-08 22:44:54');
INSERT INTO `shenqingtuandui` VALUES ('3', '023', 'S团队', '', '', '泰兴路22号', '002', '', '13623256544', '待审核', null, '2024-04-08 22:44:54');
INSERT INTO `shenqingtuandui` VALUES ('4', '028', 'K团队', '', '', '开明路22号', '018', '', '13184865998', '待审核', null, '2024-04-08 22:44:54');
INSERT INTO `shenqingtuandui` VALUES ('5', '019', 'N团队', '', '', '解放路73号', '025', '', '17505772420', '待审核', null, '2024-04-08 22:44:54');
INSERT INTO `shenqingtuandui` VALUES ('6', '013', 'J团队', '', '', '复兴街27号', '008', '', '13186835580', '待审核', null, '2024-04-08 22:44:54');
INSERT INTO `shenqingtuandui` VALUES ('7', '011', 'F团队', '', '', '人民路57号', '017', '', '13910215489', '待审核', null, '2024-04-08 22:44:54');
INSERT INTO `shenqingtuandui` VALUES ('8', '018', 'B团队', '', '', '鑫和锦园2栋', '023', '', '13159863241', '待审核', null, '2024-04-08 22:44:54');
INSERT INTO `shenqingtuandui` VALUES ('9', '024', 'P团队', '', '', '建兴路138号', '019', '', '13769548711', '待审核', null, '2024-04-08 22:44:54');
INSERT INTO `shenqingtuandui` VALUES ('10', '008', 'L团队', '', '', '河滨东路139号', '016', '', '13748589658', '待审核', null, '2024-04-08 22:44:54');
INSERT INTO `shenqingtuandui` VALUES ('12', '111', '测试', '数学', '1231', 'xxx', '001', 'J学校', '13769548711', '已通过', '001 001', '2024-04-08 23:06:42');
INSERT INTO `shenqingtuandui` VALUES ('13', '123', '团队测试', '数学', 'xxxxx', 'xxxx', 'admin1', 'G学校', '18000000000', '已通过', '帐号123密码001', '2024-04-09 10:02:23');

-- ----------------------------
-- Table structure for `tuanduifuzeren`
-- ----------------------------
DROP TABLE IF EXISTS `tuanduifuzeren`;
CREATE TABLE `tuanduifuzeren` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tuanduibianhao` varchar(50) DEFAULT NULL,
  `mima` varchar(50) DEFAULT NULL,
  `tuanduimingcheng` varchar(50) DEFAULT NULL,
  `tuanduileixing` varchar(50) DEFAULT NULL,
  `tuanduidizhi` varchar(50) DEFAULT NULL,
  `lianxidianhua` varchar(50) DEFAULT NULL,
  `xuexiaoxinxi` varchar(50) DEFAULT NULL,
  `tuanduirenshu` int(50) DEFAULT '0',
  `duiwei` varchar(50) DEFAULT NULL,
  `level` varchar(10) DEFAULT '团队负责人',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of tuanduifuzeren
-- ----------------------------
INSERT INTO `tuanduifuzeren` VALUES ('2', '111', 'dc5c7986daef50c1e02ab09b442ee34f', '测试', '数学', 'xxx', '13769548711', 'J学校', '0', null, '团队负责人', '2024-04-08 23:16:14');
INSERT INTO `tuanduifuzeren` VALUES ('3', '123', 'dc5c7986daef50c1e02ab09b442ee34f', '团队测试', '数学', 'xxxx', '18000000000', 'G学校', '2', null, '团队负责人', '2024-04-09 10:03:13');

-- ----------------------------
-- Table structure for `tuanyuan`
-- ----------------------------
DROP TABLE IF EXISTS `tuanyuan`;
CREATE TABLE `tuanyuan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shenqingbianhao` varchar(50) DEFAULT NULL,
  `tuanduibianhao` varchar(50) DEFAULT NULL,
  `tuanduimingcheng` varchar(50) DEFAULT NULL,
  `tuanduileixing` varchar(50) DEFAULT NULL,
  `xuesheng` varchar(50) DEFAULT NULL,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of tuanyuan
-- ----------------------------
INSERT INTO `tuanyuan` VALUES ('2', '202404098664', '123', '团队测试', '数学', '001', '2024-04-09 11:24:12');
INSERT INTO `tuanyuan` VALUES ('3', '202404092561', '123', '团队测试', '数学', 'admin1', '2024-04-09 11:24:42');

-- ----------------------------
-- Table structure for `xinwentongzhi`
-- ----------------------------
DROP TABLE IF EXISTS `xinwentongzhi`;
CREATE TABLE `xinwentongzhi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `biaoti` varchar(50) DEFAULT NULL,
  `leibie` varchar(50) DEFAULT NULL,
  `neirong` longtext,
  `dianjilv` varchar(50) DEFAULT NULL,
  `dianzan_d` int(11) DEFAULT '0',
  `dianzan_c` int(11) DEFAULT '0',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of xinwentongzhi
-- ----------------------------
INSERT INTO `xinwentongzhi` VALUES ('37', '公告', '公告', '<p>&nbsp;你这个有点复杂,&nbsp;我说一下逻辑</p>\n<p>大学生只能加入团员, 不能报名竞赛,&nbsp;</p>\n<p>只有团队负责人才能去报名</p>\n<p>大学生可以去申请团队负责人</p>\n<p>报名竞赛之后需要管理员审核通过才会显示 指导教师那边的一个评分按钮, 没通过&nbsp;是不会显示的</p>', null, '0', '0', '2024-04-09 11:20:25');

-- ----------------------------
-- Table structure for `xuexiaoxinxi`
-- ----------------------------
DROP TABLE IF EXISTS `xuexiaoxinxi`;
CREATE TABLE `xuexiaoxinxi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `xuexiaomingcheng` varchar(50) DEFAULT NULL,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of xuexiaoxinxi
-- ----------------------------
INSERT INTO `xuexiaoxinxi` VALUES ('2', 'E学校', '2024-04-08 22:44:54');
INSERT INTO `xuexiaoxinxi` VALUES ('3', 'G学校', '2024-04-08 22:44:54');
INSERT INTO `xuexiaoxinxi` VALUES ('4', 'K学校', '2024-04-08 22:44:54');
INSERT INTO `xuexiaoxinxi` VALUES ('5', 'J学校', '2024-04-08 22:44:54');
INSERT INTO `xuexiaoxinxi` VALUES ('6', 'F学校', '2024-04-08 22:44:54');
INSERT INTO `xuexiaoxinxi` VALUES ('7', 'C学校', '2024-04-08 22:44:54');
INSERT INTO `xuexiaoxinxi` VALUES ('8', 'H学校', '2024-04-08 22:44:54');
INSERT INTO `xuexiaoxinxi` VALUES ('9', 'D学校', '2024-04-08 22:44:54');
INSERT INTO `xuexiaoxinxi` VALUES ('10', 'N学校', '2024-04-08 22:44:54');
INSERT INTO `xuexiaoxinxi` VALUES ('11', 'P学校', '2024-04-08 22:44:54');

-- ----------------------------
-- Table structure for `zhidaojiaoshi`
-- ----------------------------
DROP TABLE IF EXISTS `zhidaojiaoshi`;
CREATE TABLE `zhidaojiaoshi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gonghao` varchar(50) DEFAULT NULL,
  `mima` varchar(50) DEFAULT NULL,
  `xingming` varchar(50) DEFAULT NULL,
  `xingbie` varchar(50) DEFAULT NULL,
  `nianling` varchar(50) DEFAULT NULL,
  `shoujihaoma` varchar(50) DEFAULT NULL,
  `zhaopian` varchar(50) DEFAULT NULL,
  `level` varchar(10) DEFAULT '指导教师',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of zhidaojiaoshi
-- ----------------------------
INSERT INTO `zhidaojiaoshi` VALUES ('2', '013', 'dc5c7986daef50c1e02ab09b442ee34f', '周盛', '1', '38', '17706641413', '', '指导教师', '2024-04-08 22:44:55');
INSERT INTO `zhidaojiaoshi` VALUES ('3', '008', 'dc5c7986daef50c1e02ab09b442ee34f', '李中国', '1', '20', '13544655202', '', '指导教师', '2024-04-08 22:44:55');
INSERT INTO `zhidaojiaoshi` VALUES ('4', '006', 'dc5c7986daef50c1e02ab09b442ee34f', '范冰冰', '1', '18', '13587835380', '', '指导教师', '2024-04-08 22:44:55');
INSERT INTO `zhidaojiaoshi` VALUES ('5', '026', 'dc5c7986daef50c1e02ab09b442ee34f', '陈明希', '0', '27', '13052154877', '', '指导教师', '2024-04-08 22:44:55');
INSERT INTO `zhidaojiaoshi` VALUES ('6', '019', 'dc5c7986daef50c1e02ab09b442ee34f', '余少庆', '0', '34', '13184865998', '', '指导教师', '2024-04-08 22:44:55');
INSERT INTO `zhidaojiaoshi` VALUES ('7', '018', 'dc5c7986daef50c1e02ab09b442ee34f', '陈燕琪', '0', '20', '13159863241', '', '指导教师', '2024-04-08 22:44:55');
INSERT INTO `zhidaojiaoshi` VALUES ('8', '017', 'dc5c7986daef50c1e02ab09b442ee34f', '陈小巧', '0', '27', '13186835580', '', '指导教师', '2024-04-08 22:44:55');
INSERT INTO `zhidaojiaoshi` VALUES ('9', '004', 'dc5c7986daef50c1e02ab09b442ee34f', '郭依洁', '0', '20', '13748589658', '', '指导教师', '2024-04-08 22:44:55');
INSERT INTO `zhidaojiaoshi` VALUES ('10', '011', 'dc5c7986daef50c1e02ab09b442ee34f', '司宪策', '0', '39', '18759640002', '', '指导教师', '2024-04-08 22:44:55');
INSERT INTO `zhidaojiaoshi` VALUES ('11', '001', 'dc5c7986daef50c1e02ab09b442ee34f', '陈贵柏', '0', '18', '13025896548', '', '指导教师', '2024-04-08 22:44:55');

-- ----------------------------
-- Table structure for `zuopinpingfen`
-- ----------------------------
DROP TABLE IF EXISTS `zuopinpingfen`;
CREATE TABLE `zuopinpingfen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `baomingbianhao` varchar(50) DEFAULT NULL,
  `jingsaibianhao` varchar(50) DEFAULT NULL,
  `jingsaimingcheng` varchar(50) DEFAULT NULL,
  `jingsaileixing` varchar(50) DEFAULT NULL,
  `zhidaolaoshi` varchar(50) DEFAULT NULL,
  `tuanduibianhao` varchar(50) DEFAULT NULL,
  `tuanduimingcheng` varchar(50) DEFAULT NULL,
  `tuanduirenshu` varchar(50) DEFAULT NULL,
  `xuexiaoxinxi` varchar(50) DEFAULT NULL,
  `zuopinmingcheng` varchar(50) DEFAULT NULL,
  `zuopinfenshu` varchar(50) DEFAULT NULL,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of zuopinpingfen
-- ----------------------------
INSERT INTO `zuopinpingfen` VALUES ('2', '202404094793', '202404089874', '测试', '数学', '001', '123', '团队测试', '2', 'G学校', '刚忘记写了', '99', '2024-04-09 11:27:03');
