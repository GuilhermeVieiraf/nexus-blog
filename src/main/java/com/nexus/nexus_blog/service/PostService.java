package com.nexus.nexus_blog.service;

import com.nexus.nexus_blog.dto.PostRequestDto;
import com.nexus.nexus_blog.dto.PostResponseDto;

import java.security.Principal;

public interface PostService {

    PostResponseDto cretePost(PostRequestDto postRequestDto, Principal principal);

}
