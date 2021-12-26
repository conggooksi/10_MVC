<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%--updatePro.jsp --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
  <table>
  
    <tr>
      <td>
        <h3>회원정보가 수정 되었습니다</h3>
      </td>
    </tr>
    
    <tr>
      <td>
        <form>
          <input type="button" value="메인으로" onClick="${ctxpath}/member/main.do">
        </form>
      </td>
    </tr>
  </table>
   <meta http-equiv="Refresh" content="3;url=${ctxpath}/member/main.do">
</body>
</html>