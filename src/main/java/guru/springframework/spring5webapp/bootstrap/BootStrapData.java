package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.entity.Author;
import guru.springframework.spring5webapp.entity.Book;
import guru.springframework.spring5webapp.entity.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
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
        System.out.println("Started in Bootstrap");

        Publisher jame = new Publisher("Jame Wann", "239/9", "Bangkok", "Bangkok", "10310");
        publisherRepository.save(jame);

        System.out.println("Number of publishers: " + publisherRepository.count());
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123124");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(jame);
        jame.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(jame);

        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Publisher's number of books: " + jame.getBooks().size());

    }
}
