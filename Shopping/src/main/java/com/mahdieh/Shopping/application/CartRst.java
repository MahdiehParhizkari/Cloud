package com.mahdieh.Shopping.application;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/17/21
  @Time 10:14 AM
  Created by Intellije IDEA
  Description: Application Layer
*/

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mahdieh.Shopping.domain.entity.Cart;
import com.mahdieh.Shopping.domain.service.CartSrv;
import com.mahdieh.Shopping.infrastructure.resource.PeopleRso;
import com.mahdieh.Shopping.infrastructure.resource.ProductRso;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/shopping")
public class CartRst {
    @Autowired private CartSrv srv;
    @Autowired private ProductRso productRso;
    @Autowired private PeopleRso peopleRso;
    public static final Logger logger = LoggerFactory.getLogger(CartRst.class);

    @Operation(summary = "return a customer")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Who am i?",
            required = true,
            content = @io.swagger.v3.oas.annotations.media.Content (
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "get customer",
                                    value = "{\n"
                                            + "  \"code\":2\n"
                                            + "}",
                                    summary = "who") }))
    @CircuitBreaker(name = "whoami", fallbackMethod = "whomFB")
    @PostMapping(value = "/who")
    public ResponseEntity<String> whoami(@RequestBody String receiveData) throws IOException {
        logger.info("Circuit is close.Enter to get customer process");
        String response = (new ObjectMapper()).writeValueAsString(peopleRso.find(receiveData));
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @Operation(summary = "return shopping list")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "cart of the customer#",
            required = true,
            content = @io.swagger.v3.oas.annotations.media.Content (
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "get cart for this customers",
                                    value = "{\n"
                                            + "  \"code\":2\n"
                                            + "}",
                                    summary = "shopping list") }))
    @PostMapping(value = "/showcart")
    public ResponseEntity<String> find() throws Exception {
        String response = (new ObjectMapper()).writeValueAsString(srv.showCart());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Delete a product from basket")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Delete a product from cart",
            required = true,
            content = @io.swagger.v3.oas.annotations.media.Content (
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "Delete product#",
                                    value = "{\n"
                                            + "  \"customercode\":2,\n"
                                            + "  \"productcode\":29\n"
                                            + "}",
                                    summary = "delete product# 29") }))
    @DeleteMapping(value = "/deletefromcart")
    public ResponseEntity<String> delete(@RequestBody String receiveData) throws Exception {
        JSONObject json = new JSONObject(receiveData);
        Integer customerCode= json.optInt("customercode", 0);
        Integer productCode = json.optInt("productcode", 0);
        return new ResponseEntity<String>(srv.deleteFromCart(customerCode, productCode),HttpStatus.OK);
    }

    @Operation(summary = "Shopping cancellation")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "cancel all products from cart",
            required = true,
            content = @io.swagger.v3.oas.annotations.media.Content (
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "cancel cart#",
                                    value = "{\n"
                                            + "  \"code\":3\n"
                                            + "}",
                                    summary = "cancel customer# 29") }))
    @DeleteMapping(value = "/cancelcart")
    public ResponseEntity<String> cancel(@RequestBody String receivedData) throws Exception {
        JSONObject json = new JSONObject(receivedData);
        Integer code=json.optInt("code",0);
        return new ResponseEntity<String>(srv.cancelCart(code),HttpStatus.OK);
    }

    @Operation(summary = "Add a product to basket")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Examples for order a product",
            required = true,
            content = @io.swagger.v3.oas.annotations.media.Content (
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "add product",
                                    value = "{\n"
                                            + "  \"customerfk\": 2,\n"
                                            + "  \"productfk\": 28,\n"
                                            + "  \"quantity\": 0,\n"
                                            + "  \"price\": 20.20\n"
                                            + "}",
                                    summary = "add product to cart") }))
    @Schema(implementation = Cart.class)
    @PutMapping(value = "/addtocart")
    public ResponseEntity<String> save(@Valid @RequestBody Cart obj) throws Exception {
        return new ResponseEntity<String>(srv.addToCart(obj),HttpStatus.OK);
    }

    @Operation(summary = "Finalize cart for payment")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "send order to payment app",
            required = true,
            content = @io.swagger.v3.oas.annotations.media.Content (
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "save order#",
                                    value = "{\n"
                                            + "  \"code\":2\n"
                                            + "}",
                                    summary = "save for customer# 29") }))
    @PostMapping(value = "/closecart")
    public ResponseEntity<String> close(@RequestBody String receivedData) throws Exception {
        JSONObject json = new JSONObject(receivedData);
        Integer customerCode=json.optInt("code",0);
        return new ResponseEntity<String>(srv.closeCart(customerCode), HttpStatus.OK);
    }

    @Operation(summary = "show Products for buy")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Examples for get Products",
            required = true,
            content = @io.swagger.v3.oas.annotations.media.Content (
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "All Products according to page number",
                                    value = "{\n"
                                            + "  \"code\":null,\n"
                                            + "  \"page\":null\n"
                                            + "}",
                                    summary = "All Products") }))
    @CircuitBreaker(name = "products", fallbackMethod = "productFB")
    @PostMapping(value = "/showproduct")
    public ResponseEntity<String> showProduct(@RequestBody(required = false) String receivedData) throws Exception {
        JSONObject json = new JSONObject(receivedData);
        Integer code=json.optInt("code",0);
        Integer page=json.optInt("page",0);
        String inputValue="{'code':"+code+",'page':"+page+"}";
        String response = (new ObjectMapper()).writeValueAsString(productRso.find(inputValue));
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @SuppressWarnings("unused")
    private ResponseEntity<String> defaultFB(Exception ex){
        logger.info("Circuit is open. In fallback method");
        return new ResponseEntity<String>(getdetailMessage(ex),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @SuppressWarnings("unused")
    private ResponseEntity<String> whomFB(Exception ex){
        logger.info("whom Circuit is open:"+getdetailMessage(ex));
        return new ResponseEntity<String>(
                "{\"personpk\":0,\"persontypeid\":0,\"typedetailid\":0,\"nationalkey\":\"0000000000\",\"lastname\":\"People microservice is down\",\"firstname\":\"People/who doesn't response\"}",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @SuppressWarnings("unused")
    private ResponseEntity<String> productFB(Exception ex){
        logger.info("show product Circuit is open:"+getdetailMessage(ex));
        return new ResponseEntity<String>(
                "[{\"productpk\":0,\"productname\":\"Product microservice is down\",\"categoryfk\":0,\"vendor\":\"AfshinParhizkari\",\"quantity\":0,\"unit\":\"Error\",\"saleprice\":0,\"description\":\"Product/showproduct doesn't work now. try it later\"}}]",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> generalException(Exception ex) {
        System.out.println("In Exception handler method");
        return new ResponseEntity<String>(getdetailMessage(ex),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static String getdetailMessage(Exception ex) {
        String result = ex.getMessage();
        Throwable throwable=ex.getCause();
        while (throwable != null) {
            result=ex.getCause().getMessage();
            throwable = throwable.getCause();
        }
        return result;
    }
}