package com.example.hotSpot.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotSpot.entity.SpotCollection;
import com.example.hotSpot.response.ResultVo;
import com.example.hotSpot.service.ISpotCollectionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping(value ="/goodsCollection")
public class CollectionController {
    @Autowired
    ISpotCollectionService iSpotCollectionService;
    @RequiresRoles("user::user")
    @ApiOperation("查询所有收藏")
    @GetMapping("/getAllFollow")
    public ResultVo all(@ApiParam("用户id")int userId, @ApiParam("当前页")int current,@ApiParam("大小") int size){
        log.info("查询所有收藏");
        Page<SpotCollection> page = new Page<>(current , size );
        LambdaQueryWrapper<SpotCollection> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(SpotCollection::getUserId,userId);
        return new ResultVo().setData(iSpotCollectionService.findByPage(page,userLambdaQueryWrapper));
    }
    @RequiresRoles("user::user")
    @ApiOperation("增加收藏")
    @GetMapping("/insert")
    public ResultVo insert(@ApiParam("用户id")int userId,@ApiParam("热点id")int spotId){
        log.info("增加收藏");
        return new ResultVo().setData(iSpotCollectionService.insert(userId,spotId));
    }
    @RequiresRoles("user::user")
    @ApiOperation("删除收藏")
    @GetMapping("/remove")
    public ResultVo remove(@ApiParam("用户id")int userId,@ApiParam("热点id")int spotId){
        log.info("删除收藏");
        return new ResultVo().setData(iSpotCollectionService.remove(userId,spotId));
    }
}
