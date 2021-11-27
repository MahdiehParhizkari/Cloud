package com.mahdieh.person.application;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/10/21
  @Time 5:48 AM
  Created by Intellije IDEA
  Description: Application Layer
*/

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mahdieh.person.domain.entity.Person;
import com.mahdieh.person.domain.service.PersonSrv;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/people")
public class PeopleRst {
    @Autowired private PersonSrv srv;

    @PostMapping(value = "/find")
    public String find(@RequestBody String receivedData) throws Exception{
        JSONObject jsonObject = new JSONObject(receivedData);
        Integer code = jsonObject.optInt("code", 0);
        Integer page = jsonObject.optInt("page", 0);
        return (new ObjectMapper()).writeValueAsString(srv.find(code, page));
    }

    @DeleteMapping(value = "/delete")
    public String delete(@RequestBody String receiveData) throws Exception {
        JSONObject jsonObject = new JSONObject(receiveData);
        Integer code = jsonObject.optInt("code", 0);
        return srv.delete(code);
    }

    @PutMapping(value = "/save")
    public String save (@Valid @RequestBody Person viewPerson) throws Exception {
        return srv.save(viewPerson);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> generalException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionUtils.getRootCause(ex).getMessage());
    }
}
