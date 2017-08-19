package com.joseph.quotes.service;

import com.joseph.quotes.model.Voting;
import com.joseph.quotes.repositories.VotingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("VotingService")
@Transactional
public class VotingServiceImpl implements VotingService{

	@Autowired
	private VotingRepository VotingRepository;

	public Voting findById(Long id) {
		return VotingRepository.findOne(id);
	}

	public void saveVoting(Voting Voting) {
		VotingRepository.save(Voting);
	}

	public void updateVoting(Voting Voting){
		saveVoting(Voting);
	}

	public void deleteVotingById(Long id){
		VotingRepository.delete(id);
	}

	public void deleteAllVotings(){
		VotingRepository.deleteAll();
	}

	public List<Voting> findAllVotings(){
		return VotingRepository.findAll();
	}

	public List<Voting> getUserVotings(Long who){
		return VotingRepository.getUserVotings(who);
	}
}
