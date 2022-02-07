package torvalds.WarehouseManager.javafx.factories;

import java.util.Optional;

import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import torvalds.WarehouseManager.javafx.consts.PathConsts;

/**
 * A Factory for Input-Dialogs.
 * 
 * @author Al Mostafa
 *
 */
public class InputDialogFactory {
	public static Optional<String> createOptionalStringDialog(String message) {
		TextInputDialog dialog = createTextInputDialog();
		dialog.setContentText(message);
		return dialog.showAndWait();
	}
	
	private static TextInputDialog createTextInputDialog() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setGraphic(null);
		dialog.setHeaderText(null);
		dialog.setTitle("Input Dialog");
		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(
		    new Image(InputDialogFactory.class.getResource(PathConsts.WAREHOUSE_MANAGER_ICON).toString()));
		return dialog;
	}

	public static Optional<Integer> createOptionalIntegerDialog(String message) {
		Optional<String> input = createOptionalStringDialog(message);
		Integer value = null;
		Optional<Integer> optional = Optional.empty();
		if(input.isPresent()) {
			value = Integer.parseInt(input.get());
			optional = Optional.of(value);
		}
		return optional;
	}
}
