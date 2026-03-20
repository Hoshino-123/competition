package com.example.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.ResultCode;
import com.example.dao.*;
import com.example.entity.Tuanduifuzeren;
import com.example.exception.CustomException;
import com.example.service.TuanduifuzerenService;
import com.example.vo.TuanduifuzerenVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@Service("tuanduifuzerenService")
public class TuanduifuzerenServiceImpl extends ServiceImpl<TuanduifuzerenDao, Tuanduifuzeren> implements TuanduifuzerenService {

    @Resource
    private TuanduifuzerenDao tuanduifuzerenDao;

    public Tuanduifuzeren add(Tuanduifuzeren tuanduifuzeren) {
		if(tuanduifuzeren.getMima()==null) {tuanduifuzeren.setMima(SecureUtil.md5("001"));}else {tuanduifuzeren.setMima(SecureUtil.md5(tuanduifuzeren.getMima())); }
	   //youquchong1
        tuanduifuzeren.setAddtime(new Date());
        tuanduifuzerenDao.insert(tuanduifuzeren);
        return tuanduifuzeren;
    }
	
	
	
    public void delete(Long id) {
        tuanduifuzerenDao.deleteById(id);
    }
	

    public void update(Tuanduifuzeren tuanduifuzeren) {
        //youquchong2
        tuanduifuzerenDao.updateById(tuanduifuzeren);
    }
	
	@Override
    public List<Map<String, Object>> daochuexcel() {
        return tuanduifuzerenDao.daochuexcel();
    }

    @Override
    public Tuanduifuzeren findById(Integer id) {
        return tuanduifuzerenDao.selectById(id);
    }

    public TuanduifuzerenVo findPage(TuanduifuzerenVo tuanduifuzerenVo) {
        LambdaQueryWrapper<Tuanduifuzeren> wrapper = Wrappers.lambdaQuery();
		wrapper.like(ObjectUtils.isNotEmpty(tuanduifuzerenVo.getTuanduibianhao()), Tuanduifuzeren::getTuanduibianhao,tuanduifuzerenVo.getTuanduibianhao());        wrapper.like(ObjectUtils.isNotEmpty(tuanduifuzerenVo.getMima()), Tuanduifuzeren::getMima,tuanduifuzerenVo.getMima());        wrapper.like(ObjectUtils.isNotEmpty(tuanduifuzerenVo.getTuanduimingcheng()), Tuanduifuzeren::getTuanduimingcheng,tuanduifuzerenVo.getTuanduimingcheng());        wrapper.like(ObjectUtils.isNotEmpty(tuanduifuzerenVo.getTuanduileixing()), Tuanduifuzeren::getTuanduileixing,tuanduifuzerenVo.getTuanduileixing());        wrapper.like(ObjectUtils.isNotEmpty(tuanduifuzerenVo.getTuanduidizhi()), Tuanduifuzeren::getTuanduidizhi,tuanduifuzerenVo.getTuanduidizhi());        wrapper.like(ObjectUtils.isNotEmpty(tuanduifuzerenVo.getLianxidianhua()), Tuanduifuzeren::getLianxidianhua,tuanduifuzerenVo.getLianxidianhua());        wrapper.like(ObjectUtils.isNotEmpty(tuanduifuzerenVo.getXuexiaoxinxi()), Tuanduifuzeren::getXuexiaoxinxi,tuanduifuzerenVo.getXuexiaoxinxi());        wrapper.like(ObjectUtils.isNotEmpty(tuanduifuzerenVo.getTuanduirenshu()), Tuanduifuzeren::getTuanduirenshu,tuanduifuzerenVo.getTuanduirenshu());        wrapper.like(ObjectUtils.isNotEmpty(tuanduifuzerenVo.getDuiwei()), Tuanduifuzeren::getDuiwei,tuanduifuzerenVo.getDuiwei());        
        Page<Tuanduifuzeren> page = new Page<>(tuanduifuzerenVo.getCurrent(),tuanduifuzerenVo.getCurrentNum());
        tuanduifuzerenDao.selectPage(page,wrapper);
        List<Tuanduifuzeren> tuanduifuzerens = page.getRecords();
        tuanduifuzerenVo.setList(tuanduifuzerens);
        tuanduifuzerenVo.setPages(page.getPages());
        tuanduifuzerenVo.setTotal(page.getTotal());
        tuanduifuzerenVo.setCurrent(page.getCurrent());
        tuanduifuzerenVo.setCurrentNum(page.getSize());
        return tuanduifuzerenVo;
    }

    @Override
    public void deleteList(List<Tuanduifuzeren> list) {
        List<Integer> ids =  list.stream().map(Tuanduifuzeren::getId).collect(Collectors.toList());
        tuanduifuzerenDao.deleteBatchIds(ids);
    }
	
	@Override
    public Tuanduifuzeren login(Tuanduifuzeren tuanduifuzeren) {
        LambdaQueryWrapper<Tuanduifuzeren> wrapper = new LambdaQueryWrapper();
        wrapper.eq(ObjectUtils.isNotEmpty(tuanduifuzeren.getTuanduibianhao()), Tuanduifuzeren::getTuanduibianhao,tuanduifuzeren.getTuanduibianhao());
        Tuanduifuzeren tuanduifuzerenInfo = tuanduifuzerenDao.selectOne(wrapper);
        if (tuanduifuzerenInfo == null) {
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        if (!SecureUtil.md5(tuanduifuzeren.getMima()).equalsIgnoreCase(tuanduifuzerenInfo.getMima())) {
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        return tuanduifuzerenInfo;
    }
	
	
	
	//youxxianxci
}
