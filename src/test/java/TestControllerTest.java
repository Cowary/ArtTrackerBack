import org.cowary.arttrackerback.Application;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@Disabled
public class TestControllerTest {

    @Autowired
    @SuppressWarnings("unused")
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "ruderu")
    public void testHello() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/heh"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

    }
}
