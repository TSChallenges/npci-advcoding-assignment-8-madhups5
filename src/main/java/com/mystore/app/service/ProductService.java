package com.mystore.app.service;

import com.mystore.app.entity.Product;
import com.mystore.app.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private Integer currentId = 1;

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        product.setId(currentId++);
        productRepository.save(product);
        return product;
    }

    // Get All Products with Pagination & Sorting
    public Page<Product> getAllProducts(int page, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        return productRepository.findAll(pageable);
    }

    public Product getProduct(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.get();
    }

    public Product updateProduct(Integer id, Product product) {
        Product p = productRepository.findById(id).get();
        if (p == null) return null;
        p.setName(product.getName());
        p.setPrice(product.getPrice());
        p.setCategory(product.getCategory());
        p.setStockQuantity(product.getStockQuantity());
        productRepository.save(p);
        return p;
    }

    public String deleteProduct(Integer id) {
        Product p = productRepository.findById(id).get();
        if (p == null) return "Product Not Found";
        productRepository.delete(p);
        return "Product Deleted Successfully";
    }

    // TODO: Method to search products by name
public List<Product> searchByProductName(String name){
    return  productRepository.findByNameContainingIgnoreCase(name);
}


    // TODO: Method to filter products by category
public List<Product> filterByCategory(String category){
        return productRepository.findByCategory(category);
}

    // TODO: Method to filter products by price range
    public List<Product> filterByPrice(double minPrice, double maxPrice){
        return productRepository.findByPriceBetween(minPrice,maxPrice);
    }

    // TODO: Method to filter products by stock quantity range
    public List<Product> filterByStockQuantity(int minQuantity, int maxQuantiy){
        return productRepository.findByStockQuantityBetween(minQuantity,maxQuantiy);
    }

}
