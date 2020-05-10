package com.crud.tasks.service;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Test
    public void shouldFetchTreloBoards() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "test_list", false));

        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("test_id", "test_name", trelloLists);
        List<TrelloBoardDto> boardDtoList = new ArrayList<>();
        boardDtoList.add(trelloBoardDto);

        when(trelloClient.getTrelloBoards()).thenReturn(boardDtoList);
        //When
        List<TrelloBoardDto> fetchedTrelloBoards = trelloService.fetchTreloBoards();
        //Then
        assertEquals(1, fetchedTrelloBoards.size());
    }

    @Test
    public void shouldNotCreateTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test_card_name",
                "test_card_description", "top", "test_id");

        CreatedTrelloCardDto createdTrelloCardDto = null;

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        //When
        CreatedTrelloCardDto createdCard = trelloService.createTrelloCard(trelloCardDto);
        //Then
        assertNull(createdCard);
    }

}