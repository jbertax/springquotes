package com.joseph.quotes.service;


import com.joseph.quotes.model.Quote;

import java.util.List;

public interface QuoteService {
	
	Quote findById(Long id);

	Quote findByName(String name);

	void saveQuote(Quote quote);

	void updateQuote(Quote quote);

	void deleteQuoteById(Long id);

	void deleteAllQuotes();

	List<Quote> findAllQuotes();

	boolean isQuoteExist(Quote quote);

	public void upVote(Long id, Integer votes);

	public void downVote(Long id, Integer votes);

	public List<Quote> findTop(String type);
}