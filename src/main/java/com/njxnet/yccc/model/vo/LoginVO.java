package com.njxnet.yccc.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginVO implements Serializable {

    @ApiModelProperty(notes = "userId", name = "userId")
    private String id;

    @ApiModelProperty(notes = "法院id", name = "courtId")
    private String courtId;

    @ApiModelProperty(notes = "法院名称", name = "courtName")
    private String courtName;

    /*@ApiModelProperty(notes = "用户权限列表", name = "list")
    private List<TmspResourceNodeInfoSysResource> list;*/
}
