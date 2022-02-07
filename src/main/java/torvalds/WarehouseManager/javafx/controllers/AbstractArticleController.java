package torvalds.WarehouseManager.javafx.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import torvalds.WarehouseManager.facades.WarehouseManager;
import torvalds.WarehouseManager.factories.LogicFactory;
import torvalds.WarehouseManager.javafx.consts.PathConsts;
import torvalds.WarehouseManager.javafx.factories.AlertFactory;
import torvalds.WarehouseManager.logic.interfaces.Article;
import torvalds.WarehouseManager.logic.interfaces.Category;
import torvalds.WarehouseManager.utils.ExceptionHandlingUtil;

/**
 * 
 * @author Al Mostafa
 */
public abstract class AbstractArticleController {

	private static final String ARTICLE_SCENE_PATH = "article-scene.fxml";
	
	@FXML
	private TextField txtArticleNumber;
	@FXML
	private TextField txtCurrentStock;
	@FXML
	private TextField txtDescription;
	@FXML
	private TextField txtGrossPrice;
	@FXML
	private TextField txtCategoryName;
	
	private boolean isClosed;
	private Article article;
	private Category category;

	public AbstractArticleController() throws IOException {
		isClosed = true;
		Stage stage = new Stage();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(ARTICLE_SCENE_PATH));
    	loader.setController(this);
    	Parent root = loader.load();
    	stage.setScene(new Scene(root));
    	stage.setTitle(getTitle());
    	stage.setResizable(false);
    	stage.getIcons().add(new Image(getClass().getResource(PathConsts.WAREHOUSE_MANAGER_ICON).toExternalForm()));
    	txtArticleNumber.setDisable(!isArticleNumberEditable());
	}
	
	public void showAndWait() {
		Stage stage = (Stage) txtArticleNumber.getScene().getWindow();
		stage.showAndWait();
	}

	@FXML
	private void btnOkClicked() {
		try {
			isClosed = false;
			if(article == null) {
				article = LogicFactory.createArticle();
				int articleNumber = Integer.parseInt(txtArticleNumber.getText());
				article.setArticleNumber(articleNumber);
			}
			int currentStock = Integer.parseInt(txtCurrentStock.getText());
			article.setCurrentStock(currentStock);
			String description = txtDescription.getText();
			article.setDescription(description);
			double grossPrice = Double.parseDouble(txtGrossPrice.getText());
			article.setGrossPrice(grossPrice);
			article.setCategory(category);
			closeStage();
		} catch (NumberFormatException e) {
			AlertFactory.createErrorAlert("Enter the Number using digits");
		} catch (IllegalArgumentException e) {
			AlertFactory.createErrorAlert(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			AlertFactory.createErrorAlert("Unhandled Exception: " + e.getMessage());
		}
	}

	@FXML
	private void btnCancelClicked() {
		isClosed = true;
		closeStage();
	}
	
	@FXML
	private void txtCategoryNameClicked() {
		try {
			SelectCategoryController controller = new SelectCategoryController(WarehouseManager.getInstance().getCurrentWarehouse().getCategoriesCollection());
			Category category = controller.getSelectedCategory();
			if(category == null) {
				txtCategoryName.setText("");
			} else {
				txtCategoryName.setText(category.getCategoryName());
			}
			this.category = category;
		} catch (Exception e) {
			AlertFactory.createErrorAlert(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void closeStage() {
		Stage stage = (Stage) txtArticleNumber.getScene().getWindow();
		stage.close();
	}

	/**
	 * Returns whether the Window was closed by the user or not.
	 * 
	 */
	public boolean isClosed() {
		return isClosed;
	}

	/**
	 * Sets the values of the given article in the textfields
	 * Should only be called once at the start.
	 * 
	 */
	public void setArticle(Article article) {
		ExceptionHandlingUtil.check(article != null, "The given article must not be null");
		this.article = article;
		txtArticleNumber.setText("" + article.getArticleNumber());
		txtCurrentStock.setText("" + article.getCurrentStock());
		txtDescription.setText(article.getDescription());
		txtGrossPrice.setText("" + article.getGrossPrice());
		category = article.getCategory();
		if(category != null) {
			txtCategoryName.setText(category.getCategoryName());
		}
	}
	
	/**
	 * Returns the resulting article
	 * Should be called once at the end.
	 * 
	 */
	public Article getArticle() {
		try {
			int articleNumber = Integer.parseInt(txtArticleNumber.getText());
			int currentStock = Integer.parseInt(txtCurrentStock.getText());
			String description = txtDescription.getText();
			double grossPrice = Double.parseDouble(txtGrossPrice.getText());
			int warehouseNumber = article.getWarehouseNumber();
			article = LogicFactory.createArticle(articleNumber, description, currentStock, grossPrice, warehouseNumber);
		} catch (NumberFormatException e) {
			AlertFactory.createErrorAlert("Enter Numbers with valid digits (and puntions)");
		}
		return article;
	}
	
	/**
	 * Returns the Title of this Window
	 * 
	 */
	protected abstract String getTitle();
	
	/**
	 * Returns whether the articleNumber is writable
	 * 
	 */
	protected abstract boolean isArticleNumberEditable();
}
