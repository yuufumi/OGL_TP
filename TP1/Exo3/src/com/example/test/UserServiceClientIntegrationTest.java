package com.example.test;

import com.example.model.GitHubResponse;
import com.example.model.GitHubUser;
import com.example.service.Endpoint;
import com.example.service.UserServiceClient;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class UserServiceClientIntegrationTest {
    @Test
    public void TestResponse() throws URISyntaxException, IOException, InterruptedException {
        Endpoint e = new Endpoint();
        UserServiceClient usc = new UserServiceClient();
        usc.setEndpoint(e);
        String url = "https://api.github.com/users/Busername";
        GitHubResponse res = usc.getUser(url);
        assertEquals(200, res.getStatusCode());
        GitHubUser user = res.getGitHubUser();
        assertNotNull(user.getLogin());
    }
}