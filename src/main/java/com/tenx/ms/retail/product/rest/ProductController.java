package com.tenx.ms.retail.product.rest;

import com.tenx.ms.commons.rest.RestConstants;
import com.tenx.ms.commons.rest.dto.ResourceCreated;
import com.tenx.ms.retail.common.rest.AbstractAPIController;
import com.tenx.ms.retail.product.rest.dto.Product;
import com.tenx.ms.retail.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value = "products", description = "Products API")
@RestController("productsControllerV1")
@RequestMapping(RestConstants.VERSION_ONE + "/products")
public class ProductController extends AbstractAPIController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService service;


    @ApiOperation(value = "Creates a new product.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Product successfully created."),
            @ApiResponse(code = 412, message = "Unable to create product. Validation error."),
            @ApiResponse(code = 500, message = "Internal server error.")}
    )
    @RequestMapping(value = {"/{storeId:\\d+}"}, method = RequestMethod.POST)
    public ResourceCreated<Long> create(
            @ApiParam(name = "storeId", value = "The store id.") @PathVariable           long    storeId,
            @ApiParam(name = "product", value = "The product.")  @Validated @RequestBody Product product) {
        LOGGER.info("Creating product {} in store {}", product, storeId);
        product.setStoreId(storeId);
        return new ResourceCreated<>(this.service.create(product));
    }

    @ApiOperation(value = "Gets all products in a given store.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all products in the specified store."),
            @ApiResponse(code = 404, message = "Store not found."),
            @ApiResponse(code = 412, message = "Validation error."),
            @ApiResponse(code = 500, message = "Internal server error.")}
    )
    @RequestMapping(value = {"/{storeId:\\d+}"}, method = RequestMethod.GET)
    public List<Product> getAll(@ApiParam(name = "storeId", value = "The store id.") @PathVariable long storeId) {
        LOGGER.info("Getting all products in store {}", storeId);
        return this.service.getAllByStoreId(storeId);
    }

    @ApiOperation(value = "Gets the product matching the given id in a given store.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the product."),
            @ApiResponse(code = 404, message = "Product or Store not found."),
            @ApiResponse(code = 500, message = "Internal server error.")}
    )
    @RequestMapping(value = {"/{storeId:\\d+}/{productId:^[0-9]*$}"}, method = RequestMethod.GET)
    public Product get(
            @ApiParam(name = "storeId",   value = "The store id.")   @PathVariable long storeId,
            @ApiParam(name = "productId", value = "The product id.") @PathVariable long productId) {
        LOGGER.info("Getting product in store {} by id {}", storeId, productId);
        return this.service.getByStoreIdAndProductId(storeId, productId).get();
    }
}
