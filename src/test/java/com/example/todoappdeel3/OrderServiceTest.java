package com.example.todoappdeel3;

import com.example.todoappdeel3.dao.UserDAO;
import com.example.todoappdeel3.models.CustomUser;
import com.example.todoappdeel3.models.Order;
import com.example.todoappdeel3.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

public class OrderServiceTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindOrderUser_withNoOrders() {
        // Arrange
        CustomUser user = new CustomUser();
        user.orders = null;
        when(userDAO.getUser(1L)).thenReturn(user);

        // Act
        List<Order> result = orderService.findOrderUser(1L);

        // Assert
        assertThat(result, is(new ArrayList<>()));
    }

    @Test
    public void testFindOrderUser_withOrders() {
        // Arrange
        Order order1 = new Order();
        Order order2 = new Order();
        List<Order> orders = List.of(order1, order2);
        CustomUser user = new CustomUser();
        user.orders = orders;
        when(userDAO.getUser(1L)).thenReturn(user);

        // Act
        List<Order> result = orderService.findOrderUser(1L);

        // Assert
        assertThat(result, is(orders));
    }

    @Test
    public void testFindOrderUser_withEmptyOrderList() {
        // Arrange
        CustomUser user = new CustomUser();
        user.orders = new ArrayList<>();
        when(userDAO.getUser(1L)).thenReturn(user);

        // Act
        List<Order> result = orderService.findOrderUser(1L);

        // Assert
        assertThat(result, is(new ArrayList<>()));
    }
}
