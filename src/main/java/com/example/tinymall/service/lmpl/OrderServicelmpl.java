package com.example.tinymall.service.lmpl;

import com.example.tinymall.Dao.OrderDao;
import com.example.tinymall.entity.Good;
import com.example.tinymall.entity.Orders;
import com.example.tinymall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderServicelmpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void startOrder(Integer uid, Integer gid, String address) {
        orderDao.startOrder(uid,gid,address);
        //return
    }

    @Override
    public Good findGoods(Integer gid) {
        return orderDao.findGoods(gid);
    }

    @Override
    public void cancelOrder(Integer oid) {
        orderDao.cancelOrder(oid);
    }

    @Override
    public Orders findOrders(Integer oid) {
        return orderDao.findOrders(oid);
    }

    @Override
    public ArrayList<Orders> orderList(Integer uid) {
        return orderDao.orderList(uid);
    }

    @Override
    public ArrayList<Good> findGoodsByName(String string) {
        return orderDao.findGoodsByName(string);
    }

    @Override
    public void addNewGoods(Good good) {
        orderDao.addNewGoods(good);
    }

    @Override
    public void deleteGoods(Integer gid) {
        orderDao.deleteGoods(gid);
    }
}
