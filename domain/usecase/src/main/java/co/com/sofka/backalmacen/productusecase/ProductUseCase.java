package co.com.sofka.backalmacen.productusecase;


import co.com.sofka.backalmacen.productmodel.Product;
import co.com.sofka.backalmacen.productmodel.gateway.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Data
@AllArgsConstructor
public class ProductUseCase {
@Autowired
    private final ProductRepository productRepository;

    public Mono<Product> create(Product product){
        return productRepository.create(product);
    }

    public Mono<Product> getByIdProduct(String id){
        return productRepository.getByIdProduct(id);
    }

    public Flux<Product> getAllProduct(){
        return productRepository.getAllProduct();
    }

    public Mono<Void> delete(String id){
        return productRepository.delete(id);
    }

    public Mono<Product> update(String id, Product product){
        return productRepository.update(id, product);
    }

}

