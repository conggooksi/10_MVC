<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<%--list.jsp --%>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Insert title here</title>
 <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<h2>글목록(전체 글:${count})</h2>

 

<c:if test="${count==0 }">
   게시판에 저장된 글이 없습니다
</c:if>

<c:if test="${count>0}">
 <table width="70%" border="1">
  <tr>
    <td colspan="6" align="right">
      <a href="${ctxpath}/board/writeForm.do"><b>글쓰기</b></a>
    </td>
  </tr>
 
   <tr height="30">
     <td>번호</td>
     <td>글제목</td>
     <td>작성자</td>
     <td>작성일</td>
     <td>조회수</td>
     <td>IP</td>
   </tr>
   
   <c:forEach var="dto" items="${list}">
     <tr>
     
       <td>
         <c:out value="${number}"/>
         <c:set var="number" value="${number-1}"/>
       </td>
       
       <td><!-- 글제목 -->
        <c:if test="${dto.re_level>0}">
          <img src="../imgs/level.gif" width="${5*dto.re_level}" height="16"/>
          <img src="../imgs/re.gif"/>
        </c:if>
       
        <c:if test="${dto.re_level==0 }">
          <img src="../imgs/level.gif" width="${5*dto.re_level}" height="16"/>
        </c:if>
        
        <!-- 제목을 클릭하면 글내용 보기로 가게 한다  -->
        <a href="${ctxpath}/board/content.do?num=${dto.num}&pageNum=${currentPage}">
           ${dto.subject}
        </a>

        <!-- 조회수 10번 이상 이면 hot.gif 표시 -->
        <c:if test="${dto.readcount>=10}">
          <img src="../imgs/hot.gif" border="0" height="16"/>
        </c:if>
       </td><!-- 글제목 끝 -->
       
        <td>
          ${dto.writer}
        </td>
        
        <td>
          ${dto.regdate}
        </td>
        
        <td>
          ${dto.readcount}
        </td>
        
        <td>
          ${dto.ip }
        </td>
     </tr>
   </c:forEach>
   
 </table>
</c:if>

<!-- 블럭처리 , 페이지 처리  -->
<table width="70%">
<tr>
<td align="center">

<c:if test="${count>0}">
    
   <c:if test="${endPage>pageCount}">
     <c:set var="endPage" value="${pageCount}"/>
   </c:if>
   
   <!-- 이전블럭 -->
   <c:if test="${startPage>10}">
      <a href="${ctxpath}/board/list.do?pageNum=${startPage-10}">
      [이전블럭]
      </a>
   </c:if>
   
   <!-- 페이지 처리  -->
   
   <c:forEach var="i" begin="${startPage}" end="${endPage}">
     <a href="${ctxpath}/board/list.do?pageNum=${i}">
     [${i}]
     </a>
   </c:forEach>
   
   <!-- 다음 블럭 -->
   <c:if test="${endPage<pageCount}">
     <a href="${ctxpath}/board/list.do?pageNum=${startPage+10}">
     [다음블럭]
     </a>
   </c:if>
   
</c:if>
  </td>
  </tr>
</table>
</body>
</html>