package com.back.admin.domain;

import com.back.admin.domain.common.Param;
import com.back.admin.domain.common.ValidationGroups.CodeCreateGroup;
import com.back.admin.domain.common.ValidationGroups.CodeUpdateGroup;
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
@ApiModel(description = "코드정보")
public class Code extends Param {

    @ApiModelProperty(example = "코드식별키")
    public String code_id;

    @ApiModelProperty(example = "코드그룹식별키")
    public String code_grp_id;

    @NotEmpty(groups = { CodeCreateGroup.class, CodeUpdateGroup.class }, message = "코드명을 입력하세요.")
    @Size(groups = { CodeCreateGroup.class, CodeUpdateGroup.class }, max=15, message = "15글자 이내로 입력하세요.")
    @ApiModelProperty(example = "코드명")
    public String code_nm;

    @NotEmpty(groups = { CodeCreateGroup.class }, message = "코드값을 입력하세요.")
    @Size(groups = { CodeCreateGroup.class },max=10, message = "10글자 이내로 입력하세요.")
    @ApiModelProperty(example = "코드값")
    public String code_val;

    @Size(groups = { CodeCreateGroup.class, CodeUpdateGroup.class }, max=150, message = "150자 이내로 입력하세요.")
    @ApiModelProperty(example = "비고")
    public String memo;

    @Size(groups = { CodeCreateGroup.class, CodeUpdateGroup.class }, max=10, message = "10자 이내로 입력하세요.")
    @ApiModelProperty(example = "순서")
    public String ord;

    @ApiModelProperty(example = "상위코드")
    public String prn_code_val;

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
