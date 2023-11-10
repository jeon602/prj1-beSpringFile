package com.example.prj1be1109.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Board {
    private String id;
    private String Artist;
    private String Title;
    private String Releasedate;
    private String Production;
    private Integer Cost;
    private LocalDateTime inserted;



}
