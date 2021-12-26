<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>    
<%--deleteForm.jsp --%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Insert title here</title>
  <link href="style.css" rel="stylesheet" type="text/css">
  
  <script>
    function check(){
    	if(document.delForm.pw.value==''){
    		alert("암호를 입력 하세요");
    		document.delForm.pw.focus();
    		return false;
    	}
    	return true;
    }
  </script>
</head>
<body>
 <h2>회원탈퇴</h2>
 
  <form name="delForm" method="post" action="${ctxpath}/member/deletePro.do" onSubmit="return check()">
    <table border="1">
      <tr height="30">
        <td>암호</td>
        <td>
           <input type="password" name="pw" id="pw" size="16">
           <input type="hidden" name="id" value="${sessionScope.id}">
        </td>
      </tr>
      
      <tr height="30">
        <td colspan="2" align="center">
          <input type="submit" value="회원탈퇴">
          <input type="button" value="취 소" onClick="location.href='${ctxpath}/member/main.do'">
        </td>
      </tr>
      
    </table>
  </form>
</body>
</html>