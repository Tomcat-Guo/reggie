package com.mimi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mimi.common.R;
import com.mimi.domain.Employee;
import com.mimi.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 登录
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        //1. 密码加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //2. 根据username查询数据库
        LambdaQueryWrapper<Employee> lqw = new LambdaQueryWrapper<Employee>();
        lqw.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(lqw);

        if (emp == null){
            return R.error("用户不存在");
        }

        //3. 对比密码
        if (!emp.getPassword().equals(password)){
            return R.error("密码不正确");
        }

        //4. 对比禁用状态
        if (emp.getStatus()!=1){
            return R.error("账户已被禁用");
        }

        //5. id存入session
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);
    }

    /**
     * 退出
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        //1. 清理session员工ID
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    /**
     * 新增员工
     * @param employee
     * @return
     */
    @PostMapping
    public R<String> add(@RequestBody Employee employee){
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employeeService.save(employee);
        return R.success("添加员工成功");
    }

    /**
     * 查询员工
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(Integer page, Integer pageSize, String name){
        //1. 构造分页构造器
        Page<Employee> iPage = new Page<>(page, pageSize);
        //3. 构造条件构造器
        LambdaQueryWrapper<Employee> lwq = new LambdaQueryWrapper();
        // 按姓名模糊匹配
        lwq.like(StringUtils.isNotEmpty(name),Employee::getName,name);
        // 按更新时间降序
        lwq.orderByDesc(Employee::getUpdateTime);

        //3 调用page方法
        employeeService.page(iPage, lwq);

        //4. 返回R
        return R.success(iPage);

    }

    /**
     * 修改员工状态
     * @param employee
     * @return
     */
    @PutMapping
    public R<String> editEmployee(@RequestBody Employee employee){
        employeeService.updateById(employee);
        return R.success("修改成功");
    }

    /**
     * 编辑反查数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Employee> queryEmployeeById(@PathVariable Long id){
        Employee emp = employeeService.getById(id);
        return R.success(emp);
    }








}
