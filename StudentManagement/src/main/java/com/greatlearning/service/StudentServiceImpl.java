package com.greatlearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.entity.Student;
import com.greatlearning.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Override
	public Student save(Student student) {
		return studentRepository.saveAndFlush(student);
	}

	@Override
	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	@Override
	public void deleteById(int id) {
		studentRepository.deleteById(id);
	}

	@Override
	public Student findById(int id) {
		return studentRepository.findById(id).get();
	}

}
