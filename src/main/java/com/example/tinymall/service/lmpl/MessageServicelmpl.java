package com.example.tinymall.service.lmpl;

import com.example.tinymall.Dao.MessageDao;
import com.example.tinymall.Dao.UserDao;
import com.example.tinymall.entity.Message;
import com.example.tinymall.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServicelmpl implements MessageService {
    @Autowired
    private MessageDao messageDao;
    @Override
    public void sendNewMessage(Message message) {
        messageDao.sendNewMessage(message);
    }

    @Override
    public ArrayList<Message> selectMessageBySidandFid(Integer UID, Integer FID) {
        return messageDao.selectMessageBySidandFid(UID,FID);
    }

    @Override
    public void messageDeleteByMid(Message message) {
        messageDao.messageDeleteByMid(message);
    }
}

/*
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
    public Users selectUserByUserid(Integer uid){return userDao.selectUserByUserid(uid);}

    @Override
    public void registerNewUser(Users user){
        userDao.registerNewUser(user);
    }

    @Override
    public Users checkUserPassword(String username, String password) {
        return userDao.checkUserPassword(username,password);
    }

    @Override
    public void updateUserInformation(Users user) {
        //return
        userDao.updateUserInformation(user);
    }


}
*/
