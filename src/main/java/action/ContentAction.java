package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import boardmysql.*;

public class ContentAction  implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		//list.jsp에서 보내준 데이터 받기 
		int num=Integer.parseInt(request.getParameter("num"));
        String pageNum=request.getParameter("pageNum");
        
        BoardDAO dao=BoardDAO.getDAO();
        BoardDTO dto=dao.getBoard(num);//dao메서드 호출 ,num에 해당하는 dto를 받는다 
        
        int ref=dto.getRef();
        int re_step=dto.getRe_step();
        int re_level=dto.getRe_level();
        
        request.setAttribute("num", new Integer(num));
        request.setAttribute("pageNum",pageNum);///////*****
        
        request.setAttribute("ref", new Integer(ref));
        request.setAttribute("re_step", new Integer(re_step));
        request.setAttribute("re_level", new Integer(re_level));
        request.setAttribute("dto", dto);
        
		return "/board/content.jsp";
	}//requestPro()-end

}//class-end
