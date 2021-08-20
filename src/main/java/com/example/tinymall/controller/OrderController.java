package com.example.tinymall.controller;

import com.example.tinymall.api.CommonResult;
import com.example.tinymall.entity.Good;
import com.example.tinymall.entity.Orders;
import com.example.tinymall.entity.Users;
import com.example.tinymall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/startorder",method = RequestMethod.POST)
    public CommonResult<String> login(@RequestBody Orders order, HttpSession session) {
        Users myself = (Users)session.getAttribute("loginUser");
        order.setUID(myself.getUID());
        try {
            if (orderService.findGoods(order.getGID()) != null){
                if (order.getAddress()!=null){
                    orderService.startOrder(order.getUID(),order.getGID(),order.getAddress());
                    return CommonResult.success("成功下单");
                }else {
                    return CommonResult.failed("地址无效");
                }
            }else {
                return CommonResult.failed("无此商品");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("发起订单失败!");
        }
    }

    @RequestMapping(value = "/cancelorder",method = RequestMethod.POST)
    public CommonResult<String> cancelOrder(@RequestBody Orders order, HttpSession session) {
        Users myself = (Users)session.getAttribute("loginUser");
        order.setUID(myself.getUID());
        try {
            if (orderService.findOrders(order.getOID()) != null){
                orderService.cancelOrder(order.getOID());
                return CommonResult.success("删除订单成功");
            }else {
                return CommonResult.failed("无此订单");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("发起订单失败!");
        }
    }

    @RequestMapping(value = "/orderlist",method = RequestMethod.POST)
    public CommonResult<String> orderList(HttpSession session) {
        Users myself = (Users)session.getAttribute("loginUser");
        try {
            String str = "";
            for (Orders orders:orderService.orderList(myself.getUID())){
                str += "OID:"+orders.getOID()+" GID:"+orders.getGID()+" Address:"+orders.getAddress()+"\n";
            }
            return CommonResult.success(str);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("查询订单失败!");
        }
    }

    @RequestMapping(value = "/goodsfilter",method = RequestMethod.POST)
    public CommonResult<String> goodsFilter(@RequestBody String string, HttpSession session) {
        try {
            String str = "";
            for (Good good:orderService.findGoodsByName("%"+string+"%")){
                str += "GID:"+good.getGID()+" Name:"+good.getGoodsname()+"\n";
            }
            System.out.println(str);
            return CommonResult.success(str);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("查找商品失败!");
        }
    }

    @RequestMapping(value = "/addnewgoods",method = RequestMethod.POST)
    public CommonResult<String> addNewGoods(@RequestBody Good good, HttpSession session) {
        try {
            orderService.addNewGoods(good);
            return CommonResult.success("添加商品成功");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("添加商品失败!");
        }
    }

    @RequestMapping(value = "/findgoodid",method = RequestMethod.POST)
    public CommonResult<String> findGoodId(@RequestBody Good good, HttpSession session) {
        try {
            Integer i = good.getGID();
            Good good1 = orderService.findGoods(i);
            if (good1!= null){
                return CommonResult.success("商品"+good1.getGoodsname());
            }else {
                return CommonResult.failed("无该商品!"+i);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("添加商品失败!");
        }
    }
    @RequestMapping(value = "/deletegoods",method = RequestMethod.POST)
    public CommonResult<String> deleteGoods(@RequestBody Good good,HttpSession session){
        try {
            if (orderService.findGoods(good.getGID())!=null){
                orderService.deleteGoods(good.getGID());
                return CommonResult.success("删除成功");
            }else {
                return CommonResult.failed("无此商品!");
            }
        }catch (Exception e){
            e.printStackTrace();
            return CommonResult.failed("删除失败!");
        }
    }

}

