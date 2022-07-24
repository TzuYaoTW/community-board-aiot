package com.tzuyao.communityboardaiot.dto;

import com.tzuyao.communityboardaiot.constant.BoardCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BoardRequest {

    @Schema(description = "訊息分類")
    @NotNull
    BoardCategory boardCategory;

    @Schema(description = "公告title")
    @NotNull
    String boardTitle;

    @Schema(description = "公告內容")
    @NotNull
    String boardDescription;
}
