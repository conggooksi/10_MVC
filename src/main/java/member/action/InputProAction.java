package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import member.*;
import java.sql.Timestamp;//년월일 시분초 

public class InputProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		request.setCharacterEncoding("UTF-8");
		
		//inputForm.jsp에서 보내준 데이터 받아서 dto에 저장  
		String id=request.getParameter("id");
		
		MemberDTO dto=new MemberDTO();
		dto.setId(id);
		
		dto.setPw(request.getParameter("pw"));
		dto.setName(request.getParameter("name"));
		dto.setEmail(request.getParameter("email"));
		dto.setTel(request.getParameter("tel"));
		dto.setZipcode(request.getParameter("zipcode"));
		dto.setAddr(request.getParameter("addr"));
		dto.setAddr2(request.getParameter("addr2"));
		
		//dto를 dao로 넘겨준다 
		MemberDAO dao=MemberDAO.getDAO();
		dao.insertMember(dto);//dao메서드 호출 
		
		request.setAttribute("id", id);
		return "/member/inputPro.jsp";//뷰 리턴
		
	}//requestPro()-end

}//class-end
