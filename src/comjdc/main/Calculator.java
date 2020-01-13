package comjdc.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.ArrayList;
public class Calculator {

	@FXML
	private Label output;
	int count=0;
	String num="",number="",ope="";
	double result=0;
	ArrayList<Double> allnum=new ArrayList<Double>();

	public void pressNumber(ActionEvent event) {
		Button btn = (Button) event.getSource();
		//for decimal///////////////////////////
	    if(!output.getText().contains(".")) {
	    	number = btn.getText();
	    }
	    else {
	    	if(count==0) {
	    		if(output.getText().contains("0")) {
	    			number = "0."+btn.getText();
	    		}
	    		else {
	    			number = "."+btn.getText();
	    		}
	    	}
	    	else {
	    		number = btn.getText();
	    	}
	    	count++;
	    }
	    /////////////////////////////////////////
		num+=number;
		if (output.getText().startsWith("-")&&allnum.size()==0) {
			output.setText("-"+num);
		}
		else {
			output.setText(num);
		}
	}

	public void pressOperator(ActionEvent event) {
		Button btn = (Button) event.getSource();
		allnum.add(Double.parseDouble(output.getText()));
		ope = btn.getText();
		num="";
		number="";
		count=0;
		output.setText(btn.getText());
	}

	public void showResult() {
		allnum.add(Double.parseDouble(output.getText()));
		switch(ope){
			case "+":
				for(int a=0;a<allnum.size();a++) {
					result+=allnum.get(a);
					}
			break;
			case "-":
				for(int a=0;a<allnum.size();a++) {
					if(a==0) {result=allnum.get(a)-result;}
					else {result-=allnum.get(a);}
				}
			break;
			case "*":
				for(int a=0;a<allnum.size();a++) {
					if(a==0) {result=allnum.get(a);}
					else {result*=allnum.get(a);}
				}
			break;
			case "/":
				for(int a=0;a<allnum.size();a++) {
					if(a==0) {result=allnum.get(a);}
					else {result/=allnum.get(a);}
				}
			break;
		}
		output.setText(Double.toString(result));
		num="";
		number="";
		ope="";
		count++;
		allnum.clear();
		result=0;
	}

	public void clear() {
		num="";
		number="";
		result=0;
		count=0;
		allnum.clear();
		ope="";
		output.setText("0");
	}

	public void doNegative() {
		String num = output.getText();
		if (!"0".equals(num)) {
			if (output.getText().startsWith("-")) {
				output.setText(num.substring(1));
			} else {
				output.setText("-".concat(num));
			}
		}
	}

	public void doPercentage() {
		double num1=Double.parseDouble(output.getText());
		String num2 = Double.toString(num1/100);
		output.setText(num2);
	}

	public void doDecimal() {
		if (!output.getText().contains(".")) {
			output.setText(output.getText().concat(".")); 
		}
	}
}
