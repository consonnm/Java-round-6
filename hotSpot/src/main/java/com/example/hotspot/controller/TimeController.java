package com.example.hotSpot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotSpot.entity.Time;
import com.example.hotSpot.response.ResultVo;
import com.example.hotSpot.service.ITimeService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/order")
public class TimeController {
    @Autowired
    ITimeService iTimeService;
    @RequiresRoles("user::user")
    @ApiOperation("热点时间线查询接口")
    @GetMapping("/time")
    public ResultVo time(@ApiParam("热点Id")int spotId,@ApiParam("当前页")int current,@ApiParam("大小") int size) {
        log.info("热点时间线查询接口");
        Page<Time> page = new Page<>(current,size);
        LambdaQueryWrapper<Time> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(Time::getSpotId, spotId).orderByAsc(Time::getTime);
        return new ResultVo().setData(iTimeService.findByPage(page,userLambdaQueryWrapper));
    }
    @RequiresRoles("user::user")
    @ApiOperation("用户删除时间线")
    @GetMapping("/cancel")
    public ResultVo cancel(@ApiParam("订单id")int timeId) {
        log.info("用户删除时间线");
        return new ResultVo().setData(iTimeService.remove(timeId));
    }
    @RequiresRoles("user::user")
    @ApiOperation("用户修改时间线接口")
    @GetMapping("/baseUpdate")
    public ResultVo baseUpdate(@ApiParam("热点id") int spotId,@ApiParam("时间2022-04-05的格式") String time,@ApiParam("描述") String describe,@ApiParam("时间线Id") int timeId) {
        log.info("用户修改时间线接口");
        return new ResultVo().setData(iTimeService.baseUpdate(spotId,time,describe,timeId));
    }
    @ApiOperation("增加时间线接口")
    @GetMapping("/insert")
    public ResultVo insert(@ApiParam("热点id") int spotId,@ApiParam("时间2022-04-05的格式") String time,@ApiParam("描述") String describe) {
        log.info("用户添加时间线接口");
        return new ResultVo().setData(iTimeService.insert(spotId,time,describe));
    }

}
