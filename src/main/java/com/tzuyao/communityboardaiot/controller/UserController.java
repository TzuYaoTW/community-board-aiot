package com.tzuyao.communityboardaiot.controller;

import com.tzuyao.communityboardaiot.dto.UserQueryParams;
import com.tzuyao.communityboardaiot.dto.UserRequest;
import com.tzuyao.communityboardaiot.model.Admin;
import com.tzuyao.communityboardaiot.model.User;
import com.tzuyao.communityboardaiot.service.UserService;
import com.tzuyao.communityboardaiot.util.Page;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Tag(name="住戶 CRUD", description = "管理員可以新增查詢修改刪除關於住戶的資料")
@Validated
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // 查詢所有使用者列表
    @Operation(summary = "住戶資料列表(模糊查詢+分頁+排序)", description = "可模糊查詢姓名，" +
            "依照前端請求orderBy指定項目排序(預設為創建資料時間)，" +
            "依照前端請求sort指定排序順序(DESC由大到小排序)，" +
            "依照前端請求limit限制每頁筆數(預設5)，" +
            "依照前端請求offset設定跳過筆數(預設0，代表第一頁)。" +
            "回傳總數count讓前端計算分頁。")
    @GetMapping("/users")
    public ResponseEntity<Page<User>> getUsers(@RequestParam(required = false) String search,
                                               @RequestParam(defaultValue = "created_date") String orderBy,
                                               @RequestParam(defaultValue = "desc") String sort,
                                               @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
                                               @RequestParam(defaultValue = "0") @Min(0) Integer offset
                                               ) {
        UserQueryParams userQueryParams = new UserQueryParams();
        userQueryParams.setSearch(search);
        userQueryParams.setOrderBy(orderBy);
        userQueryParams.setSort(sort);
        userQueryParams.setLimit(limit);
        userQueryParams.setOffset(offset);

        List<User> userList = userService.getUsers(userQueryParams);

        Integer total = userService.countUser(userQueryParams);

        Page<User> page = new Page();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(userList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
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
            @ApiResponse(responseCode = "404", description = "無此 住戶 ID，無法執行修改功能", content = {
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
