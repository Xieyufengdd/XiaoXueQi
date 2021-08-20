package com.example.tinymall.service;

import com.example.tinymall.entity.Users;

public interface UserService {
    public Users selectUserByUsername(String name);

    public void registerNewUser(Users user);

    public Users checkUserPassword(String username, String password);

    public void updateUserInformation(Users user);

    public Users selectUserByUserid(Integer rid);
}
