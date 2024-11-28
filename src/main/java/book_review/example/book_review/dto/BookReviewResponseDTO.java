package book_review.example.book_review.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
public class BookReviewResponseDTO {

    private UUID id;

    private String title;

    private String author;

    private int rating;

    private String reviewText;

    private LocalDateTime reviewDate;

}
