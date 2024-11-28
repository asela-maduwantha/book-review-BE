package book_review.example.book_review.service;

import book_review.example.book_review.dto.BookReviewCreateDTO;
import book_review.example.book_review.dto.BookReviewResponseDTO;
import book_review.example.book_review.dto.BookReviewUpdateDTO;
import book_review.example.book_review.entity.BookReview;
import book_review.example.book_review.exception.ResourceNotFoundException;
import book_review.example.book_review.repository.BookReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookReviewService {
    private final BookReviewRepository bookReviewRepository;

    public BookReviewService(BookReviewRepository bookReviewRepository) {
        this.bookReviewRepository = bookReviewRepository;
    }

    // create a new book review
    public BookReviewResponseDTO createBookReview(BookReviewCreateDTO bookReviewCreateDTO) {
        BookReview bookReview =  convertToEntity(bookReviewCreateDTO);
        BookReview savedBookReview = bookReviewRepository.save(bookReview);
        return convertToDTO(savedBookReview);
    }

    //get all book reviews
    public List<BookReviewResponseDTO> getAllBookReviews() {
        return bookReviewRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    //get a book review by id
    public BookReviewResponseDTO getBookReview(UUID bookReviewId) {
        Optional<BookReview> bookReview = bookReviewRepository.findById(bookReviewId);
        return bookReview.map(this::convertToDTO).orElse(null);
    }

    //update a book review by id
    public BookReviewResponseDTO updateBookReview(UUID bookReviewId, BookReviewUpdateDTO bookReviewUpdateDTO) {
        return bookReviewRepository.findById(bookReviewId)
                .map(savedBookReview -> {
                    savedBookReview.setTitle(bookReviewUpdateDTO.getTitle());
                    savedBookReview.setAuthor(bookReviewUpdateDTO.getAuthor());
                    savedBookReview.setReviewText(bookReviewUpdateDTO.getReviewText());
                    savedBookReview.setRating(bookReviewUpdateDTO.getRating());
                    return convertToDTO(bookReviewRepository.save(savedBookReview));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Book review not found with id: " + bookReviewId));
    }

    //delete a book review by Id
    public void deleteBookReview(UUID bookReviewId) {
        Optional<BookReview> bookReview = bookReviewRepository.findById(bookReviewId);
        bookReview.ifPresent(bookReviewRepository::delete);
    }


    //convert BookReviewDTO to entity
    private BookReview convertToEntity(BookReviewCreateDTO bookReviewCreateDTO){
        BookReview bookReview = new BookReview();
        bookReview.setTitle(bookReviewCreateDTO.getTitle());
        bookReview.setAuthor(bookReviewCreateDTO.getAuthor());
        bookReview.setRating(bookReviewCreateDTO.getRating());
        bookReview.setReviewText(bookReviewCreateDTO.getReviewText());
        return bookReview;
    }


    //Convert BookReview to DTO
    private BookReviewResponseDTO convertToDTO(BookReview bookReview){
        BookReviewResponseDTO bookReviewResponseDTO = new BookReviewResponseDTO();
        bookReviewResponseDTO.setId(bookReview.getId());
        bookReviewResponseDTO.setTitle(bookReview.getTitle());
        bookReviewResponseDTO.setAuthor(bookReview.getAuthor());
        bookReviewResponseDTO.setRating(bookReview.getRating());
        bookReviewResponseDTO.setReviewText(bookReview.getReviewText());
        bookReviewResponseDTO.setReviewDate(bookReview.getCreated());
        return bookReviewResponseDTO;
    }
}
