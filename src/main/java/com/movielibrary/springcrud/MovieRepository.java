package com.movielibrary.springcrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepository {

    @Autowired
    JdbcTemplate jdbcTemplatel;

    public List<Movie> getAll() {
        return jdbcTemplatel.query("SELECT id, name, rating FROM movie", BeanPropertyRowMapper.newInstance(Movie.class));
    }

    public Movie getById(int id) {
        return jdbcTemplatel.queryForObject("SELECT id, name, rating FROM movie where id=?", BeanPropertyRowMapper.newInstance(Movie.class), id);
    }
}
