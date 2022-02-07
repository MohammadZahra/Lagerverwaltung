package torvalds.WarehouseManager.javafx.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import torvalds.WarehouseManager.javafx.consts.PathConsts;
import torvalds.WarehouseManager.javafx.factories.AlertFactory;

/**
 * @author Al Abood, Taraq Saeed, Al Mostafa
 *
 */
public class SendArticleController {

	private static final String SEND_ARTICLE_PATH = "send-article-scene.fxml";
	private static final String NUMBER_ERROR = "Enter the Number using digits and dot only.";
	
	@FXML
	private TextField txtWarehouseNumber;
	@FXML
	private TextField txtAmount;

	private boolean isClosed;
	
	private int warehouseNumber; 
	private int amount; 
  
	public SendArticleController() throws IOException {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource(SEND_ARTICLE_PATH));
		loader.setController(this);
		Parent root = loader.load();
		stage.setScene(new Scene(root));
		stage.setTitle("Send Article");
		stage.setResizable(false);
		stage.getIcons().add(new Image(getClass().getResource(PathConsts.WAREHOUSE_MANAGER_ICON).toExternalForm()));
		isClosed = true;
		stage.showAndWait();
	}

	private void closeStage() {
		Stage stage = (Stage) txtWarehouseNumber.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void btnCancelClicked() {
		closeStage();
	}

	@FXML
	private void btnSendClicked() {
		try {
			String inputWarehouseNumber = txtWarehouseNumber.getText();
			this.warehouseNumber = Integer.parseInt(inputWarehouseNumber);
			String inputAmount = txtAmount.getText();
			this.amount = Integer.parseInt(inputAmount);
			this.isClosed = false;
			closeStage();
		} catch (NumberFormatException e) {
			AlertFactory.createErrorAlert(NUMBER_ERROR);
		}
	}
	
	/**
	 * Returns the warehouseNumber of the receiving-Warehouse
	 * 
	 */
	public int getWarehouseNumber() {
		return warehouseNumber;
	}

	/**
	 * Returns the amount of the article that should be sent
	 * 
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Returns whether this stage was closed by the user or not.
	 * 
	 */
	public boolean isClosed() {
		return isClosed;
	}
}
