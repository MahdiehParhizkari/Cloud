package com.mahdieh.product.application;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/14/21
  @Time 11:51 AM
  Created by Intellije IDEA
  Description: Application Layer: Request Handler
*/

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mahdieh.product.domain.entity.Product;
import com.mahdieh.product.domain.service.ProductSrv;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductRst {
    @Autowired private ProductSrv productSrv;

    @PostMapping(value = "/find")
    public String find(@RequestBody String receiveData) throws Exception{
        JSONObject json = new JSONObject(receiveData);
        Integer code = json.optInt("productcode", 0);
        Integer page=json.optInt("page", 0);
        return (new ObjectMapper()).writeValueAsString(productSrv.find(code, page));
    }

    @PostMapping(value = "/quantity")
    public String getQuantity(@RequestBody List<Integer> productKeys) throws Exception{
        return (new ObjectMapper()).writeValueAsString(productSrv.getQuantity(productKeys));
    }

    @DeleteMapping(value = "/delete")
    public String delete(@RequestBody String receiveData) throws Exception {
        JSONObject jsonObject = new JSONObject(receiveData);
        Integer code = jsonObject.optInt("code", 0);
        return productSrv.delete(code);
    }

    @PostMapping(value = "/save")
    public String save(@Valid @RequestBody Product object) throws Exception {
        return productSrv.save(object);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> generalException(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionUtils.getRootCause(ex).getMessage());
    }
}