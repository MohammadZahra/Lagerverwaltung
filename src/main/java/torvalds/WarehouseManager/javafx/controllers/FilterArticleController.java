package torvalds.WarehouseManager.javafx.controllers;

import java.io.IOException;
import java.util.function.Predicate;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import torvalds.WarehouseManager.javafx.consts.PathConsts;
import torvalds.WarehouseManager.javafx.factories.AlertFactory;
import torvalds.WarehouseManager.logic.interfaces.Article;

/**
 * 
 * @author Al Abood, Al Mostafa, Taraq Saeed
 *
 */
public class FilterArticleController {
	
	private static final String NUMBER_ERROR = "Enter the Number using digits and dot only.";
	private static final String FILTER_ARTICLE_PATH ="filter-article-scene.fxml"; 

    @FXML
    private TextField txtArticleNumberFrom;
    @FXML
    private TextField txtArticleNumberTo;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtPriceFrom;
    @FXML
    private TextField txtPriceTo;
    @FXML
    private TextField txtTotalPriceFrom;
    @FXML
    private TextField txtTotalPriceTo;
    @FXML
    private TextField txtStockFrom;
    @FXML
    private TextField txtStockTo;
    
    private boolean isClosed;
    private Integer articleNumberFrom;
    private Integer articleNumberTo;
    private Double priceFrom;
    private Double priceTo;
    private Double totalPriceFrom;
    private Double totalPriceTo;
    private Integer stockFrom;
    private Integer stockTo;
    
    public FilterArticleController() throws IOException {
		Stage stage = new Stage();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(FILTER_ARTICLE_PATH));
    	loader.setController(this);;
    	Parent root = loader.load();
    	stage.setScene(new Scene(root));
    	stage.setTitle("Filter Articles");
    	stage.setResizable(false);
    	stage.getIcons().add(new Image(getClass().getResource(PathConsts.WAREHOUSE_MANAGER_ICON).toExternalForm()));
    	isClosed = true;
    	stage.showAndWait();
	}
    
    private void closeStage() {
    	Stage stage = (Stage) txtArticleNumberFrom.getScene().getWindow();
		stage.close();
	}
    
    @FXML
    void btnCancelClicked() {
    	closeStage();
    }

    @FXML
    void btnFilterClicked() {
    	try {
			String inputArticleNumberFrom = txtArticleNumberFrom.getText();
			if(!inputArticleNumberFrom.isBlank()) {
				this.articleNumberFrom = Integer.parseInt(inputArticleNumberFrom);
			}
			String inputArticleNumberTo = txtArticleNumberTo.getText();
			if(!inputArticleNumberTo.isBlank()) {
				this.articleNumberTo = Integer.parseInt(inputArticleNumberTo);
			}            
			String inputPriceFrom = txtPriceFrom.getText();
			if(!inputPriceFrom.isBlank()) {
				this.priceFrom = Double.parseDouble(inputPriceFrom);
			}
            String inputPriceTo = txtPriceTo.getText();
            if(!inputPriceTo.isBlank()) {
            	this.priceTo = Double.parseDouble(inputPriceTo);
            }
            String inputTotalPriceFrom = txtTotalPriceFrom.getText();
            if(!inputTotalPriceFrom.isBlank()) {
            	this.totalPriceFrom = Double.parseDouble(inputTotalPriceFrom);
            }
            String inputTotalPriceTo = txtTotalPriceTo.getText();
            if(!inputTotalPriceTo.isBlank()) {
            	this.totalPriceTo = Double.parseDouble(inputTotalPriceTo);
            }
            String inputStockFrom = txtStockFrom.getText();
            if(!inputStockFrom.isBlank()) {
            	this.stockFrom = Integer.parseInt(inputStockFrom);
            }
			String inputStockTo = txtStockTo.getText();
			if(!inputStockTo.isBlank()) {
				this.stockTo = Integer.parseInt(inputStockTo);
			}
			this.isClosed = false;
			closeStage();
		} catch (NumberFormatException e) {
			AlertFactory.createErrorAlert(NUMBER_ERROR);
		} 
	}

	public Integer getArticleNumberFrom() {
		return articleNumberFrom;
	}

	public Integer getArticleNumberTo() {
		return articleNumberTo;
	}

	public String getDescription() {
		if(txtDescription.getText().isBlank()) {
			return null;
		} else {
			return txtDescription.getText();
		}
	}

	public Double getPriceFrom() {
		return priceFrom;
	}

	public Double getPriceTo() {
		return priceTo;
	}

	public Double getTotalPriceFrom() {
		return totalPriceFrom;
	}

	public Double getTotalPriceTo() {
		return totalPriceTo;
	}

	public Integer getStockFrom() {
		return stockFrom;
	}

	public Integer getStockTo() {
		return stockTo;
	}

	/**
	 * Returns whether the user closed this stage
	 * 
	 */
	public boolean isClosed() {
		return isClosed;
	}
	
	/**
	 * Note that this predicate doesn't check the description
	 * 
	 */
	public Predicate<Article> getPredicate() {
		Predicate<Article> predicate = (Article article) -> {
			boolean numberFrom = getArticleNumberFrom() == null
					|| article.getArticleNumber() >= getArticleNumberFrom();
			boolean numberTo = getArticleNumberTo() == null 
					|| article.getArticleNumber() <= getArticleNumberTo();
			
			boolean stockFrom = getStockFrom() == null
					|| article.getCurrentStock() >= getStockFrom();
			boolean stockTo = getStockTo() == null
					|| article.getCurrentStock() <= getStockTo();
			
			boolean priceFrom = getPriceFrom() == null
					|| article.getGrossPrice() >= getPriceFrom();
			boolean priceTo = getPriceTo() == null
					|| article.getGrossPrice() <= getPriceTo();
			
			boolean totalPriceFrom = getTotalPriceFrom() == null
					|| article.getTotalGrossPrice() >= getTotalPriceFrom();
			boolean totalPriceTo = getTotalPriceTo() == null
					|| article.getTotalGrossPrice() <= getTotalPriceTo();
			
			boolean numberCondition = numberFrom && numberTo;
			boolean stockCondition = stockFrom && stockTo;
			boolean priceCondition = priceFrom && priceTo;
			boolean totalPriceCondition = totalPriceFrom && totalPriceTo;
			
			return numberCondition && stockCondition && priceCondition && totalPriceCondition;
		};
		return predicate;
	}
}

