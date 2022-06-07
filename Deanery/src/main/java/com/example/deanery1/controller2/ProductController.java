package com.example.deanery1.controller2;


import com.example.deanery1.Dto.StudentDto;
import com.example.deanery1.model.Group;
import com.example.deanery1.model.Student;
import com.example.deanery1.repository.GroupRepository;
import com.example.deanery1.repository.StudentRepository;
import com.example.deanery1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class ProductController {
    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    GroupRepository categoryRepo;

    @GetMapping("/add")
    public String  createStudent() {
        return "AddStudentForm";
    }

    @PostMapping("/add")
    public String  createStudent(@RequestParam("name") String name, @RequestParam("personalInfo") String personalInfo,
                                 @RequestParam("surname") String  surname, @RequestParam("imageUrl") String imageURL,
                                 @RequestParam("groupId") int groupId) {
        StudentDto studentDto = new StudentDto(name, surname, imageURL, personalInfo, groupId);

        Optional<Group> optionalGroup = categoryRepo.findById(studentDto.getGroupId());
        if (!optionalGroup.isPresent()) {
            return "category does not exists";
        }
        studentService.createStudents(studentDto, optionalGroup.get());
        return "redirect:/student/list";
    }

    @GetMapping("/{groupId}")
    public String  getStudentsFromOneGroup(@PathVariable int groupId, Model model, HttpServletRequest httpServletRequest) {
        String name = httpServletRequest.getParameter("name");
        String surname = httpServletRequest.getParameter("surname");
        if(name != null && surname != null){
            List<Student> students =  studentRepository.getStudentByNameAndSurname(name, surname);
            model.addAttribute("students", students);
            model.addAttribute("groupId", groupId);
        }
        else {
            List<StudentDto> students = studentService.getAllStudentsFromOneGroup(groupId);
            model.addAttribute("students", students);
            model.addAttribute("groupId", groupId);
            System.out.println(students);
        }

        return "students";

    }

    // create an api to edit the product

    @GetMapping("/update/{groupId}")
    public String  updateStudent(@PathVariable("groupId") int groupId, Model model) throws Exception {
        Student student = studentService.findById(groupId);
        model.addAttribute("student", student);
        return "UpdateStudentForm";
    }


    @PostMapping("/update/{studentId}")
    public String  updateStudent(@RequestParam("name") String name, @RequestParam("personalInfo") String personalInfo,
                                 @RequestParam("surname") String  surname, @RequestParam("imageUrl") String imageURL,
                                 @RequestParam("id") int id, @RequestParam("groupId") int groupId, @PathVariable("studentId") int studentId) throws Exception {
        StudentDto studentDto = new StudentDto(id, name, surname, imageURL, personalInfo, groupId);
        Optional<Group> optionalGroup = categoryRepo.findById(studentDto.getGroupId());
        if (!optionalGroup.isPresent()) {
            return "group does not exist";
        }
        System.out.println(studentDto);
        studentService.updateStudent(studentDto, studentId);
        return "redirect:/student/list";
    }

    @GetMapping("/list")
    public String  getAllStudents(Model model, HttpServletRequest httpServletRequest) {

        String name = httpServletRequest.getParameter("name");
        String surname = httpServletRequest.getParameter("surname");
        if(name != null && surname != null){
            List<Student> students =  studentRepository.getStudentByNameAndSurname(name, surname);
            model.addAttribute("students", students);
        }
        else{
            List<StudentDto> students = studentService.getAllStudents();
            model.addAttribute("students", students);
        }

        return "adminStudents";
    }

    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id){
        studentService.deleteStudent(id);
        return "redirect:/student/list";
    }

}


