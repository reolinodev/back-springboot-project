package com.back.admin.domain;

import com.back.admin.domain.common.Param;
import com.back.admin.domain.common.ValidationGroups.AuthCreateGroup;
import com.back.admin.domain.common.ValidationGroups.AuthUpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "권한정보")
public class Auth extends Param {

    @ApiModelProperty(example = "권한식별키")
    public String auth_id;

    @ApiModelProperty(example = "권한명")
    @NotEmpty(groups = {AuthCreateGroup.class}, message = "권한명을 입력해 주세요.")
    @Size(groups = {AuthCreateGroup.class}, max = 15, message = "최대 15자까지 입력해주세요.")
    public String auth_nm;

    @ApiModelProperty(example = "권한코드")
    @NotEmpty(groups = {AuthCreateGroup.class}, message = "권한코드를 입력해 주세요.")
    @Size(groups = {AuthCreateGroup.class}, max = 15, message = "최대 15자까지 입력해주세요.")
    public String auth_val;

    @ApiModelProperty(example = "권한구분")
    @NotEmpty(groups = {AuthCreateGroup.class}, message = "권한구분을 입력해 주세요.")
    public String auth_role;

    @ApiModelProperty(example = "순서")
    public String ord;

    @ApiModelProperty(example = "비고")
    @Size(groups = {AuthCreateGroup.class}, max = 150, message = "최대 150자까지 입력해주세요.")
    public String memo;

    @ApiModelProperty(example = "등록일")
    public String created_at;

    @ApiModelProperty(example = "수정일")
    public String updated_at;

    @ApiModelProperty(example = "등록자")
    public String created_id;

    @ApiModelProperty(example = "수정자")
    public String updated_id;

    @NotBlank(groups = { AuthUpdateGroup.class}, message = "사용여부는 필수 입력 값입니다.")
    @ApiModelProperty(example = "사용여부")
    public String use_yn;
}