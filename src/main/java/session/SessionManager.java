package session;

import models.User;
import services.UserServices;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SessionManager {
    private Map<String, User> sessionTable;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private static final String SESSION_ID = "sessionId";
    private static final String SESSION_TABLE = "sessionUser";

    private SessionManager(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        init();
    }

    private void init() {
        session = request.getSession(true);
        if (session.getAttribute(SESSION_TABLE) == null) {
            sessionTable = new HashMap<>();
        } else {
            sessionTable = (Map<String, User>) session.getAttribute(SESSION_TABLE);
        }
        session.setAttribute(SESSION_TABLE, sessionTable);
    }

    public static SessionManager getInstance(HttpServletRequest request, HttpServletResponse response) {
        return new SessionManager(request, response);
    }

    public User getUser() {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(SESSION_ID)) {
                    return sessionTable.get(cookie.getValue());
                }
            }
        }
        return null;
    }

    public void addUser(User user) {
        String sessionId = generateSessionId();
        sessionTable.put(sessionId, user);
        session.setAttribute(SESSION_TABLE, sessionTable);
        Cookie cookie = new Cookie(SESSION_ID, sessionId);
        response.addCookie(cookie);
    }

    private String generateSessionId() {
        String sessionId = UUID.randomUUID().toString();
        while (sessionTable.containsKey(sessionId)) {
            sessionId = UUID.randomUUID().toString();
        }
        return sessionId;
    }

    public void removeUser() {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (sessionTable.get(cookie.getValue()) != null) {
                    sessionTable.remove(cookie.getValue());
                    session.setAttribute(SESSION_TABLE, sessionTable);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }

    public void updateUser() {
        int userId = getUser().getId();
        User user = UserServices.getINSTANCE().getUser(userId);
        addUser(user);
    }

}
