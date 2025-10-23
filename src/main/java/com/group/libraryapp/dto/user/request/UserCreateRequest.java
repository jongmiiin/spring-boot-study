package com.group.libraryapp.dto.user.request;

public class UserCreateRequest {
    private String name; // null이 들어오는지 아닌지 나중에 확인할 예정
    private Integer age; // int는 null을 표현할 수 없기 때문에 Integer사용

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
