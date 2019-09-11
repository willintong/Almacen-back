package co.com.sofka.backalmacen.productdriveradapter.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "product")
public class ProductData {

    @Id
    private String id;
    private String name;
    private double precio;

}
