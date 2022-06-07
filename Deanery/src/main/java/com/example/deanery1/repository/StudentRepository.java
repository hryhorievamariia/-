package com.example.deanery1.repository;

import com.example.deanery1.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> getAllByGroup_Id(int category_id);
    List<Student> getStudentByNameAndSurname(String name, String surname);
}
