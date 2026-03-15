package filters;

import Model.UserDTO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FilterHelper {
    public static UserDTO getLoginUser(HttpServletRequest httpRequest){
        
        // Truyền false = chỉ LẤY session đã có, không tạo mới
        HttpSession session = httpRequest.getSession(false);
        if(session == null){
            return null;
        }
        return (UserDTO) session.getAttribute("LOGIN_USER");
    }
}
