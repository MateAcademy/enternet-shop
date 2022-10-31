package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long id_order;
    private Long user_id;
    private Long basket_id;

    private String first_name;
    private String last_name;
    private String number_phone;
    public String street;
    private String hose_number;
    private String entered_code;

    private List<Product> products = new ArrayList<>();

    public Order(Long id_order) {
        this.id_order = id_order;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id_order=" + id_order +
                ", products=" + products +
                '}';
    }
}
