package com.mimi;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mimi.dao.EmployeeDao;
import com.mimi.domain.Employee;
import com.mimi.service.CategoryService;
import com.mimi.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Map;
import java.util.Set;

@SpringBootTest
class ReggieApplicationTests {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private CategoryService  categoryService;

    @Test
    void contextLoads() {
        LambdaQueryWrapper<Employee> lqw = new LambdaQueryWrapper<Employee>();
        lqw.eq(Employee::getUsername,"admin").eq(Employee::getPassword,"1");


    }

    @Test
    void delete(){
        categoryService.removeById(1560088069003882498L);
    }

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void testJedis(){
//        ValueOperations valueOperations = redisTemplate.opsForValue();
//        valueOperations.set("job","IT");
//        System.out.println(valueOperations.get("job"));
        redisTemplate.opsForHash().put("user1","name","tomcat");
        redisTemplate.opsForHash().put("user1","age","20");

//        Jedis jedis = new Jedis("10.53.20.21", 6379, new JedisClientConfig() {
//            @Override
//            public String getPassword() {
//                return "root";
//            }
//        });
//
//        Set<String> keys = jedis.keys("*");
//        for (String key : keys) {
//            System.out.println(key);
//        }
//
////        jedis.sadd("set","a","b","c");
////
////        Set<String> set = jedis.smembers("set");
////        for (String s : set) {
////            System.out.println(s);
////        }
////
////        jedis.set("username","tomcat");
////
////        String username = jedis.get("password");
////        System.out.println(username);
//
//        jedis.hset("myhash","addr","beijing");
//        jedis.hset("myhash","cit","shenzhen");
//
//        System.out.println(jedis.hget("myhash","addr"));
//
//        Map<String, String> myhash = jedis.hgetAll("myhash");
//        String addr = myhash.get("addr");
//        System.out.println(addr);
//        System.out.println(myhash.get("cit"));
//        System.out.println(myhash.entrySet());
//        jedis.close();
    }

}
