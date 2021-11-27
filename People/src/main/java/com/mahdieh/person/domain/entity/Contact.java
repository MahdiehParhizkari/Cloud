package com.mahdieh.person.domain.entity;
/*
  @project DDD
  @Author Mahdieh Parhizkari
  @Date 11/10/21
  @Time 6:29 AM
  Created by Intellije IDEA
  Description: Entity
*/

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.geo.Point;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;


@Entity
public class Contact {
    private static final long serialVersionUID = 2054386655979281715L;
    private UUID contactpk;
    private Integer personfk;
    private String ownernationalkey;
    private Integer contacttypeid;
    private Integer countryid;
    private Integer provinceid;
    private Integer cityid;
    private String contactnumber;
    private String contactdesc;
    private String email;
    private Point location;
    private Boolean contactstatus;
    private Date changerdate;
    private Person personByPersonfk;


    @Id
    @Column(name = "contactpk")
    @GeneratedValue(generator = "UUID") //uuid_of(t.contactpk)
    public UUID getContactpk() { return contactpk; }
    public void setContactpk(UUID contactpk) { this.contactpk = contactpk; }

    @Basic
    @Column(name = "personfk")
    @NotNull(message = "کد مشتری نمی تواند خالی باشد")
    @Max(value = 999999, message = "کد مشتری بین 1 تا 6 رقم می باشد.")
    public Integer getPersonfk() { return personfk; }
    public void setPersonfk(Integer personfk) { this.personfk = personfk; }

    @Basic
    @Column(name = "ownernationalkey")
    @Pattern(regexp = "\\d{11}|\\d{10}|^$",message = "مالک: کدملی 10 یا شناسه ملی 11 رقم است")
    public String getOwnernationalkey() { return ownernationalkey; }
    public void setOwnernationalkey(String ownernationalkey) { this.ownernationalkey = ownernationalkey; }

    @Basic
    @NotNull(message = "نوع تماس نمی تواند خالی باشد")
    @Column(name = "contacttypeid")
    @Max(value = 999,message = "نوع تماس بین 1 تا 3 رقم می باشد.")
    public Integer getContacttypeid() { return contacttypeid; }
    public void setContacttypeid(Integer contacttypeid) { this.contacttypeid = contacttypeid; }

    @Basic
    @Column(name = "countryid")
    @Max(value = 999,message = "کد کشور بین 1 تا 3 رقم می باشد.")
    public Integer getCountryid() { return countryid; }
    public void setCountryid(Integer countryid) { this.countryid = countryid; }

    @Basic
    @Column(name = "provinceid")
    @Max(value = 999,message = "کد استان بین 1 تا 3 رقم می باشد.")
    public Integer getProvinceid() { return provinceid; }
    public void setProvinceid(Integer provinceid) { this.provinceid = provinceid; }

    @Basic
    @Column(name = "cityid")
    @Max(value = 9999,message = "کد شهر بین 1 تا 4 رقم می باشد.")
    public Integer getCityid() { return cityid; }
    public void setCityid(Integer cityid) { this.cityid = cityid; }

    @Basic
    @Column(name = "contactnumber")
    public String getContactnumber() { return contactnumber; }
    public void setContactnumber(String contactnumber) { this.contactnumber = contactnumber; }

    @Basic
    @Column(name = "contactdesc")
    public String getContactdesc() { return contactdesc; }
    public void setContactdesc(String contactdesc) { this.contactdesc = contactdesc; }

    @Basic
    @Column(name = "email")
    @Pattern(regexp = "^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$|^$",message = "فرمت ایمیل درست نیست.")
    @Email(message = "This email address is invalid")
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Basic
    @Column(name = "location")
    public Point getLocation() { return location; }
    public void setLocation(Point location) { this.location = location; }

    @Basic
    @Column(name = "contactstatus")
    public Boolean getContactstatus() { return contactstatus; }
    public void setContactstatus(Boolean contactstatus) { this.contactstatus = contactstatus; }

    @Basic
    @Column(name = "changerdate")
    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date getChangerdate() { return changerdate; }
    public void setChangerdate(Date changerdate) { this.changerdate = changerdate; }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personfk", referencedColumnName = "personpk", nullable = false,insertable = false,updatable = false)
    @JsonIgnore
    public Person getPersonByPersonfk() { return personByPersonfk; }
    public void setPersonByPersonfk(Person personByPersonfk) { this.personByPersonfk = personByPersonfk; }
}
