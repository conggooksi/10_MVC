package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import boardmysql.*;

public class UpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		String pageNum=request.getParameter("pageNum");
		
		//updateForm.jsp에서 보내준 데이터를 받아서, dto넣는다
		BoardDTO dto=new BoardDTO();
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setWriter(request.getParameter("writer"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		dto.setPw(request.getParameter("pw"));
		
		BoardDAO dao=BoardDAO.getDAO();
		
		int x=dao.updateBoard(dto);//dao메서드 호출 
		
		request.setAttribute("x", x);
		request.setAttribute("num", Integer.parseInt(request.getParameter("num")));
		request.setAttribute("pageNum", pageNum);
	 			
		return "/board/updatePro.jsp";//뷰를 리턴
	}//requestPro()-end

}//class-end
