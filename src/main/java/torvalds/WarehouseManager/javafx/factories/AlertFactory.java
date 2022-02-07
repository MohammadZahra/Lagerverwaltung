package torvalds.WarehouseManager.javafx.factories;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import torvalds.WarehouseManager.javafx.consts.PathConsts;

/**
 * A Factory for alerts.
 * 
 * @author Al Mostafa
 *
 */
public class AlertFactory {
	public static Optional<ButtonType> createErrorAlert(String message) {
		Alert alert = createAlert(AlertType.ERROR, null, message);
    	return alert.showAndWait();
	}
	
	public static Optional<ButtonType> createConfirmationAlert(String message) {
		Alert alert = createAlert(AlertType.CONFIRMATION, null, message);
		return alert.showAndWait();
	}
	
	public static Optional<ButtonType> createInformationAlert(String message) {
		Alert alert = createAlert(AlertType.INFORMATION, null, message);
		return alert.showAndWait();
	}
	
	private static Alert createAlert(AlertType type, String header, String content) {
		Alert alert = new Alert(type);
		alert.setHeaderText(header);
		alert.setContentText(content);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(
		    new Image(AlertFactory.class.getResource(PathConsts.WAREHOUSE_MANAGER_ICON).toString()));
		return alert;
	}
}
