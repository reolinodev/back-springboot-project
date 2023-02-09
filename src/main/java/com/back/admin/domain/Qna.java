package com.back.admin.domain;

import com.back.admin.domain.common.Param;
import com.back.admin.domain.common.ValidationGroups.QnaCreateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "QNA 정보")
public class Qna extends Param {

    @ApiModelProperty(example = "QNA식별키")
    public String qna_id;

    @NotEmpty(groups = { QnaCreateGroup.class}, message = "게시판식별키를 입력하세요.")
    @ApiModelProperty(example = "게시판식별키")
    public String board_id;

    @NotEmpty(groups = { QnaCreateGroup.class }, message = "제목을 입력하세요.")
    @ApiModelProperty(example = "제목")
    public String qna_title;

    @ApiModelProperty(example = "본문")
    public String main_text;

    @NotEmpty(groups = { QnaCreateGroup.class }, message = "질의를 입력하세요.")
    @ApiModelProperty(example = "질의")
    public String questions;

    @NotEmpty(groups = { QnaCreateGroup.class }, message = "비밀글 여부를 입력하세요.")
    @ApiModelProperty(example = "비밀글여부")
    public String hidden_yn;

    @ApiModelProperty(example = "문의비밀번호")
    public String qna_pw;

    @ApiModelProperty(example = "응답여부")
    public String response_yn;

    @ApiModelProperty(example = "사용여부")
    public String use_yn;

    @ApiModelProperty(example = "생성자")
    public String created_id;

    @ApiModelProperty(example = "생성시간")
    public String created_at;

    @ApiModelProperty(example = "수정자")
    public String updated_id;

    @ApiModelProperty(example = "수정시간")
    public String updated_at;

    @ApiModelProperty(example = "답변자")
    public String response_id;

    @ApiModelProperty(example = "답변시간")
    public String response_at;


}

