package com.revature.hai_app.services;

import com.revature.hai_app.daos.productDAO;
import com.revature.hai_app.models.Product;

import java.util.List;

public class ProductService {
    //Injecting
    private final com.revature.hai_app.daos.productDAO productDAO;

    public ProductService(productDAO productDAO){ this.productDAO = productDAO;};

    public List<Product> getProductsByRarity(String rarity){
        return productDAO.getByRarity(rarity);
    }

    public String getProductNameByID(String id){
        Product product = productDAO.getByID(id);
        return product.getName();
    }

    public Product getByID(String id){
        Product product = productDAO.getByID(id);
        return product;
    }

    public int getProducePriceByID(String id){
        Product product = productDAO.getByID(id);
        return product.getPrice();
    }

    public void updateProduct(Product product){
        productDAO.update(product);
    }

    public List<Product> searchProdsByName(String search){
        return productDAO.searchProductsByName(search);
    }


}
