package co.com.sofka.backalmacen.productreactivewebcontroller;


import co.com.sofka.backalmacen.productmodel.Product;
import co.com.sofka.backalmacen.productusecase.ProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductController {

    private final ProductUseCase productUseCase;
    private static final String APIENPOINT = "/product";

    @GetMapping(value = APIENPOINT + "/{id}")
    public Mono<Product> getProduct(@PathVariable String id){
        return productUseCase.getByIdProduct(id);
    }

    @GetMapping
    public Flux<Product> getProduct(){
        return productUseCase.getAllProduct();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = APIENPOINT)
    public Mono<Product> createProduct(@RequestBody Product product){
        return productUseCase.create(product);
    }

    @DeleteMapping(value = APIENPOINT+"/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id){
        return productUseCase.delete(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = APIENPOINT+"/{id}")
    public Mono<Product> updateProduct(@RequestBody Product product, @PathVariable String id){
        return productUseCase.update(id, product);
    }
}
