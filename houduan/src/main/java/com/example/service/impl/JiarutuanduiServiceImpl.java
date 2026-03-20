package com.example.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.ResultCode;
import com.example.dao.*;
import com.example.entity.Jiarutuandui;
import com.example.exception.CustomException;
import com.example.service.JiarutuanduiService;
import com.example.vo.JiarutuanduiVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@Service("jiarutuanduiService")
public class JiarutuanduiServiceImpl extends ServiceImpl<JiarutuanduiDao, Jiarutuandui> implements JiarutuanduiService {

    @Resource
    private JiarutuanduiDao jiarutuanduiDao;

    public Jiarutuandui add(Jiarutuandui jiarutuandui) {
		
	   //youquchong1
        jiarutuandui.setAddtime(new Date());
        jiarutuanduiDao.insert(jiarutuandui);
        return jiarutuandui;
    }
	
	
	
    public void delete(Long id) {
        jiarutuanduiDao.deleteById(id);
    }
	

    public void update(Jiarutuandui jiarutuandui) {
        //youquchong2
        jiarutuanduiDao.updateById(jiarutuandui);
    }
	
	@Override
    public List<Map<String, Object>> daochuexcel() {
        return jiarutuanduiDao.daochuexcel();
    }

    @Override
    public Jiarutuandui findById(Integer id) {
        return jiarutuanduiDao.selectById(id);
    }

    public JiarutuanduiVo findPage(JiarutuanduiVo jiarutuanduiVo) {
        LambdaQueryWrapper<Jiarutuandui> wrapper = Wrappers.lambdaQuery();
		wrapper.like(ObjectUtils.isNotEmpty(jiarutuanduiVo.getShenqingbianhao()), Jiarutuandui::getShenqingbianhao,jiarutuanduiVo.getShenqingbianhao());        wrapper.like(ObjectUtils.isNotEmpty(jiarutuanduiVo.getTuanduibianhao()), Jiarutuandui::getTuanduibianhao,jiarutuanduiVo.getTuanduibianhao());        wrapper.like(ObjectUtils.isNotEmpty(jiarutuanduiVo.getTuanduimingcheng()), Jiarutuandui::getTuanduimingcheng,jiarutuanduiVo.getTuanduimingcheng());        wrapper.like(ObjectUtils.isNotEmpty(jiarutuanduiVo.getTuanduileixing()), Jiarutuandui::getTuanduileixing,jiarutuanduiVo.getTuanduileixing());        wrapper.like(ObjectUtils.isNotEmpty(jiarutuanduiVo.getXuexiaoxinxi()), Jiarutuandui::getXuexiaoxinxi,jiarutuanduiVo.getXuexiaoxinxi());        wrapper.like(ObjectUtils.isNotEmpty(jiarutuanduiVo.getXuesheng()), Jiarutuandui::getXuesheng,jiarutuanduiVo.getXuesheng());        wrapper.eq(ObjectUtils.isNotEmpty(jiarutuanduiVo.getIssh()), Jiarutuandui::getIssh,jiarutuanduiVo.getIssh());        
        Page<Jiarutuandui> page = new Page<>(jiarutuanduiVo.getCurrent(),jiarutuanduiVo.getCurrentNum());
        jiarutuanduiDao.selectPage(page,wrapper);
        List<Jiarutuandui> jiarutuanduis = page.getRecords();
        jiarutuanduiVo.setList(jiarutuanduis);
        jiarutuanduiVo.setPages(page.getPages());
        jiarutuanduiVo.setTotal(page.getTotal());
        jiarutuanduiVo.setCurrent(page.getCurrent());
        jiarutuanduiVo.setCurrentNum(page.getSize());
        return jiarutuanduiVo;
    }

    @Override
    public void deleteList(List<Jiarutuandui> list) {
        List<Integer> ids =  list.stream().map(Jiarutuandui::getId).collect(Collectors.toList());
        jiarutuanduiDao.deleteBatchIds(ids);
    }
	
	
	
	
	
	public Integer hsggetxianci(String tuanduibianhao,String xuesheng) { return jiarutuanduiDao.hsggetxianci(tuanduibianhao,xuesheng); }
}
