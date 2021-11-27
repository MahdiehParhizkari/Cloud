package com.mahdieh.person.domain.service;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/10/21
  @Time 7:58 AM
  Created by Intellije IDEA
  Description:
*/

import com.mahdieh.person.domain.entity.Person;
import com.mahdieh.person.infrastructure.repository.ContactDao;
import com.mahdieh.person.infrastructure.repository.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class PersonSrv {
    @Autowired private PersonDao personDao;
    @Autowired private ContactDao contactDao;

    public List<Person> find (Integer code, Integer pageNum) throws Exception{
        List<Person> returnData;
        Pageable somedata = PageRequest.of(pageNum, 10, Sort.by("personpk").descending());
        if (code==0)
            returnData = (personDao.findAll(somedata)).getContent();
        else
            returnData = personDao.findByPersonpk(code);
        return returnData;
    }

    @Transactional
    public String delete(Integer code) throws Exception{
        contactDao.deleteByPersonfk(code);
        personDao.deleteById(code);
        return "{'code':1, 'message':'record code "+code+" is deleted'}";
    }

    public String save(Person viewPerson) throws Exception{
        if (viewPerson.getPersonpk()==null) {//not pk => maybe New person
            if (personDao.findByNationalkey(viewPerson.getNationalkey()).size()==0) //No NationalKey => Add New person
                return "{'code':1,'message':'record code "+insertPerson(viewPerson)+" is added'}";
            else
                return "{'code':0,'message':'record code "+viewPerson.getNationalkey()+" is duplicated'}";
        }else{ //Exist NationalKey  => Edit Current person
            List<Person> dbPeople = personDao.findByPersonpk(viewPerson.getPersonpk());
            if (dbPeople.size()==0) return "{'code':0,'message':'record code "+ viewPerson.getPersonpk()+" is Not found'}";
            else return "{'code':2,'message':'record code "+updatePerson(viewPerson, dbPeople.get(0))+" is updated'}";
        }
    }

    @Transactional
    public Integer insertPerson(Person viewPerson){
        viewPerson = personDao.save(viewPerson);
        if (viewPerson.getContactsByPersonpk()!=null){
            if (viewPerson.getContactsByPersonpk().size()>0){
                Integer personCode = viewPerson.getPersonpk();
                viewPerson.getContactsByPersonpk().forEach(contact -> contact.setPersonfk(personCode));
                contactDao.saveAll(viewPerson.getContactsByPersonpk());
            }
            return viewPerson.getPersonpk();
        }
        return 0;
    }

    @Transactional
    public Integer updatePerson(Person viewPerson, Person dbPerson){
        dbPerson.setPersontypeid(viewPerson.getPersontypeid());
        dbPerson.setTypedetailid(viewPerson.getTypedetailid());
        dbPerson.setNationalkey(viewPerson.getNationalkey());
        dbPerson.setBooknumber(viewPerson.getBooknumber());
        dbPerson.setBookserial(viewPerson.getBookserial());
        dbPerson.setBookserie(viewPerson.getBookserie());
        dbPerson.setPassportno(viewPerson.getPassportno());
        dbPerson.setLastname(viewPerson.getLastname());
        dbPerson.setFirstname(viewPerson.getFirstname());
        dbPerson.setCountryid(viewPerson.getCountryid());
        dbPerson.setCityid(viewPerson.getCityid());
        dbPerson.setBirthdate(viewPerson.getBirthdate());
        dbPerson = personDao.save(dbPerson);
        //update Contacts
        if (viewPerson.getContactsByPersonpk().size()>0){
            personDao.deleteById(dbPerson.getPersonpk());
            Integer personCode = viewPerson.getPersonpk();
            viewPerson.getContactsByPersonpk().forEach(contact -> contact.setPersonfk(personCode));
            contactDao.saveAll(viewPerson.getContactsByPersonpk());
        }
        return dbPerson.getPersonpk();
    }
}