package com.example.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.ResultCode;
import com.example.dao.*;
import com.example.entity.Shenqingtuandui;
import com.example.exception.CustomException;
import com.example.service.ShenqingtuanduiService;
import com.example.vo.ShenqingtuanduiVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@Service("shenqingtuanduiService")
public class ShenqingtuanduiServiceImpl extends ServiceImpl<ShenqingtuanduiDao, Shenqingtuandui> implements ShenqingtuanduiService {

    @Resource
    private ShenqingtuanduiDao shenqingtuanduiDao;

    public Shenqingtuandui add(Shenqingtuandui shenqingtuandui) {
		
	    LambdaQueryWrapper<Shenqingtuandui> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Shenqingtuandui::getTuanduibianhao,shenqingtuandui.getTuanduibianhao());
        Shenqingtuandui findShenqingtuandui = shenqingtuanduiDao.selectOne(wrapper);
        if (findShenqingtuandui != null) {
            throw new CustomException("1001", "" + shenqingtuandui.getTuanduibianhao() + "\"已存在");
        }
        shenqingtuandui.setAddtime(new Date());
        shenqingtuanduiDao.insert(shenqingtuandui);
        return shenqingtuandui;
    }
	
	
	
    public void delete(Long id) {
        shenqingtuanduiDao.deleteById(id);
    }
	

    public void update(Shenqingtuandui shenqingtuandui) {
         LambdaQueryWrapper<Shenqingtuandui> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Shenqingtuandui::getTuanduibianhao,shenqingtuandui.getTuanduibianhao());
        Shenqingtuandui findShenqingtuandui = shenqingtuanduiDao.selectOne(wrapper);
		if (findShenqingtuandui != null && shenqingtuandui.getId() != findShenqingtuandui.getId()) {
            throw new CustomException("1001", "" + shenqingtuandui.getTuanduibianhao() + "\"已存在");
        }
        shenqingtuanduiDao.updateById(shenqingtuandui);
    }
	
	@Override
    public List<Map<String, Object>> daochuexcel() {
        return shenqingtuanduiDao.daochuexcel();
    }

    @Override
    public Shenqingtuandui findById(Integer id) {
        return shenqingtuanduiDao.selectById(id);
    }

    public ShenqingtuanduiVo findPage(ShenqingtuanduiVo shenqingtuanduiVo) {
        LambdaQueryWrapper<Shenqingtuandui> wrapper = Wrappers.lambdaQuery();
		wrapper.like(ObjectUtils.isNotEmpty(shenqingtuanduiVo.getTuanduibianhao()), Shenqingtuandui::getTuanduibianhao,shenqingtuanduiVo.getTuanduibianhao());        wrapper.like(ObjectUtils.isNotEmpty(shenqingtuanduiVo.getTuanduimingcheng()), Shenqingtuandui::getTuanduimingcheng,shenqingtuanduiVo.getTuanduimingcheng());        wrapper.like(ObjectUtils.isNotEmpty(shenqingtuanduiVo.getTuanduileixing()), Shenqingtuandui::getTuanduileixing,shenqingtuanduiVo.getTuanduileixing());        wrapper.like(ObjectUtils.isNotEmpty(shenqingtuanduiVo.getTuanduijieshao()), Shenqingtuandui::getTuanduijieshao,shenqingtuanduiVo.getTuanduijieshao());        wrapper.like(ObjectUtils.isNotEmpty(shenqingtuanduiVo.getTuanduidizhi()), Shenqingtuandui::getTuanduidizhi,shenqingtuanduiVo.getTuanduidizhi());        wrapper.like(ObjectUtils.isNotEmpty(shenqingtuanduiVo.getXuehao()), Shenqingtuandui::getXuehao,shenqingtuanduiVo.getXuehao());        wrapper.like(ObjectUtils.isNotEmpty(shenqingtuanduiVo.getXuexiaoxinxi()), Shenqingtuandui::getXuexiaoxinxi,shenqingtuanduiVo.getXuexiaoxinxi());        wrapper.like(ObjectUtils.isNotEmpty(shenqingtuanduiVo.getLianxidianhua()), Shenqingtuandui::getLianxidianhua,shenqingtuanduiVo.getLianxidianhua());        wrapper.like(ObjectUtils.isNotEmpty(shenqingtuanduiVo.getIssh()), Shenqingtuandui::getIssh,shenqingtuanduiVo.getIssh());        
        Page<Shenqingtuandui> page = new Page<>(shenqingtuanduiVo.getCurrent(),shenqingtuanduiVo.getCurrentNum());
        shenqingtuanduiDao.selectPage(page,wrapper);
        List<Shenqingtuandui> shenqingtuanduis = page.getRecords();
        shenqingtuanduiVo.setList(shenqingtuanduis);
        shenqingtuanduiVo.setPages(page.getPages());
        shenqingtuanduiVo.setTotal(page.getTotal());
        shenqingtuanduiVo.setCurrent(page.getCurrent());
        shenqingtuanduiVo.setCurrentNum(page.getSize());
        return shenqingtuanduiVo;
    }

    @Override
    public void deleteList(List<Shenqingtuandui> list) {
        List<Integer> ids =  list.stream().map(Shenqingtuandui::getId).collect(Collectors.toList());
        shenqingtuanduiDao.deleteBatchIds(ids);
    }
	
	
	
	
	
	public Integer hsggetxianci(String xuehao) { return shenqingtuanduiDao.hsggetxianci(xuehao); }
}
