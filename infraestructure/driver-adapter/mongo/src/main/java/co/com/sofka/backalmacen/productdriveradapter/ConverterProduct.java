package co.com.sofka.backalmacen.productdriveradapter;

import co.com.sofka.backalmacen.productdriveradapter.data.ProductData;
import co.com.sofka.backalmacen.productmodel.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ConverterProduct {

    ModelMapper modelMapper = new ModelMapper();

    public ProductData toProductData(Product product){
        return  modelMapper.map(product, ProductData.class);
    }

    public Product toProduct(ProductData productData) {
        return modelMapper.map(productData, Product.class);
    }
}
