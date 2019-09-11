package co.com.sofka.backalmacen;



import co.com.sofka.backalmacen.productdriveradapter.ProductAdapter;
import co.com.sofka.backalmacen.productdriveradapter.ProductImpl;
import co.com.sofka.backalmacen.productmodel.Product;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductImplTest {

    @Autowired
    private ProductImpl productImpl;

    @Mock
    private ProductAdapter productAdapter;

    private final Product product = new Product("12313", "Short", 11.254);
    private final Product product2 = new Product("32152" +
            "", "Jeans", 11.244);

    @Before
    public void setUp() {
        StepVerifier.create(productImpl.create(product)).expectNextCount(1).verifyComplete();
        StepVerifier.create(productImpl.create(product2)).expectNextCount(1).verifyComplete();


    }

        @Test
        public void testGetById() {
            Mono<Product> byId = productImpl.getByIdProduct(product.getId());
//            StepVerifier.create(byId).expectNext(product).verifyComplete();
            Assertions.assertThat(byId.block()).isEqualTo(product);
        }

        @Test
        public void testGetAll() {
            Flux<Product> finAll = productImpl.getAllProduct();
            StepVerifier.create(finAll.collectList()).assertNext(ProductData -> assertThat(ProductData).contains(product, product2)).verifyComplete();

        }

    @Test
    public void testDelete() {
        Mono<Void> delete = productImpl.delete(product.getId());
        StepVerifier.create(delete).expectNext().verifyComplete();
    }


    @Test
    public void testCreate() {
        Mono<Product> create = productImpl.create(product);
        StepVerifier.create(create).expectNext(product).verifyComplete();
    }

    @Test
    public void testUpdate() {
        Mono<Product> update = productImpl.update(product.getId(), product);
        StepVerifier.create(update).expectNext(product).verifyComplete();
    }
}
