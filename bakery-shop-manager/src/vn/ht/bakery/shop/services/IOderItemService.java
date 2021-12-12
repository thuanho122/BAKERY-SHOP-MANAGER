package vn.ht.bakery.shop.services;

import vn.ht.bakery.shop.model.OrderItem;

import java.util.List;

public interface IOderItemService {


    List<OrderItem> getOrderItems();

    void add(OrderItem newOrderItem);

    void update();

    OrderItem getOrderItemById(int id);


}
