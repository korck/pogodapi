package domain.services;

import domain.Product;

import java.util.List;
import java.util.ArrayList;

public class ShopService {
    private static List<Product> db = new ArrayList<Product>();
    private static int currentId = 1;
    
    public List<Product> getAllProducts() {
        return db;
    }
    
    public Product getProduct(int id){
        for(Product product : db) {
            if(product.getId()==id)
                return product;
        }
        return null;
    }
    public List<Product> getCategory(int id){
        List<Product> result = new ArrayList<Product>();
        for(Product product : result) {
            if(product.getCategory()!=id)
                result.remove(product);
        }
        return result;
    }
    
    
    public void addProduct(Product product) { 
        product.setId(++currentId);
        db.add(product);
    }
    
    public void updateProduct(Product product) {
        for(Product _product : db) {
            if(product.getId()==_product.getId()){
                product.setPrice(_product.getPrice());
                product.setName(_product.getName());
                product.setCategory(_product.getCategory());
            }
        }
    }
    
    public void deleteProduct(Product product) {
        db.remove(product);
    }
}
