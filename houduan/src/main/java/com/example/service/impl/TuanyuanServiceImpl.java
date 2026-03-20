package com.example.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.ResultCode;
import com.example.dao.*;
import com.example.entity.Tuanyuan;
import com.example.exception.CustomException;
import com.example.service.TuanyuanService;
import com.example.vo.TuanyuanVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@Service("tuanyuanService")
public class TuanyuanServiceImpl extends ServiceImpl<TuanyuanDao, Tuanyuan> implements TuanyuanService {

    @Resource
    private TuanyuanDao tuanyuanDao;

    public Tuanyuan add(Tuanyuan tuanyuan) {
		
	   //youquchong1
        tuanyuan.setAddtime(new Date());
        tuanyuanDao.insert(tuanyuan);
        return tuanyuan;
    }
	
	
	
    public void delete(Long id) {
        tuanyuanDao.deleteById(id);
    }
	

    public void update(Tuanyuan tuanyuan) {
        //youquchong2
        tuanyuanDao.updateById(tuanyuan);
    }
	
	@Override
    public List<Map<String, Object>> daochuexcel() {
        return tuanyuanDao.daochuexcel();
    }

    @Override
    public Tuanyuan findById(Integer id) {
        return tuanyuanDao.selectById(id);
    }

    public TuanyuanVo findPage(TuanyuanVo tuanyuanVo) {
        LambdaQueryWrapper<Tuanyuan> wrapper = Wrappers.lambdaQuery();
		wrapper.like(ObjectUtils.isNotEmpty(tuanyuanVo.getShenqingbianhao()), Tuanyuan::getShenqingbianhao,tuanyuanVo.getShenqingbianhao());        wrapper.like(ObjectUtils.isNotEmpty(tuanyuanVo.getTuanduibianhao()), Tuanyuan::getTuanduibianhao,tuanyuanVo.getTuanduibianhao());        wrapper.like(ObjectUtils.isNotEmpty(tuanyuanVo.getTuanduimingcheng()), Tuanyuan::getTuanduimingcheng,tuanyuanVo.getTuanduimingcheng());        wrapper.like(ObjectUtils.isNotEmpty(tuanyuanVo.getTuanduileixing()), Tuanyuan::getTuanduileixing,tuanyuanVo.getTuanduileixing());        wrapper.like(ObjectUtils.isNotEmpty(tuanyuanVo.getXuesheng()), Tuanyuan::getXuesheng,tuanyuanVo.getXuesheng());        
        Page<Tuanyuan> page = new Page<>(tuanyuanVo.getCurrent(),tuanyuanVo.getCurrentNum());
        tuanyuanDao.selectPage(page,wrapper);
        List<Tuanyuan> tuanyuans = page.getRecords();
        tuanyuanVo.setList(tuanyuans);
        tuanyuanVo.setPages(page.getPages());
        tuanyuanVo.setTotal(page.getTotal());
        tuanyuanVo.setCurrent(page.getCurrent());
        tuanyuanVo.setCurrentNum(page.getSize());
        return tuanyuanVo;
    }

    @Override
    public void deleteList(List<Tuanyuan> list) {
        List<Integer> ids =  list.stream().map(Tuanyuan::getId).collect(Collectors.toList());
        tuanyuanDao.deleteBatchIds(ids);
    }
	
	
	
	
	
	//youxxianxci
}
