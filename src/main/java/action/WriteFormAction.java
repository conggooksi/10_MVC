package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		//원글,답글처리 
		int num=0,ref=1,re_step=0,re_level=0; //초기화 
		
		if(request.getParameter("num") != null){//답글이면
			num=Integer.parseInt(request.getParameter("num"));
			ref=Integer.parseInt(request.getParameter("ref"));
			re_step=Integer.parseInt(request.getParameter("re_step"));
			re_level=Integer.parseInt(request.getParameter("re_level"));
		}
		
		//해당 뷰에서 사용할 속성
		
		request.setAttribute("num", new Integer(num));
		request.setAttribute("ref", new Integer(ref));
		request.setAttribute("re_step", new Integer(re_step));
		request.setAttribute("re_level", new Integer(re_level));
		
		return "/board/writeForm.jsp";//뷰를 리턴한다 
	}//requestPro()-end

}//class-end
