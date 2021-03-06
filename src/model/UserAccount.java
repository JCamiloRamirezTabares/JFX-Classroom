package model;

public class UserAccount {
	
	private String userName;
	private String password;
	private String gender;
	private String career;
	private String birthday;
	private String browser;
	private String urlPhoto;
	
	public UserAccount(String userName, String password, String gender, String career, String birthday, String browser, String urlPhoto) {
		
		this.userName = userName;
		this.password = password;
		this.gender = gender;
		this.career = career;
		this.birthday = birthday;
		this.browser = browser;
		this.urlPhoto = urlPhoto;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getUrlPhoto() {
		return urlPhoto;
	}

	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}
	
	

}
