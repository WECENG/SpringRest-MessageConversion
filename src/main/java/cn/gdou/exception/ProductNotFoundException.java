package cn.gdou.exception;

public class ProductNotFoundException extends RuntimeException {
    private  int productId;
    public ProductNotFoundException(int productId){
        this.productId=productId;
    }

    public int getProductId() {
        return productId;
    }
}
