<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/venus.css">
<link href="https://fonts.googleapis.com/css?family=Great+Vibes&amp;subset=latin-ext" rel="stylesheet">
<title>ユーザ情報入力完了画面</title>
<script type="text/javascript">
	window.onload = function() {
		var form = document.getElementById('createUserForm');
		setTimeout(function() {
			form.submit()
		}, 3000);
	}
</script>
</head>
<body>
<jsp:include page="header.jsp" />
	<div class="top">
		<h1>ユーザ情報入力完了画面</h1>
	</div>
	<div class="success">ユーザー情報入力が完了しました。</div>
	<s:form id="createUserForm" action="LoginAction">
		<s:hidden name="userId" value="%{#session.userIdForCreateUser}"></s:hidden>
		<s:hidden name="password" value="%{#session.password}"></s:hidden>
	</s:form>
</body>
</html>
