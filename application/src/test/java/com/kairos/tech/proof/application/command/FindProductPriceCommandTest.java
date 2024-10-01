package com.kairos.tech.proof.application.command;

import com.kairos.tech.proof.application.exception.FindProductException;
import com.kairos.tech.proof.application.port.output.FindProductPriceService;
import com.kairos.tech.proof.domain.model.BrandId;
import com.kairos.tech.proof.domain.model.Price;
import com.kairos.tech.proof.domain.model.PriceId;
import com.kairos.tech.proof.domain.model.Product;
import com.kairos.tech.proof.domain.model.ProductId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class FindProductPriceCommandTest {

    @Mock
    FindProductPriceService findProductPriceService;

    @InjectMocks
    FindProductPriceCommand command;

    int brandIdValue;
    long productIdValue;
    float baseProductPrice;
    float increment;
    Instant fee;

    @BeforeEach
    void setUp() {
        brandIdValue = 1;
        productIdValue = 12345L;
        baseProductPrice = 20.50f;
        increment = 0.1f;
        fee = Instant.now();
    }

    @Test
    void executeShouldReturnProductPrice() {
        int priceAmount = 1;
        Product expectedProduct = buildProduct(priceAmount);
        command.init(brandIdValue, productIdValue, fee);
        when(findProductPriceService.findProductPrices(new BrandId(brandIdValue), new ProductId(productIdValue), fee))
                .thenReturn(Optional.of(expectedProduct));
        Product product = command.execute();
        Assertions.assertEquals(expectedProduct, product);
        Assertions.assertEquals(1, product.getPrices().size());
    }


    @Test
    void executeShouldReturnHighestPriorityProductPrice() {
        int priorities = 4;
        Product expectedProduct = buildProduct(priorities);
        command.init(brandIdValue, productIdValue, fee);
        when(findProductPriceService.findProductPrices(new BrandId(brandIdValue), new ProductId(productIdValue), fee))
                .thenReturn(Optional.of(expectedProduct));
        Product product = command.execute();
        Price activePrice = product.getPrices().getFirst();
        Assertions.assertEquals(expectedProduct, product);
        Assertions.assertEquals(1, product.getPrices().size());
        Assertions.assertEquals(priorities, activePrice.getPriority().intValue());
        Assertions.assertEquals(baseProductPrice * (1 + increment * priorities), activePrice.getAmount());
    }

    @Test
    void executeShouldThrowFindProductException() {
        command.init(brandIdValue, productIdValue, fee);
        when(findProductPriceService.findProductPrices(new BrandId(brandIdValue), new ProductId(productIdValue), fee))
                .thenReturn(Optional.empty());
        FindProductException exception = Assertions.assertThrows(FindProductException.class, () -> command.execute());
        Assertions.assertEquals(String.format("Price not found for productId:%d and brandId:%d", productIdValue, brandIdValue), exception.getMessage());
    }

    private Product buildProduct(int pricesCount) {
        List<Price> prices = buildPrices(pricesCount);
        return new Product(new ProductId(productIdValue), new BrandId(brandIdValue), prices);
    }

    private List<Price> buildPrices(int pricesCount) {
        return IntStream.range(1, pricesCount + 1)
                .mapToObj(count -> Price.builder()
                        .id(new PriceId(1))
                        .endDate(Instant.now())
                        .startDate(Instant.now())
                        .amount(baseProductPrice * (1 + increment * count))
                        .priority((short) count)
                        .currency("EUR")
                        .build())
                .collect(Collectors.toList());
    }


}