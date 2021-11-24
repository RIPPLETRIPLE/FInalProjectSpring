package com.example.finalprojectspring.model.service;

import com.example.finalprojectspring.model.entity.Product;
import com.example.finalprojectspring.model.entity.User;
import com.example.finalprojectspring.model.exception.FieldDontPresent;
import com.example.finalprojectspring.model.repo.*;
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
            user.setStatus(user.getStatus());
            return true;
        }
        return false;
    }

    //------- ProductManage -------
    public List<Product> getAllProducts() {
        return productRepository.findAll();
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

    //------- ColorManage -------
    public Product.Color getColorByID(long colorId) throws FieldDontPresent {
        return colorRepository.findById(colorId).orElseThrow(FieldDontPresent::new);
    }

    //------- SizeManage -------
    public Product.Size getSizeByID(long sizeId) {
        return sizeRepository.getById(sizeId);
    }

}
