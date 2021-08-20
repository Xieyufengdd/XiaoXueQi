package com.example.tinymall.Dao;

import com.example.tinymall.entity.Friend;
import com.example.tinymall.entity.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface FriendDao {
    public void addToFriendList(Friend friend);
    public Friend existsRelation(Integer uid,Integer fid);
    public void deleteFriendship(Integer uid,Integer fid);
    public ArrayList<Users> friendList(Integer userId);
}
