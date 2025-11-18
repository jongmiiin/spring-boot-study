package com.group.libraryapp.repository.user;

import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

// SQL을 사용해 실제 DB와의 통신을 담당한다
// 즉, DB와의 접근을 담당
@Repository
public class UserJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isUserNotExist(long id){
        String readSql = "SELECT * FROM user WHERE id = ?";
        // boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, request.getId()).isEmpty();
        // request를 받아서 할 수도 있지만 여기서는 id 값 하나만 필요해서 id값만 사용
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty();
    }

    public void updateUserName(String name, long id){
        String sql = "UPDATE user SET name = ? WHERE id =?";
        jdbcTemplate.update(sql, name, id);
    }

    public boolean isUserNotExist(String name){
        String readSql = "SELECT * FROM user WHERE name = ?";
        // (rs, rowNum) -> 0
        // sql문을 사용해서 값이 존재한다면 0을 반환하도록 함 -> 0이 담긴 list로 반환
        // 값이 존재하지 않다면 빈 list를 반환함
        // boolean 값을 만들어서 list가 비었다면, True가 되도록 만듬
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();
    }

    public void deleteUser(String name){
        String sql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }

    public void saveUser(String name, Integer age){
        // DB연동
        String sql = "INSERT INTO user (name, age) VALUES (?, ?)";
        // 데이터의 변경 - 삽입, 수정, 삭제 모두 사용 가능
        jdbcTemplate.update(sql, name, age);
    }

    public List<UserResponse> getUsers(){
        String sql = "SELECT * FROM user";
        // jdbcquery가 들어온 sql를 수행해서 유저 정보를 받음
        // 유저 정보를 UserResponse 바꿔주는 역할
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id, name, age);
        });
    }

}
