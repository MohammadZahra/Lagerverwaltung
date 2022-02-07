package torvalds.WarehouseManager.javafx.controllers;

import java.io.IOException;

import torvalds.WarehouseManager.logic.interfaces.Warehouse;

/**
 * 
 * @author Al Abood, Al Mostafa, Taraq Saeed
 *
 */
public class EditWarehouseStage extends AbstractWarehouseController {

	public EditWarehouseStage(Warehouse warehouse) throws IOException {
		super();
		setWarehouse(warehouse);
	}

	@Override
	protected boolean getWarehouseNumberEditable() {
		return false;
	}

	@Override
	protected String getTitle() {
		return "Edit Warehouse";
	}

}
