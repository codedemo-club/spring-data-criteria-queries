package club.codedemo.springdatacriteriaqueries.dao;

import club.codedemo.springdatacriteriaqueries.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Book数据仓库层
 */
public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom, JpaSpecificationExecutor<Book> {
}