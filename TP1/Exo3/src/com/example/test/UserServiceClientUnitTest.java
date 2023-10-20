package com.example.test;

import com.example.model.GitHubResponse;
import com.example.model.GitHubUser;
import com.example.service.Endpoint;
import com.example.service.UserServiceClient;
import org.junit.Test;
import org.mockito.Mockito;

import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserServiceClientUnitTest {
    @Test
    public  void TestResponse() throws URISyntaxException, IOException, InterruptedException {
        Endpoint e =  Mockito.mock(Endpoint.class);
//        UserServiceClient usc = Mockito.mock(UserServiceClient.class);
        TestResponse test_res = new TestResponse(200,"{\n" +
                "  \"login\": \"BUserName\",\n" +
                "  \"id\": 26829078,\n" +
                "  \"node_id\": \"MDQ6VXNlcjI2ODI5MDc4\"}");
        String url = "https://api.github.com/users/Busername";
        Mockito.when(e.getResponse(url)).thenReturn(test_res);
        UserServiceClient usc = new UserServiceClient();
        usc.setEndpoint(e);

        GitHubResponse res = usc.getUser(url);
        assertEquals(200, res.getStatusCode());
        assertEquals(26829078, res.getGitHubUser().getId());


//        usc.setEndpoint(e);
//        GitHubResponse res = usc.getUser(url);
    }

    private class TestResponse implements HttpResponse<String> {
        int status;
        String bodyContent;
        public TestResponse(int status,String body) {
            this.status = status;
            this.bodyContent = body;
        }

        @Override
        public int statusCode() {
            return this.status;
        }

        @Override
        public HttpRequest request() {
            return null;
        }

        @Override
        public Optional<HttpResponse<String>> previousResponse() {
            return Optional.empty();
        }

        @Override
        public HttpHeaders headers() {
            return null;
        }

        @Override
        public String body() {
            return this.bodyContent;
        }

        @Override
        public Optional<SSLSession> sslSession() {
            return Optional.empty();
        }

        @Override
        public URI uri() {
            return null;
        }

        @Override
        public HttpClient.Version version() {
            return null;
        }
    }
}