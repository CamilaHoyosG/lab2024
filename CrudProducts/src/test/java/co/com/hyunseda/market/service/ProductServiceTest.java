/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package co.com.hyunseda.market.service;

import co.com.huynseda.market.access.Factory;
import co.com.huynseda.market.access.IProductRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jose0
 */
public class ProductServiceTest {
    @Test
    public void testSaveProduct() {
        IProductRepository repository = Factory.getInstance().getRepository("default");
        ProductService productService = new ProductService(repository);
        productService.saveProduct("Bufanda", "Azul",12,1000);
        String nameExpected = "Bufanda";
        String realName = productService.findProductByName("Bufanda").getName();
        String descriptionExpected = "Azul";
        String realDescription = productService.findProductByName("Bufanda").getDescription();
        
        assertEquals(nameExpected, realName);
        assertEquals(descriptionExpected, realDescription);
    }
}
