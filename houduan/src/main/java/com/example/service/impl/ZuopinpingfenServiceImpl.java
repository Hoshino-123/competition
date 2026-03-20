package com.example.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.ResultCode;
import com.example.dao.*;
import com.example.entity.Zuopinpingfen;
import com.example.exception.CustomException;
import com.example.service.ZuopinpingfenService;
import com.example.vo.ZuopinpingfenVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@Service("zuopinpingfenService")
public class ZuopinpingfenServiceImpl extends ServiceImpl<ZuopinpingfenDao, Zuopinpingfen> implements ZuopinpingfenService {

    @Resource
    private ZuopinpingfenDao zuopinpingfenDao;

    public Zuopinpingfen add(Zuopinpingfen zuopinpingfen) {
		
	   //youquchong1
        zuopinpingfen.setAddtime(new Date());
        zuopinpingfenDao.insert(zuopinpingfen);
        return zuopinpingfen;
    }
	
	@Override    public List<Map<String,Object>> zuopinpingfen_tj_jingsaileixing() {    List<Map<String,Object>> returnMap = zuopinpingfenDao.zuopinpingfen_tj_jingsaileixing();    return returnMap;    }    @Override    public List<Map<String,Object>> zuopinpingfen_tj_xuexiaoxinxi() {    List<Map<String,Object>> returnMap = zuopinpingfenDao.zuopinpingfen_tj_xuexiaoxinxi();    return returnMap;    }    @Override    public List<Map<String,Object>> zuopinpingfen_tj_zuopinmingcheng() {    List<Map<String,Object>> returnMap = zuopinpingfenDao.zuopinpingfen_tj_zuopinmingcheng();    return returnMap;    }    
	
    public void delete(Long id) {
        zuopinpingfenDao.deleteById(id);
    }
	

    public void update(Zuopinpingfen zuopinpingfen) {
        //youquchong2
        zuopinpingfenDao.updateById(zuopinpingfen);
    }
	
	@Override
    public List<Map<String, Object>> daochuexcel() {
        return zuopinpingfenDao.daochuexcel();
    }

    @Override
    public Zuopinpingfen findById(Integer id) {
        return zuopinpingfenDao.selectById(id);
    }

    public ZuopinpingfenVo findPage(ZuopinpingfenVo zuopinpingfenVo) {
        LambdaQueryWrapper<Zuopinpingfen> wrapper = Wrappers.lambdaQuery();
		wrapper.like(ObjectUtils.isNotEmpty(zuopinpingfenVo.getBaomingbianhao()), Zuopinpingfen::getBaomingbianhao,zuopinpingfenVo.getBaomingbianhao());        wrapper.like(ObjectUtils.isNotEmpty(zuopinpingfenVo.getJingsaibianhao()), Zuopinpingfen::getJingsaibianhao,zuopinpingfenVo.getJingsaibianhao());        wrapper.like(ObjectUtils.isNotEmpty(zuopinpingfenVo.getJingsaimingcheng()), Zuopinpingfen::getJingsaimingcheng,zuopinpingfenVo.getJingsaimingcheng());        wrapper.like(ObjectUtils.isNotEmpty(zuopinpingfenVo.getJingsaileixing()), Zuopinpingfen::getJingsaileixing,zuopinpingfenVo.getJingsaileixing());        wrapper.like(ObjectUtils.isNotEmpty(zuopinpingfenVo.getZhidaolaoshi()), Zuopinpingfen::getZhidaolaoshi,zuopinpingfenVo.getZhidaolaoshi());        wrapper.like(ObjectUtils.isNotEmpty(zuopinpingfenVo.getTuanduibianhao()), Zuopinpingfen::getTuanduibianhao,zuopinpingfenVo.getTuanduibianhao());        wrapper.like(ObjectUtils.isNotEmpty(zuopinpingfenVo.getTuanduimingcheng()), Zuopinpingfen::getTuanduimingcheng,zuopinpingfenVo.getTuanduimingcheng());        wrapper.like(ObjectUtils.isNotEmpty(zuopinpingfenVo.getTuanduirenshu()), Zuopinpingfen::getTuanduirenshu,zuopinpingfenVo.getTuanduirenshu());        wrapper.like(ObjectUtils.isNotEmpty(zuopinpingfenVo.getXuexiaoxinxi()), Zuopinpingfen::getXuexiaoxinxi,zuopinpingfenVo.getXuexiaoxinxi());        wrapper.like(ObjectUtils.isNotEmpty(zuopinpingfenVo.getZuopinmingcheng()), Zuopinpingfen::getZuopinmingcheng,zuopinpingfenVo.getZuopinmingcheng());        wrapper.like(ObjectUtils.isNotEmpty(zuopinpingfenVo.getZuopinfenshu()), Zuopinpingfen::getZuopinfenshu,zuopinpingfenVo.getZuopinfenshu());        
        Page<Zuopinpingfen> page = new Page<>(zuopinpingfenVo.getCurrent(),zuopinpingfenVo.getCurrentNum());
        zuopinpingfenDao.selectPage(page,wrapper);
        List<Zuopinpingfen> zuopinpingfens = page.getRecords();
        zuopinpingfenVo.setList(zuopinpingfens);
        zuopinpingfenVo.setPages(page.getPages());
        zuopinpingfenVo.setTotal(page.getTotal());
        zuopinpingfenVo.setCurrent(page.getCurrent());
        zuopinpingfenVo.setCurrentNum(page.getSize());
        return zuopinpingfenVo;
    }

    @Override
    public void deleteList(List<Zuopinpingfen> list) {
        List<Integer> ids =  list.stream().map(Zuopinpingfen::getId).collect(Collectors.toList());
        zuopinpingfenDao.deleteBatchIds(ids);
    }
	
	
	
	
	
	//youxxianxci
}
