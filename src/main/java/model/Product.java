package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long id_product;
    private String name;
    private Double price;
    private String description;
    private static Long index = 0L;

    public Product(Long id_product, String name) {
        this.id_product = id_product;
        this.name = name;
    }

    public Product(String name, Double price, String description) {
        this.id_product = setId_product();
        this.name = name;
        this.price = price;
        this.description = description;
    }

    private Long setId_product() {
        return ++index;
    }

}
