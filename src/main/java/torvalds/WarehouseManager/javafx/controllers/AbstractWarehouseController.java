package torvalds.WarehouseManager.javafx.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import torvalds.WarehouseManager.factories.LogicFactory;
import torvalds.WarehouseManager.javafx.consts.PathConsts;
import torvalds.WarehouseManager.javafx.factories.AlertFactory;
import torvalds.WarehouseManager.logic.interfaces.Address;
import torvalds.WarehouseManager.logic.interfaces.Warehouse;

/**
 * 
 * @author Al Mostafa, Al Abood, Saramijou
 */
public abstract class AbstractWarehouseController {
	private static final String ALL_FIELD_FILLED = "Fill in all Fields to continue";
	private static final String WAREHOUSE_SCENE_PATH = "warehouse-scene.fxml";
	private static final String NUMBER_ERROR = "Enter the Number using digits only. ";
	@FXML
	private TextField txtWarehouseNumber;

	@FXML
	private TextField txtStreet;

	@FXML
	private TextField txtNumber;

	@FXML
	private TextField txtCity;

	@FXML
	private TextField txtPostcode;

	@FXML
	private TextField txtCountry;

	@FXML
	private Button btnCreate;

	@FXML
	private Button btnCancel;

	private Warehouse warehouse;
	private boolean isClosed;
	
	public AbstractWarehouseController() throws IOException {
    	Stage stage = new Stage();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(WAREHOUSE_SCENE_PATH));
    	loader.setController(this);
    	Parent root = loader.load();
    	stage.setScene(new Scene(root));
    	stage.setTitle(getTitle());
    	stage.setResizable(false);
    	isClosed = true;
    	stage.getIcons().add(new Image(getClass().getResource(PathConsts.WAREHOUSE_MANAGER_ICON).toExternalForm()));
    	txtWarehouseNumber.setDisable(!getWarehouseNumberEditable());
    }

	protected abstract boolean getWarehouseNumberEditable();

	protected abstract String getTitle();

	@FXML
	private void btnCancelClicked() {
		closeStage();
	}

	/**
	 * Will be called when the user wants to add a new warehouse. All fields must be
	 * filled
	 */
	@FXML
	private void btnOKClicked() {
		String strWarehouseNumber = txtWarehouseNumber.getText();
		String street = txtStreet.getText();
		String number = txtNumber.getText();
		String city = txtCity.getText();
		String postcode = txtPostcode.getText();
		String country = txtCountry.getText();

		try {
			if (!areAllTextFieldsFilled()) {
				throw new IllegalStateException(ALL_FIELD_FILLED);
			}
			if(warehouse == null) {
				int warehouseNumber = Integer.parseInt(strWarehouseNumber);
				Address address = LogicFactory.createAddress(street, number, city, postcode, country);
				this.warehouse = LogicFactory.createWarehouse(warehouseNumber, address);
			} else {
				Address address = warehouse.getAddress();
				address.setStreet(street);
				address.setNumber(number);
				address.setCity(city);
				address.setPostcode(postcode);
				address.setCountry(country);
			}
			isClosed = false;
			closeStage();
		} catch (NumberFormatException e) {
			AlertFactory.createErrorAlert(NUMBER_ERROR);
		} catch (Exception e) {
			AlertFactory.createErrorAlert(e.getMessage());
		}
	}

	/**
	 * Returns: did the user close the stage?
	 * 
	 */
	public boolean isClosed() {
		return isClosed;
	}

	/**
	 * check wether the user has filled all the fields
	 * 
	 * @return the resault
	 */
	private boolean areAllTextFieldsFilled() {
		return !(txtStreet.getText().isBlank() || txtCity.getText().isBlank() || txtNumber.getText().isBlank()
				|| txtCountry.getText().isBlank() || txtWarehouseNumber.getText().isBlank()
				|| txtPostcode.getText().isBlank());
	}

	public Warehouse showAndWait() {
		Stage stage = (Stage) txtWarehouseNumber.getScene().getWindow();
		stage.showAndWait();
		return warehouse;
	}
	
	private void closeStage() {
		Stage stage = (Stage) txtWarehouseNumber.getScene().getWindow();
		stage.close();
	}

	/**
	 * Sets the values of the textfields to the given warehouse.
	 * @throws NullPointerException if warehouse null
	 */
	protected void setWarehouse(Warehouse warehouse) {
		txtWarehouseNumber.setText("" + warehouse.getWarehouseNumber());
		Address address = warehouse.getAddress();
		txtStreet.setText(address.getStreet());
		txtNumber.setText(address.getNumber());
		txtCity.setText(address.getCity());
		txtPostcode.setText(address.getPostcode());
		txtCountry.setText(address.getCountry());
		this.warehouse = warehouse;
	}
}
