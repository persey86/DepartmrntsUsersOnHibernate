package com.department.models;

import com.department.services.EntityType;
import com.department.validation.UniqueDepartmentName;
import net.sf.oval.constraint.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created  on 02.04.2017.
 */
@Entity(name = "departments")
public class Department implements EntityType {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @CheckWith(value = UniqueDepartmentName.class,message = "This name of department has used")
    @Length(min = 3, message = "Put more than 3 symbols")
    @Size(max = 15, message = "Field must contains less than 15 symbols")
    @NotEmpty(message = "Field can't be empty")
    protected String name;

    @Temporal(TemporalType.DATE)
    private Date created;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "department",fetch = FetchType.EAGER)
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users){
        this.users = users;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
