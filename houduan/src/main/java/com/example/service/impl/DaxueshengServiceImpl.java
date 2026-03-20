package com.example.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.ResultCode;
import com.example.dao.*;
import com.example.entity.Daxuesheng;
import com.example.exception.CustomException;
import com.example.service.DaxueshengService;
import com.example.vo.DaxueshengVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@Service("daxueshengService")
public class DaxueshengServiceImpl extends ServiceImpl<DaxueshengDao, Daxuesheng> implements DaxueshengService {

    @Resource
    private DaxueshengDao daxueshengDao;

    public Daxuesheng add(Daxuesheng daxuesheng) {
		if(daxuesheng.getMima()==null) {daxuesheng.setMima(SecureUtil.md5("001"));}else {daxuesheng.setMima(SecureUtil.md5(daxuesheng.getMima())); }
	    LambdaQueryWrapper<Daxuesheng> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Daxuesheng::getXuehao,daxuesheng.getXuehao());
        Daxuesheng findDaxuesheng = daxueshengDao.selectOne(wrapper);
        if (findDaxuesheng != null) {
            throw new CustomException("1001", "" + daxuesheng.getXuehao() + "\"已存在");
        }
        daxuesheng.setAddtime(new Date());
        daxueshengDao.insert(daxuesheng);
        return daxuesheng;
    }


    @Override
    public void findyonghuyonghuming(Daxuesheng daxuesheng) {
        LambdaQueryWrapper<Daxuesheng> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Daxuesheng::getXuehao,daxuesheng.getXuehao());
        Daxuesheng findDaxuesheng = daxueshengDao.selectOne(wrapper);
        if (findDaxuesheng != null) {
            throw new CustomException("1001", "" + daxuesheng.getXuehao() + "\"已存在");
        }
    }
	
	
    public void delete(Long id) {
        daxueshengDao.deleteById(id);
    }
	

    public void update(Daxuesheng daxuesheng) {
         LambdaQueryWrapper<Daxuesheng> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Daxuesheng::getXuehao,daxuesheng.getXuehao());
        Daxuesheng findDaxuesheng = daxueshengDao.selectOne(wrapper);
		if (findDaxuesheng != null && daxuesheng.getId() != findDaxuesheng.getId()) {
            throw new CustomException("1001", "" + daxuesheng.getXuehao() + "\"已存在");
        }
        daxueshengDao.updateById(daxuesheng);
    }
	
	@Override
    public List<Map<String, Object>> daochuexcel() {
        return daxueshengDao.daochuexcel();
    }

    @Override
    public Daxuesheng findById(Integer id) {
        return daxueshengDao.selectById(id);
    }

    public DaxueshengVo findPage(DaxueshengVo daxueshengVo) {
        LambdaQueryWrapper<Daxuesheng> wrapper = Wrappers.lambdaQuery();
		wrapper.like(ObjectUtils.isNotEmpty(daxueshengVo.getXuehao()), Daxuesheng::getXuehao,daxueshengVo.getXuehao());
        wrapper.like(ObjectUtils.isNotEmpty(daxueshengVo.getMima()), Daxuesheng::getMima,daxueshengVo.getMima());
        wrapper.like(ObjectUtils.isNotEmpty(daxueshengVo.getXingming()), Daxuesheng::getXingming,daxueshengVo.getXingming());
        wrapper.eq(ObjectUtils.isNotEmpty(daxueshengVo.getXingbie()), Daxuesheng::getXingbie,daxueshengVo.getXingbie());
        wrapper.like(ObjectUtils.isNotEmpty(daxueshengVo.getXuexiaoxinxi()), Daxuesheng::getXuexiaoxinxi,daxueshengVo.getXuexiaoxinxi());
        wrapper.like(ObjectUtils.isNotEmpty(daxueshengVo.getZhuanye()), Daxuesheng::getZhuanye,daxueshengVo.getZhuanye());
        wrapper.like(ObjectUtils.isNotEmpty(daxueshengVo.getLianxidianhua()), Daxuesheng::getLianxidianhua,daxueshengVo.getLianxidianhua());
        wrapper.like(ObjectUtils.isNotEmpty(daxueshengVo.getZhaopian()), Daxuesheng::getZhaopian,daxueshengVo.getZhaopian());
        wrapper.like(ObjectUtils.isNotEmpty(daxueshengVo.getTuanduirenshu()), Daxuesheng::getTuanduirenshu,daxueshengVo.getTuanduirenshu());
        
        Page<Daxuesheng> page = new Page<>(daxueshengVo.getCurrent(),daxueshengVo.getCurrentNum());
        daxueshengDao.selectPage(page,wrapper);
        List<Daxuesheng> daxueshengs = page.getRecords();
        daxueshengVo.setList(daxueshengs);
        daxueshengVo.setPages(page.getPages());
        daxueshengVo.setTotal(page.getTotal());
        daxueshengVo.setCurrent(page.getCurrent());
        daxueshengVo.setCurrentNum(page.getSize());
        return daxueshengVo;
    }

    @Override
    public void deleteList(List<Daxuesheng> list) {
        List<Integer> ids =  list.stream().map(Daxuesheng::getId).collect(Collectors.toList());
        daxueshengDao.deleteBatchIds(ids);
    }
	
	@Override
    public Daxuesheng login(Daxuesheng daxuesheng) {
        LambdaQueryWrapper<Daxuesheng> wrapper = new LambdaQueryWrapper();
        wrapper.eq(ObjectUtils.isNotEmpty(daxuesheng.getXuehao()), Daxuesheng::getXuehao,daxuesheng.getXuehao());
        Daxuesheng daxueshengInfo = daxueshengDao.selectOne(wrapper);
        if (daxueshengInfo == null) {
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        if (!SecureUtil.md5(daxuesheng.getMima()).equalsIgnoreCase(daxueshengInfo.getMima())) {
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        return daxueshengInfo;
    }
	
	
	
	//youxxianxci
}
