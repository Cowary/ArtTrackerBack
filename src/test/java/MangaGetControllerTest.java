import org.cowary.arttrackerback.Application;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@Disabled
public class MangaGetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "ruderu")
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/title/anime")
                        .header("userId", 3))
                .andExpect(status().isOk())
                .andExpect(header().stringValues("Content-Type", "application/json"));
    }

    @Test
    @WithMockUser(username = "ruderu")
    public void testGetOne() throws Exception {
        mockMvc.perform(get("/title/anime/50")
                        .header("userId", 50))
                .andExpect(status().isOk())
                .andExpect(header().stringValues("Content-Type", "application/json"));
    }
}
