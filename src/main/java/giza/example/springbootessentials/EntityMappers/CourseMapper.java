package giza.example.springbootessentials.EntityMappers;

import giza.example.springbootessentials.DTO.CourseDto;
import giza.example.springbootessentials.Models.Course;

import java.util.stream.Collectors;

public class CourseMapper implements Mapper<Course, CourseDto> {

    @Override
    public Course convertToEntity(CourseDto dto) {
        return new Course(dto.getId(),
                dto.getName(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getLevel(),
                dto.isStarted(),
                new InstructorMapper().convertToEntity(dto.getInstructor()),
                dto.getStudents().stream().map(s -> new StudentMapper().convertToEntity(s)).collect(Collectors.toSet()));
    }

    @Override
    public CourseDto convertToDto(Course entity) {
        return new CourseDto(entity.getId(),
                entity.getName(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getLevel(),
                entity.isStarted(),
                new InstructorMapper().convertToDto(entity.getInstructor()),
                entity.getStudents().stream().map(s -> new StudentMapper().convertToDto(s)).collect(Collectors.toSet()));
    }
}
