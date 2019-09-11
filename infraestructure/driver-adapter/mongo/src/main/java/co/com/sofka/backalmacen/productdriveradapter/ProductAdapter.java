package co.com.sofka.backalmacen.productdriveradapter;

import co.com.sofka.backalmacen.productdriveradapter.data.ProductData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ProductAdapter extends ReactiveMongoRepository<ProductData,String> {

    Mono<Void> deleteById(String id);
}

