package com.example.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.ResultCode;
import com.example.dao.*;
import com.example.entity.Zhidaojiaoshi;
import com.example.exception.CustomException;
import com.example.service.ZhidaojiaoshiService;
import com.example.vo.ZhidaojiaoshiVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@Service("zhidaojiaoshiService")
public class ZhidaojiaoshiServiceImpl extends ServiceImpl<ZhidaojiaoshiDao, Zhidaojiaoshi> implements ZhidaojiaoshiService {

    @Resource
    private ZhidaojiaoshiDao zhidaojiaoshiDao;

    public Zhidaojiaoshi add(Zhidaojiaoshi zhidaojiaoshi) {
		if(zhidaojiaoshi.getMima()==null) {zhidaojiaoshi.setMima(SecureUtil.md5("001"));}else {zhidaojiaoshi.setMima(SecureUtil.md5(zhidaojiaoshi.getMima())); }
	    LambdaQueryWrapper<Zhidaojiaoshi> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Zhidaojiaoshi::getGonghao,zhidaojiaoshi.getGonghao());
        Zhidaojiaoshi findZhidaojiaoshi = zhidaojiaoshiDao.selectOne(wrapper);
        if (findZhidaojiaoshi != null) {
            throw new CustomException("1001", "" + zhidaojiaoshi.getGonghao() + "\"已存在");
        }
        zhidaojiaoshi.setAddtime(new Date());
        zhidaojiaoshiDao.insert(zhidaojiaoshi);
        return zhidaojiaoshi;
    }
	
	
	
    public void delete(Long id) {
        zhidaojiaoshiDao.deleteById(id);
    }
	

    public void update(Zhidaojiaoshi zhidaojiaoshi) {
         LambdaQueryWrapper<Zhidaojiaoshi> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Zhidaojiaoshi::getGonghao,zhidaojiaoshi.getGonghao());
        Zhidaojiaoshi findZhidaojiaoshi = zhidaojiaoshiDao.selectOne(wrapper);
		if (findZhidaojiaoshi != null && zhidaojiaoshi.getId() != findZhidaojiaoshi.getId()) {
            throw new CustomException("1001", "" + zhidaojiaoshi.getGonghao() + "\"已存在");
        }
        zhidaojiaoshiDao.updateById(zhidaojiaoshi);
    }
	
	@Override
    public List<Map<String, Object>> daochuexcel() {
        return zhidaojiaoshiDao.daochuexcel();
    }

    @Override
    public Zhidaojiaoshi findById(Integer id) {
        return zhidaojiaoshiDao.selectById(id);
    }

    public ZhidaojiaoshiVo findPage(ZhidaojiaoshiVo zhidaojiaoshiVo) {
        LambdaQueryWrapper<Zhidaojiaoshi> wrapper = Wrappers.lambdaQuery();
		wrapper.like(ObjectUtils.isNotEmpty(zhidaojiaoshiVo.getGonghao()), Zhidaojiaoshi::getGonghao,zhidaojiaoshiVo.getGonghao());        wrapper.like(ObjectUtils.isNotEmpty(zhidaojiaoshiVo.getMima()), Zhidaojiaoshi::getMima,zhidaojiaoshiVo.getMima());        wrapper.like(ObjectUtils.isNotEmpty(zhidaojiaoshiVo.getXingming()), Zhidaojiaoshi::getXingming,zhidaojiaoshiVo.getXingming());        wrapper.eq(ObjectUtils.isNotEmpty(zhidaojiaoshiVo.getXingbie()), Zhidaojiaoshi::getXingbie,zhidaojiaoshiVo.getXingbie());        wrapper.like(ObjectUtils.isNotEmpty(zhidaojiaoshiVo.getNianling()), Zhidaojiaoshi::getNianling,zhidaojiaoshiVo.getNianling());        wrapper.like(ObjectUtils.isNotEmpty(zhidaojiaoshiVo.getShoujihaoma()), Zhidaojiaoshi::getShoujihaoma,zhidaojiaoshiVo.getShoujihaoma());        wrapper.like(ObjectUtils.isNotEmpty(zhidaojiaoshiVo.getZhaopian()), Zhidaojiaoshi::getZhaopian,zhidaojiaoshiVo.getZhaopian());        
        Page<Zhidaojiaoshi> page = new Page<>(zhidaojiaoshiVo.getCurrent(),zhidaojiaoshiVo.getCurrentNum());
        zhidaojiaoshiDao.selectPage(page,wrapper);
        List<Zhidaojiaoshi> zhidaojiaoshis = page.getRecords();
        zhidaojiaoshiVo.setList(zhidaojiaoshis);
        zhidaojiaoshiVo.setPages(page.getPages());
        zhidaojiaoshiVo.setTotal(page.getTotal());
        zhidaojiaoshiVo.setCurrent(page.getCurrent());
        zhidaojiaoshiVo.setCurrentNum(page.getSize());
        return zhidaojiaoshiVo;
    }

    @Override
    public void deleteList(List<Zhidaojiaoshi> list) {
        List<Integer> ids =  list.stream().map(Zhidaojiaoshi::getId).collect(Collectors.toList());
        zhidaojiaoshiDao.deleteBatchIds(ids);
    }
	
	@Override
    public Zhidaojiaoshi login(Zhidaojiaoshi zhidaojiaoshi) {
        LambdaQueryWrapper<Zhidaojiaoshi> wrapper = new LambdaQueryWrapper();
        wrapper.eq(ObjectUtils.isNotEmpty(zhidaojiaoshi.getGonghao()), Zhidaojiaoshi::getGonghao,zhidaojiaoshi.getGonghao());
        Zhidaojiaoshi zhidaojiaoshiInfo = zhidaojiaoshiDao.selectOne(wrapper);
        if (zhidaojiaoshiInfo == null) {
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        if (!SecureUtil.md5(zhidaojiaoshi.getMima()).equalsIgnoreCase(zhidaojiaoshiInfo.getMima())) {
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        return zhidaojiaoshiInfo;
    }
	
	
	
	//youxxianxci
}
