package giza.example.springbootessentials.EntityMappers;

import giza.example.springbootessentials.DTO.StudentDto;
import giza.example.springbootessentials.Models.Student;

public class StudentMapper implements Mapper<Student, StudentDto> {
    @Override
    public Student convertToEntity(StudentDto dto) {
        return new Student(dto.getId(),
            dto.getFirstName(),
            dto.getLastName(),
            dto.getAge(),
            dto.getGender(),
            dto.getEmail(),
            dto.getPhoneNumber(),
            dto.getNationalId());
    }

    @Override
    public StudentDto convertToDto(Student entity) {
        return new StudentDto(entity.getId(),
            entity.getFirstName(),
            entity.getLastName(),
            entity.getAge(),
            entity.getGender(),
            entity.getEmail(),
            entity.getPhoneNumber(),
            entity.getNationalId());
    }
}
