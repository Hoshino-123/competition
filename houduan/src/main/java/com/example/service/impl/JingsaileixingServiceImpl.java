package com.example.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.ResultCode;
import com.example.dao.*;
import com.example.entity.Jingsaileixing;
import com.example.exception.CustomException;
import com.example.service.JingsaileixingService;
import com.example.vo.JingsaileixingVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@Service("jingsaileixingService")
public class JingsaileixingServiceImpl extends ServiceImpl<JingsaileixingDao, Jingsaileixing> implements JingsaileixingService {

    @Resource
    private JingsaileixingDao jingsaileixingDao;

    public Jingsaileixing add(Jingsaileixing jingsaileixing) {
		
	   //youquchong1
        jingsaileixing.setAddtime(new Date());
        jingsaileixingDao.insert(jingsaileixing);
        return jingsaileixing;
    }
	
	
	
    public void delete(Long id) {
        jingsaileixingDao.deleteById(id);
    }
	

    public void update(Jingsaileixing jingsaileixing) {
        //youquchong2
        jingsaileixingDao.updateById(jingsaileixing);
    }
	
	@Override
    public List<Map<String, Object>> daochuexcel() {
        return jingsaileixingDao.daochuexcel();
    }

    @Override
    public Jingsaileixing findById(Integer id) {
        return jingsaileixingDao.selectById(id);
    }

    public JingsaileixingVo findPage(JingsaileixingVo jingsaileixingVo) {
        LambdaQueryWrapper<Jingsaileixing> wrapper = Wrappers.lambdaQuery();
		wrapper.like(ObjectUtils.isNotEmpty(jingsaileixingVo.getLeixing()), Jingsaileixing::getLeixing,jingsaileixingVo.getLeixing());        
        Page<Jingsaileixing> page = new Page<>(jingsaileixingVo.getCurrent(),jingsaileixingVo.getCurrentNum());
        jingsaileixingDao.selectPage(page,wrapper);
        List<Jingsaileixing> jingsaileixings = page.getRecords();
        jingsaileixingVo.setList(jingsaileixings);
        jingsaileixingVo.setPages(page.getPages());
        jingsaileixingVo.setTotal(page.getTotal());
        jingsaileixingVo.setCurrent(page.getCurrent());
        jingsaileixingVo.setCurrentNum(page.getSize());
        return jingsaileixingVo;
    }

    @Override
    public void deleteList(List<Jingsaileixing> list) {
        List<Integer> ids =  list.stream().map(Jingsaileixing::getId).collect(Collectors.toList());
        jingsaileixingDao.deleteBatchIds(ids);
    }
	
	
	
	
	
	//youxxianxci
}
