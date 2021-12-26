<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>    
    
<%--inputPro.jsp --%>
회원 가입을 축하 합니다

<%
//이전것은
//String id=(String)request.getAttribute("id");
//session.setAttribute("id", id);
%>

<c:set var="id" value="${id}" scope="session"/>

<meta http-equiv="Refresh" content="0;url=${ctxpath}/member/main.do">