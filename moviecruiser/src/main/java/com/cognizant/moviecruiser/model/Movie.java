package com.cognizant.moviecruiser.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//Using lombok Annotation Library
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie")
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	@Column(name = "title")
	private String title;
	@Column(name = "box_office")
	private String boxOffice;
	@Column(name = "active")
	private boolean active;
	@Column(name = "date_of_launch")
	private Date dateOfLaunch;
	@Column(name = "genre")
	private String genre;
	@Column(name = "has_teaser")
	private boolean hasTeaser;

	@Override
	public String toString() {

		return String.format("%-3d %-20s %-15s %-8b %-30s %-18s %-15b", id, title, boxOffice, active, dateOfLaunch,
				genre, hasTeaser);

	}
}
