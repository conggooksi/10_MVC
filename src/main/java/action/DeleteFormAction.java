package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//content.jsp가 보내준 데이터 받기 
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		
		//해당 뷰에서 사용할 속성
		//request.setAttribute("String", 객체);
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum",pageNum);
		
		
		return "/board/deleteForm.jsp";//뷰리턴
	}//requestPro()-end

}//class-end
