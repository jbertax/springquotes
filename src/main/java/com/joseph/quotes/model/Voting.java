package com.joseph.quotes.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="VOTING")
public class Voting implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name="user", nullable=false)
	private Long user;

	@Column(name="quote", nullable=false)
	private Long quote;

	@Column(name="vote", nullable=false)
	private Boolean vote;

	@Column(name="date", nullable=false)
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVote(Boolean vote) { this.vote = vote; }

	public Boolean getVote() { return vote; }

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public Long getQuote() {
		return quote;
	}

	public void setQuote(Long quote) {
		this.quote = quote;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Voting voting = (Voting) o;

		if (id != null ? !id.equals(voting.id) : voting.id != null) return false;
		if (user != null ? !user.equals(voting.user) : voting.user != null) return false;
        if (quote != null ? !quote.equals(voting.quote) : voting.quote != null) return false;
		if (vote != null ? !vote.equals(voting.vote) : voting.vote != null) return false;
		if (date != null ? !date.equals(voting.date) : voting.date != null) return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = id != null ? id.hashCode() : 0;
		result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 37 * result + (quote != null ? quote.hashCode() : 0);
		result = 41 * result + (vote != null ? vote.hashCode() : 0);
		result = 47 * result + (date != null ? date.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", user=" + user + ", quote=" + quote + ", vote: " + vote + ",date=" + date + "]";
	}


}
