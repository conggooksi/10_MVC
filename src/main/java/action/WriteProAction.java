package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import boardmysql.*;

public class WriteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		request.setCharacterEncoding("UTF-8");
		BoardDTO dto=new BoardDTO();
		
		//데이터을 바다아서 dto 저장하고, dto를 back-end로 넘긴다 
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		
		dto.setWriter(request.getParameter("writer"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		dto.setPw(request.getParameter("pw"));
		dto.setIp(request.getRemoteAddr());//ip
		
		dto.setRef(Integer.parseInt(request.getParameter("ref")));
		dto.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		dto.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		
		BoardDAO dao=BoardDAO.getDAO();
		dao.insertBoard(dto);//dao메서드 호출 
		
		return "/board/writePro.jsp";//뷰를 리턴한다 
	}//requestPro()-end

}//class-end
