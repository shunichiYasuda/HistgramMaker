package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HistgramController {
	//variables
	double minValue,maxValue,widthValue;
	String dataStr,sepCode;
	String[] dataArray;
	@FXML
	ChoiceBox<String> seperateChoice;
	@FXML
	TextArea dataArea,histArea;
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
		getData();
		for(String r:dataArray) {
			System.out.print(r+",");
		}
		System.out.println("");
	
	}
	//check parameters 
	void checkParams() {
		minValue = Double.parseDouble(minField.getText());
		maxValue =Double.parseDouble(maxField.getText());
		widthValue =Double.parseDouble(widthField.getText());
	}
	void getData() {
		String code;
		sepCode = seperateChoice.getValue();
		System.out.println("sep code"+sepCode);
		if(sepCode.equals("改行")) code = new String("\n");
		if(sepCode.equals("カンマ")) code = ",";
		if(sepCode.equals("タブ")) code = "\t";
		dataStr = dataArea.getText();
		dataArray = dataStr.split(code);
	}
	//
	public void initialize() {
		seperateChoice.setValue("改行");
		seperateChoice.getItems().add("改行");
		seperateChoice.getItems().add("カンマ");
		seperateChoice.getItems().add("タブ");
		
	}
}
