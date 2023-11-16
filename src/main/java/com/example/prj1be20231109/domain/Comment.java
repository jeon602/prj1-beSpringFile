package com.example.prj1be20231109.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Comment {

    private Integer id;
    private Integer boardId;
    private String memeberId;
    private String memberNickName;
    private String  comment;
    private LocalDate inserted;
}
