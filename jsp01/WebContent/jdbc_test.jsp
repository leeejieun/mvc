<%@page import="config.DBCP"%>
<%@page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JDBC연결 테스트</title>
</head>
<body>
<%
Connection conn = DBCP.dbConn();
out.print("DBCP 연결");
%>
</body>
</html>