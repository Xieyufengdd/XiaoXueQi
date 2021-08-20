package com.example.tinymall.Dao;

import com.example.tinymall.entity.Good;
import com.example.tinymall.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface OrderDao {
    public void startOrder(Integer uid,Integer gid,String address);
    public Good findGoods(Integer gid);
    public void cancelOrder(Integer oid);
    public Orders findOrders(Integer oid);
    public ArrayList<Orders> orderList(Integer uid);
    public ArrayList<Good> findGoodsByName(String string);
    public void addNewGoods(Good good);
    public void deleteGoods(Integer gid);
}
