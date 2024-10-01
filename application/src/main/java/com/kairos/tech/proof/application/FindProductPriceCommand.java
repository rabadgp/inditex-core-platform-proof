package com.kairos.tech.proof.application;

import com.kairos.tech.proof.application.command.Command;
import com.kairos.tech.proof.application.port.output.FindProductPriceService;
import com.kairos.tech.proof.domain.model.BrandId;
import com.kairos.tech.proof.domain.model.Product;
import com.kairos.tech.proof.domain.model.ProductId;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindProductPriceCommand implements Command<Optional<Product>> {

    @NonNull
    private final FindProductPriceService findProductPriceService;

    private BrandId brandId;

    private ProductId productId;

    private Instant feeDate;

    public void init(Integer brandId, Long productId, Instant feeDate) {
        this.brandId = new BrandId(brandId);
        this.productId = new ProductId(productId);
        this.feeDate = feeDate;
    }


    @Override
    public Optional<Product> execute() {
        return findProductPriceService.findProductPrices(brandId, productId, feeDate)
                .map(Product::getProductWithActivePrice);
    }
}
