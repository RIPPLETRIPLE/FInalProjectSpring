package com.example.finalprojectspring.model.service;

import com.example.finalprojectspring.model.entity.Order;
import com.example.finalprojectspring.model.entity.Product;
import com.example.finalprojectspring.model.entity.User;
import com.example.finalprojectspring.model.entity.enums.OrderStatus;
import com.example.finalprojectspring.model.exception.FieldDontPresent;
import com.example.finalprojectspring.model.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private ColorRepository colorRepository;

    //------- UserManage -------
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean createNewUser(User user) {
        if (userRepository.existsUserByLogin(user.getLogin())) return false;
        userRepository.save(user);
        return true;
    }

    public boolean DBContainsUser(User user) {
        User userWithSuchLogin = userRepository.findFirstByLogin(user.getLogin());
        if (userWithSuchLogin != null && user.getPassword().equals(userWithSuchLogin.getPassword())) {
            user.setRole(userWithSuchLogin.getRole());
            user.setId(userWithSuchLogin.getId());
            user.setStatus(userWithSuchLogin.getStatus());
            return true;
        }
        return false;
    }

    //------- ProductManage -------
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsWithSortAndPagination(int page, Sort.Direction sortDirection, String sortBY) {
        return productRepository.findAll(PageRequest.of(page, 8, sortDirection, sortBY)).toList();
    }

    public long getCountOfProducts() {
        return productRepository.count();
    }
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductByID(long id) throws FieldDontPresent {
        return productRepository.findById(id).orElseThrow(FieldDontPresent::new);
    }

    //------- CategoryManage -------
    public Product.Category getCategoryByID(long categoryId) throws FieldDontPresent {
        return categoryRepository.findById(categoryId).orElseThrow(FieldDontPresent::new);
    }
    public List<Product.Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    //------- ColorManage -------
    public Product.Color getColorByID(long colorId) throws FieldDontPresent {
        return colorRepository.findById(colorId).orElseThrow(FieldDontPresent::new);
    }
    public List<Product.Color> getAllColors() {
        return colorRepository.findAll();
    }

    //------- SizeManage -------
    public Product.Size getSizeByID(long sizeId) {
        return sizeRepository.getById(sizeId);
    }

    public List<Product.Size> getAllSizes() {
        return sizeRepository.findAll();
    }

    //------- OrderManage -------
    public boolean createOrder(Order order) {
        if (orderRepository.findOrderByUserAndStatusAndProduct(order.getUser(), order.getStatus(), order.getProduct()).isPresent()) {
            return false;
        }

        orderRepository.save(order);
        return true;
    }

    public List<Order> getOrdersForUser(User user) {
        return orderRepository.findOrdersByUser(user);
    }

    public List<Order> getOrdersByStatus(User user, OrderStatus status) {
        return orderRepository.findOrdersByUserAndStatus(user, status);
    }

    public Order findOrderByUserAndStatusAndProduct(Order order) throws FieldDontPresent {
        return orderRepository.findOrderByUserAndStatusAndProduct(order.getUser(), order.getStatus(), order.getProduct()).orElseThrow(FieldDontPresent::new);
    }

    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    public Order getOrderByID(long orderId) throws FieldDontPresent {
        return orderRepository.findById(orderId).orElseThrow(FieldDontPresent::new);
    }

    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }

    public void updateStatusForOrders(List<Order> orders, OrderStatus registered) {
        orders.forEach(e -> {
            e.setStatus(registered);
        });

        orderRepository.saveAll(orders);
    }

    //------- CartManage -------
    public void retainCartForLoggedUser(List<Order> cart, User user) {
        cart.forEach(e -> {
            e.setUser(user);
        });

        orderRepository.saveAll(cart);
    }
}
