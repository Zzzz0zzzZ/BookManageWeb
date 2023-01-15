package com.book.entity;

import com.book.utils.DateFormatUtil;
import lombok.Data;

import java.util.Date;

@Data
public class Borrow {
    int id;
    int book_id;
    String book_name;
    Date time;
    String student_name;
    int student_id;

    public String getTime() {
        return DateFormatUtil.getFormatDate(time);
    }
}
