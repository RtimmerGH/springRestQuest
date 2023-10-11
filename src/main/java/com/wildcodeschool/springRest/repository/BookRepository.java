package com.wildcodeschool.springRest.repository;

import com.wildcodeschool.springRest.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBooksByTitleContainingOrDescriptionContaining(String searchTerm, String SearchTerm);
}
