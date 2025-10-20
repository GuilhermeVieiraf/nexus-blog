package com.nexus.nexus_blog.service;

import com.nexus.nexus_blog.dto.PostRequestDto;
import com.nexus.nexus_blog.dto.PostResponseDto;
import com.nexus.nexus_blog.model.Post;
import com.nexus.nexus_blog.model.User;
import com.nexus.nexus_blog.repository.PostRepository;
import com.nexus.nexus_blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public PostResponseDto cretePost(PostRequestDto postRequestDto, Principal principal) {
        String username = principal.getName();
        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilizador não encontrado para criar post: " + username));

        Post newPost = new Post();
        newPost.setTitle(postRequestDto.title());
        newPost.setContent(postRequestDto.content());
        newPost.setAuthor(author);

        Post savedPost = postRepository.save(newPost);
        return mapPostToResponseDto(savedPost);
    }

    private PostResponseDto mapPostToResponseDto(Post post) {
        return new PostResponseDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor().getUsername(),
                post.getCreationDate()
        );
    }

}
