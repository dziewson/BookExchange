package com.p.lodz.pl.bookexchange.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.p.lodz.pl.bookexchange.entities.Post;
import com.p.lodz.pl.bookexchange.repositories.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	PostRepository postRepo;

	@Override
	public List<Post> findAll() {

		return postRepo.findAll();
	}

	@Override
	public Post save(Post post) {
		return postRepo.save(post);
	}

	@Override
	public Page<Post> findAll(Pageable pageable) {
		Page<Post> blogList = postRepo.findAll(pageable);
		return blogList;
	}

}


