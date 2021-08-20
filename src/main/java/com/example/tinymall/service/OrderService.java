package com.example.tinymall.service;

import com.example.tinymall.entity.Good;
import com.example.tinymall.entity.Orders;

import java.util.ArrayList;

public interface OrderService {
    public void startOrder(Integer uid,Integer gid,String address);
    public Good findGoods(Integer gid);
    public void cancelOrder(Integer oid);
    public Orders findOrders(Integer oid);
    public ArrayList<Orders> orderList(Integer uid);
    public ArrayList<Good> findGoodsByName(String string);
    public void addNewGoods(Good good);
    public void deleteGoods(Integer gid);
}
