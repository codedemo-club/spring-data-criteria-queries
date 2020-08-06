package club.codedemo.springdatacriteriaqueries.dao;

import club.codedemo.springdatacriteriaqueries.entity.Book;

import java.util.List;

/**
 * 自定义的Book数据仓库
 */
public interface BookRepositoryCustom {
    /**
     * 查询图书
     * @param authorName 作者名（精确）
     * @param title 图书名称（模糊）
     * @return
     */
    List<Book> findBooksByAuthorNameAndTitle(String authorName, String title);
}
