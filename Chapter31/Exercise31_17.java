package exercise32_17;

import java.text.DecimalFormat;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button; 
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem; 

public class Exercise31_17 extends Application {
	TextField investmentAmount = new TextField();
	TextField numberOfYears = new TextField();
	TextField annualInterestRate = new TextField(); 
	TextField futureValue = new TextField(); 
	Button calculate = new Button("Calculate");
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// Create Menu
		MenuBar menuBar = new MenuBar();
		Menu menuOperation = new Menu("Operation");
		menuBar.getMenus().addAll(menuOperation);
		MenuItem menuItemCalculate = new MenuItem("Calculate");
		MenuItem menuItemExit = new MenuItem("Exit");
		menuOperation.getItems().addAll(menuItemCalculate, menuItemExit);
		menuItemCalculate.setOnAction(e -> {
			double InvestmentAmount = Double.parseDouble(investmentAmount.getText());
			double NumberOfYears = Double.parseDouble(numberOfYears.getText());
			double MonthlyInterestRate = Double.parseDouble(annualInterestRate.getText()) / 12;
			
			double FutureValue = InvestmentAmount * Math.pow(1 + MonthlyInterestRate, NumberOfYears * 12);
			DecimalFormat df = new DecimalFormat("#.00");
			
			futureValue.appendText("$" + String.valueOf(df.format(FutureValue)));
		});
		menuItemExit.setOnAction(e -> {
			Platform.exit();
		});
		
		// Create grid Pane and add Text fields and button
		GridPane pane = new GridPane(); 
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(5);
		pane.setVgap(5);
		pane.add(new Label("Investment Amount:"), 0, 0);
		pane.add(investmentAmount, 1, 0);
		pane.add(new Label("Number of Years:"), 0, 1);
		pane.add(numberOfYears, 1, 1);
		pane.add(new Label("Annual Interest Rate:"), 0, 2);
		pane.add(annualInterestRate, 1, 2);
		pane.add(new Label("Future value:"), 0, 3);
		pane.add(futureValue, 1, 3);
		futureValue.setEditable(false);
		pane.add(calculate, 1, 4);
		
		// setOnAction for button
		calculate.setOnAction(e -> {
			double InvestmentAmount = Double.parseDouble(investmentAmount.getText());
			double NumberOfYears = Double.parseDouble(numberOfYears.getText());
			double MonthlyInterestRate = Double.parseDouble(annualInterestRate.getText()) / 12;
			
			double FutureValue = InvestmentAmount * Math.pow(1 + MonthlyInterestRate, NumberOfYears * 12);
			DecimalFormat df = new DecimalFormat("#.00");
			
			futureValue.appendText("$" + String.valueOf(df.format(FutureValue)));
		});
		
		//create main pane for scene 
		BorderPane mainPane = new BorderPane(); 
		mainPane.setTop(menuBar);
		mainPane.setCenter(pane);
		
		Scene scene = new Scene(mainPane, 350, 250);
		primaryStage.setTitle("Exercise31_17");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
