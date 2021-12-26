package member.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.*;
import action.CommandAction;

public class LoginProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		request.setCharacterEncoding("UTF-8");
		
		//main.jsp에서 보낸 데이터 받기 
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		
		MemberDAO dao=MemberDAO.getDAO();
		int x=dao.loginCheck(id, pw);//dao메서드 호출 
		
		//해당 뷰에서 사용할 속성
		request.setAttribute("x", new Integer(x));
		request.setAttribute("id", id);
		
		return "/member/loginPro.jsp";//뷰리턴
	}

}
