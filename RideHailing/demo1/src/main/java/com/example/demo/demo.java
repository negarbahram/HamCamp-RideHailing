package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@RequestMapping("/demo")
public class demo {

    @GetMapping("/hi")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }

    @GetMapping("/user")
    public ResponseEntity<String> getUser(@RequestHeader HttpHeaders headers) {
        String authHeader = headers.get(HttpHeaders.AUTHORIZATION).get(0);
        System.out.println("***" + authHeader);
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("missing authorization header");
        }

        final String token = authHeader.substring(7);
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(payload);
            String subValue = jsonNode.get("sub").asText();

            System.out.println("sub: " + subValue);  // Output: sub: 1234567890
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
