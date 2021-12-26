package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import member.*;

public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
	 
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		
		MemberDAO dao=MemberDAO.getDAO();
		int x=dao.deleteMember(id, pw);//dao메서드 호출, 삭제
		
		request.setAttribute("x", new Integer(x));//1은 삭제 성공,0은 암호틀림, -1은 없는 id
		return "/member/deletePro.jsp";//뷰=JSP 리턴
	}

}
