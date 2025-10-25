package com.group.libraryapp.controller.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController // API 진입지점으로 삼음
public class UserController {
    // final을 사용하면 재할당이 되지 않게 함
    // DB 연동하면서 이제 사용 X
    // private final List<User> users = new ArrayList<>();

    // java DB connector에 대한 클래스 - 이걸로 DB 접근 + SQL 사용
    private final JdbcTemplate jdbcTemplate;

    // jdbcTemplate 받아서 설정해주는 생성자
    public UserController(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/user") // POST /user
    public void saveUser(@RequestBody UserCreateRequest request){
        // DB 연동하면서 사용 X
        // users.add(new User(request.getName(), request.getAge()));
        // 정상적으로 잘 호출하게 된다면, 200 ok만 호출 -> void이기 때문

        // DB연동
        String sql = "INSERT INTO user (name, age) VALUES (?, ?)";
        // 데이터의 변경 - 삽입, 수정, 삭제 모두 사용 가능
        jdbcTemplate.update(sql, request.getName(), request.getAge());
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers(){
        // DB 연동하면서 사용 X
//        // 비어있는 리스트를 생성
//        List<UserResponse> responses = new ArrayList<>();
//        // users에 담겨있는 user들을 하나씩 돌면서 user response형태로 바꿈
//        for(int i=0;i<users.size();i++){
//            // 만들어둔 결과 리스트에 추가
//            //responses.add(new UserResponse(i+1, users.get(i).getName(), users.get(i).getAge()));
//            // user로 바꾸면서 더 깔끔하게 변경
//            responses.add(new UserResponse(i+1, users.get(i)));
//        }
//        // responses를 반환하면서 API정상 작동하게 함
//        return responses;

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
