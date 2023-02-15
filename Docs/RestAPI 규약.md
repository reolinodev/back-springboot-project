STATUS|result_code|description|
|------|---|---|
|200|ok|API가 정상적으로 호출해서 성공|
|200|newToken|access_token이 유효가 만료되고 refresh_token을 재발급|
|200|pwchange|비밀번호 초기화가 필요한 경우|
|201||
|400||
|401|unauthorized|access_token 발급실패|
|403|tokenInvalid|access_token과 refresh_token이 일치하지 않는 경우|
|500||
