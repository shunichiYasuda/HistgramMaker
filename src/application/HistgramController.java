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

	// �x�����z������B
	private void getFrequency(double[] in, double[] range, int[] counter) {
		// data �z���擪�������ǂݍ���
		double v; // data �z��̂ЂƂ̐��l
		for (int n = 0; n < in.length; n++) {
			v = in[n]; // in[0] �������l������
			// �ǂݎ���� v ���K���ɓ����Ă��邩�ǂ����𔻒f
			// �Ȃ�Ƃ��ȏ�(min�j�A�Ȃ�Ƃ�����(max) ������
			double min, max;
			// �͈͂̋��E�����߂��z�� range �����Ԃ�2���ǂݍ���
			for (int i = 0; i < range.length - 1; i++) {
				// range �̔z��� i+1 �܂œǂނ̂ŁA���[�v�̉񐔂�1�񌸂炷
				min = range[i]; // i�Ԗڂ���������
				max = range[i + 1]; // i+1�Ԗڂ��傫����
				// if �����g���āAv ��min�ȏ�Amax �����ł��邩�ǂ����𔻒�
				if (v >= min && v < max) {
					counter[i]++; // �J�E���^�̊Y���ӏ���1���₷�B
				} // end of if(v >= min ....
			} // end of for(int i=0...

		} // end of for(int n=0....
			// �����܂łŁA data �̒l�͂��ׂă`�F�b�N�����B
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
		if (sepCode.equals("���s")) {
			code = new String("\n");
		}
		if (sepCode.equals("�J���}"))
			code = ",";
		if (sepCode.equals("�^�u"))
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
		seperateChoice.setValue("���s");
		seperateChoice.getItems().add("���s");
		seperateChoice.getItems().add("�J���}");
		seperateChoice.getItems().add("�^�u");

	}
}
