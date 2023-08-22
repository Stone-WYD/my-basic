package com.njxnet.yccc.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.github.cage.Cage;
import com.github.cage.IGenerator;
import com.github.cage.image.ConstantColorGenerator;
import com.github.cage.image.Painter;
import com.njxnet.framework.common.exception.BaseException;
import com.njxnet.framework.common.exception.BaseResultStatusCode;
import com.njxnet.framework.common.model.AjaxResult;
import com.njxnet.framework.common.utils.AjaxResultUtil;
import com.njxnet.yccc.exception.ResultStatusCode;
import com.njxnet.yccc.model.CaptchaModel;
import com.njxnet.yccc.model.dto.CaptchaDTO;
import com.njxnet.yccc.model.info.SysUserInfo;
import com.njxnet.yccc.model.query.LoginQuery;
import com.njxnet.yccc.model.vo.LoginVO;
import com.njxnet.yccc.service.SysUserService;
import com.njxnet.yccc.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@RequestMapping(value = "/user")
@RestController
@Api(value = "用户登录登出", protocols = "http/https", tags = "用户登录登出")
@Slf4j
public class LoginController {


    /**
     * @Description: 将冻结的账号封禁
     * @Author: Stone
     * @Date: 2023/7/14
     */
/*    @PostConstruct
    public void init(){
        List<TmspSysUser> list = sysUserService.query().eq("status", 0).list();
        if (CollectionUtil.isNotEmpty(list)) {
            list.forEach(user -> StpUtil.disable(user.getId(), -1));
        }
    }*/

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "用户登录", notes = "用户登录")
    public AjaxResult<LoginVO> login(@RequestBody @Validated LoginQuery param){
        Map<String, CaptchaModel> capMap = Constants.capMap;
        CaptchaModel cModel = capMap.get(param.getCaptchaKey());
        if (null == cModel || !param.getCaptchaValue().equalsIgnoreCase(cModel.getText())) {
            throw new BaseException(ResultStatusCode.CAPTCHA_ERROR.getCode(), ResultStatusCode.CAPTCHA_ERROR.getName());
        }
        return sysUserService.sysLogin(param.getUsername(), param.getPassword());
    }

    @Resource
    private SysUserService sysUserService;

    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    @RequestMapping(value = "/getCaptcha", method = RequestMethod.POST)
    public AjaxResult<CaptchaDTO> getCaptcha() {
        AjaxResult<CaptchaDTO> ajaxResult = new AjaxResult<>();
        IGenerator<Color> ig = new ConstantColorGenerator(Color.BLACK);
        Painter painter = new Painter(115, 38, null, null, null, null);
        Cage cage = new Cage(painter, null, ig, null, null, null, null);
        //获取验证码字符串
        String text = cage.getTokenGenerator().next().toUpperCase().substring(0, 4);
        //将用户名+时间戳生成唯一标识
        String kapKey = UUID.randomUUID().toString().replace("-", "");
        Map<String, CaptchaModel> capMap = Constants.capMap;
        CaptchaModel captchaModel = new CaptchaModel(kapKey, text, System.currentTimeMillis()+Constants.CAP_TIMEOUT);
        capMap.put(kapKey, captchaModel);
        //生成图片
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            cage.draw(text, outputStream);
            BASE64Encoder encoder = new BASE64Encoder();
            String base64 = encoder.encode(outputStream.toByteArray());
            String captchaBase64 = "data:image/jpeg;base64," + base64.replaceAll("\r\n", "");
            CaptchaDTO captchaDTO = new CaptchaDTO();
            captchaDTO.setImg(captchaBase64);
            captchaDTO.setKey(kapKey);
            AjaxResultUtil.getTrueAjaxResult(ajaxResult);
            ajaxResult.setData(captchaDTO);
            log.info("请求的验证码为：{}:{}", kapKey, text);
        } catch (IOException e) {
            log.error("获取验证码异常：{}", e.getMessage());
            AjaxResultUtil.getFalseAjaxResult(ajaxResult, BaseResultStatusCode.ERROR.getName(), BaseResultStatusCode.ERROR.getCode());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                log.error("获取验证码异常：{}", e.getMessage());
                AjaxResultUtil.getFalseAjaxResult(ajaxResult, BaseResultStatusCode.ERROR.getName(), BaseResultStatusCode.ERROR.getCode());
            }
        }
        return ajaxResult;
    }


    /**
     * @Description: 用户退出登录
     * @Author: Stone
     * @Date: 2023/7/17
     */
    @SaCheckLogin
    @RequestMapping(value = "/out", method = RequestMethod.POST)
    @ApiOperation(value = "用户退出登录", notes = "用户退出登录")
    public AjaxResult loginOut(@RequestBody @Validated(value = SysUserInfo.update.class) SysUserInfo param){
        StpUtil.logout(param.getId());
        return AjaxResultUtil.getTrueAjaxResult(new AjaxResult<>());
    }

}
