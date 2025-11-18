package com.group.libraryapp.domain.user;

import javax.persistence.*;

// 스프링이 User객체와 user테이블을 같은 것으로 바라본다
// Entity : 저장되고, 관리되어야 하는 데이터
@Entity
public class User {

    // 이것이 id라는 것을 알리기 위해 어노테이션 달아줘야함
    // javax.persistence로 해야함
    // 이 필드를 primary key로 간주함
    @Id
    // MySQL에서 auto_increment가 걸려있기 때문에 GenerationType.IDENTITY를 걸어줌
    // primary key는 자동 생성되는 값이다
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // SQL에서 id를 bigint로 만들었다
    // bigint와 대응되는 long으로 생성
    private Long id = null;

    // column : 객체의 필드와 테이블의 필드를 매핑
    // null이 들어갈 수 있는지 여부, 길이 제한, DB에서의 column이름 등 지정
    // name varchar(20)과 동일
//    @Column(nullable = false, length = 20, name = "name")
    // user객체 이름도 name이고 user테이블의 name cloumn이름도 name이면 생략가능
    @Column(nullable = false, length = 20)
    private String name;
    // age도 이름, 자료형 모두 동일하기에 생략가능
    private Integer age;

    // JPA객체는 매개변수가 하나도 없는 기본 생성자가 필요
    // protected여도 상관없음
    protected User() {}

    public User(String name, Integer age) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public void updateName(String name){
        this.name = name;
    }
}
