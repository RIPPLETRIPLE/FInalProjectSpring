package com.example.finalprojectspring.model.service;

import com.example.finalprojectspring.model.entity.Product;
import com.example.finalprojectspring.model.entity.User;
import com.example.finalprojectspring.model.repo.OrderRepository;
import com.example.finalprojectspring.model.repo.ProductRepository;
import com.example.finalprojectspring.model.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    //------- UserManage -------
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createNewUser(User user) {
        return userRepository.save(user);
    }

    //------- ProductManage -------
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductByID(long id) {
        return productRepository.getById(id);
    }

}
