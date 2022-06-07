package com.example.deanery1.service;

import com.example.deanery1.Dto.StudentDto;
import com.example.deanery1.model.Group;
import com.example.deanery1.model.Student;
import com.example.deanery1.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public void createStudents(StudentDto studentDto, Group group) {
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setSurname(studentDto.getSurname());
        student.setImageURL(studentDto.getImageURL());
        student.setGroup(group);
        student.setPersonalInfo(studentDto.getPersonalInfo());
        studentRepository.save(student);
    }

    public StudentDto getStudentDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setSurname(student.getSurname());
        studentDto.setImageURL(student.getImageURL());
        studentDto.setName(student.getName());
        studentDto.setGroupId(student.getGroup().getId());
        studentDto.setPersonalInfo(student.getPersonalInfo());
        studentDto.setId(student.getId());
        return studentDto;
    }

    public List<StudentDto> getAllStudents() {
        List<Student> allStudents = studentRepository.findAll();

        List<StudentDto> studentDtos = new ArrayList<>();
        for(Student student : allStudents) {
            studentDtos.add(getStudentDto(student));
        }
        return studentDtos;
    }

    public void updateStudent(StudentDto studentDto, int Id) throws Exception {
        Optional<Student> optionalStudent = studentRepository.findById(Id);
        // throw an exception if student does not exists
        if (!optionalStudent.isPresent()) {
            throw new Exception("student not present");
        }
        Student student = optionalStudent.get();
        student.setPersonalInfo(studentDto.getPersonalInfo());
        student.setImageURL(studentDto.getImageURL());
        student.setName(studentDto.getName());
        student.setSurname(studentDto.getSurname());
        studentRepository.save(student);
    }

    public Student findById(Integer productId) throws Exception {
        Optional<Student> optionalStudent =  studentRepository.findById(productId);
        if (!optionalStudent.isPresent()) {
            throw new Exception("student does not exist");
        }
        Student student = optionalStudent.get();
        return student;
    }

    public List<StudentDto> getAllStudentsFromOneGroup(int category_id) {
        List<Student> allStudents = studentRepository.getAllByGroup_Id(category_id);

        List<StudentDto> studentDtos = new ArrayList<>();
        for(Student student : allStudents) {
            studentDtos.add(getStudentDto(student));
        }
        return studentDtos;
    }

    public void deleteStudent(int id){
        studentRepository.deleteById(id);
    }


}
