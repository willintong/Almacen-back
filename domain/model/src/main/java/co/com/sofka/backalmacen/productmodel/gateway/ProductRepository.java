package co.com.sofka.backalmacen.productmodel.gateway;


import co.com.sofka.backalmacen.productmodel.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ProductRepository {

    Mono<Product> create(Product product);
    Mono<Product> getByIdProduct(String id);
    Flux<Product> getAllProduct();
    Mono<Void> delete(String id);
    Mono<Product> update(String id, Product product);
}
