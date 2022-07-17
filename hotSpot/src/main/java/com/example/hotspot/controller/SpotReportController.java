package com.example.hotSpot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotSpot.entity.SpotReport;
import com.example.hotSpot.response.ResultVo;
import com.example.hotSpot.service.ISpotReportService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@Slf4j
@RestController
@RequestMapping(value ="/goodReport")
public class SpotReportController {
    @Autowired
    ISpotReportService iSpotReportService;
    @RequiresRoles("admin")
    @ApiOperation("查询所有热点举报记录")
    @GetMapping("/getAllGoodReport")
    public ResultVo all(@ApiParam("当前页")int current, @ApiParam("大小") int size){
        Page<SpotReport> page = new Page<>(current , size );
        LambdaQueryWrapper<SpotReport> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(SpotReport::getStatus, "未审核");
        return new ResultVo().setData(iSpotReportService.findByPage(page,userLambdaQueryWrapper));
    }
    @RequiresRoles("user::user")
    @ApiOperation("增加热点举报记录")
    @GetMapping("/insert")
    public ResultVo insert(@ApiParam("内容")String context,int spotId){
        return new ResultVo().setData(iSpotReportService.insert(context,spotId));
    }
    @RequiresRoles("admin")
    @ApiOperation("修改热点审核状态")
    @GetMapping("/update")
    public ResultVo update(String status,int reportId){
        return new ResultVo().setData(iSpotReportService.update(status,reportId));
    }

}
