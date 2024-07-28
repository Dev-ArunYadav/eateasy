package com.court.eateasy.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "seller")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sellers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seller_id;

    private String name;

    @NotBlank(message = "Mobile number is required.")
    private String mobileNumber;

    @NotBlank(message = "Email Id is required.")
    private String email;

    private String password;
    private String image;
    private String description;

    @OneToMany(mappedBy = "product_sellers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Products> products;
}
