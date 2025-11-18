package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserServiceV2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // API 진입지점으로써 HTTP Body를 객체로 변환
public class UserController {
    // final을 사용하면 재할당이 되지 않게 함
    // DB 연동하면서 이제 사용 X
    // private final List<User> users = new ArrayList<>();
//    @Autowired
//    private final UserService userService;
    private final UserServiceV2 userService;

// jdbcTemplate 받아서 설정해주는 생성자
    public UserController(UserServiceV2 userService) {
        this.userService = userService;
    }

    // Create
    @PostMapping("/user") // POST /user
    public void saveUser(@RequestBody UserCreateRequest request){
        // DB 연동하면서 사용 X
        // users.add(new User(request.getName(), request.getAge()));
        // 정상적으로 잘 호출하게 된다면, 200 ok만 호출 -> void이기 때문

        userService.saveUser(request);
    }

    // Read
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

        return userService.getUsers();
    }

    // Update
    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request){
        userService.updateUser(request);
    }

    // Delete
    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name){ // 삭제는 쿼리로 받기 때문에 Param를 사용
        userService.deleteUser(name);
    }

}
