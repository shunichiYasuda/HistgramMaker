package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class HistgramController {
	//variables
	double minValue,maxValue,widthValue;
	@FXML
	TextField minField,maxField,widthField;
	@FXML 
	Button QuitBtn;
	@FXML
	void quitAction() {
		System.exit(0);
	}
	@FXML
	Button ExecBtn;
	@FXML
	void execAction() {
		checkParams();
		System.out.println("min="+minValue+"\tmax="+maxValue+"\twidth="+widthValue);
	}
	//check parameters 
	void checkParams() {
		minValue = Double.parseDouble(minField.getText());
		maxValue =Double.parseDouble(maxField.getText());
		widthValue =Double.parseDouble(widthField.getText());
	}
}
