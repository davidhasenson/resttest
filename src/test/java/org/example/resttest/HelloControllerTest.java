package org.example.resttest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class HelloControllerTest {

    private HelloController helloController;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setUp() {
        // Skapa kontrollern direkt utan Spring-kontext
        helloController = new HelloController();

        // Koppla MockRestServiceServer till kontrollerns interna RestTemplate
        mockServer = MockRestServiceServer.createServer(helloController.restTemplate);
    }

    @Test
    void testHello() {
        String response = helloController.hello();
        assertEquals("Hej", response);
    }

    @Test
    void testSynk() throws InterruptedException {
        // Förbered mock-svar för det externa HTTP-anropet
        mockServer.expect(requestTo("http://localhost:8081/test"))
                .andRespond(withSuccess("External Response", MediaType.TEXT_PLAIN));

        String response = helloController.synk();

        assertEquals("External Response", response);
        mockServer.verify();
    }

    @Test
    void testAsynk() {
        mockServer.expect(requestTo("http://localhost:8081/test"))
                .andRespond(withSuccess("Async Response", MediaType.TEXT_PLAIN));

        String response = helloController.asynk();

        assertEquals("Done", response);
    }
}