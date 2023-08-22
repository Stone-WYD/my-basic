package com.njxnet.yccc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.njxnet.framework.common.model.AjaxResult;
import com.njxnet.yccc.entity.SysUser;
import com.njxnet.yccc.model.vo.LoginVO;

/**
 * (SysUser)表服务接口
 *
 * @author Stone
 * @since 2023-08-22 15:34:36
 */
public interface SysUserService extends IService<SysUser> {

    AjaxResult<LoginVO> sysLogin(String userName, String password);
}

