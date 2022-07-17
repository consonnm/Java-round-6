package com.example.hotSpot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hotSpot.dao.ITimeDao;
import com.example.hotSpot.entity.Time;
import com.example.hotSpot.service.ITimeService;

import org.springframework.stereotype.Service;

@Service
public class TimeService extends ServiceImpl<ITimeDao, Time> implements ITimeService {
    @Override
    public Boolean baseUpdate(int spotId,String time,String describe,int timeId) {
        Time time1 = baseMapper.selectById(timeId);
        time1.setSpotId(spotId);
        time1.setTime(time);
        time1.setDescribe(describe);
        save(time1);
        return saveOrUpdate(time1);
    }
    @Override
    public int insert(int spotId,String time,String describe){
        Time time1 = new Time();
        time1.setSpotId(spotId);
        time1.setTime(time);
        time1.setDescribe(describe);
        save(time1);
        return time1.getTimeId();
    }
    @Override
    public IPage<Time> findByPage(Page<Time> page, LambdaQueryWrapper<Time> userLambdaQueryWrapper){
        return  baseMapper.selectPage(page,userLambdaQueryWrapper);
    }
    @Override
    public Boolean remove(int timeId) {
        LambdaQueryWrapper<Time> lwq = Wrappers.lambdaQuery();
        lwq.eq(Time::getTimeId,timeId);
        return remove(lwq);
    }
}
