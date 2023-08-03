package com.greatlearning.service;

import java.util.List;

import com.greatlearning.entity.Student;

public interface StudentService {

	public Student save(Student student);

	public List<Student> findAll();

	public void deleteById(int id);

	public Student findById(int id);

}
