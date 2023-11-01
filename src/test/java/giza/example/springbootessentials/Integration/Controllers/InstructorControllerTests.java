package giza.example.springbootessentials.Integration.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import giza.example.springbootessentials.EntityMappers.InstructorMapper;
import giza.example.springbootessentials.Models.Instructor;
import giza.example.springbootessentials.Models.InstructorDetails;
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
public class InstructorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;
    @Test
    public void instructorController_findById_returnValidInstructorWith200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/university/instructors/{id}", "9fb801b0-9dac-4162-b202-d2b5968ceeb5"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("9fb801b0-9dac-4162-b202-d2b5968ceeb5"));
    }

    @Test
    public void instructorController_findAll_returnAllInstructors() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/university/instructors"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void instructorController_saveInstructor_returnValidInstructorWithIdWithCreated() throws Exception {
        Instructor instructor = new Instructor(
                null,
                "Muhammad",
                "Salah",
                "m.salahovich@gmail.com",
                "01211820187",
                "MR",
                new InstructorDetails(null, "@Megamind", "/megamind")
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/university/instructors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new InstructorMapper().convertToDto(instructor))))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());
    }

    @Test
    public void instructorController_deleteInstructorById_return200AndNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/university/instructors/{id}", "9fb801b0-9dac-4162-b202-d2b5968ceeb5"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/university/instructors/{id}", "9fb801b0-9dac-4162-b202-d2b5968ceeb5"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
