package torvalds.WarehouseManager.javafx.controllers;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import torvalds.WarehouseManager.facades.WarehouseManager;
import torvalds.WarehouseManager.factories.LogicFactory;
import torvalds.WarehouseManager.javafx.consts.PathConsts;
import torvalds.WarehouseManager.javafx.factories.AlertFactory;
import torvalds.WarehouseManager.javafx.factories.InputDialogFactory;
import torvalds.WarehouseManager.logic.interfaces.Address;
import torvalds.WarehouseManager.logic.interfaces.Article;
import torvalds.WarehouseManager.logic.interfaces.Category;
import torvalds.WarehouseManager.logic.interfaces.Warehouse;
import torvalds.WarehouseManager.utils.SearchingUtil;

/**
 * @author Al Abood, Saramijou, Al Mostafa, Taraq Saeed
 *
 */
public class WarehouseViewController {
	private static final String WAREHOUSE_VIEW_PATH = "warehouse-view-scene.fxml"; 
	
	@FXML private AnchorPane pane;
	@FXML private TableView<Article> articleTableView;
	@FXML private TableColumn<Article, Integer> columnArticleNumber;
	@FXML private TableColumn<Article, String> columnDescription;
	@FXML private TableColumn<Article, Integer> columnCurrentStock;
	@FXML private TableColumn<Article, Double> columnGrossPrice;
	@FXML private TableColumn<Article, Double> columnTotalGrossPrice;
	@FXML private TextField txtSearch;
	
	@FXML private ImageView addArticleImageView;
    @FXML private ImageView removeArticleImageView;
    @FXML private ImageView editArticleImageView;
    @FXML private ImageView addStockImageView;
    @FXML private ImageView deleteStockImageView;
    @FXML private ImageView searchImageView;
    @FXML private ImageView sendArticlesImageView;
	
	private final WarehouseManager warehouseManager;
	
	/**
     * Will be called when the user opened/created new warehouse 
     * 
     */
	public WarehouseViewController() throws IOException {
		this.warehouseManager = WarehouseManager.getInstance();
		Stage stage = new Stage();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(WAREHOUSE_VIEW_PATH));
    	loader.setController(this);
    	Parent root = loader.load();
    	stage.setScene(new Scene(root));
    	stage.setTitle(generateTitle());
    	stage.getIcons().add(new Image(getClass().getResource(PathConsts.WAREHOUSE_MANAGER_ICON).toExternalForm()));
    	initImageViews();
    	initTable();
    	initColumns();
    	stage.showAndWait();
	}
	
	/**
	 * Sets the icons of the scenes-buttons
	 * 
	 */
	private void initImageViews() {
		try {
			addArticleImageView.setImage(new Image(getClass().getResource(PathConsts.ADD_ARTICLE_ICON).toExternalForm()));
		    removeArticleImageView.setImage(new Image(getClass().getResource(PathConsts.DELETE_ARTICLE_ICON).toExternalForm()));
		    editArticleImageView.setImage(new Image(getClass().getResource(PathConsts.EDIT_ARTICLE_ICON).toExternalForm()));
		    addStockImageView.setImage(new Image(getClass().getResource(PathConsts.ADD_ARTICLE_STOCK_ICON).toExternalForm()));
		    deleteStockImageView.setImage(new Image(getClass().getResource(PathConsts.REMOVE_ARTICLE_STOCK_ICON).toExternalForm()));
		    searchImageView.setImage(new Image(getClass().getResource(PathConsts.FIND_ICON).toExternalForm()));
		    sendArticlesImageView.setImage(new Image(getClass().getResource(PathConsts.SEND_ARTICLE_ICON).toExternalForm()));
		} catch (RuntimeException e) {
			throw new RuntimeException("Failed to load button-icons for warehouse-view.", e);
		}
	}

	private void initTable() {
		articleTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		updateTable();
	}

	private String generateTitle() {
		String title = "Warehouse: #" + warehouseManager.getCurrentWarehouse().getWarehouseNumber();
		title += "; Address: " + warehouseManager.getCurrentWarehouse().getAddress();
		return title;
	}
	
	private void initColumns() {
		columnArticleNumber.setCellValueFactory(
				new PropertyValueFactory<>("articleNumber"));
		columnDescription.setCellValueFactory(
				new PropertyValueFactory<>("description"));
		columnCurrentStock.setCellValueFactory(
				new PropertyValueFactory<>("currentStock"));
		columnGrossPrice.setCellValueFactory(
				new PropertyValueFactory<>("grossPrice"));
		columnTotalGrossPrice.setCellValueFactory((a) -> {
			ObservableValue<Double> value = new ObservableValueBase<>() {
				@Override
				public Double getValue() {
					return a.getValue().getTotalGrossPrice();
				}
			};
			return value;
		});
	}
	
	private void updateTable() {
		Warehouse warehouse = warehouseManager.getCurrentWarehouse();
		updateTable(warehouse.getArticlesCollection());
	}
	
	private void updateTable(Collection<Article> articles) {
		List<TableColumn<Article, ?>> sortColumns = new LinkedList<>(articleTableView.getSortOrder());
		
		ObservableList<Article> list = FXCollections.observableArrayList();
		for(Article article : articles) {
			list.add(article);
		}
		articleTableView.setItems(list);
		
		articleTableView.getSortOrder().addAll(sortColumns);
		articleTableView.refresh();
	}

	@FXML
	private void closeStage() {
		Stage stage = (Stage) pane.getScene().getWindow();
		stage.close();
    }

    @FXML
    private void deleteWarehouse() {
    	Optional<ButtonType> optional = AlertFactory.createConfirmationAlert(
    			"Are you sure you want to permanently delete warehouse: #" 
    			+ warehouseManager.getCurrentWarehouse().getWarehouseNumber());
		if(optional.isPresent() && optional.get() == ButtonType.OK) {
			warehouseManager.removeCurrentWarehouse();
	    	closeStage();
		}
    }
    
    @FXML
    private void btnAddArticleClicked() throws IOException {
    	ArticleReaderStage articleReaderStage = new ArticleReaderStage();
    	articleReaderStage.showAndWait();
    	if(!articleReaderStage.isClosed()) {
    		Article article = articleReaderStage.getArticle();
    		warehouseManager.insertArticleToCurrentWarehouse(article);
    		updateTable();
    	}
    }
    
    @FXML
    private void btnRemoveArticleClicked() {
    	try {
    		Article article = getSelectedArticle();
        	Optional<ButtonType> optional = AlertFactory.createConfirmationAlert(
    	    			"Are you sure you want to permanently delete Article: #" 
    	    			+ article.getArticleNumber());
        	if(optional.isPresent() && optional.get() == ButtonType.OK) {
        		warehouseManager.deleteArticleFromCurrentWarehouse(article);
            	updateTable();
        	}
		} catch (IllegalStateException e) {
			AlertFactory.createErrorAlert(e.getMessage());
		} catch (Exception e) {
			AlertFactory.createErrorAlert(e.getMessage());
			e.printStackTrace();
		}
    }
    
    @FXML
    private void btnEditArticleClicked() {
    	try {
			ArticleEditorStage articleEditorStage = new ArticleEditorStage(getSelectedArticle());
			articleEditorStage.showAndWait();
			Article updatedArticle = articleEditorStage.getArticle();
			warehouseManager.updateArticleInCurrentWarehouse(updatedArticle);
			updateTable();
    	} catch (IllegalStateException e) {
			AlertFactory.createErrorAlert(e.getMessage());
		} catch (Exception e) {
			AlertFactory.createErrorAlert(e.getMessage());
			e.printStackTrace();
		}
    }
    
    @FXML
    private void btnSearchClicked() {
    	try {
    		final String searchInput = txtSearch.getText();
        	if(searchInput.isBlank()) {
        		txtSearch.setText("");
        		updateTable();
        	} else {
        		final Collection<Article> articles = warehouseManager.getCurrentWarehouse().getArticlesCollection();
            	Collection<Article> result = SearchingUtil.quickSearchForArticlesWith(articles, searchInput);
            	if(result.isEmpty()) {
            		result = SearchingUtil.deepSearchForArticlesWith(articles, searchInput, 1000);
            		if(result.isEmpty()) {
                		AlertFactory.createInformationAlert("No Matchings found !");
                	} else {
                		updateTable(result);
                	}
            	} else {
            		updateTable(result);
            	}
        	}
		} catch (Exception e) {
			AlertFactory.createErrorAlert(e.getMessage());
			e.printStackTrace();
		}
    }
    
    @FXML
    private void btnAddStockClicked() {
    	try {
    		Article article = getSelectedArticle();
    		Optional<Integer> optional = InputDialogFactory.createOptionalIntegerDialog("Amount:");
    		if(optional.isPresent()) {
    			article.addStock(optional.get());
    			warehouseManager.updateArticleInCurrentWarehouse(article);
    			updateTable();
    		}
    	} catch (NumberFormatException e) {
    		AlertFactory.createErrorAlert("Enter the amount using digits only !");
		} catch (IllegalStateException e) {
			AlertFactory.createErrorAlert(e.getMessage());
		} catch (Exception e) {
			AlertFactory.createErrorAlert(e.getMessage());
			e.printStackTrace();
		}
    }
    
    @FXML
    private void btnDeleteStockClicked() {
    	try {
    		Article article = getSelectedArticle();
    		Optional<Integer> optional = InputDialogFactory.createOptionalIntegerDialog("Amount:");
    		if(optional.isPresent()) {
    			article.removeStock(optional.get());
    			warehouseManager.updateArticleInCurrentWarehouse(article);
    			updateTable();
    		}
    	} catch (NumberFormatException e) {
    		AlertFactory.createErrorAlert("Enter the amount using digits only !");
		} catch (IllegalStateException e) {
			AlertFactory.createErrorAlert(e.getMessage());
		} catch (Exception e) {
			AlertFactory.createErrorAlert(e.getMessage());
			e.printStackTrace();
		}
    }
    
    @FXML
    private void createCategory() {
    	try {
    		/*
    		 * OLBERTZ Strings bitte nicht fest verdrahten, sondern immer den Internationalisierungsmechanismus 
    		 * verwenden und die Strings in properties-Dateien auslagern. 
    		 */
    		Optional<String> optional = InputDialogFactory.createOptionalStringDialog("CategoryName: ");
        	if(optional.isPresent()) {
        		int warehouseNumber = warehouseManager.getCurrentWarehouse().getWarehouseNumber();
        		Category category = LogicFactory.createCategory(optional.get(), warehouseNumber);
        		warehouseManager.insertCategoryToCurrentWarehouse(category);
        	}
		} catch (IllegalArgumentException e) {
			AlertFactory.createErrorAlert(e.getMessage());
		} catch (Exception e) {
			AlertFactory.createErrorAlert(e.getMessage());
			e.printStackTrace();
		}
    }
    
    @FXML
    private void deleteCategory() {
    	try {
    		Collection<Category> categories = warehouseManager.getCurrentWarehouse().getCategoriesCollection();
        	SelectCategoryController selectCategoryController = new SelectCategoryController(categories);
    		Category category = selectCategoryController.getSelectedCategory();
        	if(!selectCategoryController.isClosed() && category != null) {
        		Optional<ButtonType> optional = AlertFactory.createConfirmationAlert(
    	    			"Are you sure you want to permanently delete Category: #" 
    	    			+ category.getCategoryName());
	        	if(optional.isPresent() && optional.get() == ButtonType.OK) {
	        		warehouseManager.deleteCategoryFromCurrentWarehouse(category);
	            	updateTable();
	        	}
        	}
		} catch (Exception e) {
			AlertFactory.createErrorAlert(e.getMessage());
			e.printStackTrace();
		}
    }
    
    @FXML
    private void editCategory() {
    	try {
    		Collection<Category> categories = warehouseManager.getCurrentWarehouse().getCategoriesCollection();
        	SelectCategoryController selectCategoryController = new SelectCategoryController(categories);
    		Category category = selectCategoryController.getSelectedCategory();
        	if(!selectCategoryController.isClosed() && category != null) {
        		Optional<String> optional = InputDialogFactory.createOptionalStringDialog("Edit: " + category.getCategoryName());
        		if(optional.isPresent()) {
        			category.setCategoryName(optional.get());
        			warehouseManager.updateCategoryInCurrentWarehouse(category);
        		}
        	}
		} catch (Exception e) {
			AlertFactory.createErrorAlert(e.getMessage());
			e.printStackTrace();
		}
    }
    
    @FXML
    private void changePriceForSelected() {
    	try {
    		changePriceFor(getSelectedArticles());
		} catch (IllegalStateException e) {
			AlertFactory.createErrorAlert(e.getMessage());
		} catch (Exception e) {
			AlertFactory.createErrorAlert(e.getMessage());
			e.printStackTrace();
		}
    }
    
    @FXML
    private void changePriceForAll() {
    	try {
    		changePriceFor(warehouseManager.getCurrentWarehouse().getArticlesCollection());
		} catch (Exception e) {
			AlertFactory.createErrorAlert(e.getMessage());
			e.printStackTrace();
		}
    }
    
    private void changePriceFor(Collection<Article> articles) throws IOException {
    	ChangePriceController changePriceController = new ChangePriceController();
    	if(!changePriceController.isClosed()) {
    		double percentage = changePriceController.getPercentage();
    		for(Article article : articles) {
    			article.changePrice(percentage);
    			warehouseManager.updateArticleInCurrentWarehouse(article);
    		}
    		updateTable();
    	}
    }
    
    @FXML
    private void filterArticles() {
    	try {
			FilterArticleController controller = new FilterArticleController();
			if(!controller.isClosed()) {
				Predicate<Article> predicate = controller.getPredicate();
				Collection<Article> filteredArticles = warehouseManager.getCurrentWarehouse().filter(predicate);
				String description = controller.getDescription();
				if(description != null) {
					filteredArticles = SearchingUtil.deepSearchForArticlesWith(filteredArticles, description , 1000);
				}
				if(filteredArticles.isEmpty()) {
					AlertFactory.createInformationAlert("No Such Articles were found! ");
				} else {
					updateTable(filteredArticles);
				}
			}
		} catch (Exception e) {
			AlertFactory.createErrorAlert(e.getMessage());
			e.printStackTrace();
		}
    	
    }
    @FXML
    private void editAddress() {
    	try {
    		Warehouse warehouse = warehouseManager.getCurrentWarehouse();
        	EditWarehouseStage editWarehouseStage = new EditWarehouseStage(warehouse);
        	Address address = editWarehouseStage.showAndWait().getAddress();
    		if(!editWarehouseStage.isClosed()) {
    			setTitle(generateTitle());
            	warehouseManager.updateAddress(address);
    		}
		} catch (Exception e) {
			AlertFactory.createErrorAlert(e.getMessage());
			e.printStackTrace();
		}
    	
    }
    
    private void setTitle(String title) {
    	Stage stage = (Stage) pane.getScene().getWindow();
    	stage.setTitle(title);
	}
    
    @FXML private void btnSendArticleClicked() {
    	try {
    		Article article = getSelectedArticle();
			SendArticleController controller = new SendArticleController();
			if(!controller.isClosed()) {
				int receivingWarehouseNumber = controller.getWarehouseNumber();
				int amount = controller.getAmount();
				warehouseManager.sendArticlesFromCurrentWarehouseTo(receivingWarehouseNumber, article, amount);
				updateTable();
			}
		} catch (IllegalArgumentException e) {
			AlertFactory.createErrorAlert(e.getMessage());
		} catch (IllegalStateException e) {
			AlertFactory.createErrorAlert(e.getMessage());
		} catch (Exception e) {
			AlertFactory.createErrorAlert(e.getMessage());
			e.printStackTrace();
		}
    }

    /**
     * Returns a List of all selected articles
     * @throws IllegalStateException if no article/s selected
     * 
     */
	private List<Article> getSelectedArticles() {
    	List<Article> articlesSelected = articleTableView.getSelectionModel().getSelectedItems();
    	if(articlesSelected.size() == 0) {
    		throw new IllegalStateException("Select at least one Article !");
    	}
    	return articlesSelected;
    }
    
	/**
	 * Returns the single article selected
	 * @throws IllegalStateException if not exactly one article is selected
	 * 
	 */
    private Article getSelectedArticle() {
    	List<Article> articlesSelected = articleTableView.getSelectionModel().getSelectedItems();
    	if(articlesSelected.size() != 1) {
    		throw new IllegalStateException("Please Select exactly One Article");
    	}
    	return articlesSelected.get(0);
    }
}
