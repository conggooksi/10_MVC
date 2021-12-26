<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>    
<%--updateForm.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="style.css" rel="stylesheet" type="text/css">

<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>
function openDaumPostcode(){
	 
	new daum.Postcode({
		oncomplete:function(data){
			document.getElementById('zipcode').value=data.zonecode;
			document.getElementById('addr').value=data.address;
 		}
	}).open();
}//openDaumPostcode()---
</script>

<script>
  function check(){
	  if($('#pw').val()==''){
		  alert("암호를 입력하세요");
		  $('#pw').focus();
		  return false;
	  }
	  
	  if($('#pw').val() != $('#pw2').val()){
		  alert("암호와 암호확인이 다릅니다");
		  $('#pw2').val('');
		  $('#pw').val('').focus();
		  return false;
	  }
	  return true;
  }//check()-end
  
</script>
</head>
<body>
  <h2>(^^)내정보 수정폼(^^)</h2>
  <form method="post" action="${ctxpath}/member/updatePro.do" onSubmit="return check()">
    <table border="1">
    
      <tr>
        <td>ID</td>
        <td><input type="text" name="id" id="id" value="${sessionScope.id}" readonly></td>
      </tr>
      
      <tr>
        <td>암호</td>
        <td><input type="password" name="pw" id="pw" size="20"></td>
      </tr>
      
      <tr>
        <td>암호확인</td>
        <td><input type="password" name="pw2" id="pw2" size="20"></td>
      </tr>
      
      <tr>
        <td>이름</td>
        <td><input type="text" name="name" id="name" value="${dto.name}" size="30"></td>
      </tr>
      
      <tr>
        <td>Email</td>
        <td><input type="text" name="email" id="email" value="${dto.email}" size="40"></td>
      </tr>
      
      <tr>
        <td>전화</td>
        <td><input type="text" name="tel" id="tel" value="${dto.tel}" size="14"></td>
      </tr>
      
      <tr>
        <td>우편번호</td>
        <td>
          <input type="text" name="zipcode" id="zipcode" value="${dto.zipcode}" size="5">
          <input type="button" value="주소찾기" onClick="openDaumPostcode()">
        </td>
      </tr>
      
      <tr>
        <td>주소</td>
        <td>
          <input type="text" name="addr" id="addr" value="${dto.addr}" size="50">
          <br>
          상세주소:<input type="text" name="addr2" id="addr2" value="${dto.addr2}" size="20">
        </td>
      </tr>
      
      <tr>
        <td colspan="2" align="center">
          <input type="submit" value="내정보 수정">
          <input type="reset" value="다시입력">
          <input type="button" value="취소" onClick="location.href='${ctxpath}/member/main.do'">
        </td>
      </tr>
      
    </table>
  </form>
  
</body>
</html>