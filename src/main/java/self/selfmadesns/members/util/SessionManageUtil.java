package self.selfmadesns.members.util;

import javax.servlet.http.HttpSession;

public class SessionManageUtil {

    private HttpSession httpSession;

    public SessionManageUtil(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public String createSession(String sessionName, String sessionValue){
        String result = "";
        httpSession.setAttribute(sessionName,sessionValue);
        Object returnSession = httpSession.getAttribute(sessionName);
        if(returnSession!=null){
            result = returnSession.toString();
        } else {

        }
        return result;
    }

    public String removeSession(String sessionName){
        String result = "";
        httpSession.removeAttribute(sessionName);
        Object returnSession = httpSession.getAttribute(sessionName);
        if(returnSession!=null){
            result = returnSession.toString();
        } else {

        }
        return result;
    }
}
