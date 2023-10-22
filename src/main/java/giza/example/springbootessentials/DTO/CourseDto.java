package giza.example.springbootessentials.DTO;

import giza.example.springbootessentials.Enums.CourseLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    private UUID id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private CourseLevel level;
    private boolean started;
    private InstructorDto instructor;
    private Set<StudentDto> students;
}
