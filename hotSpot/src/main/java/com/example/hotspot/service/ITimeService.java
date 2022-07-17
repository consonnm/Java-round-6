package com.example.hotSpot.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hotSpot.entity.Time;

public interface ITimeService extends IService<Time> {
    Boolean baseUpdate(int spotId,String time,String describe,int timeId);
    int insert(int spotId,String time,String describe);
    IPage<Time> findByPage(Page<Time> page, LambdaQueryWrapper<Time> userLambdaQueryWrapper);
    Boolean remove(int orderId);
}