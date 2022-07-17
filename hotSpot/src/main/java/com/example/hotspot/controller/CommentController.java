package com.example.hotSpot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotSpot.entity.Comment;
import com.example.hotSpot.response.ResultVo;
import com.example.hotSpot.service.ICommentService;
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
@RequestMapping(value ="/commment")
public class CommentController {
    @Autowired
    ICommentService iCommentService;

    public CommentController(ICommentService iCommentService) {
        this.iCommentService = iCommentService;
    }
    @RequiresRoles("user::user")
    @ApiOperation("查询所有评论")
    @GetMapping("/getAllComment")
    public ResultVo all(@ApiParam("热点id")int spotId, @ApiParam("当前页")int current, @ApiParam("大小")int size){
        log.info("查询所有评论");
        Page<Comment> page = new Page<>(current , size );
        LambdaQueryWrapper<Comment> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(Comment::getCommentedUserId,spotId);
        return new ResultVo().setData(iCommentService.findByPage(page,userLambdaQueryWrapper));
    }
    @RequiresRoles("user::user")
    @ApiOperation("增加评论")
    @GetMapping("/insert")
    public ResultVo insert(@ApiParam("热点id")int spotId,@ApiParam("评论者id")int userId,@ApiParam("评论内容")String context){
        log.info("增加评论");
        return new ResultVo().setData(iCommentService.insert(spotId,userId,context));
    }
    @RequiresRoles("usr::user")
    @ApiOperation("删除评论")
    @GetMapping("/remove")
    public ResultVo remove(@ApiParam("评论id")int commentId){
        log.info("删除评论");
        return new ResultVo().setData(iCommentService.remove(commentId));
    }


}
