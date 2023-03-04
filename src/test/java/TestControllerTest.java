import org.cowary.arttrackerback.Application;
import org.cowary.arttrackerback.rest.anime.GetAnimeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class TestControllerTest {

    private GetAnimeController getAnimeController;

    @Autowired
    private MockMvc mockMvc;

    // TODO: 04.03.2023 Проблема с названием
    @Test
    public void testAnimes() throws Exception {
        this.mockMvc.perform(get("/api/animes?userId=3"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAnime() throws Exception {
        this.mockMvc.perform(get("/api/anime?userId=3&titleId=50"))
                .andExpect(status().isOk());
    }
}
