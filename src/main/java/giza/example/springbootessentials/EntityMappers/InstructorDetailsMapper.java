package giza.example.springbootessentials.EntityMappers;

import giza.example.springbootessentials.DTO.InstructorDetailsDto;
import giza.example.springbootessentials.Models.InstructorDetails;

public class InstructorDetailsMapper implements Mapper<InstructorDetails, InstructorDetailsDto> {
    @Override
    public InstructorDetails convertToEntity(InstructorDetailsDto dto) {
        return new InstructorDetails(
                dto.getId(),
                dto.getYoutube(),
                dto.getHobby());
    }

    @Override
    public InstructorDetailsDto convertToDto(InstructorDetails entity) {
        return new InstructorDetailsDto(
                entity.getId(),
                entity.getYoutube(),
                entity.getHobby());
    }
}