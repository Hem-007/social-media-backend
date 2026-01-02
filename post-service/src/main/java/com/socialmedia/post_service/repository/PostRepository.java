package com.socialmedia.post_service.repository;

import com.socialmedia.post_service.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post , Long> {

    Optional<Post> findByIdAndIsDeletedFalse(Long id);

    Page<Post> findByAuthorIdAndIsDeletedFalse(Long authorId, Pageable pageable);

}
