package com.example.springbooks.controller;

import com.example.springbooks.forms.BookForm;
import com.example.springbooks.forms.UpdateBookForm;
import com.example.springbooks.model.Book;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping
public class BookController {
    private static List<Book> books = new ArrayList<>();

    static {
        books.add(new Book("Full Stack development","Deepu K Sasidharan," +
                "Sendil Kumar N"));
        books.add(new Book("Pro Spring Secutity","Carlo Scarioni, Massimo Nardone"));
    }

    @Value("${welcome.message}")
    private String message;
    @Value("${error.message}")
    private String errorMessage;

    //@RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
    @GetMapping(value = {"/","/index"})
    public ModelAndView index(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        model.addAttribute("message",message);
        log.info("/index was called");
        return  modelAndView;
    }

   // @RequestMapping(value = {"/allbooks"}, method = RequestMethod.GET)
    @GetMapping(value = {"/allbooks"})
    public ModelAndView bookList(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("booklist");
        model.addAttribute("books", books);
        log.info("/booklist was called");
        return  modelAndView;
    }

    //@RequestMapping(value = {"/addbook"}, method = RequestMethod.GET)
    @GetMapping(value = {"/addbook"})
    public ModelAndView showAddBookPage(Model model){
        ModelAndView modelAndView = new ModelAndView("addbook");
        BookForm bookForm = new BookForm();
        model.addAttribute("bookform",bookForm);
        log.info("/addbook Get was called");
        return  modelAndView;
    }

    //@RequestMapping(value = {"/addbook"}, method = RequestMethod.POST)
    @PostMapping(value = {"/addbook"})
    public ModelAndView saveBook(Model model,
                                 @ModelAttribute("bookform") BookForm bookForm){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("booklist");
        String title = bookForm.getTitle();
        String author = bookForm.getAuthor();
        log.info("/addbook POST was called");

        if(title!=null && title.length()>0
        && author != null && author.length()>0){
            Book newBook = new Book(title, author);
            books.add(newBook);
            model.addAttribute("books", books);
            return modelAndView;
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("addbook");
        return  modelAndView;
    }

    //@RequestMapping(value = {"/delbook"}, method = RequestMethod.GET)
    @GetMapping(value = {"/delbook"})
    public ModelAndView showDelBookPage(Model model){
        ModelAndView modelAndView = new ModelAndView("delbook");
        BookForm bookForm = new BookForm();
        model.addAttribute("bookform",bookForm);
        log.info("/delbook GET was called");
        return  modelAndView;
    }

    @PostMapping(value = {"/delbook"})
    //@RequestMapping(value = {"/delbook"}, method = RequestMethod.POST)
    public ModelAndView delBook(Model model,
                                 @ModelAttribute("bookform") BookForm bookForm){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("booklist");
        String title = bookForm.getTitle();
        String author = bookForm.getAuthor();
        log.info("/delbook POST was called");

        if(title!=null && title.length()>0
                && author != null && author.length()>0){
            int index = 0;
            for (Book book: books){
                if(book.getTitle().equals(title) && book.getAuthor().equals(author)){
                    books.remove(index);
                    break;
                }
                index++;
            }
            model.addAttribute("books", books);
            return modelAndView;
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("delbook");
        return  modelAndView;
    }

    @GetMapping(value = {"/updatebook"})
    //@RequestMapping(value = {"/updatebook"}, method = RequestMethod.GET)
    public ModelAndView showUpdateBookPage(Model model){
        ModelAndView modelAndView = new ModelAndView("updatebook");
        UpdateBookForm bookForm = new UpdateBookForm();
        model.addAttribute("bookform",bookForm);
        log.info("/updatebook GET was called");
        return  modelAndView;
    }

    @PostMapping(value = {"/updatebook"})
    //@RequestMapping(value = {"/updatebook"}, method = RequestMethod.POST)
    public ModelAndView updateBook(Model model,
                                @ModelAttribute("bookform") UpdateBookForm bookForm){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("booklist");
        String title = bookForm.getTitle();
        String author = bookForm.getAuthor();
        String newTitle = bookForm.getNewTitle();
        String newAuthor = bookForm.getNewAuthor();
        log.info("/updatebook POST  was called");

        if(title!=null && title.length()>0
                && author != null && author.length()>0
                && newTitle != null && newTitle.length()>0
                && newAuthor !=null && newAuthor.length()>0){
            for (Book book: books){
                if(book.getTitle().equals(title) && book.getAuthor().equals(author)){
                    book.setTitle(newTitle);
                    book.setAuthor(newAuthor);
                    model.addAttribute("books", books);
                    return modelAndView;
                }
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("updatebook");
        return  modelAndView;
    }
}
