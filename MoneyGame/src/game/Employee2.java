package game;   // W fxml zmieniaj fx:id na takie same jak tu!!!!!Male literki patrz !!!

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class Employee2 {

// static zmienia bardzo duzo
	private static int count2;

	@FXML
	private Button button2;
	
	@FXML
	private ImageView ivEmp2 = new ImageView();
	
	public Employee2() {
	
	}

	 @FXML
	public void buy() {
		
		button2.setOnAction((ActionEvent event) -> {	Image image = new Image("game/Employee2.png");
		
		
		if (MainController.getCash() >= 400) {
			button2.setDisable(true);
			button2.setText("sold");
			//circle.setFill(Color.RED);
			ivEmp2.setImage(image);
			ivEmp2.setPreserveRatio(true);
			ivEmp2.setSmooth(true);
			ivEmp2.setCache(true);   
			
			int cash = (MainController.getCash()-400);
			MainController.setCash(cash);
	//	button.setGraphic(new ImageView(image));
			count2++;
			//	button.enabled = false;
			
			// pay =- 100;
			// System.out.println(count);
		}
		});
		}
	

	public static int getCount2() {
		// TODO Auto-generated method stub
		return count2;
	}

	
	
}