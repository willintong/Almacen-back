package co.com.sofka.backalmacen;


import co.com.sofka.backalmacen.productreactivewebcontroller.ProductController;
import co.com.sofka.backalmacen.productusecase.ProductUseCase;
import co.com.sofka.backalmacen.productmodel.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;


import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringJUnit4ClassRunner.class)
@WebFluxTest(ProductController.class)
public class TestProductController {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ProductUseCase useCase;

    private static final String APIENDPOINT = "/product";

    private Product product = Product.builder().id("123").name("gorra").precio(122.28).build();

    private Product product2 = Product.builder().id("456").name("playera").precio(514.69).build();


    @Before
    public void init(){
        webTestClient = WebTestClient.bindToController(new ProductController(useCase)).build();
    }

        @Test
        public void testProductCreate(){
            Mono<Product> productMono = Mono.just(product2);
            webTestClient.post().uri("/api/products"+APIENDPOINT).contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON).body(productMono,Product.class)
                    .exchange().expectStatus().isOk();
        }

        @Test
        public void testFindById(){
            WebTestClient.ResponseSpec  spec = webTestClient.get().uri("/api/products"+APIENDPOINT+"/{id}",product.getId()).exchange();
            spec.expectBodyList(Product.class).consumeWith(res -> {
                HttpStatus status = res.getStatus();
                assertThat(status.is2xxSuccessful()).isTrue();
            });
        }

    @Test
    public void testFinAll(){
        WebTestClient.ResponseSpec spec = webTestClient.get().uri("/api/products").exchange();
        spec.expectBodyList(Product.class).consumeWith(res ->{
            HttpStatus status = res.getStatus();
            assertThat(status.is2xxSuccessful()).isTrue();
        });
    }

    @Test
    public void testDelete(){
        webTestClient.delete().uri("/api/products"+APIENDPOINT+"/{id}", product.getId())
                .exchange().expectStatus().isOk();
    }

    @Test
    public void testUpdateById() {

        webTestClient.put()
                .uri("/api/products"+APIENDPOINT+"/{id}",product.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(product),Product.class)
                .exchange()
                .expectStatus().isOk();
    }
}

