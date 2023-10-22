package giza.example.springbootessentials.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorDetailsDto {
    private UUID id;
    private String youtube;
    private String hobby;
}
