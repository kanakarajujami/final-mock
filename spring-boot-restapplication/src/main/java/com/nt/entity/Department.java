package com.nt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="department")
@Setter
@Getter
//@AllArgsConstructor
@RequiredArgsConstructor
//@NoArgsConstructor(force = true)
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private   Long id;
    private  String name;
    private  String location;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Employee> employees=new HashSet<>();


    public Department(Long id, String location, String name) {
        this.id = id;
        this.location = location;
        this.name = name;
    }

    public Department(String location, String name) {
        this.location = location;
        this.name = name;
    }


}
