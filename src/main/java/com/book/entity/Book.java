package com.book.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Book {
    int bid;
    String title;
    String desc;
    BigDecimal price;
}
