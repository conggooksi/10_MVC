<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--template.jsp --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table width="90%" height="90%" border="1">

<tr height="30" bgcolor="ivory">
 <td colspan="2" align="right">
  <jsp:include page="/module/top.jsp" flush="false"/>
 </td>
</tr>

<tr width="500">
<td width="150" valign="top">
<jsp:include page="/module/left.jsp" flush="false"/>
</td>

<td width="700" height="90%" valign="top">
 <jsp:include page="${CONTENT}" flush="false"/>
</td>
</tr>

<tr>
<td width="700" valign="top" colspan="2">
<jsp:include page="/module/bottom.jsp" flush="false"/>
</td>
</tr>

</table>
</body>
</html>