package torvalds.WarehouseManager.javafx.controllers;

import java.io.IOException;

/**
 * @author Al Abood, Saramijou, Al Mostafa
 *
 */
public class CreateWarehouseStage extends AbstractWarehouseController {

	public CreateWarehouseStage() throws IOException {
		super();
	}

	@Override
	protected boolean getWarehouseNumberEditable() {
		return true;
	}

	@Override
	protected String getTitle() {
		return "Create Warehouse";
	}
	   
}
