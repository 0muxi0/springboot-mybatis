package com.lovelee.springbootmybatis.controller;

import com.lovelee.springbootmybatis.model.UserDomain;
import com.lovelee.springbootmybatis.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/add")
    public int addUser(UserDomain user){
        return userService.addUser(user);
    }

    @ResponseBody
    @GetMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize){
        return userService.findAllUser(pageNum,pageSize);
    }

    @ResponseBody
    @GetMapping("/delete")
    public String delete(int id) {
        userService.deleteUser(id);
        return "1";
    }

    @ResponseBody
    @PostMapping("/update")
    public String updateUserById(@RequestParam Integer userId, @RequestParam String userName, @RequestParam String password, @RequestParam String phone, HttpServletRequest req, HttpServletResponse res) {
        UserDomain user = new UserDomain();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setPassword(password);
        user.setPhone(phone);
        userService.updateUserById(user);
        return "1";
    }
}
