<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
		<script>
			function fn_sendMember(){
				var frmMember = document.frmMember;
				var id = frmMember.id.value;
				var pwd = frmMember.pwd.value;
				var name = frmMember.name.value;
				var email = frmMember.email.value;
				
				if (id.length == 0 || id==""){
					alert("아이디는 필수입니다.");
				}else if (pwd.length == 0 || pwd==""){
					alert("비밀번호는 필수입니다.");
				}else if (name.length == 0 || name==""){
					alert("이름은 필수입니다.");
				}else if (email.length == 0 || email==""){
					alert("이메일은 필수입니다.");
				}else{
					frmMember.method = "post";
					frmMember.action = "<%= request.getContextPath() %>/member";
					frmMember.submit();
					
				}
			}
		</script>
</head>
<body>
<%= request.getContextPath() %> <!-- 현재 파일의 서블릿 경로 --> <br/>
${pageContext.request.contextPath } <!-- 위에 줄과 같은 역할을 함.둘 중에 하나를 쓰면 됨 -->
<form name="frmMember">
	<table>
		<th>회원 가입창</th>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="id" readonly="readonly" value="${dto.id }"/></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="pwd"value="${dto.pwd }" /></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name"value="${dto.name }" /></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email"value="${dto.email }" /></td>
		</tr>
	</table>
	<input type="button" value="수정하기" onclick="fn_sendMember()"/>
	<input type="reset" value="다시입력" />
	<input type="hidden" name="command" value="updateMember" />
</form>
</body>
</html>