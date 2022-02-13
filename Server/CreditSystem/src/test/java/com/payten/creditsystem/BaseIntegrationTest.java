package com.payten.creditsystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseIntegrationTest {

    protected String BEARER_TOKEN = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzZW1paEBzZW1paC5jb20iLCJleHAiOjE2NDUwMjY5NTd9.lzGHSiCXs7RWUD57Kx4mhPfgXC_XJVccqdPIPPWqTjFOHo0Hs442ktWptzZc3aM2y9E1eC_hbuCkJNQlfrt2vQ";

    @LocalServerPort
    protected int serverPort;

    @Autowired
    protected TestRestTemplate testRestTemplate;

    @Test
    void baseIntegrationTestContextLoads() {
    }
}
