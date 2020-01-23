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
    private String firstName;
    private String lastName;

    Employee(String firstName, String lastName, String role){
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public String getName(){
        return firstName + " " + lastName;
    }

    public void setName(String name) {
        String[] parts =name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }
}
