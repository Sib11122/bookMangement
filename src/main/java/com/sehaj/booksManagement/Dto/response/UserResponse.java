package com.sehaj.booksManagement.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserResponse {
    private String id;
    private String name;
    private String role;
    private List<String> booksIds;
}
