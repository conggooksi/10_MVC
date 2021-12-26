<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>    
<%--loginPro.jsp --%>
<%
//session.setAttribute("id",(String)request.getAttribute("id"));
%>
<!--  EL,JSTL 사용 -->

<!-- 로그인 성공 -->
<c:if test="${x==1}">
  <c:set var="id" value="${id}" scope="session"/>
  <meta http-equiv="Refresh" content="0;url=${ctxpath}/member/main.do">
</c:if>

<!-- 로그인 실패 -->
<c:if test="${x==0}">
  <script>
   alert("암호가 틀립니다");
   history.back();
  </script>
</c:if>

<!-- 없는 ID -->
<c:if test="${x==-1}">
  <script>
   alert("없는ID");
   history.back();
  </script>
</c:if>