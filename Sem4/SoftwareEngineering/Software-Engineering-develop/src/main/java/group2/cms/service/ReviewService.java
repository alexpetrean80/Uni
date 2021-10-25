package group2.cms.service;

import group2.cms.domain.PCMember;
import group2.cms.domain.Paper;
import group2.cms.domain.Review;
import group2.cms.exceptions.InvalidIDException;
import group2.cms.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Review addReview(Long id, PCMember pcMember, Paper paper, File feedback, Integer result){
        return reviewRepository.save(new Review(id, pcMember, paper, feedback, result));
    }

    public void deleteReview(Long reviewID){
        try{
            reviewRepository.deleteById(reviewID);
        }catch(IllegalArgumentException | EmptyResultDataAccessException e){
            throw new InvalidIDException("Invalid Review ID: " + reviewID);
        }
    }

    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

    public Review getReviewByID(Long reviewID){
        return reviewRepository.findById(reviewID).orElseThrow(
                () -> new InvalidIDException("Invalid Presentation ID: " + reviewID)
        );
    }
}
