package com.back.admin.domain;

import com.back.admin.domain.common.Param;
import com.back.admin.domain.common.ValidationGroups.BoardCreateGroup;
import com.back.admin.domain.common.ValidationGroups.BoardUpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "게시판 정보")
public class Board extends Param {

    @ApiModelProperty(example = "게시판식별키")
    public String board_id;

    @NotEmpty(groups = { BoardCreateGroup.class, BoardUpdateGroup.class  }, message = "게시판명을 입력하세요.")
    @ApiModelProperty(example = "게시판명")
    public String board_title;

    @NotEmpty(groups = { BoardCreateGroup.class  }, message = "게시판유형을 입력하세요.")
    @ApiModelProperty(example = "게시판유형")
    public String board_type;

    @ApiModelProperty(example = "비고")
    public String memo;

    @NotEmpty(groups = { BoardUpdateGroup.class  }, message = "사용여부를 입력하세요.")
    @ApiModelProperty(example = "사용여부")
    public String use_yn;

    @ApiModelProperty(example = "등록일")
    public String created_at;

    @ApiModelProperty(example = "수정일")
    public String updated_at;

    @ApiModelProperty(example = "등록자")
    public String created_id;

    @ApiModelProperty(example = "수정자")
    public String updated_id;
}

