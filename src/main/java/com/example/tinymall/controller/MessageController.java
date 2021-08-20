package com.example.tinymall.controller;

import com.example.tinymall.api.CommonResult;
import com.example.tinymall.entity.Friend;
import com.example.tinymall.entity.Message;
import com.example.tinymall.entity.Orders;
import com.example.tinymall.entity.Users;
import com.example.tinymall.service.MessageService;
import com.example.tinymall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MessageController {
    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/messagesend",method = RequestMethod.POST)
    public CommonResult<String> messagesend(@RequestBody Message message, HttpSession session) {

        Users myself = (Users)session.getAttribute("loginUser");
        message.setSID(myself.getUID());
        message.setType(1);
        try {
            Users user1 = userService.selectUserByUserid(message.getRID());
            if (user1 != null) {
                if (user1 != myself){
                    messageService.sendNewMessage(message);
                    return CommonResult.failed("发送成功 "+message.toString());
                }else {
                    return CommonResult.failed("不可以发送给自己 "+message.toString());
                }
            } else {
                return CommonResult.failed("用户不存在 "+message.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("发送失败 "+message.toString());
        }
    }

    @RequestMapping(value = "/messageselect",method = RequestMethod.POST)
    public Map<String, Object> messageselect(@RequestBody Users user, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            Users myself = (Users)session.getAttribute("loginUser");
            Integer UID = myself.getUID();
            Integer FID = user.getUID();
            Users user2 = userService.selectUserByUserid(FID);
            if (myself == null || user2==null) {
                map.put("success", false);
                map.put("message", "用户不存在！"+UID+" "+FID);
            }
            else{
                ArrayList<Message> messageList=messageService.selectMessageBySidandFid(UID,FID);
                String string=new String();
                for(Message message:messageList){
                    string+=message.toString()+"\n";
                }
                map.put("success", true);
                map.put("message", string);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "消息查询失败！");
        }
        return map;
    }

    @RequestMapping(value = "/messagedelete",method = RequestMethod.POST)
    public CommonResult<String> messagedelete(@RequestBody Message message, HttpSession session) {

        try {
            Users myself = (Users)session.getAttribute("loginUser");

            if (myself != null) {
                messageService.messageDeleteByMid(message);
                return CommonResult.failed("删除成功 "+message.toString());
            }
            else {
                return CommonResult.failed("删除失败 "+message.toString());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("删除失败 "+message.toString());
        }
    }
}
