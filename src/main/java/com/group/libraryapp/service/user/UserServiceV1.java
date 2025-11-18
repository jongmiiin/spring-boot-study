package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// 현재 유저가 있는지, 없는지 등을 확인하고 예외 처리를 해준다
// 즉, 분기처리나 로직 같은 것을 담당
@Service
public class UserServiceV1 {

    private final UserJdbcRepository userJdbcRepository;

    public UserServiceV1(UserJdbcRepository userJdbcRepository){
        this.userJdbcRepository = userJdbcRepository;
    }

    // jdbcTemplate 사용을 위해 jdbctemplate 넣어주고
    // request 사용을 위해 UserUpdateRequest 넣어줌
    public void saveUser(UserCreateRequest request){
        userJdbcRepository.saveUser(request.getName(), request.getAge());
    }

    public List<UserResponse> getUsers(){
        return userJdbcRepository.getUsers();
    }

    public void updateUser(UserUpdateRequest request){
        if(userJdbcRepository.isUserNotExist(request.getId())){
            throw new IllegalArgumentException();
        }

        userJdbcRepository.updateUserName(request.getName(), request.getId());
    }

    public void deleteUser(String name){

        if(userJdbcRepository.isUserNotExist(name)){
            throw new IllegalArgumentException();
        }

        userJdbcRepository.deleteUser(name);
    }

}
