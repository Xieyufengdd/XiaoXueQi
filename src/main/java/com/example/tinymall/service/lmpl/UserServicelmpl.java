package com.example.tinymall.service.lmpl;

import com.example.tinymall.Dao.UserDao;
import com.example.tinymall.entity.Users;
import com.example.tinymall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicelmpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Users selectUserByUsername(String name){
        return userDao.selectUserByUsername(name);
    }

    @Override
    public Users selectUserByEmail(String email) {
        return userDao.selectUserByEmail(email);
    }

    @Override
    public void registerNewUser(Users user){
        userDao.registerNewUser(user);
    }

    @Override
    public Users checkUserPassword(String email, String password) {
        return userDao.checkUserPassword(email,password);
    }

    @Override
    public void updateUserInformation(Users user) {
        //return
        userDao.updateUserInformation(user);
    }

    @Override
    public Users selectUserByUserid(Integer rid) {
        return userDao.selectUserByUserid(rid);
    }


}
