package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Basket {

    private Long id_basket;
    private User user;
    private List<Product> products;

    public Basket(User user) {
        this.user = user;
    }
}
