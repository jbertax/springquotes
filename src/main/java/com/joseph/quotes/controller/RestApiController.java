package com.joseph.quotes.controller;

import java.util.Date;
import java.util.List;

import com.joseph.quotes.model.Quote;
import com.joseph.quotes.model.User;
import com.joseph.quotes.model.Voting;
import com.joseph.quotes.service.QuoteService;
import com.joseph.quotes.service.VotingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.joseph.quotes.service.UserService;
import com.joseph.quotes.util.CustomErrorType;

/**
 * This class is the gateway for all the backend functionality.
 * To be able to create and update, list and delete
 * Users
 * Quotes
 * List graph data for votings in general
 * List graph data for votings for an user
 */
@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	UserService userService; //Service which will do all data retrieval/manipulation work

	@Autowired
	QuoteService quoteService;

	@Autowired
	VotingService votingService;

	// -------------------Retrieve All Users---------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// -------------------Retrieve Single User------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id") long id) {
		logger.info("Fetching User with id {}", id);
		User user = userService.findById(id);
		if (user == null) {
			logger.error("User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("User with id " + id
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// -------------------Create a User-------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", user);

		if (userService.isUserExist(user)) {
			logger.error("Unable to create. A User with name {} already exist", user.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " + 
			user.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a User ------------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		logger.info("Updating User with id {}", id);

		User currentUser = userService.findById(id);

		if (currentUser == null) {
			logger.error("Unable to update. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentUser.setName(user.getName());
		currentUser.setPassword(user.getPassword());

		userService.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	// ------------------- Delete a User-----------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting User with id {}", id);

		User user = userService.findById(id);
		if (user == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Users-----------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers() {
		logger.info("Deleting All Users");

		userService.deleteAllUsers();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}


	//                     ---- QUOTE

	// -------------------Retrieve All Quotes---------------------------------------------

	@RequestMapping(value = "/quote/", method = RequestMethod.GET)
	public ResponseEntity<List<Quote>> listAllQuotes() {
		List<Quote> quotes = quoteService.findAllQuotes();
		if (quotes.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Quote>>(quotes, HttpStatus.OK);
	}

	// -------------------Retrieve Single Quote------------------------------------------

	@RequestMapping(value = "/quote/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getQuote(@PathVariable("id") long id) {
		logger.info("Fetching Quote with id {}", id);
		Quote quote = quoteService.findById(id);
		if (quote == null) {
			logger.error("Quote with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Quote with id " + id
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Quote>(quote, HttpStatus.OK);
	}

	// -------------------Retrieve Top 10 Quotes------------------------------------------

	@RequestMapping(value = "/quote/top/{type}", method = RequestMethod.GET)
	public ResponseEntity<?> getTop(@PathVariable("type") String type) {
		logger.info("Fetching Top 10 Quote of with id {}", type);
		List<Quote> quotes = quoteService.findTop(type);
		if (quotes.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Quote>>(quotes, HttpStatus.OK);
	}

	@RequestMapping(value = "/quote/top", method = RequestMethod.GET)
	public ResponseEntity<?> getQuote() {
		return getTop("top");
	}
	// -------------------Create a Quote-------------------------------------------

	@RequestMapping(value = "/quote/", method = RequestMethod.POST)
	public ResponseEntity<?> createQuote(@RequestBody Quote quote, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Quote : {}", quote);

		if (quoteService.isQuoteExist(quote)) {
			logger.error("Unable to create. A Quote with name {} already exist", quote.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A Quote with name " +
					quote.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		quoteService.saveQuote(quote);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(quote.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Quote ------------------------------------------------

	@RequestMapping(value = "/quote/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateQuote(@PathVariable("id") long id, @RequestBody Quote quote) {
		logger.info("Updating Quote with id {}", id);

		Quote currentQuote = quoteService.findById(id);

		if (currentQuote == null) {
			logger.error("Unable to update. Quote with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Quote with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentQuote.setName(quote.getName());
		currentQuote.setPosted(quote.getPosted());

		quoteService.updateQuote(currentQuote);
		return new ResponseEntity<Quote>(currentQuote, HttpStatus.OK);
	}

	// ------------------- Delete a Quote-----------------------------------------

	@RequestMapping(value = "/quote/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteQuote(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting Quote with id {}", id);

		Quote quote = quoteService.findById(id);
		if (quote == null) {
			logger.error("Unable to delete. Quote with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Quote with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		quoteService.deleteQuoteById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Upvote a Quote-----------------------------------------

	@RequestMapping(value = "/upvote/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> upvoteQuote(@PathVariable("id") long quoteId,  @PathVariable("who") long who) {
		logger.info("Fetching & upvoting Quote with id {}", quoteId);

		Quote quote = quoteService.findById(quoteId);
		if (quote == null) {
			logger.error("Unable to upvote. Quote with id {} not found.", quoteId);
			return new ResponseEntity(new CustomErrorType("Unable to upvote. Quote with id " + quoteId + " not found."),
					HttpStatus.NOT_FOUND);
		}
		quoteService.upVote(quoteId, quote.getVotes() + 1);
		storeVoting(quoteId, who, Boolean.TRUE);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	/**
	 * This method is to store voting - who voted to what quote and when - for graph
	 * @param quoteId
	 * @param who
	 * @param vote
	 */
	private void storeVoting(@PathVariable("id") long quoteId, @PathVariable("who") long who, Boolean vote) {
		// store who voted when for the graph
		Voting voting = new Voting();
		voting.setQuote(quoteId);
		voting.setVote(vote);
		voting.setUser(who);
		voting.setDate(new Date());
		votingService.saveVoting(voting);
	}

// ------------------- Upvote a Quote-----------------------------------------

	@RequestMapping(value = "/downvote/{id}/{who}", method = RequestMethod.POST)
	public ResponseEntity<?> downvoteQuote(@PathVariable("id") long quoteId, @PathVariable("who") long who) {
		logger.info("Fetching & upvoting Quote with id {}", quoteId);

		Quote quote = quoteService.findById(quoteId);
		if (quote == null) {
			logger.error("Unable to downvote. Quote with id {} not found.", quoteId);
			return new ResponseEntity(new CustomErrorType("Unable to downvote. Quote with id " + quoteId + " not found."),
					HttpStatus.NOT_FOUND);
		}

		// store who voted when for the graph
		quoteService.downVote(quoteId, quote.getVotes() - 1);
		storeVoting(quoteId, who, Boolean.FALSE);

		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}


	// ------------------- Delete All Quotes-----------------------------

	@RequestMapping(value = "/quote/", method = RequestMethod.DELETE)
	public ResponseEntity<Quote> deleteAllQuotes() {
		logger.info("Deleting All Quotes");

		quoteService.deleteAllQuotes();
		return new ResponseEntity<Quote>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/voting", method = RequestMethod.GET)
	public ResponseEntity<List<Voting>> listAllVoting() {
		List<Voting> votins = votingService.findAllVotings();
		if (votins.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Voting>>(votins, HttpStatus.OK);
	}

	@RequestMapping(value = "/uservotes/{who}", method = RequestMethod.GET)
	public ResponseEntity<List<Voting>> getVotingsOfUser(@PathVariable("who") long who) {
		List<Voting> votins = votingService.getUserVotings(who);
		if (votins.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Voting>>(votins, HttpStatus.OK);
	}

}