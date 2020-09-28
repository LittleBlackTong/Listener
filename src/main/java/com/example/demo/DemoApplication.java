package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
@RestController
public class DemoApplication {

    @Value("${file.path}")
    private String SHELL_FILE;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @RequestMapping(value = "/github_monitor", method = RequestMethod.POST)
    public String githubMonitor() throws IOException, InterruptedException {
        ProcessBuilder processBuilder2 = new ProcessBuilder("/bin/chmod", "755", "/Users/crazy/Documents/GitHub/WorkSpace/Listener/" + SHELL_FILE);
        Process process2 = processBuilder2.start();
        int rc = process2.waitFor();
        ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", "/Users/crazy/Documents/GitHub/WorkSpace/Listener/" + SHELL_FILE);
        try {
            Process process = processBuilder.start();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            try {
                int runningStatus = process.waitFor();
                System.out.println(runningStatus);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
