package com.p.lodz.pl.bookexchange.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.p.lodz.pl.bookexchange.entities.Post;

public interface PostRepository   extends JpaRepository<Post, Long>{

}
