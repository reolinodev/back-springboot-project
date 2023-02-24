package com.back.admin.domain;

import com.back.admin.domain.common.ValidationGroups.MenuCreateGroup;
import com.back.admin.domain.common.ValidationGroups.MenuUpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "메뉴")
public class Menu {

    @ApiModelProperty(example = "메뉴식별키")
    public String menu_id;

    @NotEmpty(groups = { MenuCreateGroup.class, MenuUpdateGroup.class }, message = "메뉴명을 입력하세요.")
    @Size(groups = { MenuCreateGroup.class, MenuUpdateGroup.class }, max=50, message = "30글자 이내로 입력하세요.")
    @ApiModelProperty(example = "메뉴명")
    public String menu_nm;

    @ApiModelProperty(example = "메뉴레벨")
    public int menu_lv;

    @ApiModelProperty(example = "상위식별키")
    public String prn_menu_id;

    @NotEmpty(groups = { MenuCreateGroup.class }, message = "권한구분을 입력하세요.")
    @ApiModelProperty(example = "권한구분")
    public String auth_role;

    @Size(groups = { MenuCreateGroup.class, MenuUpdateGroup.class}, max=200, message = "200글자 이내로 입력하세요.")
    @ApiModelProperty(example = "url")
    public String url;

    @Size(groups = { MenuCreateGroup.class, MenuUpdateGroup.class}, max=10, message = "10글자 이내로 입력하세요.")
    @ApiModelProperty(example = "메뉴타입")
    public String menu_type;

    @ApiModelProperty(example = "순서")
    public int ord;

    @NotEmpty(groups = { MenuCreateGroup.class, MenuUpdateGroup.class }, message = "사용여부를 입력하세요.")
    @ApiModelProperty(example = "사용여부")
    public String use_yn;

    @NotEmpty(groups = { MenuCreateGroup.class, MenuUpdateGroup.class }, message = "메인화면 여부를 입력하세요.")
    @ApiModelProperty(example = "메인화면여부")
    public String main_yn;

    @ApiModelProperty(example = "게시판값")
    public String board_val;

    @ApiModelProperty(example = "등록일")
    public String created_at;

    @ApiModelProperty(example = "수정일")
    public String updated_at;

    @ApiModelProperty(example = "등록자")
    public String created_id;

    @ApiModelProperty(example = "수정자")
    public String updated_id;
}
