package com.wildcodeschool.springRest.controller;

import com.wildcodeschool.springRest.entity.Book;
import com.wildcodeschool.springRest.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class BookController {
    public final BookRepository repository;

    public BookController(BookRepository repository){
        this.repository = repository;
    }

    @GetMapping("/books")
    public List<Book> index() {
        return this.repository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book show(@PathVariable Long id) {
        return this.repository.findById(id).get();
    }

    @PostMapping("/books")
    public Book create(@RequestBody Book book){
        return this.repository.save(book);
    }

    @PostMapping("/books/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book){
        Book bookToUpdate;
        if (id != null) {
            Optional<Book> optionalBook = repository.findById(id);
            if (optionalBook.isPresent()) {
                bookToUpdate = optionalBook.get();
                bookToUpdate.setAuthor(book.getAuthor());
                bookToUpdate.setTitle(book.getTitle());
                bookToUpdate.setDescription(book.getDescription());
                return this.repository.save(bookToUpdate);
            }
        }
        return this.repository.save(book);
    }

    @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return this.repository.findBooksByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }

    @DeleteMapping("/books/{id}")
    public boolean delete(@PathVariable Long id){
        this.repository.deleteById(id);
        return true;
    }
}
