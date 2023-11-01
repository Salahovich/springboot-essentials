package giza.example.springbootessentials.Integration.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import giza.example.springbootessentials.EntityMappers.CourseMapper;
import giza.example.springbootessentials.Enums.CourseLevel;
import giza.example.springbootessentials.Models.Course;
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

import java.time.LocalDateTime;
import java.util.HashSet;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;
    @Test
    public void courseController_findById_return200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/university/courses/{id}", "11de79bc-5d74-4ea4-a4c2-b7fed6e38913"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("11de79bc-5d74-4ea4-a4c2-b7fed6e38913"));
    }

    @Test
    public void courseController_findAll_returnAllCoursesWith200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/university/courses"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void courseController_saveCourse_returnValidCourseWithCreated() throws Exception {
        Instructor instructor = new Instructor(
                null,
                "Muhammad",
                "Salah",
                "m.salahovich@gmail.com",
                "01211820187",
                "MR",
                new InstructorDetails(null, "@Megamind", "/megamind")
        );

        Course course = new Course(
                null,
                "Arabic",
                LocalDateTime.now(),
                LocalDateTime.now(),
                CourseLevel.Advanced,
                true,
                instructor,
                new HashSet<>()
        );


        mockMvc.perform(MockMvcRequestBuilders.post("/university/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new CourseMapper().convertToDto(course))))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());
    }

    @Test
    public void courseController_deleteCourseById_return200AndNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/university/courses/{id}", "11de79bc-5d74-4ea4-a4c2-b7fed6e38913"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/university/courses/{id}", "11de79bc-5d74-4ea4-a4c2-b7fed6e38913"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
