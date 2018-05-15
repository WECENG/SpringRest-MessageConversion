package cn.gdou.web.controller;

import cn.gdou.DAO.entity.Product;
import cn.gdou.exception.ProductNotFoundException;
import cn.gdou.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import cn.gdou.exception.Error;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
//@RestController
@RequestMapping(value = "/rest")
public class SpringMvcController {
    @Autowired
    private ProductService service;

    @RequestMapping(value = "/welcome")
    public String welcome(Model model){
        model.addAttribute("product",new Product());
        return "welcome";
    }

    @RequestMapping(value = "/save",consumes = "application/json",produces = "application/json")
    public @ResponseBody ResponseEntity<Product> saveProduct(
            Model model,@RequestBody Product product,
            UriComponentsBuilder ucb){
        Product product1=product;
        service.save(product1);
        HttpHeaders headers=new HttpHeaders();
        URI locationUri=ucb.path("/rest/query/")
                            .path(String.valueOf(product1.getId()))
                            .build()
                            .toUri();
        headers.setLocation(locationUri);
        ResponseEntity<Product> responseEntity=
                new ResponseEntity<>(product1,headers,HttpStatus.CREATED);
        return responseEntity;
    }

    @RequestMapping(value = "/query/{id}")
    public @ResponseBody Product queryProduct(@PathVariable("id")int id){
        Product product= service.findById(id);
        if(product==null) {
            throw new ProductNotFoundException(id);
        }
        return product;
    }

    @RequestMapping(value = "/xmlquery")
    public @ResponseBody List<Product> xmlquery(Model model){
        return service.queryall();
    }

    @RequestMapping(value = "/jsonquery",produces = "application/json")
    public @ResponseBody List<Product> jsonquery(Model model){
        return service.queryall();
    }

    //异常处理器，处理该类中所有抛出ProductNotFoundException
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody Error productNotFound(ProductNotFoundException e){
        long productId=e.getProductId();
        return new Error(404,"Product ["+productId+"] not found");
    }
}
