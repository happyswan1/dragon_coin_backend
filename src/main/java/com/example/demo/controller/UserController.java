package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.po.DragonUser;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public DragonUser getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

//    @PostMapping
//    public boolean addUser(@RequestBody DragonUser dragonUser) {
//        return userService.save(dragonUser);
//    }
//
//    @PostMapping
//    public boolean updateUser(@RequestBody DragonUser dragonUser) {
//        return userService.updateById(dragonUser);
//    }

    @PostMapping("/delete/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        return userService.removeById(id);
    }

    @GetMapping("/page")
    public Page<DragonUser> getUsers(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "10") int pageSize) {
        return userService.selectUserPage(page, pageSize);
    }
    @GetMapping("/test")
    public String test(){
        return "hello from usercontroller";
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody DragonUser dragonUser) {
        System.out.println("进来注册了");
        // 检查用户是否已存在
        DragonUser existingUser = userService.lambdaQuery().eq(DragonUser::getPhoneNumber, dragonUser.getPhoneNumber()).one();
        if (existingUser != null) {
            return ResponseEntity.badRequest().body("Error: Phone number is already in use!");
        }

        // 保存用户
        userService.save(dragonUser);
        return ResponseEntity.ok("User registered successfully!");
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody DragonUser loginRequest) {
        DragonUser dragonUser = userService.lambdaQuery().eq(DragonUser::getPhoneNumber, loginRequest.getPhoneNumber()).one();

        if (dragonUser != null && dragonUser.getPwd().equals(loginRequest.getPwd())) {
            return ResponseEntity.ok("User logged in successfully!");
        } else {
            return ResponseEntity.badRequest().body("Error: Phone number or password is incorrect!");
        }
    }

    // 其他方法...
}