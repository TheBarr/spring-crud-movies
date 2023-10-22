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

    public int save(List<Movie> movies) {
        movies.forEach(movie -> jdbcTemplatel.update("INSERT INTO movie(name, rating) VALUES (?, ?)", movie.getName(), movie.getRating()));
        return 1;
    }

    public int update(Movie movie){
        return jdbcTemplatel.update("UPDATE movie SET name=?, rating=? WHERE id=?", movie.getName(), movie.getRating(), movie.getId());
    }

    public int delete(int id){
        return jdbcTemplatel.update("DELETE FROM movie WHERE id=?", id);
    }
}
