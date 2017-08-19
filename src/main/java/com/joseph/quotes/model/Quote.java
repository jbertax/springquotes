package com.joseph.quotes.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="QUOTE")
public class Quote implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name="NAME", nullable=false)
	private String name;

	@Column(name="VOTES", nullable=false)
	private Integer votes = new Integer(0);

	@Column(name="POSTED", nullable=false)
	private String posted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}

	public String getPosted() {
		return posted;
	}

	public void setPosted(String posted) {
		this.posted = posted;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Quote quot = (Quote) o;

		if (id != null ? !id.equals(quot.id) : quot.id != null) return false;
		if (name != null ? !name.equals(quot.name) : quot.name != null) return false;
        if (votes != null ? !votes.equals(quot.votes) : quot.votes != null) return false;
		if (posted != null ? !posted.equals(quot.posted) : quot.posted != null) return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 37 * result + (votes != null ? votes.hashCode() : 0);
		result = 71 * result + (posted != null ? posted.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Quote [id=" + id + ", name=" + name + ", votes=" + votes + ", posted=" + posted + "]";
	}


}
