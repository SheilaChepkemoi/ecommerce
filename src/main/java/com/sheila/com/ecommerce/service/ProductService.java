package com.sheila.com.ecommerce.service;

import com.sheila.com.ecommerce.dto.ProductDTO;
import com.sheila.com.ecommerce.model.Products;
import com.sheila.com.ecommerce.repo.ProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service

public class ProductService {

    private final ProductRepo productRepo;
    private final ModelMapper modelMapper = new ModelMapper();

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;

    }

//    public List<ProductDTO> getAllProducts() {
//
//        return modelMapper.map(productRepo.findAll(), List<ProductDTO>.class);
////      return productRepo.findAll();
//
//    }

    public List<ProductDTO> getAllProducts() {
        return productRepo.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
    }

    public Optional<Products> findProductById(Long id) {

        return productRepo.findById(id);

    }

    public Products addNewProduct(Products products, MultipartFile image) throws IOException {

        products.setImageName(image.getOriginalFilename());
        products.setImageType(image.getContentType());
        products.setImageData(image.getBytes());
        return productRepo.save(products);

    }


    public Products updateProductInfo(Products products, MultipartFile image) throws IOException {

        products.setImageName(image.getOriginalFilename());
        products.setImageType(image.getContentType());
        products.setImageData(image.getBytes());
        System.out.println("Updating ID: " + products.getId());
        return productRepo.save(products);

    }

    public void deleteProductById(Long id) {

        productRepo.deleteById(id);


    }

//    public List<Products> searchProducts(String keyword) {
//
//        return productRepo.searchProduct(keyword);
//    }
}
