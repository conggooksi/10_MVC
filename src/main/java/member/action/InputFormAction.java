package member.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
//인테페이스 상속 받아 ,메서드 오버라이딩한다 
//클래스 implements 인터페이스
public class InputFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		return "/member/inputForm.jsp";
	}

}
