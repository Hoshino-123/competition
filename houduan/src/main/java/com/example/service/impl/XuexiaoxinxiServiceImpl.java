package com.example.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.ResultCode;
import com.example.dao.*;
import com.example.entity.Xuexiaoxinxi;
import com.example.exception.CustomException;
import com.example.service.XuexiaoxinxiService;
import com.example.vo.XuexiaoxinxiVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@Service("xuexiaoxinxiService")
public class XuexiaoxinxiServiceImpl extends ServiceImpl<XuexiaoxinxiDao, Xuexiaoxinxi> implements XuexiaoxinxiService {

    @Resource
    private XuexiaoxinxiDao xuexiaoxinxiDao;

    public Xuexiaoxinxi add(Xuexiaoxinxi xuexiaoxinxi) {
		
	   //youquchong1
        xuexiaoxinxi.setAddtime(new Date());
        xuexiaoxinxiDao.insert(xuexiaoxinxi);
        return xuexiaoxinxi;
    }
	
	
	
    public void delete(Long id) {
        xuexiaoxinxiDao.deleteById(id);
    }
	

    public void update(Xuexiaoxinxi xuexiaoxinxi) {
        //youquchong2
        xuexiaoxinxiDao.updateById(xuexiaoxinxi);
    }
	
	@Override
    public List<Map<String, Object>> daochuexcel() {
        return xuexiaoxinxiDao.daochuexcel();
    }

    @Override
    public Xuexiaoxinxi findById(Integer id) {
        return xuexiaoxinxiDao.selectById(id);
    }

    public XuexiaoxinxiVo findPage(XuexiaoxinxiVo xuexiaoxinxiVo) {
        LambdaQueryWrapper<Xuexiaoxinxi> wrapper = Wrappers.lambdaQuery();
		wrapper.like(ObjectUtils.isNotEmpty(xuexiaoxinxiVo.getXuexiaomingcheng()), Xuexiaoxinxi::getXuexiaomingcheng,xuexiaoxinxiVo.getXuexiaomingcheng());        
        Page<Xuexiaoxinxi> page = new Page<>(xuexiaoxinxiVo.getCurrent(),xuexiaoxinxiVo.getCurrentNum());
        xuexiaoxinxiDao.selectPage(page,wrapper);
        List<Xuexiaoxinxi> xuexiaoxinxis = page.getRecords();
        xuexiaoxinxiVo.setList(xuexiaoxinxis);
        xuexiaoxinxiVo.setPages(page.getPages());
        xuexiaoxinxiVo.setTotal(page.getTotal());
        xuexiaoxinxiVo.setCurrent(page.getCurrent());
        xuexiaoxinxiVo.setCurrentNum(page.getSize());
        return xuexiaoxinxiVo;
    }

    @Override
    public void deleteList(List<Xuexiaoxinxi> list) {
        List<Integer> ids =  list.stream().map(Xuexiaoxinxi::getId).collect(Collectors.toList());
        xuexiaoxinxiDao.deleteBatchIds(ids);
    }
	
	
	
	
	
	//youxxianxci
}
