<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>    
<%--main.jsp --%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Insert title here</title>
  <link href="style.css" rel="stylesheet" type="text/css">
  
  <script>
    function check(){
    	 if(document.inForm.id.value==''){
    	    	alert("ID을 입력 하세요");
    	    	document.inForm.id.focus();
    	    	return false;
    	    }
    	 if(document.inForm.pw.value==''){
    	    	alert("암호을 입력 하세요");
    	    	document.inForm.pw.focus();
    	    	return false;
    	    }
    	 return true;
    }//check()-end
  </script>
</head>
<body>
  
  <c:if test="${empty sessionScope.id}">
    <form name="inForm" method="post" action="${ctxpath}/member/loginPro.do" onSubmit="return check()">
       <table border="1">
          <tr>
            <td>ID</td>
            <td><input type="text" name="id" id="id" size="20"></td>
          </tr>
          
          <tr>
            <td>암호</td>
            <td><input type="password" name="pw" id="pw" size="20"></td>
          </tr>
          
          <tr>
           <td colspan="2" align="center">
             <input type="submit" value="로그인">
             <!-- 
             <input type="button" value="회원가입" onClick="location.href='${ctxpath}/member/inputForm.do'">
              -->
           </td>
          </tr>
       </table>
    </form>
  </c:if>
  
  <!-- 로그인 상태일때 -->
  <c:if test="${!empty sessionScope.id}">
    <table border="1">
      <tr>
        <td>
                   박문수 홈 입니다, 환영 해요^^
        </td>
      </tr>
      
      <tr>
        <td>
          ${sessionScope.id }님 방문을 환영 합니다
          <form method="post" action="${ctxpath}/member/logOut.do">
            <input type="submit" value="로그아웃">
            <input type="button" value="회원 정보 변경" onClick="location.href='${ctxpath}/member/modify.do'"> 
          </form>
        
        </td>
        
      </tr>
    </table>
  </c:if>
  
</body>
</html>