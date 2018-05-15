package cn.gdou.DAO.repository;

import cn.gdou.DAO.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findById(int id);
}
