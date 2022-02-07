package torvalds.WarehouseManager.javafx.controllers;

import java.io.IOException;

/**
 * 
 * @author Al Mostafa
 */
public class ArticleReaderStage extends AbstractArticleController {

	public ArticleReaderStage() throws IOException {
		super();
	}

	@Override
	protected String getTitle() {
		return "Add New Article";
	}

	@Override
	protected boolean isArticleNumberEditable() {
		return true;
	}
}
