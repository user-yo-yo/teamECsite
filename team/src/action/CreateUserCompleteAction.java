package com.internousdev.venus.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.venus.dao.UserInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class CreateUserCompleteAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;

	public String execute() throws SQLException {
		if (session.isEmpty()) {
			return "sessionTimeout";
		}

		String result = ERROR;
		int sex = 0;
		if (String.valueOf(session.get("sex")).equals("女性")) {
			sex = 1;
		} else {
			sex = 0;
		}

		UserInfoDAO userDAO = new UserInfoDAO();
		int count = userDAO.createUser(String.valueOf(session.get("familyName")),
				String.valueOf(session.get("firstName")), String.valueOf(session.get("familyNameKana")),
				String.valueOf(session.get("firstNameKana")), sex, String.valueOf(session.get("email")),
				String.valueOf(session.get("userIdForCreateUser")), String.valueOf(session.get("password")));

		if (count > 0) {
			result = SUCCESS;
		}

		return result;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
