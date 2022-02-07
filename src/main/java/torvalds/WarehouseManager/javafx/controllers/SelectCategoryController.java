package torvalds.WarehouseManager.javafx.controllers;

import java.io.IOException;
import java.util.Collection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Callback;
import torvalds.WarehouseManager.javafx.consts.PathConsts;
import torvalds.WarehouseManager.logic.interfaces.Category;

/**
 * 
 * @author Taraq Saeed, Al Mostafa
 *
 */
public class SelectCategoryController {
	private Category category;
	private boolean isClosed;
	
	@FXML private ComboBox<Category> categoriesComboBox;

	private static final String CREATE_CATEGORY_PATH = "select-category-scene.fxml";

	public SelectCategoryController(Collection<Category> categories) throws IOException {
	    	Stage stage = new Stage();
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource(CREATE_CATEGORY_PATH));
	    	loader.setController(this);
	    	Parent root = loader.load();
	    	stage.setScene(new Scene(root));
	    	stage.setTitle("Select a Category");
	    	stage.setResizable(false);
	    	stage.getIcons().add(new Image(getClass().getResource(PathConsts.WAREHOUSE_MANAGER_ICON).toExternalForm()));
	    	setCategoriesMenuButton(categories);
	    	isClosed = true;
	    	stage.showAndWait();
    }

	private void setCategoriesMenuButton(Collection<Category> categories) {
		ObservableList<Category> list = FXCollections.observableArrayList(categories);
		categoriesComboBox.setItems(list);
		Callback<ListView<Category>, ListCell<Category>> callback = new Callback<>() {
			@Override
			public ListCell<Category> call(ListView<Category> param) {
				return new ListCell<Category>() {
					@Override
		            protected void updateItem(Category item, boolean empty) {
						super.updateItem(item, empty);
						if (item == null || empty) {
		                    setGraphic(null);
		                } else {
		                    setText(item.getCategoryName());
		                }
					}
				};
			}
		};
		categoriesComboBox.setButtonCell(callback.call(null));
		categoriesComboBox.setCellFactory(callback);
	}

	@FXML
	private void btnOKClicked() {
		this.category = categoriesComboBox.getSelectionModel().getSelectedItem();
		this.isClosed = false;
		closeStage();
	}

	@FXML
	private void btnCancelClicked() {
		closeStage();
	}

	private void closeStage() {
		Stage stage = (Stage) categoriesComboBox.getScene().getWindow();
		stage.close();
	}

	/**
	 * Returns the Category selected by the user.
	 * 
	 */
	public Category getSelectedCategory() {
		return category;
	}

	/**
	 * Returns whether this stage was closed by the user or not.
	 * 
	 */
	public boolean isClosed() {
		return isClosed;
	}

}
