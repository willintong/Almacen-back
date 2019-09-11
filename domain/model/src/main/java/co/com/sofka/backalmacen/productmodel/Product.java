package co.com.sofka.backalmacen.productmodel;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String id;
    private String name;
    private double precio;
}

