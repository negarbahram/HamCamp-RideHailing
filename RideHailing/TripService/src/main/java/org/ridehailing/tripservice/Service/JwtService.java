package org.ridehailing.tripservice.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Base64;


@Service
public class JwtService {
    public String getEmailByToken(HttpHeaders headers) {

        String authHeader = headers.get(org.springframework.http.HttpHeaders.AUTHORIZATION).get(0);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("missing authorization header");
        }

        final String token = authHeader.substring(7);

        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        String subValue = null;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(payload);
            subValue = jsonNode.get("sub").asText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subValue;
    }
}
