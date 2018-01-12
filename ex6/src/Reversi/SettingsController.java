package Reversi;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SettingsController implements Initializable {

	@FXML
	private ColorPicker Player1Color;

	@FXML
	private ColorPicker Player2Color;

	@FXML
	private ChoiceBox<Integer> rowBox;

	@FXML
	private ChoiceBox<Integer> colBox;

	@FXML
	private Button mainMenuButton;

	@FXML
	private ChoiceBox<String> firstPlayerPick;
	
	@FXML
	protected void mainMenu() {
		try {
			ParseSettingsFile parser = new ParseSettingsFile();
			parser.writeSettings(rowBox.getValue(), colBox.getValue(), Player1Color.getValue().toString(),
					Player2Color.getValue().toString(), firstPlayerPick.getValue());

			Stage primaryStage = (Stage) mainMenuButton.getScene().getWindow();

			GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("MainMenuFXML.fxml"));

			Scene scene = new Scene(root, 400, 350);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Reversi");
			primaryStage.setScene(scene);

			primaryStage.show();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		for (int j = 4; j < 21; j++) {
			rowBox.getItems().add(j);
			colBox.getItems().add(j);
		}
		
		firstPlayerPick.getItems().add("Player 1");
		firstPlayerPick.getItems().add("Player 2");
		parseSettingsFile();
	}

	private void parseSettingsFile() {
		ParseSettingsFile parser = new ParseSettingsFile();
		parser.parseSettingsFile();
		rowBox.setValue(parser.getRowBox());
		colBox.setValue(parser.getColBox());
		Player1Color.setValue(Color.web(parser.getPlayer1Color()));
		Player2Color.setValue(Color.web(parser.getPlayer2Color()));
		firstPlayerPick.setValue(parser.getFirstPlayer());
	}

}
