package game; // W fxml zmieniaj fx:id na takie same jak tu!!!!!Male literki patrz !!!

import javafx.animation.PathTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;

public class Employee {

	private static int count; // static zmienia bardzo duzo
	private boolean gamerun = true;
	private int flag=0;
	@FXML
	private Button button;

	@FXML
	private ImageView ivEmp = new ImageView();

	@FXML
	private AnchorPane anch;
	
	public Employee() {
		
	}

	@FXML
	public void buy() {
		button.setOnAction((ActionEvent event) -> {
			

			if (MainController.getCash() >= 200) {
				button.setDisable(true);
				button.setText("sold");
				//ivEmp.setImage(MainController.getImage()); //proba prostej animacj z podmiana obrazkow
				ivEmp.setImage(MainController.getImage());
				ivEmp.setPreserveRatio(true);
				ivEmp.setSmooth(true);
				ivEmp.setCache(true);
				int cash = (MainController.getCash() - 200);
				MainController.setCash(cash);
				// button.setGraphic(new ImageView(image));
				count++;
				// button.enabled = false;

				// pay =- 100;
				// System.out.println(count);
				Polyline polyline = new Polyline();
				polyline.getPoints().addAll(new Double[]{
					-40.0, 75.0,
					-40.0, 30.0,
					-40.0, 75.0});
				PathTransition tranistion = new PathTransition();
				tranistion.setNode(ivEmp);
				tranistion.setDuration(Duration.seconds(6));
				tranistion.setPath(polyline);
				tranistion.setCycleCount(PathTransition.INDEFINITE);
				tranistion.play();
			
			}
		});

	}
	
	public ImageView getIvEmp() {
		return ivEmp;
	}

	public void setIvEmp(ImageView ivEmp) {
		this.ivEmp = ivEmp;
	}

	
	public static int getCount() {
		// System.out.println(count+"z klasy");
		return count;
	}

}