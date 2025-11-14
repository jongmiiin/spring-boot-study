package com.group.libraryapp.repository.book;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

// 이 스프링빈이 우선권이 있다고 Primary를 선언함
// 그러면 BookMemoryRepository보다 BookMySqlRepository를 우선해서 컨테이너에 적용
@Primary
@Repository
public class BookMySqlRepository implements BookRepository {
    @Override
    public void saveBook() {
        System.out.println("MySqlRepository");
    }
}
