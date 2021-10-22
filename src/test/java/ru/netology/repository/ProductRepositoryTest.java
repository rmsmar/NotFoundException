package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;
import ru.netology.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    private Product item1 = new Book(1, "Harry Potter", 500, "J.K.Rowling");
    private Product item2 = new Book(2, "Mary Poppins", 600, "Pamela Lyndon Travers");
    private Product item3 = new TShirt(3, "Black", 2200, "Levi's");
    private Product item4 = new TShirt(4, "White", 2090, "Jack & Jones");

    @BeforeEach
    public void setUp() {
        repository.save(item1);
        repository.save(item2);
        repository.save(item3);
        repository.save(item4);
    }

    @Test
    public void shouldGetAll() {
        Product[] expected = new Product[]{item1, item2, item3, item4};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveById() {
        repository.removeById(1);
        Product[] actual = repository.findAll();
        Product[] expected = {
                item2,
                item3,
                item4,
        };
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotRemoveById() {
        assertThrows(NotFoundException.class, () -> repository.removeById(6));
    }
}