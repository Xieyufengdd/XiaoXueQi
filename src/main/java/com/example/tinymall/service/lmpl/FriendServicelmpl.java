package com.example.tinymall.service.lmpl;

import com.example.tinymall.Dao.FriendDao;
import com.example.tinymall.entity.Friend;
import com.example.tinymall.entity.Users;
import com.example.tinymall.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FriendServicelmpl implements FriendService {
    @Autowired
    private FriendDao friendDao;

    @Override
    public void addToFriendList(Friend friend) {
        friendDao.addToFriendList(friend);
    }

    @Override
    public Friend existsRelation(Integer uid,Integer fid) {
        return friendDao.existsRelation(uid,fid);
    }

    @Override
    public void deleteFriendship(Integer uid, Integer fid) {
        friendDao.deleteFriendship(uid,fid);
    }

    @Override
    public ArrayList<Users> friendList(Integer userId) {
        return friendDao.friendList(userId);
    }
}
