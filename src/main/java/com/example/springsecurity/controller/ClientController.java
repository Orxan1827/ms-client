package com.example.springsecurity.controller;

import com.example.springsecurity.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "Orxan", "Rustamov"),
            new Student(1, "Aslan", "Rustamov"),
            new Student(1, "Simare", "Rustamov")
    );


    private final Environment environment;

    @GetMapping("/main")
    public String getMainPage() {
        return "Main";
    }


    @GetMapping("/{id}")
    public Student getClients(@PathVariable Integer id) {
        return STUDENTS.stream()
                .filter(client -> client.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException());
    }

    @GetMapping("/port")
    public String getPort() {
        return environment.getProperty("local.server.port");
    }
}
