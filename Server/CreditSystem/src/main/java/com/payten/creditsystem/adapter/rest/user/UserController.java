package com.payten.creditsystem.adapter.rest.user;

import com.payten.creditsystem.adapter.rest.user.request.UserCreateRequest;
import com.payten.creditsystem.adapter.rest.user.request.UserUpdateRequest;
import com.payten.creditsystem.adapter.rest.user.response.UserCreateResponse;
import com.payten.creditsystem.adapter.rest.user.response.UserResponse;
import com.payten.creditsystem.adapter.rest.user.response.UserUpdateResponse;
import com.payten.creditsystem.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserCreateResponse create(@RequestBody @Valid UserCreateRequest request) {
        Long createdUserId = userService.create(request.convertToUser());
        return UserCreateResponse.builder().id(createdUserId).build();
    }

    @GetMapping("/users")
    public List<UserResponse> retrieve() {
        return userService.retrieve()
                .stream()
                .map(UserResponse::from)
                .toList();
    }

    @DeleteMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId) {
        userService.delete(userId);
    }

    @PutMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public UserUpdateResponse update(@RequestBody @Valid UserUpdateRequest request) {
        Long updatedUserId = userService.update(request.convertToUser());
        return UserUpdateResponse.builder().id(updatedUserId).build();
    }
}
