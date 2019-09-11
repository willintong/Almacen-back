package co.com.sofka.backalmacen;



import co.com.sofka.backalmacen.productmodel.Product;
import co.com.sofka.backalmacen.productmodel.gateway.ProductRepository;
import co.com.sofka.backalmacen.productusecase.ProductUseCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductUseCaseTest {


    @InjectMocks
    private ProductUseCase productUseCase;

    @Mock
    private ProductRepository repository;


    private Product product = new Product("1231343","tenis",14.24) ;

    private Product product2 = new Product("1231343","gorra",14.24) ;




    @Test
    public  void testFindById(){
        Mockito.when(repository.getByIdProduct(product.getId())).thenReturn(Mono.just(product));
        Assert.assertEquals(Mono.just(product).block(),productUseCase.getByIdProduct("1231343").block());
    }


    @Test
    public void testFindAll(){
        Flux<Product> finAll = Flux.just(product,product2);
        Mockito.when(repository.getAllProduct()).thenReturn(finAll);
        StepVerifier.create(productUseCase.getAllProduct()).expectNext(product,product2).verifyComplete();

    }


    @Test
    public void  testCreate(){
        Mono<Product> create = Mono.just(product);
        Mockito.when(repository.create(product)).thenReturn(create);
        StepVerifier.create(productUseCase.create(product)).expectNext(product).verifyComplete();
    }

    @Test
    public void testDelete(){

        productUseCase.delete(product.getId());
        verify(repository, times(1)).delete(eq(product.getId()));

    }


    @Test
    public void testUpdate(){
        Mono<Product> update = Mono.just(product);
        Mockito.when(repository.update(product.getId(),product)).thenReturn(update);
        StepVerifier.create(productUseCase.update(product.getId(),product)).expectNext(product).verifyComplete();


    }


}
