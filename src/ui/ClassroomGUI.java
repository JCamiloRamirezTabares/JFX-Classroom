package ui;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import model.Classroom;
import model.UserAccount;

public class ClassroomGUI {
	
	 @FXML
     private TableView<UserAccount> tvAccountList;

     @FXML
     private TableColumn<UserAccount, String> tcUsername;

     @FXML
     private TableColumn<UserAccount, String> tcGender;

     @FXML
     private TableColumn<UserAccount, String> tcCareer;

     @FXML
     private TableColumn<UserAccount, String> tcBirthday;

     @FXML
     private TableColumn<UserAccount, String> tcBrowser;

     @FXML
     private Label profilePhoto;

     @FXML
     private Label profileName;
	
	 @FXML
	 private TextField txtFUsername;

	 @FXML
	 private PasswordField txtPassword;

	 @FXML
	 private PasswordField txtCPassword;

	 @FXML
	 private RadioButton rdbttnMale;

	 @FXML
	 private RadioButton rdbttnFemale;

	 @FXML
	 private RadioButton rdbttnOther;

	 @FXML
	 private CheckBox checkSystemE;

	 @FXML
	 private CheckBox checkTelemE;

	 @FXML
	 private CheckBox checkIndusE;

	 @FXML
	 private DatePicker birthdayBox;
	 
	 @FXML
	 private TextField urlPhoto;

	 @FXML
	 private ComboBox<String> browserBox;
     
     @FXML
     private ToggleGroup gender;
     
     @FXML
     private TextField txtUser;

     @FXML
     private PasswordField txtPass;
     
     @FXML
     private Pane mainPane;
     
     private Classroom classroom;
     
     @FXML
     private Label prueba;

     //Constructor Method
     public ClassroomGUI() {
    	 classroom = new Classroom();
	}

	@FXML
     public void createAccount(ActionEvent event) {
    	 String verify = "";
    	 
    	 String username = txtFUsername.getText();
    	 String password = txtPassword.getText();
    	 String password2 = txtCPassword.getText();
    	 String genderSelected = "";
    	 
    	 if(rdbttnFemale.isSelected()) {
    		 genderSelected = "Female";
    	 }else if(rdbttnMale.isSelected()){
    		 genderSelected = "Male";
    	 } else if (rdbttnOther.isSelected()){
    		 genderSelected = "Other";
    	 } else {
    		 genderSelected = "";
    	 }
    	 
    	 String careerSelected = "";
    	 
    	 if(checkSystemE.isSelected()) {
    		 careerSelected += " Software Engineering";
    	 }
    	 if(checkTelemE.isSelected()){
    		 careerSelected += " Telematic Engineering";
    	 }
    	 if(checkIndusE.isSelected()){
    		 careerSelected += " Industrial Engineering";
    	 }
    	 
    	 String birthday = "";
    	 if(birthdayBox.getValue() != null) {
    		 LocalDate date = birthdayBox.getValue();
        	 birthday += date;
    	 }
    	 
    	 String favoriteBrowser = "";
    	 if(browserBox.getValue() != null) {
    		 favoriteBrowser = browserBox.getValue();
    	 }
    	 
    	 String urlPicture = urlPhoto.getText();
    	 
    	 verify = username + password + password2 + genderSelected + careerSelected + birthday + favoriteBrowser + urlPicture;
    	 
    	 if(verifyIfThereEmptyFields(username, password, password2, genderSelected, careerSelected, birthday, favoriteBrowser, urlPicture)) {
             JOptionPane.showMessageDialog(null, "Please fill in all the required fields");
    	 } else if(password.equals(password2)){
    		 if(classroom.addNewUser(username, password, genderSelected, careerSelected, birthday, favoriteBrowser, urlPicture)) {
    			 JOptionPane.showMessageDialog(null, "You have successfully registered");
    		 } else {
    			 JOptionPane.showMessageDialog(null, "Registration could not be done correctly");
    		 }
    	 } else {
    		 JOptionPane.showMessageDialog(null, "The passwords is not match");
    	 }
    	 
    	 prueba.setText(verify);
    	 
     }
     
	@FXML
	public void signUpEvent(ActionEvent event) throws IOException {
		 
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("register.fxml"));
    	fxmlloader.setController(this);
    	Parent reg = fxmlloader.load();
    	mainPane.getChildren().setAll(reg);
    	
        browserBox.getItems().addAll("Google Chrome", "Mozilla Firefox", "Microsoft Edge", "Internet Explorer", "Safari", "Opera");
	}
	
	@FXML
    public void returnToLogin(ActionEvent event) throws IOException {
		startLoginMenu();
    }
	
	@FXML
    public void login(ActionEvent event) {
		
		String user = txtUser.getText();
		String pass = txtPass.getText();
		
    }
	
	@FXML
	public void openDirectory(ActionEvent event) {
		
		String path = null;
		
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*Images", "jpg", "png", "jpeg");
		chooser.addChoosableFileFilter(filter);
		
		int fileState = chooser.showSaveDialog(null);
		
		if(fileState == JFileChooser.APPROVE_OPTION) {
			
			File selectedImage = chooser.getSelectedFile();
			path = selectedImage.getAbsolutePath();
			urlPhoto.setText(path);
			
		}
		
	}

	public void startLoginMenu() throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("login.fxml"));
    	fxmlloader.setController(this);
    	Parent log = fxmlloader.load();
    	mainPane.getChildren().setAll(log);
	}
	
	public void initializeTableView() {
		
	}
	
	public boolean verifyIfThereEmptyFields(String item1, String item2, String item3, String item4, String item5, String item6, String item7, String item8) {
		if(item1.equals("")||item2.equals("")||item3.equals("")||item4.equals("")||item5.equals("")||item6.equals("")||item7.equals("")||item8.equals("")) {
			return true;
		}else {
			return false;
		}
	}
}
