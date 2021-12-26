<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>    
<%--logOut.jsp --%>
 
 <c:remove var="id" scope="session"/><!-- 변수제거,세션무효화 -->
 
 <meta http-equiv="Refresh" content="0;url=${ctxpath}/member/main.do">