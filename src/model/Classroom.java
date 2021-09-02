package model;

import java.util.ArrayList;

public class Classroom {
	
	private ArrayList<UserAccount> list;

	//Constructor Method
	public Classroom() {
		list = new ArrayList<UserAccount>();
	}

	public boolean addNewUser(String userName, String password, String gender, String career, String birthday, String browser, String urlPhoto) {
		
		if(list.add(new UserAccount(userName, password, gender, career, birthday, browser, urlPhoto))) {
			
			return true;
			
		} else {
			
			return false;
			
		}
		
	}
	
	
	
	//getter Method
	public ArrayList<UserAccount> getList() {
		return list;
	}
	
	

}
