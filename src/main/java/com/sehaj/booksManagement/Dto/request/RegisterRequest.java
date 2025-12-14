package com.sehaj.booksManagement.Dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String name;
    private String password;
    private String role; // USER or ADMIN
}
