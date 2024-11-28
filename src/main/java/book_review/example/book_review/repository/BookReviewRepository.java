package book_review.example.book_review.repository;

import book_review.example.book_review.entity.BookReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookReviewRepository extends JpaRepository<BookReview, UUID> {
}
