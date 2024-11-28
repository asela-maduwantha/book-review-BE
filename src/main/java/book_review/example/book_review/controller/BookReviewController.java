package book_review.example.book_review.controller;

import book_review.example.book_review.dto.BookReviewCreateDTO;
import book_review.example.book_review.dto.BookReviewResponseDTO;
import book_review.example.book_review.dto.BookReviewUpdateDTO;
import book_review.example.book_review.service.BookReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reviews")
@Tag(name = "Book Reviews", description = "Book Review Management APIs")
public class BookReviewController {
    private final BookReviewService bookReviewService;

    public BookReviewController(BookReviewService bookReviewService) {
        this.bookReviewService = bookReviewService;
    }

    //create a new book review
    @PostMapping
    @Operation(summary = "Create a new book review")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Book review created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<BookReviewResponseDTO> createBookReview(@RequestBody BookReviewCreateDTO bookReviewCreateDTO) {
        BookReviewResponseDTO createdBookReview = bookReviewService.createBookReview(bookReviewCreateDTO);
        return new ResponseEntity<>(createdBookReview, HttpStatus.CREATED);
    }

    //get all book reviews
    @GetMapping
    @Operation(summary = "Get all book reviews")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of book reviews retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No book reviews found")
    })
    public ResponseEntity<List<BookReviewResponseDTO>> getAllBookReviews() {
        List<BookReviewResponseDTO> bookReviews = bookReviewService.getAllBookReviews();
        if (bookReviews.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookReviews, HttpStatus.OK);
    }


    //update a book by review id
    @PutMapping("/{id}")
    @Operation(summary = "Update a book review")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book review updated successfully"),
            @ApiResponse(responseCode = "404", description = "Book review not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<BookReviewResponseDTO> updateBookReview(@PathVariable("id") UUID id, @RequestBody BookReviewUpdateDTO bookReviewUpdateDTO) {
        BookReviewResponseDTO updatedBookReview = bookReviewService.updateBookReview(id, bookReviewUpdateDTO);
        return new ResponseEntity<>(updatedBookReview, HttpStatus.OK);
    }

    //get a book review by id
    @GetMapping("/{id}")
    @Operation(summary = "Get a book review by Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book review retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Book review not found"),
    })
    public ResponseEntity<BookReviewResponseDTO> getBookReview(@PathVariable("id") UUID id) {
        BookReviewResponseDTO bookReview = bookReviewService.getBookReview(id);
        if(bookReview == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookReview, HttpStatus.OK);
    }


    //delete a book review by id
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book review by Id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Book review deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Book review not found")
    })
    public ResponseEntity<Void> deleteBookReview(@PathVariable("id") UUID id) {
        bookReviewService.deleteBookReview(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
