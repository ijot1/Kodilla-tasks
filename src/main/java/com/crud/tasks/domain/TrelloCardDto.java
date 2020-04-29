package com.crud.tasks.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TrelloCardDto {

    private String name;

    private String description;

    private String listId;

    @Override
    public String toString() {
        return "TrelloCardDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", listId='" + listId + '\'' +
                '}';
    }
}
