package com.example.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.ResultCode;
import com.example.dao.*;
import com.example.entity.Baomingcansai;
import com.example.exception.CustomException;
import com.example.service.BaomingcansaiService;
import com.example.vo.BaomingcansaiVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@Service("baomingcansaiService")
public class BaomingcansaiServiceImpl extends ServiceImpl<BaomingcansaiDao, Baomingcansai> implements BaomingcansaiService {

    @Resource
    private BaomingcansaiDao baomingcansaiDao;

    public Baomingcansai add(Baomingcansai baomingcansai) {
		
	   //youquchong1
        baomingcansai.setAddtime(new Date());
        baomingcansaiDao.insert(baomingcansai);
        return baomingcansai;
    }
	
	
	
    public void delete(Long id) {
        baomingcansaiDao.deleteById(id);
    }
	

    public void update(Baomingcansai baomingcansai) {
        //youquchong2
        baomingcansaiDao.updateById(baomingcansai);
    }
	
	@Override
    public List<Map<String, Object>> daochuexcel() {
        return baomingcansaiDao.daochuexcel();
    }

    @Override
    public Baomingcansai findById(Integer id) {
        return baomingcansaiDao.selectById(id);
    }

    public BaomingcansaiVo findPage(BaomingcansaiVo baomingcansaiVo) {
        LambdaQueryWrapper<Baomingcansai> wrapper = Wrappers.lambdaQuery();
		wrapper.like(ObjectUtils.isNotEmpty(baomingcansaiVo.getBaomingbianhao()), Baomingcansai::getBaomingbianhao,baomingcansaiVo.getBaomingbianhao());        wrapper.like(ObjectUtils.isNotEmpty(baomingcansaiVo.getJingsaibianhao()), Baomingcansai::getJingsaibianhao,baomingcansaiVo.getJingsaibianhao());        wrapper.like(ObjectUtils.isNotEmpty(baomingcansaiVo.getJingsaimingcheng()), Baomingcansai::getJingsaimingcheng,baomingcansaiVo.getJingsaimingcheng());        wrapper.like(ObjectUtils.isNotEmpty(baomingcansaiVo.getJingsaileixing()), Baomingcansai::getJingsaileixing,baomingcansaiVo.getJingsaileixing());        wrapper.like(ObjectUtils.isNotEmpty(baomingcansaiVo.getJingsaididian()), Baomingcansai::getJingsaididian,baomingcansaiVo.getJingsaididian());        wrapper.like(ObjectUtils.isNotEmpty(baomingcansaiVo.getZhidaolaoshi()), Baomingcansai::getZhidaolaoshi,baomingcansaiVo.getZhidaolaoshi());        wrapper.like(ObjectUtils.isNotEmpty(baomingcansaiVo.getTuanduibianhao()), Baomingcansai::getTuanduibianhao,baomingcansaiVo.getTuanduibianhao());        wrapper.like(ObjectUtils.isNotEmpty(baomingcansaiVo.getTuanduimingcheng()), Baomingcansai::getTuanduimingcheng,baomingcansaiVo.getTuanduimingcheng());        wrapper.like(ObjectUtils.isNotEmpty(baomingcansaiVo.getTuanduirenshu()), Baomingcansai::getTuanduirenshu,baomingcansaiVo.getTuanduirenshu());        wrapper.like(ObjectUtils.isNotEmpty(baomingcansaiVo.getXuexiaoxinxi()), Baomingcansai::getXuexiaoxinxi,baomingcansaiVo.getXuexiaoxinxi());        wrapper.like(ObjectUtils.isNotEmpty(baomingcansaiVo.getZuopinmingcheng()), Baomingcansai::getZuopinmingcheng,baomingcansaiVo.getZuopinmingcheng());        wrapper.like(ObjectUtils.isNotEmpty(baomingcansaiVo.getCansaizuopin()), Baomingcansai::getCansaizuopin,baomingcansaiVo.getCansaizuopin());        wrapper.like(ObjectUtils.isNotEmpty(baomingcansaiVo.getIssh()), Baomingcansai::getIssh,baomingcansaiVo.getIssh());        
        Page<Baomingcansai> page = new Page<>(baomingcansaiVo.getCurrent(),baomingcansaiVo.getCurrentNum());
        baomingcansaiDao.selectPage(page,wrapper);
        List<Baomingcansai> baomingcansais = page.getRecords();
        baomingcansaiVo.setList(baomingcansais);
        baomingcansaiVo.setPages(page.getPages());
        baomingcansaiVo.setTotal(page.getTotal());
        baomingcansaiVo.setCurrent(page.getCurrent());
        baomingcansaiVo.setCurrentNum(page.getSize());
        return baomingcansaiVo;
    }

    @Override
    public void deleteList(List<Baomingcansai> list) {
        List<Integer> ids =  list.stream().map(Baomingcansai::getId).collect(Collectors.toList());
        baomingcansaiDao.deleteBatchIds(ids);
    }
	
	
	
	
	
	public Integer hsggetxianci(String jingsaibianhao,String tuanduibianhao) { return baomingcansaiDao.hsggetxianci(jingsaibianhao,tuanduibianhao); }
}
