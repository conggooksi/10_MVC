<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="member.*"
    %>
<%--confirmID.jsp --%>
<%-- html주석 쓰지 마세요, 에러 나와요 --%>
<%
String id=request.getParameter("id");//Ajax이 넘겨준 
MemberDAO dao=MemberDAO.getDAO();
int x=dao.confirmId(id);//dao메서드 호출  1은 사용중, -1은 사용가능 
%>
{
"x":<%=x%>
}