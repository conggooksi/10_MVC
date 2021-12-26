package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import member.*;

public class UpdateFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		String id=request.getParameter("id");
		MemberDAO dao=MemberDAO.getDAO();
		MemberDTO dto=dao.getMember(id);//id에 해당하는 자료들을 받는다
		
		request.setAttribute("dto",dto);
		
		return "/member/updateForm.jsp";//뷰=jsp
	}

}
