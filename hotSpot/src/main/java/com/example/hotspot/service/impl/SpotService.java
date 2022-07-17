package com.example.hotSpot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hotSpot.dao.IGoodsDao;
import com.example.hotSpot.entity.Spot;
import com.example.hotSpot.service.ISpotService;
import com.example.hotSpot.utils.AliyunOSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SpotService extends ServiceImpl<IGoodsDao, Spot> implements ISpotService {
    @Autowired
    AliyunOSSUtil aliyunOSSUtil;
    @Override
    public Spot queryById(int goodId) {
        return getOne(new LambdaQueryWrapper<Spot>()
                .eq(Spot::getSpotId,goodId)
        );
    }

    @Override
    public Boolean update(String goodName, String summary, String detail,String goodSort,int goodId) {
        Spot spot = baseMapper.selectById(goodId);
        spot.setSpotId(goodId);
        spot.setSpotName(goodName);
        spot.setSummary(summary);
        spot.setDetail(detail);
        spot.setSpotSort(goodSort);
        return saveOrUpdate(spot);
    }
    @Override
    public Boolean updatePhoto(MultipartFile file,int goodId) {
        String url = aliyunOSSUtil.upload(file);
        Spot spot = baseMapper.selectById(goodId);
        spot.setImage(url);
        spot.setSpotId(goodId);
        spot.setUserId(spot.getUserId());
        return saveOrUpdate(spot);
    }
    @Override
    public Boolean approved(String approved,int goodId) {
        Spot spot = baseMapper.selectById(goodId);
        spot.setApproved(approved);
        spot.setSpotId(goodId);
        return saveOrUpdate(spot);
    }
    @Override
    public Boolean remove(int goodId) {
        LambdaQueryWrapper<Spot> lwq = Wrappers.lambdaQuery();
        lwq.eq(Spot::getSpotId,goodId);
        return remove(lwq);
    }
    @Override
    public int insert(String goodName,String summary,String detail,String goodSort,int userId){
        Spot spot = new Spot();
        spot.setSpotName(goodName);
        spot.setSummary(summary);
        spot.setDetail(detail);
        spot.setSpotSort(goodSort);
        spot.setUserId(userId);
        spot.setApproved("未审核");
        save(spot);
        return spot.getSpotId();
    }
    @Override
    public IPage<Spot> findByPage(Page<Spot> page, LambdaQueryWrapper<Spot> userLambdaQueryWrapper){
        return  baseMapper.selectPage(page,userLambdaQueryWrapper);
    }
    @Override
    public Boolean change(Boolean flag,int goodId) {
        Spot spot = baseMapper.selectById(goodId);
        if(flag==true) spot.setPositive(spot.getPositive()+1);
        else spot.setNegative(spot.getNegative()+1);
        return saveOrUpdate(spot);
    }

}
