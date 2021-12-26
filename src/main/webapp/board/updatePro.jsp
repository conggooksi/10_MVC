<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>    
<%--updatePro.jsp --%>

<c:if test="${x==1}">
  <meta http-equiv="Refresh" content="0;url=${ctxpath}/board/list.do?pageNum=${pageNum}">
</c:if>
 
<c:if test="${x==0}">
  <script>
    alert("암호가 틀립니다. 다시 하세요");
    //history.back();
    location.href="${ctxpath}/board/updateForm.do?num=${num}&pageNum=${pageNum}";
  </script>
</c:if>