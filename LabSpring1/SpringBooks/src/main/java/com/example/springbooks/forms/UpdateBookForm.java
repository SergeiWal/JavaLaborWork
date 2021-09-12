package com.example.springbooks.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookForm {
    private String title;
    private String author;
    private String newTitle;
    private String newAuthor;
}
