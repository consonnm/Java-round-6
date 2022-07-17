package com.example.hotSpot.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hotSpot.entity.Spot;
import org.springframework.web.multipart.MultipartFile;

public interface ISpotService extends IService<Spot> {
    Spot queryById(int goodId);

    Boolean update(String goodName, String summary, String detail, String goodSort, int goodId);

    Boolean updatePhoto(MultipartFile file, int goodId);

    Boolean remove(int goodId);

    int insert(String goodName, String summary, String detail,String goodSort, int userId);

    IPage<Spot> findByPage(Page<Spot> page, LambdaQueryWrapper<Spot> userLambdaQueryWrapper);

    Boolean approved(String approved,int goodId);

    Boolean change(Boolean flag,int goodId);

}

