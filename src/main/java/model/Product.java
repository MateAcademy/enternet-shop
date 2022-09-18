package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author Sergey Klunniy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    //id по товару
    private Long id;
    private String name;
    private Double price;
    private String description;
    private static Long index = 0L;

    public Product(String name, Double price, String description) {
        this.id = setId();
        this.name = name;
        this.price = price;
        this.description = description;
    }

    private Long setId() {
        return ++index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name) && price.equals(product.price) && description.equals(product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, description);
    }
}
