package com.internousdev.venus.action;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.venus.dao.UserInfoDAO;
import com.internousdev.venus.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class CreateUserConfirmAction extends ActionSupport implements SessionAware {
	private String id;
	private String userId;
	private String password;
	private String familyName;
	private String firstName;
	private String familyNameKana;
	private String firstNameKana;
	private String email;
	private String sex;
	public Map<String, Object> session;
	private List<String> userIdErrorMessageList;
	private List<String> passwordErrorMessageList;
	private List<String> familyNameErrorMessageList;
	private List<String> firstNameErrorMessageList;
	private List<String> familyNameKanaErrorMessageList;
	private List<String> firstNameKanaErrorMessageList;
	private List<String> emailErrorMessageList;
	private String userErrorMessage;

	public UserInfoDAO userDAO = new UserInfoDAO();

	public String execute() throws SQLException {
		if (session.isEmpty()) {
			return "sessionTimeout";
		}

		InputChecker check = new InputChecker();
		familyNameErrorMessageList = check.doCheck("姓", familyName, 1, 16, true, true, true, false, false, false,
				false);
		firstNameErrorMessageList = check.doCheck("名", firstName, 1, 16, true, true, true, false, false, false, false);
		familyNameKanaErrorMessageList = check.doCheck("姓ふりがな", familyNameKana, 1, 16, false, false, true, false, false,
				false, false);
		firstNameKanaErrorMessageList = check.doCheck("名ふりがな", firstNameKana, 1, 16, false, false, true, false, false,
				false, false);
		emailErrorMessageList = check.doCheck("メールアドレス", email, 10, 32, true, false, false, true, true, false, false);
		userIdErrorMessageList = check.doCheck("ユーザーID", userId, 1, 8, true, false, false, true, false, false, false);
		passwordErrorMessageList = check.doCheck("パスワード", password, 1, 16, true, false, false, true, false, false,
				false);

		String result = ERROR;

		session.put("userIdForCreateUser", userId);
		session.put("familyName", familyName);
		session.put("firstName", firstName);
		session.put("familyNameKana", familyNameKana);
		session.put("firstNameKana", firstNameKana);
		session.put("email", email);
		session.put("sex", sex);

		if (userDAO.isExistsUserId(userId)) {
			userErrorMessage = "使用できないユーザーIDです。";
			result = ERROR;
		} else if (familyNameErrorMessageList.size() > 0 || firstNameErrorMessageList.size() > 0
				|| familyNameKanaErrorMessageList.size() > 0 || firstNameKanaErrorMessageList.size() > 0
				|| emailErrorMessageList.size() > 0 || userIdErrorMessageList.size() > 0
				|| passwordErrorMessageList.size() > 0) {
			result = ERROR;

		} else {
			session.put("password", password);
			result = SUCCESS;
		}
		return result;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFamilyNameKana() {
		return familyNameKana;
	}

	public void setFamilyNameKana(String familyNameKana) {
		this.familyNameKana = familyNameKana;
	}

	public String getFirstNameKana() {
		return firstNameKana;
	}

	public void setFirstNameKana(String firstNameKana) {
		this.firstNameKana = firstNameKana;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public List<String> getUserIdErrorMessageList() {
		return userIdErrorMessageList;
	}

	public void setUserIdErrorMessageList(List<String> userIdErrorMessageList) {
		this.userIdErrorMessageList = userIdErrorMessageList;
	}

	public List<String> getPasswordErrorMessageList() {
		return passwordErrorMessageList;
	}

	public void setPasswordErrorMessageList(List<String> passwordErrorMessageList) {
		this.passwordErrorMessageList = passwordErrorMessageList;
	}

	public List<String> getFamilyNameErrorMessageList() {
		return familyNameErrorMessageList;
	}

	public void setFamilyNameErrorMessageList(List<String> familyNameErrorMessageList) {
		this.familyNameErrorMessageList = familyNameErrorMessageList;
	}

	public List<String> getFirstNameErrorMessageList() {
		return firstNameErrorMessageList;
	}

	public void setFirstNameErrorMessageList(List<String> firstNameErrorMessageList) {
		this.firstNameErrorMessageList = firstNameErrorMessageList;
	}

	public List<String> getFamilyNameKanaErrorMessageList() {
		return familyNameKanaErrorMessageList;
	}

	public void setFamilyNameKanaErrorMessageList(List<String> familyNameKanaErrorMessageList) {
		this.familyNameKanaErrorMessageList = familyNameKanaErrorMessageList;
	}

	public List<String> getFirstNameKanaErrorMessageList() {
		return firstNameKanaErrorMessageList;
	}

	public void setFirstNameKanaErrorMessageList(List<String> firstNameKanaErrorMessageList) {
		this.firstNameKanaErrorMessageList = firstNameKanaErrorMessageList;
	}

	public List<String> getEmailErrorMessageList() {
		return emailErrorMessageList;
	}

	public void setEmailErrorMessageList(List<String> emailErrorMessageList) {
		this.emailErrorMessageList = emailErrorMessageList;
	}

	public String getUserErrorMessage() {
		return userErrorMessage;
	}

	public void setUserErrorMessage(String userErrorMessage) {
		this.userErrorMessage = userErrorMessage;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
