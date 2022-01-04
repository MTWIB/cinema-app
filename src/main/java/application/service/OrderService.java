package application.service;

import application.model.Order;
import application.model.ShoppingCart;
import application.model.User;
import java.util.List;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrdersHistory(User user);
}
