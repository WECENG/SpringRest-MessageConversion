package cn.gdou.service;

import cn.gdou.DAO.entity.Product;
import cn.gdou.DAO.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product){
        return productRepository.save(product);
    }

    public Product findById(int id){
        return productRepository.findById(id);
    }

    public List<Product> queryall(){
        return productRepository.findAll();
    }
}
