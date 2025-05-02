package com.example.friendface;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
@Setter
public class User {
    @Id
    @Column
    @Size(min = 3, max = 99)
    private String username;
    @Column
    @Size(max = 20)
    private String colour;
    @Column
    @Size(min = 3, max = 100)
    @JsonIgnore
    private String password;


}