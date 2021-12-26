package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import member.*;
public class UpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		String id=request.getParameter("id");
		
		//updateForm.jsp에서 데이터를 dto에 저장
		MemberDTO dto=new MemberDTO();
		
		dto.setId(id);
		dto.setPw(request.getParameter("pw"));
		dto.setName(request.getParameter("name"));
		dto.setEmail(request.getParameter("email"));
		dto.setTel(request.getParameter("tel"));
		dto.setZipcode(request.getParameter("zipcode"));
		dto.setAddr(request.getParameter("addr"));
		dto.setAddr2(request.getParameter("addr2"));
		
		MemberDAO dao=MemberDAO.getDAO();
		dao.updateMember(dto);//dao메서드 호출, 수정완료 
		
		
		request.setAttribute("id", id);
		return "/member/updatePro.jsp";//뷰=JSP 리턴
	}//requestPro()-end

}//class-end
