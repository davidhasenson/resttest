package org.example.resttest;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/hello")
    public String hello() {
        return "Hej";
    }

    @GetMapping("/synk")
    public String synk() throws InterruptedException {
        Thread.sleep(13000);
        String reply = restTemplate.getForObject(
                "http://localhost:8081/test", String.class
        );
        return reply;
    }

    @GetMapping("/asynk")
    public String asynk() {
        randomFunction();
        return "Done";
    }

    @Async
    public void randomFunction() {
        String reply = restTemplate.getForObject(
                "http://localhost:8081/test", String.class);

        System.out.println("App1" + reply);
    }
}
