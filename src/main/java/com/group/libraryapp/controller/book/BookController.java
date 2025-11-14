package com.group.libraryapp.controller.book;

import com.group.libraryapp.service.book.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
//    private final BookService bookService = new BookService();
    // 스프링빈을 사용하면서 new를 사용하지 않고 생성자를 만들어서 스프링빈을 주입받음
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book")
    public void saveBook(){
        bookService.saveBook();
    }
}
