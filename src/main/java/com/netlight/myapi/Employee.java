package com.netlight.myapi;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee {
    private @Id @GeneratedValue long id;
    private String role;
    private String name;

    Employee(String name, String role){
        this.name = name;
        this.role = role;
    }
}
