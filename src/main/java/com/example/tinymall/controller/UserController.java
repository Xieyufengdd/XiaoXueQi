package com.example.tinymall.controller;


import com.example.tinymall.api.CommonResult;
import com.example.tinymall.entity.Users;
import com.example.tinymall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public Map<String, Object> registerNewUser(@RequestBody Map<String, String> remap) {
        String username = remap.get("username");
        String password = remap.get("password");
        String email = remap.get("email");
        String formatOfEmail = "([0-9]|[a-z]|[A-Z]|@)+.(com|cn)";

        Map<String, Object> map = new HashMap<>();
        try {
            Users student = new Users(username, password,email);
            Users student1 = userService.selectUserByUsername(student.getUsername());
            Users student2 = userService.selectUserByEmail(student.getEmail());
            if (!Pattern.matches(formatOfEmail,email)){
                map.put("code", "400");
                map.put("message", "邮箱格式错误！");
            }else if (student2 != null){
                map.put("code", "400");
                map.put("message", "邮箱已注册！");
            }
            else if (student1 != null) {
                map.put("code", "400");
                map.put("message", "用户名已注册！");
            }
            else{
                userService.registerNewUser(student);
                map.put("code", "200");
                map.put("message", "用户注册成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
            map.put("message", "用户注册失败！");
        }
        return map;
    }

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public Map<String, Object> login(@RequestBody Users user, HttpSession session) {
        String email = user.getEmail();
        String password = user.getPassword();
        Map<String, Object> map = new HashMap<>();
        try {

            Users user1 = userService.selectUserByEmail(email);
            if (user1 != null) {
                Users user2 = userService.checkUserPassword(user1.getEmail(), password);
                if (user2 != null) {
                    session.setAttribute("loginUser", user2);
                    map.put("code", "200");
                    map.put("message","用户登录成功");
                    map.put("data","Info : "+session.getAttribute("loginUser").toString());
                    return map;
                } else {
                    map.put("code", "400");
                    map.put("message","密码错误");
                    return map;
                }
            } else {
                map.put("code", "400");
                map.put("message","邮箱未注册");
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
            map.put("message","用户登录失败");
            return map;
        }
    }

    @RequestMapping(value = "/user/logout",method = RequestMethod.POST)
    public Map<String, Object> Logout(HttpSession session) {
        session.invalidate();
        Map<String, Object> map = new HashMap<>();
        map.put("code", "200");
        map.put("message","登出成功！");
        return map;
    }

    //基本信息
    @RequestMapping(value = "/user/log",method = RequestMethod.POST)
    public Map<String, Object> Log(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "200");
        map.put("message", session.getAttribute("loginUser").toString());
        return map;
    }

    @RequestMapping(value = "/user/logset",method = RequestMethod.POST)
    public Map<String, Object> logset(@RequestBody Users user, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            Users loginer= (Users) session.getAttribute("loginUser");
            Users student1 = userService.selectUserByUsername(user.getUsername());
            if (student1 != null && !student1.getUsername().equals(loginer.getUsername())) {
                map.put("code", "400");
                map.put("message", "用户名已注册！");

            }
            else{
                user.setUID(loginer.getUID());
                userService.updateUserInformation(user);
                map.put("code", "200");
                map.put("message", "用户修改成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "400");
            map.put("message", "用户修改失败！");
        }
        return map;
    }
/*@PostMapping("/register")
    public Map<String, Object> registerNewUser(@RequestBody Map<String, String> remap) {
        String username = remap.get("username");
        String password = remap.get("password");
        Map<String, Object> map = new HashMap<>();
        try {
            Users student = new Users(username, password);
            Users student1 = userService.selectUserByUsername(student.getUsername());
            if (student1 != null) {
                map.put("success", false);
                map.put("message", "用户名已注册！");
            }
            else{
                userService.registerNewUser(student);
                map.put("success", true);
                map.put("message", "用户注册成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "用户注册失败！");
        }
        return map;
    }*/





}
