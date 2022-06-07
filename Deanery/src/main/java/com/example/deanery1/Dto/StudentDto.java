package com.example.deanery1.Dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class StudentDto {
    // for create it can be optional
    // for update we need the id
    private Integer id;
    private  String name;
    private String surname;
    private  String imageURL;
    private  String personalInfo;
    private  Integer groupId;

    public StudentDto(String name, String surname, String imageURL, String personalInfo, Integer groupId) {
        this.name = name;
        this.surname = surname;
        this.imageURL = imageURL;
        this.personalInfo = personalInfo;
        this.groupId = groupId;
    }
}
