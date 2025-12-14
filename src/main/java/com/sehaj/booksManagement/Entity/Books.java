package com.sehaj.booksManagement.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Primary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Books {
    @Id
    private String id ;
    private String name;
    private String author;
    private boolean taken;

}
