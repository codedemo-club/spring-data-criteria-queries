package club.codedemo.springdatacriteriaqueries.dao;

import club.codedemo.springdatacriteriaqueries.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * 图书数据访问对象
 */
@Repository
public class BookDao implements BookRepositoryCustom {

    /**
     * 实体管理器
     */
    @Autowired
    EntityManager em;

    /**
     * 查询图书
     * @param authorName 作者名称（精确查询）
     * @param title 图书名称 模糊查询
     * @return
     */
    @Override
    public List<Book> findBooksByAuthorNameAndTitle(String authorName, String title) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);

        Root<Book> book = cq.from(Book.class);
        Predicate authorNamePredicate = cb.equal(book.get("author"), authorName);
        Predicate titlePredicate = cb.like(book.get("title"), "%" + title + "%");
        cq.where(authorNamePredicate, titlePredicate);

        TypedQuery<Book> query = em.createQuery(cq);
        return query.getResultList();
    }
}
