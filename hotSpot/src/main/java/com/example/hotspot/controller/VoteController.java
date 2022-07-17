package com.example.hotSpot.controller;

import com.example.hotSpot.response.ResultVo;
import com.example.hotSpot.service.ISpotService;
import com.example.hotSpot.service.IVoteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/vote")
public class VoteController {
    IVoteService iVoteService;
    ISpotService iSpotService;
    @RequiresRoles("user::user")
    @ApiOperation("增加投票")
    @PostMapping("/insert")
    public ResultVo insert(@ApiParam("用户Id")int userId, @ApiParam("热点Id")int spotId,@ApiParam("true为正方，false 为反方")Boolean flag){
        log.info("增加投票");
        if(iVoteService.find(userId,spotId)) {
            return new ResultVo().setMessage("已投过票");
        }
        iSpotService.change(flag,spotId);
        return new ResultVo().setData(iVoteService.insert(userId,spotId));
    }
}
