package appy.aparna.example.com.news;

/**
 * Created by Administrator on 5/28/2017.
 */

public class News {
    private String section;
    private String title;
    private String date;
    private String article_url;

    //Constructor


    public News(String section, String title, String date, String article_url) {
        this.section = section;
        this.title = title;
        this.date = date;
        this.article_url = article_url;
    }

    /**
     * @return article section
     */
    public String getSection() {
        return section;
    }

    /**
     * @return title of the article
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return date of the article
     */
    public String getDate() {
        return date;
    }

    /**
     * @return url of the article
     */
    public String getArticle_url() {
        return article_url;
    }
}
