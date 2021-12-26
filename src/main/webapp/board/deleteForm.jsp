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
    	 if(doucument.delForm.pw.value==''){
    		 alert("암호를입력 하세요");
    		 doucument.delForm.pw.focus();
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


 <h2>글삭제 폼</h2>
 <form name="delForm" method="post" action="${ctxpath}/board/deletePro.do?pageNum=${pageNum}" onSubmit="return check()">
    <table border="1" width="350">
    
      <tr height="30">
        <td>
          <b>암호를 입력해 주세요</b>
        </td>
      </tr>
      
      <tr height="30">
        <td>
                  암호:<input type="password" name="pw" id="pw" size="12">
              <input type="hidden" name="num" value="${num}">
        </td>
      </tr>
      
      <tr height="30">
        <td align="center">
         <input type="submit" value="글삭제">
         <input type="button" value="글목록" onClick="location.href='${ctxpath}/board/list.do?pageNum=${pageNum}'">
        </td>
      </tr>
    </table>
   
 </form>
</body>
</html>