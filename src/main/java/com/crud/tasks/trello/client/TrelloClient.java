package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient {

    /*@Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("ireneuszjaneczek128")
    private String username;
    @Value("faa7c889a877670ffaaa7202ca60e7ef")
    private String trelloAppKey;
    @Value("9bf2a0181bb77584d268ecffcb94e2a99404bd26661d26e174065d6121c2fef6")
    private String trelloToken;
    @Value("${trello.app.token}")
    private String trelloToken;*/

    private final String trelloApiEndpoint = "https://api.trello.com/1";
    private final String username = "ireneuszjaneczek128";
    private final String trelloAppKey = "faa7c889a877670ffaaa7202ca60e7ef";
    private final String trelloToken = "9bf2a0181bb77584d268ecffcb94e2a99404bd26661d26e174065d6121c2fef6";

    @Autowired
    private RestTemplate restTemplate;

    public List<TrelloBoardDto> getTrelloBoards() {
        return Arrays.asList(Optional.ofNullable(restTemplate.getForObject(url, TrelloBoardDto[].class)).orElseThrow(IllegalArgumentException::new));
    }

    private URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + username + "/boards")
            .queryParam("key", trelloAppKey)
            .queryParam("token", trelloToken)
            .queryParam("fields", "name, id").build().encode().toUri();
}
