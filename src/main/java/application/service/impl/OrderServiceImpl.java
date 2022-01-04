package application.service.impl;

import application.dao.OrderDao;
import application.lib.Inject;
import application.lib.Service;
import application.model.Order;
import application.model.ShoppingCart;
import application.model.Ticket;
import application.model.User;
import application.service.OrderService;
import application.service.ShoppingCartService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        Order order = new Order();
        List<Ticket> listOfTickets = new ArrayList<>(shoppingCart.getTickets());
        order.setTickets(listOfTickets);
        User user = shoppingCart.getUser();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        orderDao.add(order);
        shoppingCartService.clearShoppingCart(shoppingCart);
        return order;
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        return orderDao.getByUser(user);
    }
}
