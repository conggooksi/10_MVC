<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>    
<%--inputForm.jsp --%>
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
	  if($('#id').val()==''){
		  alert("아이디를 입력 하세요");
		  $('#id').focus();
		  return false;
	  }
	  
	  if($('#pw').val()==''){
		  alert("암호를  입력 하세요");
		  $('#pw').focus();
		  return false;
	  }
	  
	  if($('#pw2').val()==''){
		  alert("암호확인를  입력 하세요");
		  $('#pw2').focus();
		  return false;
	  }
	  
	  if($('#pw').val() != $('#pw2').val()){
		  alert("암호와 암호확인 일치하지 않습니다");
		  $('#pw2').val('');//지우기
		  $('#pw').val('').focus();//지우기고 포커스 설정
		  return false;
	  }
	  
	  if($('#name').val()==''){
		  alert("이름를  입력 하세요");
		  $('#name').focus();
		  return false;
	  }
	  
	  return true;
  }//check()-end
  
//ajax 이용하여 id중복체크
  function confirmIDcheck(){
  			if($('#id').val()==''){
  				alert("id를 입력 하세요")
  			}else{
  				$.ajax({
  					type:'POST',
  					url:'confirmID.jsp',
  					data:"id="+$('#id').val(),//서버로 보내는 값
  					dataType:'JSON',//서버로부터 받은 자료 타입
  					success:function(data){
  						//alert(data);
  						//alert(data.x);// 1 또는 -1
  						if(data.x==1){
  							alert("사용중인 id");
  							$('#id').val('').focus();
  						}else{
  							alert('사용가능한 id');
  							$('#pw').focus();
  						}//else-end
  					}//success-end
  				});//Ajax-end
  			}//else-end
  	 
  }//confirmIDcheck()-end
  
</script>
</head>
<body>
 <h2>(^.^)회원가입(^.^)</h2>
 
 <form name="userForm" method="post" action="${ctxpath}/member/inputPro.do" onSubmit="return check()">
   <table border="1">
   
     <tr>
       <td>ID</td>
       <td>
         <input type="text" name="id" id="id" size="20">
         <input type="button" value="ID중복체크" onClick="confirmIDcheck()">
       </td>
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
       <td><input type="text" name="name" id="name" size="30"></td>
     </tr>
     
     <tr>
       <td>Email</td>
       <td><input type="text" name="email" id="email" size="40"></td>
     </tr>
     
     <tr>
       <td>전화</td>
       <td><input type="text" name="tel" id="tel" size="14"></td>
     </tr>
     
     <tr>
       <td>우편번호</td>
       <td>
         <input type="text" name="zipcode" id="zipcode" size="5">
         <input type="button" value="주소찾기" onClick="openDaumPostcode()">
       </td>
     </tr>
     
     <tr>
        <td>주소</td>
        <td>
          <input type="text" name="addr" id="addr" size="50">
          <br>
          <input type="text" name="addr2" id="addr2" size="20" placeholder="상세주소">
        </td>
     </tr>
     
     <tr>
      <td colspan="2" align="center">
        <input type="submit" value="회원가입">
        <input type="reset" value="다시입력">
        <input type="button" value="가입안함" onClick="location='${ctxpath}/member/main.do'">
      </td>
     </tr>
     
   </table>
 </form>
 
</body>
</html>