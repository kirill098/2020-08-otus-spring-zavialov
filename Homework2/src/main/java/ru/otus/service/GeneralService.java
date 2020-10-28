package ru.otus.service;

import ru.otus.model.BookInfo;

public interface GeneralService {

    BookInfo creteBookInfo(String title, String genreName, String authorName);
}
