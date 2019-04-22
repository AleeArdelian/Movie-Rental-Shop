package com.shop.web.converter;

import org.springframework.stereotype.Component;
import com.shop.core.model.Student;
import com.shop.web.dto.StudentDto;
import com.shop.web.dto.StudentsDto;

/**
 * author: radu
 */
@Component
public class StudentConverter extends BaseConverter<Student, StudentDto> {
    @Override
    public Student convertDtoToModel(StudentDto dto) {
        Student student = Student.builder()
                                 .name(dto.getName())
                                 .grade(dto.getGrade())
                                 .build();
        student.setId(dto.getId());
        return student;
    }

    @Override
    public StudentDto convertModelToDto(Student student) {
        StudentDto dto = StudentDto.builder()
                                   .name(student.getName())
                                   .grade(student.getGrade())
                                   .build();
        dto.setId(student.getId());
        return dto;
    }
}
