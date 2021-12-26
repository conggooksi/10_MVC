package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import boardmysql.*;

public class UpdateFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		// content.jsp에서 보내준 데이터 받기 
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		
		BoardDAO dao=BoardDAO.getDAO();
		BoardDTO dto=dao.getBoard(num);//dao메서드 호출, num해당 하는 자료을 받는다
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("dto", dto);
		
		return "/board/updateForm.jsp";
	}

}
