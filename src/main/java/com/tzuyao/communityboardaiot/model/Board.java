package com.tzuyao.communityboardaiot.model;

import com.tzuyao.communityboardaiot.constant.BoardCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class Board {
    @Schema(description = "公佈欄 ID")
    private Integer boardId;

    @Schema(description = "公佈欄 分類")
    private BoardCategory boardCategory;

    @Schema(description = "公佈欄 標題 ")
    private String boardTitle;

    @Schema(description = "公佈欄 內容文字")
    private String boardDescription;

    @Schema(description = "公佈欄 被創建時間")
    private Date createdDate;

    @Schema(description = "公佈欄 最後被修改時間")
    private Date lastModifiedDate;
}
