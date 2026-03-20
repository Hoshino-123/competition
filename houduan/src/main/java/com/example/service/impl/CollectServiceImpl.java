package com.example.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.CollectDao;
import com.example.dao.FileInfoDao;
import com.example.entity.Collect;
import com.example.entity.FileInfo;
import com.example.entity.Daxuesheng;
import com.example.entity.Jingsaixinxi;

import com.example.service.CollectService;
import com.example.service.DaxueshengService;
import com.example.service.JingsaixinxiService;



import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("collectService")
public class CollectServiceImpl  extends ServiceImpl<CollectDao, Collect> implements CollectService {

    @Resource
    private CollectDao collectDao;
	
	@Resource
private DaxueshengService daxueshengService;
@Resource
private JingsaixinxiService jingsaixinxiService;



    @Override
    public List<Map<String, String>> getCollected() {
        return collectDao.getCollected();
    }


    public Collect add(Collect collect) {
        Collect collect1 = collectDao.findByUser(collect);
        if(collect1 == null) {
            collectDao.insert(collect);
            return collect;
        }else {
            return null;
        }
    }

    public void delete(Integer id) {
        collectDao.deleteById(id);
    }

    public void update(Collect collect) {
        collectDao.updateById(collect);
    }

    public Collect findById(Long id) {
        return collectDao.selectById(id);
    }

    public List<Collect> findFrontPages(Integer userId) {
        List<Collect> collects;
        if (userId != null){
            collects = collectDao.findByEndUserId(userId);
        } else {
            collects = new ArrayList<>();
        }
        for (Collect collect : collects) {
            packOrder(collect);
        }
        return collects;
    }
    /**
     * 包装收藏的用户和商品信息
     */

	public List<Daxuesheng> findPaperByDaxuesheng(Integer id) {
        List<Collect> collects = collectDao.findByEndUserId(id);
        List<Daxuesheng> daxueshengList = new ArrayList<>();
        for (Collect collect : collects) {
            Integer foreignId = collect.getShangpinxinxiID();
            Daxuesheng daxuesheng = null;
            if(foreignId != null){
                daxuesheng = daxueshengService.findById(foreignId);
            };
            if(daxuesheng != null){
                daxueshengList.add(daxuesheng);
            }
        }
        return daxueshengList;
    }

	
	private void packOrder(Collect collect) {
        collect.setDaxuesheng(daxueshengService.findById(collect.getUserId()));
        List<Jingsaixinxi> goodsList = CollUtil.newArrayList();
        collect.setProductList(goodsList);
        Jingsaixinxi goodsDetail = jingsaixinxiService.findById(collect.getShangpinxinxiID());
        if (goodsDetail != null) {
            goodsList.add(goodsDetail);
        }
    }

	@Override
    public void deleteByUserId(Integer id, Integer userId) {
        collectDao.deleteByUserId(id,userId);
    }
    
}
