package com.greatlearning.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.entity.Student;
import com.greatlearning.service.StudentServiceImpl;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	StudentServiceImpl studentServiceImpl;

	@GetMapping("/list")
	public String listStudents(Model model) {
		List<Student> students = studentServiceImpl.findAll();
		model.addAttribute("students", students);
		return "students/list-students";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		Student student = new Student();
		String heading = "Add Student";
		model.addAttribute("student", student);
		model.addAttribute("heading", heading);
		return "students/student-form";
	}

	@PostMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int id, Model model) {
		Student student = studentServiceImpl.findById(id);
		String heading = "Update Student";
		model.addAttribute("student", student);
		model.addAttribute("heading", heading);
		return "students/student-form";
	}

	@PostMapping("/save")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentServiceImpl.save(student);
		return "redirect:/students/list";
	}

	@PostMapping("/delete")
	public String deleteStudent(@RequestParam("studentId") int id) {
		studentServiceImpl.deleteById(id);
		return "redirect:/students/list";
	}

	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {
		ModelAndView model = new ModelAndView();
		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", "You do not have permission to access this page!");
		}
		model.setViewName("403");
		return model;
	}
}
