package club.codedemo.springdatacriteriaqueries.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 图书
 */
@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 名称
     */
    String title;

    /**
     * 作者
     */
    String author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
