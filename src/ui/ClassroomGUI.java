package ui;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
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
     private ImageView profilePhoto;

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
     
     private ObservableList<UserAccount> Olist;
     
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
    	  //seteo de prueba
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
    public void login(ActionEvent event) throws IOException {
		
		String user = txtUser.getText();
		String pass = txtPass.getText();
		
		if(user.equals("")||pass.equals("")) {
			JOptionPane.showMessageDialog(null, "Please fill in all the required fields");
		} else {
			int index = classroom.login(user, pass);
			
			if(index != -1) {
				
				FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("account-list.fxml"));
		    	fxmlloader.setController(this);
		    	Parent log = fxmlloader.load();
		    	mainPane.getChildren().setAll(log);
		    	
		    	initializeTableView();
		    	File file = new File(classroom.getList().get(index).getUrlPhoto());
		    	Image image = new Image(file.toURI().toString());
		    	profilePhoto.setImage(image);
		    	
		    	profileName.setText("Bienvenid@ " + classroom.getList().get(index).getUserName());
				
			} else {
				JOptionPane.showMessageDialog(null, "The user is not registered");
			}
		}
		
		
    }
	
	@FXML
    void logOut(ActionEvent event) throws IOException {
		startLoginMenu();
    }
	
	@FXML
	public void openDirectory(ActionEvent event) {
		
		String path = null;
		
		FileChooser chooser = new FileChooser();
		FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image Files", ".jpg", ".png");
		chooser.getExtensionFilters().add(filter);
		File file = chooser.showOpenDialog(null);
		
		if(file != null) {
			
			path = file.getAbsolutePath();
			urlPhoto.setText(path);
			
		}
		
	}

	public void startLoginMenu() throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("login.fxml"));
    	fxmlloader.setController(this);
    	Parent log = fxmlloader.load();
    	mainPane.getChildren().setAll(log);
	}
	
	private void initializeTableView() {
    	Olist = FXCollections.observableArrayList(classroom.getList());
    	
    	tvAccountList.setItems(Olist);
    	tcUsername.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("userName"));
    	tcGender.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("gender"));
    	tcCareer.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("career"));
    	tcBirthday.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("birthday"));
    	tcBrowser.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("browser"));
    
    }
	
	public boolean verifyIfThereEmptyFields(String item1, String item2, String item3, String item4, String item5, String item6, String item7, String item8) {
		if(item1.equals("")||item2.equals("")||item3.equals("")||item4.equals("")||item5.equals("")||item6.equals("")||item7.equals("")||item8.equals("")) {
			return true;
		}else {
			return false;
		}
	}
}
