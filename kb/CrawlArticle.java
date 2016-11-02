package edu.washington.kb;

public class CrawlArticle {

    private String date;
    private String title;
    private String url;
    private String text;
    private String articleId;


    // Getters and setters are not required for this example.
    // GSON sets the fields directly using reflection.

    @Override
    public String toString() {
        return "articleId:" + articleId;
    }

    public String getName() {
	//return("articleId:"+articleId);
	return("crawlCorpus:"+articleId);
    }

    public String getText() {
        return(text);
    }
   

}

