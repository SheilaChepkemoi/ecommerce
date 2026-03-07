package com.sheila.com.ecommerce.controller;

import com.sheila.com.ecommerce.dto.ProductDTO;
import com.sheila.com.ecommerce.model.Products;
import com.sheila.com.ecommerce.service.ProductService;

import com.sheila.com.ecommerce.utils.responses.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")

    public CustomResponse getProductList(){
        List<ProductDTO> products = productService.getAllProducts();
        return CustomResponse.builder()
                .payLoad(products)
                .build();

    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable Long id){
        return productService.findProductById(id).
                map(ResponseEntity::ok).
                orElseGet(()-> ResponseEntity.notFound().build());
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable Long productId){
        Optional<Products> products = productService.findProductById(productId);

        if(products.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Products product = products.get();

        if(product.getImageData() == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().
                header("Content-Type", product.getImageType()).
                body(product.getImageData());

    }


    @PostMapping( value = "/add-product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addProduct(@RequestPart Products products, @RequestPart MultipartFile imageFile) throws IOException {
        try{
            Products savedProduct = productService.addNewProduct(products, imageFile);
            return ResponseEntity.ok(savedProduct);
        }
        catch (IOException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

    @PutMapping(value="/update-product/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateProduct(@PathVariable Long id,@RequestPart Products products, @RequestPart MultipartFile imageFile) throws IOException {
        try{
            Products newProduct= productService.updateProductInfo(products, imageFile);
            return ResponseEntity.ok(newProduct);
        }
        catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        Optional<Products> products = productService.findProductById(id);
        if(products.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        productService.deleteProductById(id);

        return ResponseEntity.ok("Product deleted successfully");

    }

//    @GetMapping("/products/{keyword}")
//
//    public ResponseEntity<List<Products>> searchByKeyword(@RequestParam String keyword){
//        List<Products> products = productService.searchProducts(keyword);
//
//        return ResponseEntity.ok(products);
//
//    }

}
