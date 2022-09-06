package com.mimi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.mimi.common.R;
import com.mimi.domain.Employee;
import com.mimi.domain.User;
import com.mimi.exception.CustomException;
import com.mimi.service.UserService;
import com.mimi.utils.SMSUtils;
import com.mimi.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 发送手机验证码
     * @param user
     * @return
     */
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){
        // 获取手机号
        String phone = user.getPhone();

        if (StringUtils.isNotEmpty(phone)){
            //生成随机码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();

            //调用aliyun发送短信
            //SMSUtils.sendMessage("瑞吉外卖","",phone,code);
            log.info("code = {}",code);

            //保存验证码到session
//            session.setAttribute(phone,code);

            //保存验证码到redis
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);

            return R.success("短信验证码发送成功");
        }

        return R.error("短信验证码发送失败");
    }

    /**
     * 登录
     * @param session
     * @param map
     * @return
     */
    @PostMapping("/login")
    public R<User> login(HttpSession session, @RequestBody Map map){
        //获取手机号
        String phone = map.get("phone").toString();

        //获取验证码
        String code = map.get("code").toString();

        //session中获取验证码
//        Object codeInSession = session.getAttribute(phone);

        //redis中获取验证码
        Object codeInRedis = redisTemplate.opsForValue().get(phone);

        //比对验证码
        if (codeInRedis != null && codeInRedis.equals(code)){
            //如果比对成功，登录成功


            LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
            lqw.eq(User::getPhone,phone);
            User user = userService.getOne(lqw);

            //判断是否新用户，是就自动完成注册
            if (user == null){
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user",user.getId());

            //登录成功，删除redis缓存中的验证码
            redisTemplate.delete(phone);

            return R.success(user);
        }

        return R.error("验证码错误");
    }

    /**
     * 退出
     */
    @PostMapping("/loginout")
    public R<String> logout(HttpSession session){
        //1. 清理session员工ID
        session.removeAttribute("user");
        return R.success("退出成功");
    }
}
