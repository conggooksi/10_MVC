<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>    
<%--content.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>
<h2>글내용 보기</h2>

  <table border="1">
    <tr>
      <td>글번호</td>
      <td>${dto.num}</td>
      
      <td>조회수</td>
      <td>${dto.readcount}</td>
    </tr>
    
    <tr>
      <td>작성자</td>
      <td>${dto.writer }</td>
      
      <td>작성일</td>
      <td>${dto.regdate}</td>
    </tr>
    
    <tr>
      <td>제목</td>
      <td colspan="3">${dto.subject}</td>
    </tr>
    
    <tr>
      <td>글내용</td>
      <td colspan="3">${dto.content}</td>
    </tr>
    <!-- 글수정, 글삭제,딥글쓰기 ,리스트 -->
    <tr>
      <td colspan="4" align="center">
        <input type="button" value="글수정" onClick="location.href='${ctxpath}/board/updateForm.do?num=${num}&pageNum=${pageNum}'">
        <input type="button" value="글삭제" onClick="location.href='${ctxpath}/board/deleteForm.do?num=${num}&pageNum=${pageNum}'">
        <input type="button" value="답글쓰기" onClick="location.href='${ctxpath}/board/writeForm.do?num=${num}&pageNum=${pageNum}&ref=${ref}&re_step=${re_step }&re_level=${re_level}'">
        <input type="button" value="리스트"  onClick="location.href='${ctxpath}/board/list.do?pageNum=${pageNum}'">
      </td>
    </tr>
  </table>
</body>
</html>