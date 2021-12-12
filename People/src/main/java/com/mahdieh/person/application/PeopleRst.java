package com.mahdieh.person.application;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/10/21
  @Time 5:48 AM
  Created by Intellije IDEA
  Description: Application Layer
*/

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mahdieh.person.domain.entity.Person;
import com.mahdieh.person.domain.service.PersonSrv;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/people")
public class PeopleRst {
    @Autowired private PersonSrv srv;

    @Operation(summary = "return a customer or customers")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Example for get customer(s)",
            required = true,
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "All Customers according to page number",
                                    value = "{\n" +
                                            "\"code\":0,\n" +
                                            "\"page\": 0\n" +
                                            "}",
                            summary = "All Customers"),
                    @ExampleObject(
                            name = "Just Customer code 2",
                            value = "{\n" +
                                    "\"code\":2,\n" +
                                    "\"page\": 0\n" +
                                    "}",
                            summary = "One Customer") }))
    @PostMapping(value = "/find")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> find(@RequestBody String receivedData) throws Exception{
        JSONObject jsonObject = new JSONObject(receivedData);
        Integer code = jsonObject.optInt("code", 0);
        Integer page = jsonObject.optInt("page", 0);
        String response = (new ObjectMapper()).writeValueAsString(srv.find(code, page));
        return (new ResponseEntity<String>(response, HttpStatus.OK));
    }

    @Operation(summary = "return a customer")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Who am i?",
            required = true,
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "get customer",
                                    value = "{\n" +
                                            "  \"code\":2\n" +
                                            "}",
                                    summary = "who") }))
    @PostMapping(value = "/who")
    @ResponseStatus(HttpStatus.OK)
    @JsonView(Person.PersonLight.class)
    public ResponseEntity<String> whoami(@RequestBody String receivedData) throws Exception {
        System.out.println("Attention: Some App call this Instance");
        JSONObject json = new JSONObject(receivedData);
        Integer code=json.optInt("code",0);
        String response=(new ObjectMapper()).writeValueAsString(srv.find(code,0));
        return new ResponseEntity<String>(response,HttpStatus.OK);
    }

    @Operation(summary = "Delete a Customer")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Delete",
            required = true,
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "Delete Customer",
                                    value = "{\n" +
                                            "\"code\": \"453\"\n" +
                                            "}",
                            summary = "delete customer: 465") }))
    @DeleteMapping(value = "/delete")
    public String delete(@RequestBody String receiveData) throws Exception {
        JSONObject jsonObject = new JSONObject(receiveData);
        Integer code = jsonObject.optInt("code", 0);
        return srv.delete(code);
    }

    @Operation(summary = "Add a Customer")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Example for save customer",
            required = true,
            content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "Save a Customer",
                                    value = "{\n" +
                                            "  \"personpk\": null,\n" +
                                            "  \"persontypeid\": 1,\n" +
                                            "  \"typedetailid\": 1,\n" +
                                            "  \"nationalkey\": \"0042010955\",\n" +
                                            "  \"booknumber\": 2783,\n" +
                                            "  \"bookserial\": \"A/93/547643\",\n" +
                                            "  \"bookserie\": \"1334557\",\n" +
                                            "  \"passportno\": \"5432401DH\",\n" +
                                            "  \"lastname\": \"Parhizkari\",\n" +
                                            "  \"firstname\": \"Afshin\",\n" +
                                            "  \"countryid\": 98,\n" +
                                            "  \"cityid\": null,\n" +
                                            "  \"birthdate\": \"1978-12-06\",\n" +
                                            "  \"changerdate\": \"2021-09-30\",\n" +
                                            "  \"contactsByPersonpk\": [\n" +
                                            "    {\n" +
                                            "      \"contactpk\": null,\n" +
                                            "      \"personfk\": null,\n" +
                                            "      \"ownernationalkey\": \"\",\n" +
                                            "      \"contacttypeid\": 1,\n" +
                                            "      \"countryid\": 98,\n" +
                                            "      \"provinceid\": 90,\n" +
                                            "      \"cityid\": 90,\n" +
                                            "      \"contactnumber\": \"+989032430637\",\n" +
                                            "      \"contactdesc\": \"Par St,Tehran,Iran\",\n" +
                                            "      \"email\": \"Afshin.Parhizkari@gmail.com\",\n" +
                                            "      \"location\": null,\n" +
                                            "      \"contactstatus\": true,\n" +
                                            "      \"changerdate\": \"2021-08-25\"\n" +
                                            "    }\n" +
                                            "  ]\n" +
                                            "}",
                                    summary = "Save Customer") }))
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