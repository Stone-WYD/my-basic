package com.njxnet.yccc.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.njxnet.framework.common.model.AjaxResult;
import com.njxnet.framework.common.utils.AjaxResultUtil;
import com.njxnet.yccc.dao.SysUserDao;
import com.njxnet.yccc.entity.SysUser;
import com.njxnet.yccc.model.vo.LoginVO;
import com.njxnet.yccc.service.SysUserService;
import org.springframework.stereotype.Service;

import static com.njxnet.yccc.exception.ResultStatusCode.LOGIN_ERROR;

/**
 * (SysUser)表服务实现类
 *
 * @author Stone
 * @since 2023-08-22 15:34:36
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Override
    public AjaxResult<LoginVO> sysLogin(String userName, String password) {
        SysUser user = query().eq("user_name", userName).eq("password", password).one();
        // 用户名或密码不正确
        if (user == null)
            return AjaxResultUtil.getFalseAjaxResult(new AjaxResult<>(), LOGIN_ERROR.getName(), LOGIN_ERROR.getCode());
        // 登录
        StpUtil.login(user.getId());
        // 返回结果
        return AjaxResultUtil.getTrueAjaxResult(new AjaxResult<>());
    }
}

