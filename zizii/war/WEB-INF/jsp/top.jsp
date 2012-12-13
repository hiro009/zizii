<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Top Page</title>
</head>
<body>
<h1>メインメニュー</h1><br>
ようこそ${player.name}さん.<br>
あなたの所持金は${player.money }円です。<br>

<c:if test="${zizii == null}">
<form action=/create_zizii method="post">
じじいの名前：<input type="text" name="ziziiName" size="40" maxlength="20"/>
<input type="submit" value="新規じじい作成" name="sakusei" />
</form>
</c:if>

<c:if test="${zizii.location == 1 }">
じじいはダンジョンに居ます<br>
</c:if>
<c:if test="${zizii.location == 0 }">
あなたのじじいです！！<br><br>
[名前]　${zizii.name }<br>
<div class="span2">
	<img src="<c:url value="${zizii.picture}" />" width="110" height="160"/>
</div>
<form action="/select_dungeon" method="post">
<input type="submit" value="ダンジョンへ"/>
</form><br>
</c:if>

<form action="/itemview" method="get">
<input type="submit" value="アイテム"/><br><br>
</form>

</body>
</html>