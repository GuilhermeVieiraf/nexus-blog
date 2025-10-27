package com.nexus.nexus_blog.service;

import com.nexus.nexus_blog.dto.PostRequestDto;
import com.nexus.nexus_blog.dto.PostResponseDto;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

public interface PostService {

    PostResponseDto createPost(PostRequestDto postRequestDto, Principal principal);

    List<PostResponseDto> getAllPosts();

}
