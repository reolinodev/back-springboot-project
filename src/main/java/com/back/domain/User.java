package com.back.domain;

import com.back.domain.common.DataLog;
import com.back.domain.common.ValidationGroups.UserCreateGroup;
import com.back.domain.common.ValidationGroups.UserUpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "사용자정보")
public class User extends DataLog {

    @ApiModelProperty(example = "사용자식별키")
    public String user_id;

    @ApiModelProperty(example = "이름")
    @NotBlank(groups = { UserCreateGroup.class}, message = "이름은 필수 입력 값입니다.")
    @Size(groups = { UserCreateGroup.class}, min = 2, max = 10, message = "최소 2자에서 10자사이로 입력해주세요")
    public String user_nm;

    @ApiModelProperty(example = "아이디")
    @NotBlank(groups = { UserCreateGroup.class}, message = "아이디는 필수 입력 값입니다.")
    @Email(groups = { UserCreateGroup.class}, message = "아이디는 이메일 형식입니다.")
    public String login_id;

    @ApiModelProperty(example = "비밀번호")
    @NotBlank(groups = { UserCreateGroup.class, UserUpdateGroup.class}, message = "비밀번호는 필수 입력 값입니다.")
    @Size(groups = { UserCreateGroup.class, UserUpdateGroup.class}, max = 20, message = "최대 20자를 넘길수 없습니다,")
    @Pattern(groups = { UserCreateGroup.class, UserUpdateGroup.class}, regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
        message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    public String user_pw;

    @ApiModelProperty(example = "휴대폰 번호")
    @NotBlank(groups = { UserCreateGroup.class}, message = "휴대폰 번호는 필수 입력 값입니다.")
    @Pattern(groups = { UserCreateGroup.class}, regexp = "^\\d{2,3}\\d{3,4}\\d{4}$", message = "올바른 휴대폰번호 형식이 아닙니다. ex) 01011112222")
    public String tel_no;

    @ApiModelProperty(example = "사용여부")
    public String use_yn;

    @ApiModelProperty(example = "패스실패횟수")
    public int pw_fail_cnt;

    @ApiModelProperty(example = "최근 로그인시간")
    public String last_login_dt;
}
