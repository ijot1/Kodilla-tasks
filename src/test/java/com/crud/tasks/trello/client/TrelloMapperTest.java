package com.crud.tasks.trello.client;


import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTest {
    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void shouldMapToBoards() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "list_#1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "list_#2", false);
        List<TrelloListDto> listDtos = new ArrayList<>();
        listDtos.add(trelloListDto1);
        listDtos.add(trelloListDto2);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "board_dto_#1", listDtos);
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(trelloBoardDto);
        //When
        List<TrelloBoard> boardList = trelloMapper.mapToBoards(trelloBoardDtos);
        System.out.println(boardList);
        //Then
        Assert.assertEquals(1, boardList.size());

    }

    @Test
    public void shouldMapToBoardsDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "list_#1", false);
        TrelloList trelloList2 = new TrelloList("2", "list_#2", false);
        List<TrelloList> lists = new ArrayList<>();
        lists.add(trelloList1);
        lists.add(trelloList2);
        TrelloBoard trelloBoard = new TrelloBoard("1", "board_#1", lists);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);
        //When
        List<TrelloBoardDto> boardDtoList = trelloMapper.mapToBoardsDto(trelloBoards);
        System.out.println(boardDtoList);
        //Then
        Assert.assertEquals(1, boardDtoList.size());

    }

    @Test
    public void shouldMapToList() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "list_#1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "list_#2", false);
        List<TrelloListDto> listDtos = new ArrayList<>();
        listDtos.add(trelloListDto1);
        listDtos.add(trelloListDto2);
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(listDtos);
        System.out.println(trelloLists);
        //Then
        Assert.assertEquals(2, trelloLists.size());
    }

    @Test
    public void shouldMapToListDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "list_#1", false);
        TrelloList trelloList2 = new TrelloList("2", "list_#2", false);
        List<TrelloList> lists = new ArrayList<>();
        lists.add(trelloList1);
        lists.add(trelloList2);
        //When
        List<TrelloListDto> listDtos = trelloMapper.mapToListDto(lists);
        System.out.println(listDtos);
        //Then
        Assert.assertEquals(2, listDtos.size());
    }

    @Test
    public void shouldMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card_#1", "desc_#1",
                "pos_#1", "listId = 1");
        //When
        String str = trelloMapper.mapToCard(trelloCardDto).getName();
        System.out.println(str);
        //Then
        Assert.assertEquals("card_#1", str);
    }

    @Test
    public void shouldMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("card_#1", "desc_#1",
                "pos_#1", "listId = 1");
        //When
        String str = trelloMapper.mapToCardDto(trelloCard).getName();
        System.out.println(str);
        //Then
        Assert.assertEquals("card_#1", str);
    }
}
