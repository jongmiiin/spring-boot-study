package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 반환타입은 User, 유저가 없다면 null이 반환
    // 함수 이름만 작성하면, 알아서 sql이 조립
    // find라고 작성하면 1개의 데이터만 가져온다
    // By 뒤에 붙는 필드 이름으로 SELECT 쿼리의 WHERE 문이 작성된다
    // User findByName(String name);

    // Optional 타입을 붙이면 유저가 null인지 체크하는 부분에서
    // orElseThrow로 에러를 던질 수 있게 된다
    Optional<User> findByName(String name);
}
