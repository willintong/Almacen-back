package co.com.sofka.backalmacen.config;


import co.com.sofka.backalmacen.productmodel.gateway.ProductRepository;
import co.com.sofka.backalmacen.productusecase.ProductUseCase;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class UseCaseConfig {

    @Bean
    public  ProductUseCase createProduct(ProductRepository productRepository) {
        return new ProductUseCase(productRepository);
    }


}