package com.mahdieh.product.domain.service;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/14/21
  @Time 12:01 PM
  Created by Intellije IDEA
  Description:
*/

import com.mahdieh.product.domain.entity.Category;
import com.mahdieh.product.infrastructure.repository.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class CategorySrv {
    @Autowired
    private CategoryDao categoryDao;

    public List<Category> find () throws Exception{
        return categoryDao.findAll();
    }

    public String delete(Integer code){
        categoryDao.deleteById(code);
        return "{'code':1, 'message':'record code "+code+" is deleted'}";
    }

    public String save(Category category) throws Exception{
        if (category.getCategorypk() == null){ //not pk => maybe New person
            return "{'code' :1, 'message' :'record code "+categoryDao.save(category).getCategorypk()+" is added'}";
        }else{ //Exist nationalkey => edit current person
            List<Category> dbcategory = categoryDao.findByCategorypk(category.getCategorypk());
            if (dbcategory.size()==0)
                return "{'code' :0, 'message' :'record code "+dbcategory.get(0).getCategorypk()+" is not found'}";
            else return "{'code':2,'message':'record code "+updateCategory(category, dbcategory.get(0))+" is updated'}";
        }
    }
    public Integer updateCategory(Category dbcategory, Category viewcategory){
        dbcategory.setCategoryname(viewcategory.getCategoryname());
        dbcategory.setCategorydescription(viewcategory.getCategorydescription());
        return categoryDao.save(dbcategory).getCategorypk();
    }
}
