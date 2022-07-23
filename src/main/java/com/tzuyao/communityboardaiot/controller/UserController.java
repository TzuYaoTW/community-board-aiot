package com.tzuyao.communityboardaiot.controller;

import com.tzuyao.communityboardaiot.dto.UserRequest;
import com.tzuyao.communityboardaiot.model.Admin;
import com.tzuyao.communityboardaiot.model.User;
import com.tzuyao.communityboardaiot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name="住戶 CRUD")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // 查詢所有使用者列表
    @Operation(summary = "取得所有住戶資料", description = "取得所有住戶資料")
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> userList = userService.getUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @Operation(summary = "取得住戶資料", description = "透過userId取得住戶資料並回傳前端")
    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Operation(summary = "新增住戶資料", description = "新增住戶資料，完成後回傳新增的住戶資訊")
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody @Valid UserRequest userRequest) {
        Integer userId = userService.createUser(userRequest);
        User user = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Operation(summary = "修改住戶資料", description = "透過 userId 修改住戶資料")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Admin.class))
                            )
                    }),
            @ApiResponse(responseCode = "404", description = "無此 住戶 ID", content = {
                    @Content()
            })
    })
    @PutMapping("/users/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Integer userId,
                                           @RequestBody UserRequest userRequest) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        userService.updateUser(userId, userRequest);
        User updateUser = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.OK).body(updateUser);
    }

    @Operation(summary = "刪除住戶資料", description = "透過 userId 刪除住戶資料")
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
