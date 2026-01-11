package com.marcinsz.githubrepos;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")

@EnableWireMock(@ConfigureWireMock(port = 8089))
public class GithubIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnFullDataWhenUserExists() throws Exception {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/users/MarcinSz1993/repos"))
                .willReturn(WireMock.okJson("""
                    [
                        {
                            "name": "example-project",
                            "owner": {"login": "MarcinSz1993"},
                            "fork": false
                        }
                    ]
                """)));

        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/repos/MarcinSz1993/example-project/branches"))
                .willReturn(WireMock.okJson("""
                    [
                        {
                            "name": "main",
                            "commit": {"sha": "sha123456"}
                        }
                    ]
                """)));
        mockMvc.perform(MockMvcRequestBuilders.get("/repos/MarcinSz1993")
                        .accept(String.valueOf(MediaType.APPLICATION_JSON)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].repositoryName").value("example-project"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].branches[0].lastCommitSha").value("sha123456"));
    }

    @Test
    void shouldReturn404WhenUserNotFound() throws Exception {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/users/NotExistingUser/repos"))
                .willReturn(WireMock.notFound()));

        mockMvc.perform(MockMvcRequestBuilders.get("/repos/NotExistingUser"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(404))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User NotExistingUser does not exist"));
    }
}
