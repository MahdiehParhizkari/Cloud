package com.mahdieh.product.domain.service;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/14/21
  @Time 12:00 PM
  Created by Intellije IDEA
  Description: Business Logic
*/

import com.mahdieh.product.application.Utility;
import com.mahdieh.product.domain.entity.Product;
import com.mahdieh.product.domain.entity.Quantity;
import com.mahdieh.product.infrastructure.repository.CategoryDao;
import com.mahdieh.product.infrastructure.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductSrv {
    @Autowired private ProductDao productDao;
    @Autowired private CategoryDao categoryDao;

    public List<Product> find(Integer code, Integer pageNum) throws Exception {
        List<Product> returnData;
        Pageable somedata =  PageRequest.of(pageNum, 10, Sort.by("productpk").descending());
        if(code==0) returnData = (productDao.findByActive(somedata,true)).getContent();
        else returnData=productDao.findByProductpk(code);
        return returnData;
    }

    public List<Quantity> getQuantity(List<Integer> productKeys) throws Exception {
        return Utility.Product2Quantity(productDao.findByProductpkIn(productKeys));
    }

    public String delete(Integer code) throws Exception {
        productDao.deleteById(code);
        return "{'code':1,'message':'record code "+code+" is deleted'}";
    }

    public String save(Product viewProduct) throws Exception {
        if(viewProduct.getProductpk()==null) {//not pk => maybe New person
            return "{'code':1,'message':'record code "+productDao.save(viewProduct).getProductpk()+" is added'}";
        }else {//Exist NationalKey  => Edit Current person
            List<Product> dbProduct = productDao.findByProductpk(viewProduct.getProductpk());
            if(dbProduct.size()==0) return "{'code':0,'message':'record code "+ viewProduct.getProductpk()+" is Not found'}";
            else return "{'code':2,'message':'record code "+updateProduct(viewProduct, dbProduct.get(0))+" is updated'}";
        }
    }
    public Integer updateProduct(Product viewProduct, Product dbProduct){
        //update person
        dbProduct.setProductname(viewProduct.getProductname());
        dbProduct.setCategoryfk(viewProduct.getCategoryfk()) ;
        dbProduct.setVendor(viewProduct.getVendor());
        dbProduct.setQuantity(viewProduct.getQuantity());
        dbProduct.setUnit(viewProduct.getUnit());
        dbProduct.setBuyprice(viewProduct.getBuyprice());
        dbProduct.setSaleprice(viewProduct.getSaleprice());
        dbProduct.setActive(viewProduct.getActive());
        dbProduct.setDescription(viewProduct.getDescription());
        return productDao.save(dbProduct).getProductpk();
    }
}