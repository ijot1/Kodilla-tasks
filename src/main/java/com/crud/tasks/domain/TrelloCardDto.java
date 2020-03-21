package com.crud.tasks.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TrelloCardDto {

    private String name;

    @JsonProperty("desc")
    private String description;

    private String pos;

    @JsonProperty("idList")
    private String listId;
}