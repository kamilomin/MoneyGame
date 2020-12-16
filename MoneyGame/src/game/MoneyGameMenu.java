package game;

import java.io.IOException;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MoneyGameMenu extends Application {

	

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Button startButton = new Button();
		startButton.setText("Play MG");
		
		final Tooltip toolTipButton = new Tooltip();
		toolTipButton.setText("Click to start game");
		startButton.setTooltip(toolTipButton);
		toolTipButton.setStyle("-fx-background-color:green;");
		
		Circle circle = new Circle(100);
		Polyline polyline = new Polyline();
		polyline.getPoints().addAll(new Double[]{
			-80.0, -80.0,
			-80.0, 80.0,
			-80.0, -80.0});
		
		
		
		
		startButton.setOnAction((ActionEvent event) -> {

			Parent secroot;
			try {
				
				secroot = FXMLLoader.load(getClass().getResource("/game/mainController.fxml"));
				Stage secondStage = new Stage();

				
				secondStage.setTitle("Money Game");
				secondStage.setScene(new Scene(secroot, 1024, 768));
				secondStage.show();
				
				startButton.setDisable(true);
				
				secondStage.setOnCloseRequest(e -> Platform.exit());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
	
	
        
		StackPane root = new StackPane();
		root.getChildren().add(startButton);
		Scene scene = new Scene(root, 500, 450);
		// Parent root =
		// FXMLLoader.load(getClass().getResource("/game/moneyGameMenu.fxml"));
		primaryStage.setTitle("Money Game Menu");
		primaryStage.setScene(scene);
		// primaryStage.setScene(new Scene(root, 1024, 768));
		primaryStage.show();
		
		
		
		BackgroundImage myBI = new BackgroundImage(new Image("res/employeeRoom1.png", 500, 450, false, true),
				BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		root.setBackground(new Background(myBI));
		
		Image img = new Image(getClass().getResource("/res/employee.png").toURI().toString());
		ImageView iv = new ImageView(img);
		iv.setFitHeight(128);
		iv.setFitWidth(128);
		root.getChildren().add(iv);

		
		PathTransition tranistion = new PathTransition();
		tranistion.setNode(iv);
		tranistion.setDuration(Duration.seconds(6));
		tranistion.setPath(polyline);
		tranistion.setCycleCount(PathTransition.INDEFINITE);
		tranistion.play();
		
		
		
	}
	

	




	public static void main(String[] args) {
		launch(args);
	}

}
