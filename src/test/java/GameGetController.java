import org.cowary.arttrackerback.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class GameGetController {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/title/game")
                        .header("userId", 3))
                .andExpect(status().isOk())
                .andExpect(header().stringValues("Content-Type", "application/json"));
    }

    @Test
    public void testGetOne() throws Exception {
        mockMvc.perform(get("/title/game/6"))
                .andExpect(status().isOk())
                .andExpect(header().stringValues("Content-Type", "application/json"));
    }
}
