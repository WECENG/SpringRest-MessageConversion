package cn.gdou.DAO.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "tb_product")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})  //java对象转为json时需要忽略这些属性
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String descr;
    private double price;
    private double quantity;

    public Product() {
    }

    public Product(String name, String descr, double price, double quantity) {
        this.name = name;
        this.descr = descr;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(Integer id, String name, String descr, double price, double quantity) {
        this.id = id;
        this.name = name;
        this.descr = descr;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", descr='" + descr + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
