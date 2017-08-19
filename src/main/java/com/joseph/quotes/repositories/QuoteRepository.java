package com.joseph.quotes.repositories;

import com.joseph.quotes.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    Quote findByName(String name);
    List<Quote> findAll();

    @Modifying
    @Query(value = "UPDATE Quote q set q.votes = :votes where q.id = :id", nativeQuery = true)
    public void upVote(@Param("id") Long id, @Param("votes") Integer votes);

    @Modifying
    @Query(value = "UPDATE Quote q set q.votes = :votes where q.id = :id", nativeQuery = true)
    public void downVote(@Param("id") Long id, @Param("votes") Integer votes);


    @Query(value = "SELECT q FROM Quote as q ORDER BY q.votes DESC")
    public List<Quote> findTop(Pageable pageable);

    @Query(value = "SELECT q FROM Quote as q ORDER BY q.votes")
    public List<Quote> findFlop(Pageable pageable);

}