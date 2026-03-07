package com.sheila.com.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products", schema = "ecommerce")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;
    private Date releaseDate;
    private int productAvailability;
    private int stockQuantity;
    private String imageName;
    private String imageType;
    private Date createdOn;
    private Date deletedOn;
    private Date modifiedOn;
    @Lob
    private byte[] imageData;


}
