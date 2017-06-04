package com.p.lodz.pl.bookexchange.services;

import java.util.List;

import com.p.lodz.pl.bookexchange.entities.Post;

public interface PostService {
	public List<Post> findAll();
	public Post save(Post post);
} 
