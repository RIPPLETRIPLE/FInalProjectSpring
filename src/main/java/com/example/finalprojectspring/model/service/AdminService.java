package com.example.finalprojectspring.model.service;

import com.example.finalprojectspring.model.entity.Order;
import com.example.finalprojectspring.model.entity.Product;
import com.example.finalprojectspring.model.entity.User;
import com.example.finalprojectspring.model.exception.FieldDontPresent;
import com.example.finalprojectspring.model.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
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

    //------ ProductManage ------//

    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    //------ UserManage ------//
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<Order> getAllOrdersForUser(User user) {
        return orderRepository.findOrdersByUser(user);
    }

    public User getUserByID(long id) throws FieldDontPresent {
        return userRepository.findById(id).orElseThrow(FieldDontPresent::new);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }
}
