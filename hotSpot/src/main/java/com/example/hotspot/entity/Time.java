package com.example.hotSpot.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@TableName("time")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "时间线")
public class Time {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("时间线id")
    int timeId;
    @ApiModelProperty("热点id")
    int spotId;
    @ApiModelProperty("时间")
    String time;
    @ApiModelProperty("描述")
    String describe;
}
