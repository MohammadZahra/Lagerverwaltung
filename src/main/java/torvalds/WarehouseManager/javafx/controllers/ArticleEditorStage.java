package torvalds.WarehouseManager.javafx.controllers;

import java.io.IOException;

import torvalds.WarehouseManager.logic.interfaces.Article;

/**
 * 
 * @author Al Mostafa
 */
public class ArticleEditorStage extends AbstractArticleController {

	public ArticleEditorStage(Article article) throws IOException {
		super();
		setArticle(article);
	}

	@Override
	protected String getTitle() {
		return "Edit Article";
	}

	@Override
	protected boolean isArticleNumberEditable() {
		return false;
	}

}
