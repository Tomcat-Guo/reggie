package com.mimi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mimi.common.R;
import com.mimi.domain.Employee;
import com.mimi.domain.User;
import com.mimi.exception.CustomException;
import com.mimi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public R<User> login(HttpServletRequest request, @RequestBody User user){
        String phone = user.getPhone();

        //2. 根据username查询数据库
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        lqw.eq(User::getPhone,phone);
        User u = userService.getOne(lqw);

//        log.info(u.toString());
//        log.info("ID为{}",u.getId());

        if (u == null){
            throw new CustomException("用户不存在");
        }

        //4. 对比禁用状态
        if (u.getStatus()!=1){
            return R.error("账户已被禁用");
        }

        //5. id存入session
        request.getSession().setAttribute("user",u.getId());
        return R.success(u);
    }

    /**
     * 退出
     */
    @PostMapping("/loginout")
    public R<String> logout(HttpServletRequest request){
        //1. 清理session员工ID
        request.getSession().removeAttribute("user");
        return R.success("退出成功");
    }
}
