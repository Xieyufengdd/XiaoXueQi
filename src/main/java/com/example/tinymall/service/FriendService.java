package com.example.tinymall.service;

import com.example.tinymall.entity.Friend;
import com.example.tinymall.entity.Users;

import java.util.ArrayList;

public interface FriendService {
    public void addToFriendList(Friend friend);

    public Friend existsRelation(Integer uid,Integer fid);

    public void deleteFriendship(Integer uid,Integer fid);

    public ArrayList<Users> friendList(Integer userId);
}
