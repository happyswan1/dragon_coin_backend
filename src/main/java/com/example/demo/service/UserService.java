package com.example.demo.service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.UserMapper;
import com.example.demo.po.DragonUser;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, DragonUser> {

    public Page<DragonUser> selectUserPage(int page, int pageSize) {
        Page<DragonUser> userPage = new Page<>(page, pageSize);
        return this.page(userPage);
    }

    // 这里可以添加其他自定义方法
}