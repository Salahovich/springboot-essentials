package giza.example.springbootessentials.EntityMappers;

import giza.example.springbootessentials.DTO.InstructorDto;
import giza.example.springbootessentials.Models.Instructor;

import java.util.stream.Collectors;

public class InstructorMapper implements Mapper<Instructor, InstructorDto> {

    @Override
    public Instructor convertToEntity(InstructorDto dto) {
        return new Instructor(dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEmail(),
                dto.getPhoneNumber(),
                dto.getTitle(),
                new InstructorDetailsMapper().convertToEntity(dto.getDetails()));
    }

    @Override
    public InstructorDto convertToDto(Instructor entity) {
        return new InstructorDto(entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.getTitle(),
                new InstructorDetailsMapper().convertToDto(entity.getDetails()));
    }
}
