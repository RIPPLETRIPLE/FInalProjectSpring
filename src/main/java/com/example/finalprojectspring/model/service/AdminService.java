package com.example.finalprojectspring.model.service;

import com.example.finalprojectspring.model.entity.Order;
import com.example.finalprojectspring.model.entity.Product;
import com.example.finalprojectspring.model.entity.User;
import com.example.finalprojectspring.model.exception.FieldDontPresent;
import com.example.finalprojectspring.model.repo.*;
import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public void deleteProductByID(long productId) {
        productRepository.deleteById(productId);
    }

    public void saveImage(MultipartFile image) throws IOException {
        String folder = "src/main/webapp/product-image/";
        byte[] bytes = image.getBytes();
        Path path = Paths.get(folder + image.getOriginalFilename());
        Files.write(path, bytes);
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
