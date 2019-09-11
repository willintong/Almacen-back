package co.com.sofka.backalmacen.productdriveradapter;

import co.com.sofka.backalmacen.productmodel.Product;
import co.com.sofka.backalmacen.productmodel.gateway.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ProductImpl implements ProductRepository{

    @Autowired
    private final ProductAdapter productAdapter;

    @Autowired
    private final  ConverterProduct converter;


    public ProductImpl(ProductAdapter productAdapter, ConverterProduct converter){
        this.productAdapter = productAdapter;
        this.converter = converter;
    }

    @Override
    public Mono<Product> create(Product product){
        return Mono.just(product)
                .map(converter::toProductData)
                .flatMap(productAdapter::save)
                .map(converter::toProduct);
    }

    @Override
    public Mono<Product> getByIdProduct(String id){
        return productAdapter.findById(id).map(converter::toProduct);
    }

    @Override
    public Flux<Product> getAllProduct(){
        return productAdapter.findAll().map(converter::toProduct);
    }

    @Override
    public Mono<Void> delete(String id){
        return productAdapter.deleteById(id);
    }

    @Override
    public Mono<Product> update(String id, Product product) {
        product.setId(id);
        return create(product);
    }

}
