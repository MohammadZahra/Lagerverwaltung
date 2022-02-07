package torvalds.WarehouseManager.javafx.controllers;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import torvalds.WarehouseManager.javafx.consts.PathConsts;
import torvalds.WarehouseManager.javafx.factories.AlertFactory;

/**
 * @author Al Abood, Saramijou, Al Mostafa
 *
 */
public class ChooseWarehouseController {
	private static final String CONFIRMATION_QUESTION = "Are you sure you want quit Application? "; 
	private static final String CHOOSE_WAREHOUSE_PATH = "choose-warehouse-scene.fxml"; 
	private static final String NUMBER_ERROR = "Enter the Number using digits only. "; 
    @FXML
    private TextField txtWarehouseNumber;
	
    private boolean createNewClicked;
	private boolean openClicked;
	private boolean closed;
	
	private int warehouseNumber;
	
	/**
	 * Main Constructor creates and shows the stage of this class
	 * @throws IOException
	 */
	public ChooseWarehouseController() throws IOException {
		Stage stage = new Stage();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(CHOOSE_WAREHOUSE_PATH));
    	loader.setController(this);
    	Parent root = loader.load();
    	stage.setScene(new Scene(root));
    	stage.setOnCloseRequest(e -> {
    		e.consume();
    		closeIfUserChoosesTo();
    	});
    	stage.setResizable(false);
    	stage.setTitle("Open Warehouse");
    	stage.getIcons().add(new Image(getClass().getResource(PathConsts.WAREHOUSE_MANAGER_ICON).toExternalForm()));
    	stage.showAndWait();
	}
    
    @FXML
    void btnCancelClicked() {
    	closeIfUserChoosesTo();
    }

    /**
     * Called when the user has clicked the close button.
     * the stage will be closed if the user chose to
     */
    private void closeIfUserChoosesTo() {
    	Optional<ButtonType> choice = AlertFactory.createConfirmationAlert(CONFIRMATION_QUESTION);
    	if(choice.get() == ButtonType.OK) {
    		closeStage();
    		closed = true;
    	} 
	}

	private void closeStage() {
    	Stage stage = (Stage) txtWarehouseNumber.getScene().getWindow();
		stage.close();
	}

	@FXML
    void btnCreateNewWarehouseClicked() {
		closeStage();
		createNewClicked = true;
    }
	/**
     * Will be called when the user wants to open a registered warehouse
     * 
     */
	@FXML
    void btnOpenWarehouseClicked() {
    	try {
			String input = txtWarehouseNumber.getText();
			this.warehouseNumber = Integer.parseInt(input);
			closeStage();
			openClicked = true;
		} catch (NumberFormatException e) {
			AlertFactory.createErrorAlert(NUMBER_ERROR);
		} catch (Exception e) {
			AlertFactory.createErrorAlert(e.getMessage());
		}
    }
	/**
	 * check whether the user has clicked the button close 
	 * 
	 */
	public boolean isClosed() {
		return closed;
	}
	
	/**
	 * check whether the user has clicked the button open warehouse 
	 * 
	 */
	public boolean isOpenClicked() {
		return openClicked;
	}
	
	/**
	 * check whether the user has clicked the button create new warehouse 
	 * 
	 */
	public boolean isCreateNewClicked() {
		return createNewClicked;
	}
	
	/**
	 * return the warehouse number entered by the user
	 * 
	 */
	public int getWarehouseNumber() {
		return warehouseNumber;
	}
}
