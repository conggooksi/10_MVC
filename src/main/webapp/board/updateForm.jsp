<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>    
<%--updateForm.jsp --%>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Insert title here</title>
 <link rel="stylesheet" href="style.css" type="text/css">
 
  <script>
    function check(){
    	if(document.updateForm.pw.value==''){
    		alert("암호를 입력 하세요");
    		document.updateForm.pw.focus();
    		return false;
    	}
    	return true;
    }
  </script>
 </head>
<body>

<c:if test="${sessionScope.id==null}">
<script>
   alert(" 로그인 하셔야 글을 슬 수 있습니다 ");
   location.href="${ctxpath}/member/main.do";
</script>
</c:if>


  <h2>글수정 폼</h2>
  <form name="updateForm" method="post" action="${ctxpath}/board/updatePro.do?pageNum=${pageNum}" onSubmit="return check()">
    <table border="1">
      
      <tr>
        <td>이름</td>
        <td>
          <input type="text" name="writer" id="writer" size="20" value="${dto.writer}">
          <input type="hidden" name="num" value="${dto.num}">
        </td>
      </tr>
      
      <tr>
        <td>제목</td>
        <td><input type="text" name="subject" id="subject" size="30" value="${dto.subject}"></td>
      </tr>
      
      <tr>
        <td>글내용</td>
        <td>
        <textarea name="content" id="content" rows="10" cols="50">${dto.content}</textarea>
        </td>
      </tr>
      
      <tr>
        <td>암호</td>
        <td><input type="password" name="pw" id="pw" size="12"></td>
      </tr>
      
      <tr>
        <td colspan="2" align="center">
         <input type="submit" value="글수정">
         <input type="reset" value="다시쓰기">
         <input type="button" value="글목록" onClick="location.href='${ctxpath}/board/list.do?pageNum=${pageNum}'">
        </td>
      </tr>
      
    </table>
  </form>
</body>
</html>