package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import boardmysql.*;
public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		request.setCharacterEncoding("UTF-8");
		
		//deleteForm.jsp 에서 보내준 데이터 받기
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		String pw=request.getParameter("pw");
		
		BoardDAO dao=BoardDAO.getDAO();
		int x=dao.deleteBoard(num, pw);//dao메서드 호출 
		
		//해당 뷰에서 사용할 속성
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("x", x);
		
		
		return "/board/deletePro.jsp";
		
	}//requestPro()-end
}//class-end
