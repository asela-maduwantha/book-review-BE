package book_review.example.book_review.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookReviewCreateDTO {
   @NotBlank(message = "Book Title is required.")
   private String title;

   @NotBlank(message = "Author is required.")
   private String author;


   @NotBlank(message = "Rating is required.Must between 1-5.")
   @Min(value = 1, message = "Rating must be at least 1")
   @Max(value = 5, message = "Rating cannot exceed 5")
   private int rating;

   @NotBlank(message = "Review text is Required.")
   @Size(max = 5000, message = "Review must be less than 5000 words")
   private String reviewText;

}
