package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import boardmysql.*;//DAO,DTO

//Action은 JSP의 로직처리를 액션에다 코딩한다 
//Action은 dao메서드를 호출하고 결과를 받아서 request.setAttribute("키",값)
//그리고 뷰를 리턴한다. 컨트롤러는 뷰로 포워딩한다 

//Action은 스프링에서 컨트롤러라고 한다 

public class ListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		String pageNum=request.getParameter("pageNum");
		
		if(pageNum==null){
			pageNum="1";
		}
		
		int pageSize=10;
		int currentPage=Integer.parseInt(pageNum);
		
		int startRow=(currentPage-1)*pageSize+1;//페이지의 시작 row(행)
		int endRow=currentPage*pageSize;
		
		int count=0;//총 글갯수 넣을 변수
		int number=0;//글번호 처리 
		int pageBlock=10;//블럭당 페이지 수
 		List list=null;
		
		BoardDAO dao=BoardDAO.getDAO();//dao객체 얻기 
		count=dao.getBoardCount();//총글갯수 얻기 
		
		if(count>0){
			list=dao.getList(startRow, pageSize);
		}else{
			list=Collections.EMPTY_LIST;//비어있는 리스트
		}
		
		number=count-(currentPage-1)*pageSize;
		int pageCount=count/pageSize+(count%pageSize==0?0:1);
		
		int startPage=(int)(currentPage/pageBlock)*10+1;
		int endPage=startPage+pageBlock-1;
		
		request.setAttribute("startPage", new Integer(startPage));
		request.setAttribute("endPage", new Integer(endPage));
		
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		
		request.setAttribute("pageBlock", new Integer(pageBlock));
		request.setAttribute("pageCount", new Integer(pageCount));
		
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		
		request.setAttribute("list", list);
		 
		
		return "/board/list.jsp";//뷰를 리턴한다 
	}//requestPro()-end

}//class-end
