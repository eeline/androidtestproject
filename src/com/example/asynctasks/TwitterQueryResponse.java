package com.example.asynctasks;

import java.io.Serializable;
import java.util.List;

import com.example.datatypes.Result;

public class TwitterQueryResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2631857159439807676L;
	private String query;
	private List<Result> results;

	public TwitterQueryResponse(String query, List<Result> results) {
		this.query = query;
		this.results = results;
	}
	
	public String getQuery(){
		return this.query;
	}
	
	public List<Result> getResults(){
		return this.results;
	}
}
