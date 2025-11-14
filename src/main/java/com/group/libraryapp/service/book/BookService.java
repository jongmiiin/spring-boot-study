package com.group.libraryapp.service.book;

import com.group.libraryapp.repository.book.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {
//    private final BookMemoryRepository bookMemoryRepository
//            = new BookMemoryRepository();
    private final BookRepository bookRepository;

    // bookservice에서도 이제 생성자를 통해 주입받음
    // BookMemoryRepository와 BookMySqlRepository를 만들어서 스프링컨테이너가 선택하게 함
    // 이렇게 함으로써 BookService코드는 건드리지않고 변경 가능
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void saveBook() {
    bookRepository.saveBook();
    }
}
