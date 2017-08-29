package appy.aparna.example.com.booklist;

/**
 * Created by Administrator on 5/27/2017.
 */

public class Book {
    private String title;
    private String author;
    private String imageUrl;
    private String infoUrl;


    //Constructor


    public Book(String title, String author, String imageUrl, String infoUrl) {
        this.title = title;
        this.author = author;
        this.imageUrl = imageUrl;
        this.infoUrl = infoUrl;
    }

    /**
     * @return Book title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return Book author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @return Image url
     */

    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @return url for information
     */
    public String getInfoUrl() {
        return infoUrl;
    }
}
