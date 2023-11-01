package giza.example.springbootessentials.Integration.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import giza.example.springbootessentials.EntityMappers.StudentMapper;
import giza.example.springbootessentials.Enums.Gender;
import giza.example.springbootessentials.Models.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;
    @Test
    public void studentController_findById_returnValidStudentWith200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/university/students/{id}", "cf3860b6-77d5-4c8f-a806-3f59a0e40e88"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("cf3860b6-77d5-4c8f-a806-3f59a0e40e88"));
    }

    @Test
    public void studentController_findAll_returnAllInstructors() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/university/students"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void studentController_saveInstructor_returnValidStudentWithIdWithCreated() throws Exception {
        Student student = new Student(
                null,
                "Muhammad",
                "Salah",
                20,
                Gender.Male,
                "m.salahovich@gmail.com",
                "01211820187",
                "55464564564646"
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/university/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new StudentMapper().convertToDto(student))))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());
    }

    @Test
    public void studentController_deleteStudentById_return200AndNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/university/students/{id}", "cf3860b6-77d5-4c8f-a806-3f59a0e40e88"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/university/students/{id}", "cf3860b6-77d5-4c8f-a806-3f59a0e40e88"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
