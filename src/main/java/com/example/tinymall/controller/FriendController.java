package com.example.tinymall.controller;

import com.example.tinymall.api.CommonResult;
import com.example.tinymall.entity.Friend;
import com.example.tinymall.entity.Users;
import com.example.tinymall.service.FriendService;
import com.example.tinymall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class FriendController {
    @Autowired
    private UserService userService;
    @Autowired
    private FriendService friendService;

    @RequestMapping(value = "/friendadd",method = RequestMethod.POST)
    public CommonResult<String> friendadd(@RequestBody Users friend, HttpSession session) {
        Users myself = (Users)session.getAttribute("loginUser");
        String friendName = friend.getUsername();
        try {
            Users user1 = userService.selectUserByUsername(friendName);
            if (user1 != null) {
                if (user1 != myself){
                    if (friendService.existsRelation(user1.getUID(),myself.getUID())==null){
                        Friend friend1 = new Friend();
                        friend1.setUSR_ID(myself.getUID());
                        friend1.setFRI_ID(user1.getUID());
                        friendService.addToFriendList(friend1);
                        return CommonResult.success("添加成功");
                    }else {
                        return CommonResult.failed("已添加该好友");
                    }
                }else {
                    return CommonResult.failed("不可以添加自己");
                }
            } else {
                return CommonResult.failed("用户名不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("好友添加失败!");
        }
    }

    @RequestMapping(value = "/deletefriendship",method = RequestMethod.POST)
    public CommonResult<String> deleteFriendship(@RequestBody Users friend, HttpSession session) {
        Users myself = (Users)session.getAttribute("loginUser");
        String friendName = friend.getUsername();
        try {
            Users user1 = userService.selectUserByUsername(friendName);
            if (user1 != null) {
                if (user1 != myself){
                    if (friendService.existsRelation(user1.getUID(),myself.getUID())!=null){
                        friendService.deleteFriendship(user1.getUID(),myself.getUID());
                        return CommonResult.success("删除成功");
                    }else {
                        return CommonResult.failed("未添加该好友");
                    }
                }else {
                    return CommonResult.failed("不可以删除自己");
                }
            } else {
                return CommonResult.failed("用户名不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("好友删除失败!");
        }
    }

    @RequestMapping(value = "/friendlist",method = RequestMethod.POST)
    public CommonResult<String> friendList(HttpSession session) {
        Users myself = (Users)session.getAttribute("loginUser");
        try {
            String str = "好友列表：";
            for (Users user:friendService.friendList(myself.getUID())){
                str += user.getUsername()+"\n";
            }
            return CommonResult.success(str);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("查看好友列表失败!");
        }
    }
}
