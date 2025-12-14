package com.sehaj.booksManagement.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookResponse {
    private String id;
    private String name;
    private String author;
    private boolean taken;
}
