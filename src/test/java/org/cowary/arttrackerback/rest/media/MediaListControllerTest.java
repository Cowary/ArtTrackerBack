package org.cowary.arttrackerback.rest.media;

import org.cowary.arttrackerback.Application;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@Disabled
class MediaListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "ruderu")
    public void tkMedia() {
        try {
            mockMvc.perform(get("/title/view/media")
                            .header("userId", 3))
                    .andExpect(status().isOk())
                    .andExpect(header().stringValues("Content-Type", "application/json"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @WithMockUser(username = "ruderu")
    public void tkPlay() {
        try {
            mockMvc.perform(get("/title/view/play")
                            .header("userId", 3))
                    .andExpect(status().isOk())
                    .andExpect(header().stringValues("Content-Type", "application/json"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @WithMockUser(username = "ruderu")
    public void tkWatch() {
        try {
            mockMvc.perform(get("/title/view/watch")
                            .header("userId", 3))
                    .andExpect(status().isOk())
                    .andExpect(header().stringValues("Content-Type", "application/json"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @WithMockUser(username = "ruderu")
    public void tkRead() {
        try {
            mockMvc.perform(get("/title/view/read")
                            .header("userId", 3))
                    .andExpect(status().isOk())
                    .andExpect(header().stringValues("Content-Type", "application/json"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}