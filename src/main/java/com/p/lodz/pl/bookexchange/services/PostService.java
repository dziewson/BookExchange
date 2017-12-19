package com.p.lodz.pl.bookexchange.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.p.lodz.pl.bookexchange.entities.Post;

public interface PostService {
	public List<Post> findAll();
	public Post save(Post post);
	public Page<Post> findAll(Pageable pageable);
} 
