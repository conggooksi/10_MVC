<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
<%@ include file="header.jsp" %>
    
<%--deletePro.jsp --%>
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>Insert title here</title>
   <link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>

  <c:if test="${x==1}">
    <meta http-equiv="Refresh" content="0;url=${ctxpath}/board/list.do?pageNun=${pageNum}">
  </c:if>
  
  <c:if test="${x==0}">
   암호가 틀립니다<br>
   <a href="javaScript:hsitory.back()">글삭제 폼으로 가기</a>
  </c:if>
</body>
</html>