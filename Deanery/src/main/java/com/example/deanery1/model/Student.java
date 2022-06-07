package com.example.deanery1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private  String name;
    private  String surname;
    private  String imageURL;
    private  String personalInfo;


    @Transient
    public int groupId;



    // Many to one relationship
    @ManyToOne
    @JoinColumn(name = "group_id")
    public Group group;


}
