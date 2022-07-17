package com.example.hotSpot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hotSpot.dao.IGoodsCollectionDao;
import com.example.hotSpot.entity.SpotCollection;
import com.example.hotSpot.service.ISpotCollectionService;
import org.springframework.stereotype.Service;

@Service
public class GoodCollectionService extends ServiceImpl<IGoodsCollectionDao, SpotCollection> implements ISpotCollectionService {
    @Override
    public Boolean insert(int userId,int goodId){
        SpotCollection spotCollection = new SpotCollection();
        spotCollection.setUserId(userId);
        spotCollection.setSpotId(goodId);
        return save(spotCollection);
    }
    @Override
    public Boolean remove(int userId,int goodId) {
        LambdaQueryWrapper<SpotCollection> lwq = Wrappers.lambdaQuery();
        lwq.eq(SpotCollection::getUserId,userId).eq(SpotCollection::getSpotId,goodId);
        return remove(lwq);
    }
    @Override
    public IPage<SpotCollection> findByPage(Page<SpotCollection> page, LambdaQueryWrapper<SpotCollection> userLambdaQueryWrapper){
        return  baseMapper.selectPage(page,userLambdaQueryWrapper);
    }
}
