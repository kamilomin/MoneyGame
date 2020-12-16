
package game;

import java.io.IOException;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainController {

	protected static final int VALUTE_TO_COMPLETE = 100;
	protected static int cash = 18000;
	protected static int day = 0;
	protected static int halfday = 1;
	protected static int hours = 0;
	protected static int minutes = 0;
	protected static boolean ampm = false;
	private boolean gamerun = true;
	private int numEmp = 0;
	private int numEmp2 = 0;
	private int flag = 0;
	private static Image image = new Image("res/employee.png");
	private int imageFlag = 0;
	private Parent secroot;
	@FXML
	private Button btnRoom1;
	@FXML
	private Button btnRoom2;
	@FXML
	private Button btnRoom3;
	@FXML
	private Button btnRoom4;
	@FXML
	private HBox listOfElements1;
	@FXML
	private HBox listOfElements2;
	@FXML
	private HBox listOfElements3;
	@FXML
	private HBox listOfElements4;
	@FXML
	private VBox vBox1;
	@FXML
	private Text textCash;
	@FXML
	private Text textTime;
	@FXML
	private Button btnWarszawa;
	@FXML
	private Button btnKrakow;

	public static int getCash() {
		return cash;
	}

	public static void setCash(int cash) {
		MainController.cash = cash;
	}


	@FXML
	// Gdy jest tworzony
	private void initialize() {
		
		
		btnWarszawa.setOnAction((ActionEvent event) -> {
			
			StackPane root = new StackPane();
			Scene scene = new Scene(root, 1000, 150);
			root.getChildren().add(listOfElements1);
			
			listOfElements2.getChildren().clear();
			//listOfElements1.getChildren().clear();
		//	listOfElements1.getChildren().addAll();
	     //   secroot.getChildren().clear();
	      //  secroot.getChildren().add(listOfElements1);
			//	listOfElements1.getChildren().remove(btnRoom1);
			
		});
btnKrakow.setOnAction((ActionEvent event) -> {
			initialize();
			//StackPane root = new StackPane();
			//root.getChildren().remove(listOfElements1);
			StackPane root2 = new StackPane();
			Scene scene = new Scene(root2, 1000, 150);
			root2.getChildren().add(listOfElements1);
			root2.getChildren().add(listOfElements2);
			
			
			//root.getChildren().add(listOfElements1);
			
			//listOfElements1.getChildren().clear();
			//listOfElements1.getChildren().clear();
		//	listOfElements1.getChildren().addAll();
	     //   secroot.getChildren().clear();
	      //  secroot.getChildren().add(listOfElements1);
			//	listOfElements1.getChildren().remove(btnRoom1);
			
		});
		
		
		loadBackgroundGame();  //wczytujemy poniszczone pokoje grafike

		btnRoom1.setOnAction((ActionEvent event) -> {
			if (cash >= 1000) {
				buyRoom(1);
				cash -= 1000;
				listOfElements1.getChildren().remove(btnRoom1);
			}
		});
		btnRoom2.setOnAction((ActionEvent event) -> {
			if (cash >= 1000) {
				buyRoom(2);
				cash -= 1000;
				listOfElements2.getChildren().remove(btnRoom2);
			}
		});
		btnRoom3.setOnAction((ActionEvent event) -> {
			if (cash >= 1000) {
				buyRoom(3);
				cash -= 1000;
				listOfElements3.getChildren().remove(btnRoom3);
			}
		});
		btnRoom4.setOnAction((ActionEvent event) -> {
			if (cash >= 1000) {
				buyRoom(4);
				cash -= 1000;
				listOfElements4.getChildren().remove(btnRoom4);
			}

		});

		Task<Integer> progressTask = new Task<Integer>() {
			@Override
			protected Integer call() throws Exception {
				int value = 0;
				while (gamerun = true) {
					if (Employee.getCount() > numEmp) {
						cash += (Employee.getCount() * 50 - 50); // Kupiony pracownik zarabia dopiero od nas tury
						numEmp = Employee.getCount();
					}
					if (Employee2.getCount2() > numEmp2) {
						cash += (Employee2.getCount2() * 100 - 100); // Kupiony pracownik zarabia dopiero od nas tury
						numEmp2 = Employee2.getCount2(); // jesli jest kilku kupionych w jednej turze to zaczynaj¹
															// owszycy od jednej zarabiac
					}
					cash += (Employee.getCount() * 50 + Employee2.getCount2() * 100); // pierwszy pracownik i drugi
																						// pracownik

					System.out.println("ilosc zwyklych pracownikow to " + Employee.getCount() + " a specjalnych to "
							+ Employee2.getCount2() + " kasa to " + cash + " czas " + hours + ":" + minutes);
					updateMessage("Cash " + cash);
					// updateProgress(value, VALUTE_TO_COMPLETE);
					Thread.sleep(5000);
				}
				return value;
			}
		};
		Task<Integer> progressTask2 = new Task<Integer>() {
			@Override
			protected Integer call() throws Exception {
				int value = 0;
				while (gamerun = true) {
					if (minutes == 60) {
						hours++;
						minutes = 0;
						if (hours == 12) {
							hours = 0;
							ampm = !ampm;
							halfday++;
						}
					}
					if (halfday == 2) {
						halfday = 0;
						day++;
					}
					if (ampm = true) {
						updateMessage("Day " + day + " Time " + hours + ":" + minutes + " am");
					} else
						updateMessage("Day " + day + " Time " + hours + ":" + minutes + " pm");
					minutes += 10;
					/*
					 * if (imageFlag == 1) { imageFlag = 0; Image image = new
					 * Image("game/Employee2.png"); //animacja pracownika ... nie dziala xD jak
					 * bindowac ? } else { imageFlag = 1; Image image = new
					 * Image("game/Employee1.png"); }
					 */
					Thread.sleep(300);
				}
				return value;

			}
		};
		Task<Integer> progressTask3 = new Task<Integer>() {
			@Override
			protected Integer call() throws Exception {
				int value = 0;
				while (gamerun = true) {
					updateMessage("Cash " + cash);
					Thread.sleep(1000); // super szybka akutalizacja kasy np po kupieniu
				}
				return value;
			}
		};

		// Dowi¹zujemy wartoœci
		// progress.progressProperty().bind(progressTask.progressProperty());
		// name.textProperty().bind(progressTask.messageProperty());
		textCash.textProperty().bind(progressTask.messageProperty());
		textTime.textProperty().bind(progressTask2.messageProperty());
		textCash.textProperty().bind(progressTask3.messageProperty());

		// Uruchamiamy
		Thread thread = new Thread(progressTask);
		thread.setDaemon(true);
		thread.start();
		Thread thread2 = new Thread(progressTask2);
		thread2.setDaemon(true);
		thread2.start();
		Thread thread3 = new Thread(progressTask3);
		thread3.setDaemon(true);
		thread3.start();

	}

	private void loadBackgroundGame() {
		BackgroundFill myBF = new BackgroundFill(Color.GREEN, new CornerRadii(1), new Insets(0.0, 0.0, 0.0, 0.0));// or
		// padding
		vBox1.setBackground(new Background(myBF));

		BackgroundFill myBF1 = new BackgroundFill(Color.DARKRED, new CornerRadii(1), new Insets(0.0, 0.0, 0.0, 0.0));

		BackgroundImage myBI1 = new BackgroundImage(new Image("res/roomCrash1.png", 1000, 150, false, true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		BackgroundImage myBI2 = new BackgroundImage(new Image("res/roomCrash2.png", 1000, 150, false, true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		BackgroundImage myBI4 = new BackgroundImage(new Image("res/roomCrash4.png", 1000, 150, false, true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);

		listOfElements1.setBackground(new Background(myBI1));
		listOfElements2.setBackground(new Background(myBI2));
		listOfElements3.setBackground(new Background(myBI1));
		listOfElements4.setBackground(new Background(myBI4));

		final Tooltip toolTipButton = new Tooltip();
		toolTipButton.setText("Buy room for 1000 cash");
		btnRoom1.setTooltip(toolTipButton);
		btnRoom2.setTooltip(toolTipButton);
		btnRoom3.setTooltip(toolTipButton);
		btnRoom4.setTooltip(toolTipButton);
		toolTipButton.setStyle("-fx-background-color:green;");

	}

	private void buyRoom(int b) {
		if (b == 1) {
			for (int i = 0; i < 4; i++) {
				createRoom1();
			}
			createSpecRoom1();
		}
		if (b == 2) {
			for (int i = 0; i < 4; i++) {
				createRoom2();
			}
			createSpecRoom2();
		}
		if (b == 3) {
			for (int i = 0; i < 4; i++) {
				createRoom3();
			}
			createSpecRoom3();
		}

		if (b == 4) {
			for (int i = 0; i < 4; i++) {
				createRoom4();

			}
			createSpecRoom4();
		}
	}

	private void createRoom1() {
		// Zaczytujemy widok
		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/game/employee.fxml"));
		// Sami robimy kontroler
		Employee controller1 = new Employee();
		// Podpinamy kontroler do widoku
		loader1.setController(controller1);
		Pane pane1 = null;

		try {
			// £adujemy widok
			pane1 = loader1.load();
			BackgroundImage myBI = new BackgroundImage(new Image("res/employeeRoom1.png", 200, 150, false, true),
					BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
					BackgroundSize.DEFAULT);
			// Tooltip tooltip1 = new Tooltip("First room");
			// Tooltip.install(listOfElements1, tooltip1);
			listOfElements1.setBackground(new Background(myBI));
			// vBox1.getChildren().add(imgView);
			listOfElements1.getChildren().add(pane1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createRoom2() {
		// Zaczytujemy widok
		FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/game/employee.fxml"));
		// Sami robimy kontroler
		Employee controller2 = new Employee();
		// Podpinamy kontroler do widoku
		loader2.setController(controller2);
		Pane pane2 = null;

		try {
			// £adujemy widok
			pane2 = loader2.load();
			BackgroundImage myBI = new BackgroundImage(new Image("res/employeeRoom1.png", 200, 150, false, true),
					BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
					BackgroundSize.DEFAULT);
			// Tooltip tooltip1 = new Tooltip("First room");
			// Tooltip.install(listOfElements1, tooltip1);
			listOfElements2.setBackground(new Background(myBI));
			// vBox1.getChildren().add(imgView);
			listOfElements2.getChildren().add(pane2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createRoom3() {
		// Zaczytujemy widok
		FXMLLoader loader3 = new FXMLLoader(getClass().getResource("/game/employee.fxml"));
		// Sami robimy kontroler
		Employee controller3 = new Employee();
		// Podpinamy kontroler do widoku
		loader3.setController(controller3);
		Pane pane3 = null;

		try {
			// £adujemy widok
			pane3 = loader3.load();
			BackgroundImage myBI = new BackgroundImage(new Image("res/employeeRoom1.png", 200, 150, false, true),
					BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
					BackgroundSize.DEFAULT);
			// Tooltip tooltip1 = new Tooltip("First room");
			// Tooltip.install(listOfElements1, tooltip1);
			listOfElements3.setBackground(new Background(myBI));
			// vBox1.getChildren().add(imgView);
			listOfElements3.getChildren().add(pane3);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createRoom4() {
		// Zaczytujemy widok
		FXMLLoader loader4 = new FXMLLoader(getClass().getResource("/game/employee.fxml"));
		// Sami robimy kontroler
		Employee controller4 = new Employee();
		// Podpinamy kontroler do widoku
		loader4.setController(controller4);
		Pane pane4 = null;

		try {
			// £adujemy widok
			pane4 = loader4.load();
			BackgroundImage myBI = new BackgroundImage(new Image("res/employeeRoom1.png", 200, 150, false, true),
					BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
					BackgroundSize.DEFAULT);
			// Tooltip tooltip1 = new Tooltip("First room");
			// Tooltip.install(listOfElements1, tooltip1);
			listOfElements4.setBackground(new Background(myBI));
			// vBox1.getChildren().add(imgView);
			listOfElements4.getChildren().add(pane4);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Tu tworzymy jedn¹ kontrolkê
	private void createSpecRoom1() {
		// Zaczytujemy widok
		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/game/employee2.fxml"));
		// Sami robimy kontroler
		Employee2 controller1 = new Employee2();
		// Podpinamy kontroler do widoku
		loader1.setController(controller1);
		Pane pane1 = null;
		try {
			// £adujemy widok
			pane1 = loader1.load();
			// Dodajemy widok do kontrolki
			listOfElements1.getChildren().add(pane1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createSpecRoom2() {
		// Zaczytujemy widok
		FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/game/employee2.fxml"));
		// Sami robimy kontroler
		Employee2 controller2 = new Employee2();
		// Podpinamy kontroler do widoku
		loader2.setController(controller2);
		Pane pane2 = null;
		try {
			// £adujemy widok
			pane2 = loader2.load();
			// Dodajemy widok do kontrolki
			listOfElements2.getChildren().add(pane2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createSpecRoom3() {
		// Zaczytujemy widok
		FXMLLoader loader3 = new FXMLLoader(getClass().getResource("/game/employee2.fxml"));
		// Sami robimy kontroler
		Employee2 controller3 = new Employee2();
		// Podpinamy kontroler do widoku
		loader3.setController(controller3);
		Pane pane3 = null;
		try {
			// £adujemy widok
			pane3 = loader3.load();
			// Dodajemy widok do kontrolki
			listOfElements3.getChildren().add(pane3);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createSpecRoom4() {
		// Zaczytujemy widok
		FXMLLoader loader4 = new FXMLLoader(getClass().getResource("/game/employee2.fxml"));
		// Sami robimy kontroler
		Employee2 controller4 = new Employee2();
		// Podpinamy kontroler do widoku
		loader4.setController(controller4);
		Pane pane4 = null;
		try {
			// £adujemy widok
			pane4 = loader4.load();
			// Dodajemy widok do kontrolki
			listOfElements4.getChildren().add(pane4);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Image setImage(Image image) {
		image = image;
		return image;
	}

	public static Image getImage() {
		return image;
	}

	public HBox getListOfElements1() {
		return listOfElements1;
	}

	public void setListOfElements1(HBox listOfElements1) {
		this.listOfElements1 = listOfElements1;
	}

	

	public Parent getSecroot() {
		return secroot;
	}

	public static void setSecroot(Parent secroot) {
		secroot = secroot;
	}

	

	
	

}
/*
 * private void onButtonClick() { //
 * mainText.setStyle("fx-fill: rgb 12,34,34); ");
 * 
 * 
 * Random rand = new Random(); int number = rand.nextInt(2)+1;
 * 
 * if(number ==1){ mainText.getStyleClass().clear();
 * mainText.getStyleClass().add("kolor1"); } else if(number ==2){
 * mainText.getStyleClass().clear(); mainText.getStyleClass().add("kolor2"); }
 * // mainText.getStyleClass().add("violet"); mainText.setText(flag ? "World!" :
 * "Hello"); System.out.println(number); flag = !flag ; }
 */
