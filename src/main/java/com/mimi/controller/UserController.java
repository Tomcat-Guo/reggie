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
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public R<User> login(HttpSession session, @RequestBody Map map){
        String phone = map.get("phone").toString();

        //2. 根据phone查询数据库
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        lqw.eq(User::getPhone,phone);
        User user = userService.getOne(lqw);

//        log.info(u.toString());
//        log.info("ID为{}",u.getId());
        //如果user不存在，自动注册
        if (user == null){
            user = new User();
            user.setPhone(phone);
            user.setStatus(1);
            userService.save(user);
        }

        //5. id存入session
        session.setAttribute("user",user.getId());
        return R.success(user);
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
