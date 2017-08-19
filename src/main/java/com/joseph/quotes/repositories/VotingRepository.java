package com.joseph.quotes.repositories;

import com.joseph.quotes.model.User;
import com.joseph.quotes.model.Voting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotingRepository extends JpaRepository<Voting, Long> {

    @Query(value = "SELECT v FROM Voting as v WHERE v.user = :who")
    public List<Voting> getUserVotings(@Param("who") Long who);
}