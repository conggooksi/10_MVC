<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>    
<%--writeForm.jsp --%>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Insert title here</title>
 <link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>

<c:if test="${sessionScope.id==null}">
<script>
   alert(" 로그인 하셔야 글을 슬 수 있습니다 ");
   location.href="${ctxpath}/member/main.do";
</script>
</c:if>



<h2>글쓰기 답글쓰기</h2>
  <form method="post" name="writeForm" action="${ctxpath}/board/writePro.do">
     <input type="hidden" name="num" value="${num}">
     <input type="hidden" name="ref" value="${ref}">
     <input type="hidden" name="re_step" value="${re_step}">
     <input type="hidden" name="re_level" value="${re_level}">
     
     <table border="1">
     
       <tr>
         <td colspan="2" align="right">
           <a href="${ctxpath}/board/list.do"><b>리스트</b></a>
         </td>
       </tr>
       
       <tr>
         <td>이름</td>
         <td><input type="text" name="writer" id="writer" size="20"></td>
       </tr>
       
       <tr>
         <td>제목</td>
         <td>
           <c:if test="${num==0}">
             <input type="text" name="subject" id="subject" size="30">
           </c:if>
           
           <c:if test="${num != 0}">
             <input type="text" name="subject" id="subject" size="30" value="[답변]">
           </c:if>
         </td>
       </tr>
       
       <tr>
         <td>글내용</td>
         <td>
          <textarea name="content" id="content" rows="10" cols="50"></textarea>
         </td>
       </tr>
       
       <tr>
         <td>암호</td>
         <td><input type="password" name="pw" id="pw" size="12"></td>
       </tr>
       
       <tr>
         <td colspan="2" align="center">
           <input type="submit" value="글쓰기">
           <input type="reset" value="다시쓰기">
         </td>
       </tr>
       
       
     </table>
     
  </form>
</body>
</html>