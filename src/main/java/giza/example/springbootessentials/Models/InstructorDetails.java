package giza.example.springbootessentials.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="instructor_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstructorDetails  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name="youtube")
    private String youtube;

    @Column(name ="hobby")
    private String hobby;
}
