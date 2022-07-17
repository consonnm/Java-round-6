package com.example.hotSpot.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotSpot.entity.Spot;
import com.example.hotSpot.response.ResultVo;
import com.example.hotSpot.service.ISpotService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping(value ="/good")
public class SpotController {
    @Autowired
    ISpotService iSpotService;
    @ApiOperation("模糊查询热点")
    @GetMapping("/getGood")
    public ResultVo list(@ApiParam("热点名称") String spotName,@ApiParam("当前页")int current,@ApiParam("大小")int size) {
        log.info("模糊查询热点");
        Page<Spot> page = new Page<>(current , size );
        LambdaQueryWrapper<Spot> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.like(Spot::getSpotName, spotName).eq(Spot::getApproved,"已审核");
        return new ResultVo().setData(iSpotService.findByPage(page,userLambdaQueryWrapper));
    }
    @ApiOperation("查询所有热点")
    @GetMapping("/getAllGood")
    public ResultVo all(@ApiParam("当前页")int current,@ApiParam("大小")int size) {
        log.info("查询所有热点");
        Page<Spot> page = new Page<>(current , size );
        LambdaQueryWrapper<Spot> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(Spot::getApproved,"已审核");
        return new ResultVo().setData(iSpotService.findByPage(page,userLambdaQueryWrapper));
    }
    @ApiOperation("根据类别查询热点")
    @GetMapping("/getGoodBySort")
    public ResultVo sort(@ApiParam("商品类别") String spotSort,@ApiParam("当前页")int current,@ApiParam("大小")int size) {
        log.info("根据类别查询热点");
        Page<Spot> page = new Page<>(current,size);
        LambdaQueryWrapper<Spot> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(Spot::getSpotSort, spotSort).eq(Spot::getApproved,"已审核");
        return new ResultVo().setData(iSpotService.findByPage(page,userLambdaQueryWrapper));
    }
    @ApiOperation("根据类别及关键字查询热点")
    @GetMapping("/getGoodBySortByKey")
    public ResultVo sortByKey(@ApiParam("商品类别") String spotSort,@ApiParam("商品关键字")String spotName,@ApiParam("当前页")int current,@ApiParam("大小")int size) {
        log.info("根据类别查询热点");
        Page<Spot> page = new Page<>(current,size);
        LambdaQueryWrapper<Spot> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(Spot::getSpotSort, spotSort).like(Spot::getSpotName,spotName).eq(Spot::getApproved,"已审核");
        return new ResultVo().setData(iSpotService.findByPage(page,userLambdaQueryWrapper));
    }
    @ApiOperation("根据id查询热点的详细信息")
    @GetMapping("/getGoodById")
    public ResultVo list(@ApiParam("热点id") int spotId) {
        log.info("根据id查询商品的详细信息");
        return new ResultVo().setData(iSpotService.queryById(spotId));
    }
    @RequiresRoles("user::user")
    @ApiOperation("热点基础信息修改")
    @GetMapping("/baseMessageUpdate")
    public ResultVo baseUpdate(@ApiParam("热点名称")String spotName,@ApiParam("热点概述") String summary,@ApiParam("热点详细介绍") String detail ,@ApiParam("分类")String spotSort,@ApiParam("热点id")int spotId) {
        log.info("热点基础信息修改");
        return new ResultVo().setData(iSpotService.update(spotName, summary, detail, spotSort,spotId));
    }
    @RequiresRoles("admin")
    @ApiOperation("查询所有未审核热点")
    @GetMapping("/select")
    public ResultVo select(@ApiParam("当前页")int current,@ApiParam("大小")int size) {
        log.info("查询所有未审核热点");
        Page<Spot> page = new Page<>(current , size );
        LambdaQueryWrapper<Spot> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(Spot::getApproved, "未审核");
        return new ResultVo().setData(iSpotService.findByPage(page,userLambdaQueryWrapper));
    }
    @RequiresRoles("admin")
    @ApiOperation("热点审核状态修改接口")
    @GetMapping("/approved")
    public ResultVo approved(String approved,int spotId) {
        log.info("热点审核状态修改接口");
        return new ResultVo().setData(iSpotService.approved(approved,spotId));
    }
    @RequiresRoles("user::user")
    @ApiOperation("热点图片修改接口")
    @PostMapping("/photoUpdate")
    public ResultVo photoUpdate(@ApiParam("图片")MultipartFile file, @ApiParam("商品id")int spotId) {
        log.info("热点图片修改接口");
        return new ResultVo().setData(iSpotService.updatePhoto(file, spotId));
    }
    @RequiresRoles("user::user")
    @ApiOperation("增加热点接口")
    @GetMapping("/insert")
    public ResultVo insert(@ApiParam("商品名称")String spotName, @ApiParam("概述")String summary, @ApiParam("详细介绍")String detail, @ApiParam("分类")String spotSort,@ApiParam("用户id")int userId){
        log.info("增加热点接口");
        return new ResultVo().setData(iSpotService.insert(spotName,summary,detail,spotSort,userId));
    }
    @RequiresRoles("user::user")
    @ApiOperation("删除热点接口")
    @GetMapping("/remove")
    public ResultVo remove(@ApiParam("商品id")int spotId) {
        log.info("删除热点接口");
        return new ResultVo().setData(iSpotService.remove(spotId));
    }
    @RequiresRoles("user::user")
    @ApiOperation("热点正反方投票修改")
    @GetMapping("/change")
    public ResultVo change(@ApiParam("热点名称")Boolean flag,@ApiParam("热点id")int spotId) {
        log.info("热点正反方投票修改");
        return new ResultVo().setData(iSpotService.change(flag,spotId));
    }
}
