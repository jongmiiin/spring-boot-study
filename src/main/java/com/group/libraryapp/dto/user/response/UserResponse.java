package com.group.libraryapp.dto.user.response;

import com.group.libraryapp.domain.user.User;

public class UserResponse {
    private long id;
    private String name;
    private Integer age;

    public UserResponse(long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    //    public UserResponse(long id, String name, Integer age) {
//        this.id = id;
//        this.name = name;
//        this.age = age;
//}
    // 더 이쁘게 작성하게 위해 User로 변경
    public UserResponse(long id, User user) {
        this.id = id;
        this.name = user.getName();
        this.age = user.getAge();
    }

    // UserServiceV2.java에서 사용하기 위한 생성자 생성
    public UserResponse(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.age = user.getAge();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
