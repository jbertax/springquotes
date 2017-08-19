package com.joseph.quotes.service;

import com.joseph.quotes.model.Quote;
import com.joseph.quotes.repositories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.springframework.data.domain.PageRequest;

@Service("QuoteService")
@Transactional
public class QuoteServiceImpl implements QuoteService{

	@Autowired
	private QuoteRepository QuoteRepository;

	public Quote findById(Long id) {
		return QuoteRepository.findOne(id);
	}

	public Quote findByName(String name) {
		return QuoteRepository.findByName(name);
	}

	public void saveQuote(Quote Quote) {
		QuoteRepository.save(Quote);
	}

	public void updateQuote(Quote Quote){
		saveQuote(Quote);
	}

	public void deleteQuoteById(Long id){
		QuoteRepository.delete(id);
	}

	public void deleteAllQuotes(){
		QuoteRepository.deleteAll();
	}

	public List<Quote> findAllQuotes(){
		return QuoteRepository.findAll();
	}

	public boolean isQuoteExist(Quote Quote) {
		return findByName(Quote.getName()) != null;
	}

	public void upVote(Long id, Integer votes) {
		QuoteRepository.upVote(id, votes);
	}

	public void downVote(Long id, Integer votes) {
		QuoteRepository.downVote(id, votes);
	}

	public List<Quote> findTop(String type){
		if (type == "flop"){
			return QuoteRepository.findFlop(new PageRequest(0, 10));
		} else {
			return QuoteRepository.findTop(new PageRequest(0, 10));
		}
	}

}
