package com.back.admin.domain;

import com.back.admin.domain.common.Param;
import com.back.admin.domain.common.ValidationGroups.CodeGrpCreateGroup;
import com.back.admin.domain.common.ValidationGroups.CodeGrpUpdateGroup;
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
@ApiModel(description = "코드 그룹")
public class CodeGrp extends Param {

    @ApiModelProperty(example = "코드그룹식별키")
    public String code_grp_id;

    @ApiModelProperty(example = "코드그룹명")
    @NotEmpty(groups = { CodeGrpCreateGroup.class, CodeGrpUpdateGroup.class }, message = "코드그룹명을 입력해주세요.")
    @Size(groups = { CodeGrpCreateGroup.class, CodeGrpUpdateGroup.class },max=15, message = "15자 이내로 입력해주세요.")
    public String code_grp_nm;

    @NotEmpty(groups = { CodeGrpCreateGroup.class }, message = "코드그룹값을 입력해주세요.")
    @Size(groups = { CodeGrpCreateGroup.class }, max=15, message = "15자 이내로 입력해주세요.")
    @ApiModelProperty(example = "코드그룹값")
    public String code_grp_val;

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
