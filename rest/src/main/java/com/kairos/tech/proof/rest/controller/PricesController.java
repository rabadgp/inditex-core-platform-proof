package com.kairos.tech.proof.rest.controller;

import com.kairos.tech.proof.application.command.FindProductPriceCommand;
import com.kairos.tech.proof.application.port.input.UseCaseInvoker;
import com.kairos.tech.proof.domain.model.Product;
import com.kairos.tech.proof.rest.mapper.ProductResponseMapper;
import lombok.AllArgsConstructor;
import org.openapitools.api.PricesApi;
import org.openapitools.model.ProductPriceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@AllArgsConstructor
public class PricesController implements PricesApi {

    FindProductPriceCommand findProductPriceCommand;
    UseCaseInvoker useCaseInvoker;
    ProductResponseMapper productResponseMapper;

    @Override
    public ResponseEntity<ProductPriceResponse> findPrices(Integer brandId, Long productId, Instant fee) {
        findProductPriceCommand.init(brandId, productId, fee);
        useCaseInvoker.setCommand(findProductPriceCommand);
        Product product = useCaseInvoker.executeCommand();
        return ResponseEntity.ok(productResponseMapper.toProductPriceResponse(product));
    }
}
