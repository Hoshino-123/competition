package com.example.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.ResultCode;
import com.example.dao.*;
import com.example.entity.Jingsaixinxi;
import com.example.exception.CustomException;
import com.example.service.CollectService;
import com.example.service.JingsaixinxiService;
import com.example.utils.UserBasedCollaborativeFiltering;
import com.example.vo.JingsaixinxiVo;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service("jingsaixinxiService")
public class JingsaixinxiServiceImpl extends ServiceImpl<JingsaixinxiDao, Jingsaixinxi> implements JingsaixinxiService {

    @Resource
    private JingsaixinxiDao jingsaixinxiDao;

    public Jingsaixinxi add(Jingsaixinxi jingsaixinxi) {
		
	   //youquchong1
        jingsaixinxi.setAddtime(new Date());
        jingsaixinxiDao.insert(jingsaixinxi);
        return jingsaixinxi;
    }


    @Lazy
    @Resource
    private CollectService collectService;



    @Override
    public Map<String, Double> recommend(Integer userId, Integer num) {
        // 创建用户-物品收藏矩阵
        Map<String, Set<String>> userItemCollections = new HashMap<>();

        List<Map<String, String>> maps = collectService.getCollected();

        for (Map<String, String> map : maps) {
            userItemCollections.put(String.valueOf(map.get("user_id")), new HashSet<>(Arrays.asList(map.get("items").split(","))));
        }

        UserBasedCollaborativeFiltering cf = new UserBasedCollaborativeFiltering(userItemCollections);

        // 为目标用户推荐物品
        Map<String, Double> recommendations = cf.recommendItems(String.valueOf(userId), num);

        // 输出推荐结果
        System.err.println("基于用户收藏的推荐 用户ID:" + userId);
        for (Map.Entry<String, Double> entry : recommendations.entrySet()) {
            System.err.println("商品ID: " + entry.getKey() + ", 分数: " + entry.getValue());
        }

        return recommendations;
    }

    @Override
    public List<Jingsaixinxi> findByIds(List<Integer> ids) {
        return jingsaixinxiDao.selectBatchIds(ids);
    }


    @Override
    public List<Map<String,Object>> jingsaixinxi_tj_cansairenshu() {
        List<Map<String,Object>> returnMap = jingsaixinxiDao.jingsaixinxi_tj_cansairenshu();
        return returnMap;
    }


    public void delete(Long id) {
        jingsaixinxiDao.deleteById(id);
    }
	

    public void update(Jingsaixinxi jingsaixinxi) {
        //youquchong2
        jingsaixinxiDao.updateById(jingsaixinxi);
    }
	
	@Override
    public List<Map<String, Object>> daochuexcel() {
        return jingsaixinxiDao.daochuexcel();
    }

    @Override
    public Jingsaixinxi findById(Integer id) {
        return jingsaixinxiDao.selectById(id);
    }

    public JingsaixinxiVo findPage(JingsaixinxiVo jingsaixinxiVo) {
        LambdaQueryWrapper<Jingsaixinxi> wrapper = Wrappers.lambdaQuery();
		wrapper.like(ObjectUtils.isNotEmpty(jingsaixinxiVo.getJingsaibianhao()), Jingsaixinxi::getJingsaibianhao,jingsaixinxiVo.getJingsaibianhao());
        wrapper.like(ObjectUtils.isNotEmpty(jingsaixinxiVo.getJingsaimingcheng()), Jingsaixinxi::getJingsaimingcheng,jingsaixinxiVo.getJingsaimingcheng());
        wrapper.like(ObjectUtils.isNotEmpty(jingsaixinxiVo.getJingsaileixing()), Jingsaixinxi::getJingsaileixing,jingsaixinxiVo.getJingsaileixing());
        wrapper.like(ObjectUtils.isNotEmpty(jingsaixinxiVo.getJingsaididian()), Jingsaixinxi::getJingsaididian,jingsaixinxiVo.getJingsaididian());
        wrapper.like(ObjectUtils.isNotEmpty(jingsaixinxiVo.getJingsaishijian()), Jingsaixinxi::getJingsaishijian,jingsaixinxiVo.getJingsaishijian());
        wrapper.like(ObjectUtils.isNotEmpty(jingsaixinxiVo.getJingsaineirong()), Jingsaixinxi::getJingsaineirong,jingsaixinxiVo.getJingsaineirong());
        wrapper.like(ObjectUtils.isNotEmpty(jingsaixinxiVo.getJingsaifengmian()), Jingsaixinxi::getJingsaifengmian,jingsaixinxiVo.getJingsaifengmian());
        wrapper.like(ObjectUtils.isNotEmpty(jingsaixinxiVo.getCansairenshu()), Jingsaixinxi::getCansairenshu,jingsaixinxiVo.getCansairenshu());
        wrapper.like(ObjectUtils.isNotEmpty(jingsaixinxiVo.getZhidaolaoshi()), Jingsaixinxi::getZhidaolaoshi,jingsaixinxiVo.getZhidaolaoshi());
        
        Page<Jingsaixinxi> page = new Page<>(jingsaixinxiVo.getCurrent(),jingsaixinxiVo.getCurrentNum());
        jingsaixinxiDao.selectPage(page,wrapper);
        List<Jingsaixinxi> jingsaixinxis = page.getRecords();
        jingsaixinxiVo.setList(jingsaixinxis);
        jingsaixinxiVo.setPages(page.getPages());
        jingsaixinxiVo.setTotal(page.getTotal());
        jingsaixinxiVo.setCurrent(page.getCurrent());
        jingsaixinxiVo.setCurrentNum(page.getSize());
        return jingsaixinxiVo;
    }

    @Override
    public void deleteList(List<Jingsaixinxi> list) {
        List<Integer> ids =  list.stream().map(Jingsaixinxi::getId).collect(Collectors.toList());
        jingsaixinxiDao.deleteBatchIds(ids);
    }
	
	
	
	
	
	//youxxianxci
}
