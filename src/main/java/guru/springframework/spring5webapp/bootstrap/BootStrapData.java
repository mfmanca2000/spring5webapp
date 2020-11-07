package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started bootstrap");

        Publisher publisher = new Publisher();
        publisher.setName("Einaudi");
        publisher.setCity("Torino");
        publisher.setState("Italy");
        publisher.setZip("10100");

        publisherRepository.save(publisher);

        System.out.println("Number of Publishers: " + publisherRepository.count());

        Author auth1 = new Author("Eric", "Evans");
        Book book1 = new Book("Bestseller", "123123");

        auth1.getBooks().add(book1);
        book1.getAuthors().add(auth1);
        book1.setPublisher(publisher);
        publisher.getBooks().add(book1);

        authorRepository.save(auth1);
        bookRepository.save(book1);
        publisherRepository.save(publisher);

        Author auth2 = new Author("Rod", "Johnson");
        Book book2 = new Book("J2EE advanced", "321321");

        auth2.getBooks().add(book2);
        book2.getAuthors().add(auth2);
        book2.setPublisher(publisher);
        publisher.getBooks().add(book2);

        authorRepository.save(auth2);
        bookRepository.save(book2);
        publisherRepository.save(publisher);

        System.out.println("Number of books:" + bookRepository.count());

        System.out.println("Publisher number of books:" + publisher.getBooks().size());
    }
}
