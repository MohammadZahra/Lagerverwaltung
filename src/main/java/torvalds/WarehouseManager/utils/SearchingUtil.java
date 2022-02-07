package torvalds.WarehouseManager.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;
import torvalds.WarehouseManager.logic.interfaces.Article;

/**
 * This class provides methods for searching
 *
 * @author Al Mostafa
 */
public class SearchingUtil {
	private SearchingUtil() {
		
	}
	
	/**
	 * Searches for Articles that have an articleNumber/description that 
	 * exactly matches the target string. Note that this method is 
	 * Case-insensitive
	 * 
	 * @param articles the articles on which this search will be done
	 * @param targetString the Searching-Input.
	 * @return the resulting collection of articles matching the target-String
	 */
	public static Collection<Article> quickSearchForArticlesWith(Collection<Article> articles, String targetString) {
		final List<Article> result = new LinkedList<>();
		for(Article article : articles) {
			String articleNumberStr = "" + article.getArticleNumber();
			String description = article.getDescription();
			if(targetString.equalsIgnoreCase(articleNumberStr)) {
				result.add(article);
			} else if(targetString.equalsIgnoreCase(description)) {
				result.add(article);
			}
		}
		return result;
	}
	
	/**
	 * Searches for Articles using the lucene library.
	 * The used Query-type here is Fuzzy which is made for finding similarity in strings
	 * 
	 * @param articles the articles on which this search will be done
	 * @param targetString the Searching-Input.
	 * @return the resulting collection of articles matching the target-String
	 * @throws IOException 
	 */
	public static Collection<Article> deepSearchForArticlesWith(Collection<Article> articles, String targetString, int limit) throws IOException {
		List<String> descriptions = articles
				.stream()
				.map(a -> a.getDescription())
				.collect(Collectors.toList());
		List<String> results = findElementsWith(descriptions, targetString, limit);
		List<Article> articleResults = new ArrayList<>();
		for(String description : results)	{
			for(Article article : articles) {
				if(article.getDescription().equalsIgnoreCase(description)) {
					articleResults.add(article);
				}
			}
		}
		return articleResults;
	}
	
	private static List<String> findElementsWith(Collection<String> elements, String target, int limit) throws IOException {
		final String ATTRIBUTE = "A";
		
		StandardAnalyzer analyzer = new StandardAnalyzer();
		Directory index = new MMapDirectory(Files.createTempDirectory("lucene"));
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		IndexWriter writter = new IndexWriter(index, config);
		
		for(String element : elements) {
			Document document = new Document();
			document.add(new TextField(ATTRIBUTE, element, Store.YES));
			writter.addDocument(document);
		}

		writter.close();
		
		Term term = new Term(ATTRIBUTE, target);
		Query query = new FuzzyQuery(term);
		
		IndexReader indexReader = DirectoryReader.open(index);
	    IndexSearcher searcher = new IndexSearcher(indexReader);
	    TopDocs topDocs = searcher.search(query, limit);
	    List<Document> documents = new ArrayList<>();
	    for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
	        documents.add(searcher.doc(scoreDoc.doc));
	    }
	    
	    List<String> result = new ArrayList<>();
	    for(Document document : documents) {
		    result.add(document.get(ATTRIBUTE));
	    }
	    
	    return result;
	}
}
