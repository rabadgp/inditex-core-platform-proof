package com.kairos.tech.proof.application.command;

import com.kairos.tech.proof.application.exception.FindProductException;
import com.kairos.tech.proof.application.port.output.FindProductPriceService;
import com.kairos.tech.proof.domain.model.BrandId;
import com.kairos.tech.proof.domain.model.Product;
import com.kairos.tech.proof.domain.model.ProductId;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.Instant;

@Component
@RequiredArgsConstructor
public class FindProductPriceCommand implements Command<Product> {

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
    public Product execute() {
        return findProductPriceService.findProductPrices(brandId, productId, feeDate)
                .map(Product::getProductWithActivePrice)
                .orElseThrow(() -> new FindProductException(
                        MessageFormat.format("Price not found for productId:{0} and brandId:{1}",
                                productId.getValue(), brandId.getValue())));
    }
}
