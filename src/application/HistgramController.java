package application;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HistgramController {
	// variables
	double minValue, maxValue, widthValue;
	String dataStr, sepCode;
	String[] dataArray;
	ArrayList<Double> data = new ArrayList<Double>();
	double[] range;
	int[] counter;
	@FXML
	ChoiceBox<String> seperateChoice;
	@FXML
	TextArea dataArea, histArea;
	@FXML
	TextField minField, maxField, widthField;
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
		System.out.println("min=" + minValue + "\tmax=" + maxValue + "\twidth=" + widthValue);
		getData();
		for (String r : dataArray) {
			System.out.print(r + ",");
		}
		System.out.println("");
		makeRange();

	}

	// 度数分布をつくる。
	private void getFrequency(double[] in, double[] range, int[] counter) {
		// data 配列を先頭から一つずつ読み込む
		double v; // data 配列のひとつの数値
		for (int n = 0; n < in.length; n++) {
			v = in[n]; // in[0] から一つずつ値が入る
			// 読み取った v が階級に入っているかどうかを判断
			// なんとか以上(min）、なんとか未満(max) を準備
			double min, max;
			// 範囲の境界を決めた配列 range を順番に2つずつ読み込む
			for (int i = 0; i < range.length - 1; i++) {
				// range の配列の i+1 まで読むので、ループの回数は1回減らす
				min = range[i]; // i番目が小さい方
				max = range[i + 1]; // i+1番目が大きい方
				// if 文を使って、v がmin以上、max 未満であるかどうかを判定
				if (v >= min && v < max) {
					counter[i]++; // カウンタの該当箇所を1増やす。
				} // end of if(v >= min ....
			} // end of for(int i=0...

		} // end of for(int n=0....
			// ここまでで、 data の値はすべてチェックした。
	} // end of method void getFrequency(double[] ....
		// make range

	private void makeRange() {
		int numOfClass = (int) Math.ceil((maxValue - minValue) / widthValue) + 1;
		System.out.println("numOfClass=" + numOfClass);
		range = new double[numOfClass];
		range[0] = minValue;
		for (int i = 1; i < range.length; i++) {
			range[i] = range[i - 1] + widthValue;
		}
		//
		for (double v : range) {
			System.out.print(v + "\t");
		}
		System.out.println();
		//
		counter = new int[numOfClass];
		for (int i = 0; i < counter.length; i++)
			counter[i] = 0;
	}

	// check parameters
	void checkParams() {
		minValue = Double.parseDouble(minField.getText());
		maxValue = Double.parseDouble(maxField.getText());
		widthValue = Double.parseDouble(widthField.getText());
	}

	void getData() {
		String code = null;
		sepCode = seperateChoice.getValue();
		// System.out.println("sep code"+sepCode);
		dataStr = dataArea.getText();
		if (sepCode.equals("改行")) {
			code = new String("\n");
		}
		if (sepCode.equals("カンマ"))
			code = ",";
		if (sepCode.equals("タブ"))
			code = "\t";
		dataArray = dataStr.split(code);
		//
		for (String s : dataArray) {
			try {
				double v = Double.parseDouble(s);
				data.add(v);
			} catch (NumberFormatException e) {
				System.out.println("exception");
			}
			

		}
	}

	//
	public void initialize() {
		seperateChoice.setValue("改行");
		seperateChoice.getItems().add("改行");
		seperateChoice.getItems().add("カンマ");
		seperateChoice.getItems().add("タブ");

	}
}
