package com.cwm.student_rest.service;

import com.cwm.student_rest.domain.Student;
import com.cwm.student_rest.model.StudentDTO;
import com.cwm.student_rest.repos.StudentRepository;
import com.cwm.student_rest.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(final StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDTO> findAll() {
        final List<Student> students = studentRepository.findAll(Sort.by("id"));
        return students.stream()
                .map(student -> mapToDTO(student, new StudentDTO()))
                .toList();
    }

    public StudentDTO get(final Long id) {
        return studentRepository.findById(id)
                .map(student -> mapToDTO(student, new StudentDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final StudentDTO studentDTO) {
        final Student student = new Student();
        mapToEntity(studentDTO, student);
        return studentRepository.save(student).getId();
    }

    public void update(final Long id, final StudentDTO studentDTO) {
        final Student student = studentRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(studentDTO, student);
        studentRepository.save(student);
    }

    public void delete(final Long id) {
        studentRepository.deleteById(id);
    }

    private StudentDTO mapToDTO(final Student student, final StudentDTO studentDTO) {
        studentDTO.setId(student.getId());
        studentDTO.setFirstname(student.getFirstname());
        studentDTO.setLastname(student.getLastname());
        studentDTO.setEmail(student.getEmail());
        return studentDTO;
    }

    private Student mapToEntity(final StudentDTO studentDTO, final Student student) {
        student.setFirstname(studentDTO.getFirstname());
        student.setLastname(studentDTO.getLastname());
        student.setEmail(studentDTO.getEmail());
        return student;
    }

    public boolean emailExists(final String email) {
        return studentRepository.existsByEmailIgnoreCase(email);
    }

}
