## # RestApi status 및 result_code 규약

STATUS|result_code|description|
|------|---|---|
|200|ok|API 성공|
|200|newToken|access_token이 유효가 만료되고 refresh_token을 재발급|
|200|pwchange|비밀번호 초기화가 필요한 경우|
|201|ok|create 성공
|400|fail|API 실패
|400|invalid|파라미터 타입 유효하지 않음
|401|unauthorized|access_token 발급실패|
|403|tokenInvalid|access_token과 refresh_token 유효기간이 종료 되었을때
|500|error|서버에러|

