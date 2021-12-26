package action;
import javax.servlet.http.*;
//인터페이스 : 메서드 선언만 한다, 내용은 없다
public interface CommandAction {
	public String requestPro(HttpServletRequest request,HttpServletResponse response)
	throws Throwable;
}
