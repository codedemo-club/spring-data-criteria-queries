package club.codedemo.springdatacriteriaqueries;

import club.codedemo.springdatacriteriaqueries.dao.BookDao;
import club.codedemo.springdatacriteriaqueries.dao.BookRepository;
import club.codedemo.springdatacriteriaqueries.entity.Book;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.data.jpa.domain.Specification.where;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookTest {
    private String authName = "panjie";

    private String[] titles = {"spring", "spring boot"};

    @Autowired
    BookDao bookDao;

    @Autowired
    BookRepository bookRepository;

    @BeforeAll
    void beforeAll() {
        for (int i = 0; i < 2; i++) {
            Book book = new Book();
            book.setAuthor(this.authName);
            book.setTitle(this.titles[i]);
            this.bookRepository.save(book);
        }
    }

    /**
     * 测试bookDao的综合查询方法
     */
    @Test
    void bookDaoFindBooksByAuthorNameAndTitle() {
        Assertions.assertEquals(0,
                this.bookDao.findBooksByAuthorNameAndTitle("zhangsan", "spring").size());

        Assertions.assertEquals(2,
                this.bookDao.findBooksByAuthorNameAndTitle("panjie", "spring").size());

        Assertions.assertEquals(1,
                this.bookDao.findBooksByAuthorNameAndTitle("panjie", "spring boot").size());
    }

    /**
     * 测试bookRepository的综合查询方法
     */
    @Test
    void bookRepositoryFindBooksByAuthorNameAndTitle() {
        Assertions.assertEquals(0,
                this.bookRepository.findBooksByAuthorNameAndTitle("zhangsan", "spring").size());

        Assertions.assertEquals(2,
                this.bookRepository.findBooksByAuthorNameAndTitle(null, "spring").size());

        Assertions.assertEquals(2,
                this.bookRepository.findBooksByAuthorNameAndTitle("panjie", "spring").size());

        Assertions.assertEquals(2,
                this.bookRepository.findBooksByAuthorNameAndTitle("panjie", null).size());

        Assertions.assertEquals(1,
                this.bookRepository.findBooksByAuthorNameAndTitle("panjie", "spring boot").size());
    }

    /**
     * 测试Spring提供的specification
     */
    @Test
    void specification() {
        Assertions.assertEquals(2,
                bookRepository.findAll(hasAuthor("panjie")).size());

        Assertions.assertEquals(1,
                bookRepository.findAll(where(hasAuthor("panjie")).and(titleContains("spring boot"))).size());
    }

    /**
     * 查询作者
     *
     * @param author 作者
     * @return
     */
    static Specification<Book> hasAuthor(String author) {
        return (book, cq, cb) -> cb.equal(book.get("author"), author);
    }

    /**
     * 查询图书名
     *
     * @param title 名称
     * @return
     */
    static Specification<Book> titleContains(String title) {
        return (book, cq, cb) -> cb.like(book.get("title"), "%" + title + "%");
    }

}
