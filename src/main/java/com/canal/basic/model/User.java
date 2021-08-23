package com.canal.basic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user")
@Data
public class User extends BaseModel implements Serializable {
    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "portrait")
    private String portrait;
}
