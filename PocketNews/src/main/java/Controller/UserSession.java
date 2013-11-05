package Controller;

import Model.entity.SuperUser;


import javax.servlet.http.HttpSession;

public class UserSession {
	public static final String USER_SESSION_KEY="Controller.usersession";
	
	public static void addUser(SuperUser superUser,HttpSession session){
		session.setAttribute(USER_SESSION_KEY, superUser);
	}
	
	public static SuperUser getUser(HttpSession session){
		return (SuperUser) session.getAttribute(USER_SESSION_KEY);
	}
	
	public static void removeUser(HttpSession session){
		session.removeAttribute(USER_SESSION_KEY);
	}
}
