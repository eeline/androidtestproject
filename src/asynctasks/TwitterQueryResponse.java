package asynctasks;

import java.util.List;

import com.example.datatypes.Result;

public class TwitterQueryResponse {
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
