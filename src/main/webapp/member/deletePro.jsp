<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>    
<%--deletePro.jsp --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link href="style.css" rel="stylesheet" type="text/css">
	
</head>
<body>
 <c:if test="${x==1}">
  <c:remove var="id" scope="session"/><!-- 로그아웃,세션 무효화  -->
  
  <h3>회원 탈퇴 었습니다</h3>
  <meta http-equiv="Refresh" content="2;url=${ctxpath}/member/main.do">
 </c:if>
 
 
 <c:if test="${x==-1}">
   <script>
     alert("암호가 틀림 ");
     history.back();
   </script>
 </c:if>
 
 
</body>
</html>