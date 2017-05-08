package com.department.models;

import com.department.services.EntityType;
import com.department.validation.UniqueUserEmail;
import net.sf.oval.constraint.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Created on 02.04.2017.
 */
@Entity(name = "user")
public class User implements EntityType {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 3, message = "Field must must have more than 3 symbols")
    @Length(max = 15, message = "Field must contains less than 15 symbols")
    @NotEmpty(message = "Field 'name' can't be empty, type something")
    private String name;

    @Size(min = 3, message = "Field must must have more than 3 symbols")
    @Length(max = 15, message = "Field must contains less than 15 symbols")
    @NotEmpty(message = "Field 'surname' can't be empty, type something")
    private String surname;

    @CheckWith(value = UniqueUserEmail.class, message = "This e-mail has used, put other e-mail")
    @NotEmpty(message = "Field e-mail can't be empty")
    @Email(message = "Uncorrected format e-mail, try to put other")
    private String email;

    @Temporal(TemporalType.DATE)
    private Date created;


    @NotNegative(message = "Use positive numbers only")
    private Double salary;


    @Temporal(TemporalType.DATE)
    private Date birthday;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

}
