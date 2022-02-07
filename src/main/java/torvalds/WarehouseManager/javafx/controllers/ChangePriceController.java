package torvalds.WarehouseManager.javafx.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import torvalds.WarehouseManager.javafx.consts.PathConsts;
import torvalds.WarehouseManager.javafx.factories.AlertFactory;
/**
 * @author Al Abood, Taraq Saeed, Al Mostafa
 *
 */
public class ChangePriceController {

	private static final String CHOOSE_CHANGE_PRICE_PATH = "change-price-scene.fxml"; 
	private static final String NUMBER_ERROR = "Enter the Number using digits and dot only."; 

  	@FXML
    private Button btnCancel;
    @FXML
    private TextField txtPercentage;
    @FXML
    private Label txtLabel;
    @FXML
    private RadioButton btnIncrease;
    @FXML
    private RadioButton btnDecrease;
	private boolean isClosed;
	private double percentage;
	
	public ChangePriceController() throws IOException {
		Stage stage = new Stage();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(CHOOSE_CHANGE_PRICE_PATH));
    	loader.setController(this);
    	Parent root = loader.load();
    	stage.setScene(new Scene(root));
    	stage.setTitle("Change Price");
    	stage.setResizable(false);
    	stage.getIcons().add(new Image(getClass().getResource(PathConsts.WAREHOUSE_MANAGER_ICON).toExternalForm()));
    	ToggleGroup group = new ToggleGroup();
    	btnIncrease.setToggleGroup(group);
    	btnDecrease.setToggleGroup(group);
    	isClosed = true;
    	stage.showAndWait();
	}

	@FXML
    void btnCancelClicked() {
		closeStage();

    }

    @FXML
    void btnOKClicked() { 
		try {
			String input = txtPercentage.getText();
			this.percentage = Math.abs(Double.parseDouble(input));
			if(btnDecrease.isSelected()) {
				percentage = -percentage;
			}
			this.isClosed = false;
			closeStage();
		} catch (NumberFormatException e) {
			AlertFactory.createErrorAlert(NUMBER_ERROR);
		} 
    }
    
    private void closeStage() {
    	Stage stage = (Stage) txtPercentage.getScene().getWindow();
		stage.close();
	}
    
    /**
     * Returns the percentage read from the user
     * 
     */
    public double getPercentage() {
    	return percentage;
    }

    /**
     * Returns whether this stage was closed by the user or not
     * 
     */
	public boolean isClosed() {
		return isClosed;
	}
	
}
