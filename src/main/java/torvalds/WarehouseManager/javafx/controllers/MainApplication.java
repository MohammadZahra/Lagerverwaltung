package torvalds.WarehouseManager.javafx.controllers;
import java.io.IOException;
import java.util.Locale;

import javafx.application.Application;
import javafx.stage.Stage;
import torvalds.WarehouseManager.facades.WarehouseManager;
import torvalds.WarehouseManager.javafx.factories.AlertFactory;
import torvalds.WarehouseManager.logic.interfaces.Warehouse;

/**
 * @author Al Abood, Saramijou, Al Mostafa
 *
 */
public class MainApplication extends Application {
	
	private final WarehouseManager warehouseManager;
	
	public MainApplication() {
		warehouseManager = WarehouseManager.getInstance();
	}
	
    @Override
    public void start(Stage primaryStage) throws Exception {
    	openChooseWarehouseView();
    }

    /**
     * Will be called when the user starts the program 
     * the general view of the program will be shown
     */
    private void openChooseWarehouseView() throws IOException {
    	boolean repeat = true;
    	while(repeat) {
    		try {
	    		ChooseWarehouseController controller = new ChooseWarehouseController();
		    	if(controller.isOpenClicked()) {	    		
	    			int warehouseNumber = controller.getWarehouseNumber();
        			warehouseManager.setCurrentWarehouse(warehouseNumber);
        			openWarehouseView();
		    	} else if(controller.isCreateNewClicked()) {
		    		openCreateWarehouseView();
		    	} else { // user closed the stage
		    		repeat = false;
		    	}
    		} catch (IllegalArgumentException e) {
    			AlertFactory.createErrorAlert(e.getMessage());
    		} catch (Exception e) {
    			AlertFactory.createErrorAlert(e.getMessage());
				e.printStackTrace();
			}
    	}
	}

	public static void main(String[] args) {
		Locale.setDefault(Locale.ENGLISH);
        launch();
    }
	
	/**
     * Will be called when the user wants to create a new warehouse 
     * 
     */
    private void openCreateWarehouseView() throws IOException {
    	boolean repeat = true;
    	while(repeat) {
    		try {
		    	CreateWarehouseStage createWarehouseStage = new CreateWarehouseStage();
		    	Warehouse newWarehouse = createWarehouseStage.showAndWait();
		    	if(newWarehouse != null) {
		    		warehouseManager.insertWarehouse(newWarehouse);
		    		warehouseManager.setCurrentWarehouse(newWarehouse);
		        	openWarehouseView();
		    	}
		    	repeat = false;
    		} catch (IllegalArgumentException e) {
    			AlertFactory.createErrorAlert(e.getMessage());
    		} catch (Exception e) {
    			AlertFactory.createErrorAlert(e.getMessage());
				e.printStackTrace();
			}
    	}
	}
    
    /**
     * Opens the Stage which pictures the warehouse that has been opened or created
     * 
     */
    @SuppressWarnings("unused")
	private void openWarehouseView() throws IOException {
    	WarehouseViewController warehouseViewController = new WarehouseViewController();
    }

}
