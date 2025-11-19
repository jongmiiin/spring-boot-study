package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {

    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 트랜잭션 어노테이션을 붙여서 함수가 끝나야 커밋이 되게 만든다
    // 함수 시작시 start transaction, 예외 없이 끝나면 commit, 문제가 생기면 rollback
    @Transactional
    // save 메소드에 객체를 넣어주면 INSERT sql이 자동으로 날아간다
    public void saveUser(UserCreateRequest request){
        User u = userRepository.save(new User(request.getName(), request.getAge()));
    }

    // SELECT 쿼리만 사용시 readOnly옵션 사용 가능
    // 사용시 다른 기능을 빼기 때문에 약간의 성능적 효과가 생긴다
    @Transactional(readOnly = true)
    public List<UserResponse> getUsers(){
        // findall : 자동으로 sql을 날려서 자동으로 해당 테이블에 있는 모든 데이터를 가져옴
        // userRepository의 findall을 부르면 sql을 자동으로 불러서 모든 유저 정보를 가져오고
        // 가져온 유저정보는 매핑해두었던 유저객체가 된다.
        // 객체가 나온걸 유저리스펀스로 바꿔주고 다시 전체 리스트로 변경해서 반환한다
        return userRepository.findAll().stream()
                // UserResponse에서 생성자를 만들어 더욱 효율적인 코드를 만듬
//                .map(user -> new UserResponse(user.getId(), user.getName(), user.getAge()))
                .map(UserResponse::new)
                .collect(Collectors.toList());
        // 즉, 유저 객체를 가져와서 객체 간의 변환을 하게 된것이다
    }

    @Transactional
    public void updateUser(UserUpdateRequest request){
        // userRepository의 findById를 사용해 id 기준 1개의 데이터를 가져온다
        // 결과로 Optional<User>가 나온다
        // 유저가 없다면 예외처리를 하고 있다면 결과가 user에 들어감
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        user.updateName(request.getName());
        // 이때 자동으로 유저의 이름이 바뀐 것을 확인하고 바뀐 것을 기준으로 업데이트 쿼리가 날아간다

        // 영속성 컨텍스트의 특징 1 : 변경감지
        // 영속성 컨텍스트 내에서는 명시적으로 save를 해주지 않아도 알아서 변경을 감지해서 저장한다
        // 따라서 save함수를 쓰지 않아도 자동으로 저장된다
//        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(String name){
//        User user = userRepository.findByName(name);
//        if(user == null){
//            throw new IllegalArgumentException();
//        }
        // Repository에서 Optional 옵션을 사용해서 더 깔끔하게 변경
        User user = userRepository.findByName(name)
                .orElseThrow(IllegalArgumentException::new);
        userRepository.delete(user);
    }
}
