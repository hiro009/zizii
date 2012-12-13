<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jp.co.zizii.dto.Dungeon" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Select Dungeon</title>
</head>
<body>
<h1>ダンジョンを選ぼう!</h1><br>
-------------------------------------------------
<form action="/dungeon" method="post">

<input type="submit" value="初心者の森" name="dungeon"/><br>
</form>
<br><br>
-------------------------------------------------
<form action="/dungeon" method="post">

<input type="submit" value="コンビニ" name="dungeon"/><br>
</form>
<br><br>
-------------------------------------------------
<form action="/dungeon" method="post">

<input type="submit" value="公民館" name="dungeon"/><br>
</form>
<br><br>
-------------------------------------------------
<form action="/dungeon" method="post">

<input type="submit" value="接骨院" name="dungeon"/><br>
</form>
<br><br>
-------------------------------------------------
<form action="/dungeon" method="post">

<input type="submit" value="イオン" name="dungeon"/><br>
</form>
<br><br>
-------------------------------------------------
<form action="/dungeon" method="post">

<input type="submit" value="保険会社" name="dungeon"/><br>
</form>
<br><br>
-------------------------------------------------
<form action="/dungeon" method="post">

<input type="submit" value="ア〇ムウェイ" name="dungeon"/><br>
</form>
<br><br>

</body>
</html>