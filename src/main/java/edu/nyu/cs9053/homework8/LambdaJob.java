package edu.nyu.cs9053.homework8;

import java.time.LocalDateTime;

public class LambdaJob implements Comparable<LambdaJob>{
	private final LocalDateTime start;
	private final LocalDateTime finish;
	private final Double profit;

	public LambdaJob(LocalDateTime start, LocalDateTime finish, Double profit){
		this.start = start;
		this.finish = finish;
		this.profit = profit;
	}

	public LocalDateTime getStart(){
		return start;
	}

	public LocalDateTime getFinish(){
		return finish;
	}

	public Double getProfit(){
		return profit;
	}

	@Override public int compareTo(LambdaJob other){
		return finish.compareTo(other.finish);
	}

}