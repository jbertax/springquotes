package com.joseph.quotes.service;


import com.joseph.quotes.model.Voting;

import java.util.List;

public interface VotingService {
	
	Voting findById(Long id);


	void saveVoting(Voting voting);

	void updateVoting(Voting voting);

	void deleteVotingById(Long id);

	void deleteAllVotings();

	List<Voting> findAllVotings();

	List<Voting> getUserVotings(Long who);


}