package com.back.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "메뉴엔티티")
public class MenuEntity extends Menu {

    @ApiModelProperty(example = "상위메뉴명")
    public String prn_menu_nm;

    @ApiModelProperty(example = "권한식별키")
    public String auth_id;

    @ApiModelProperty(example = "사용자식별키")
    public String user_id;

    @ApiModelProperty(example = "전체메인여부")
    public String all_main_yn;

}