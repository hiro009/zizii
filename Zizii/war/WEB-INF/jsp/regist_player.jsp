<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規ユーザ登録</title>
</head>
<body>
	<form action="/register" method="post">
		<div>ニックネーム<textarea name="playerName" rows="1" cols="40"></textarea></div>
		<div><input type="submit" value="登録"></input></div>
	</form>
</body>
</html>