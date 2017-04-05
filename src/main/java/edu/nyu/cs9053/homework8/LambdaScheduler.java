package edu.nyu.cs9053.homework8;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;

public class LambdaScheduler{
	private final ArrayList<LambdaJob> lambdaScheduler;

	public LambdaScheduler(){
		lambdaScheduler = new ArrayList<>();
	}

	public ArrayList<LambdaJob> getScheduler(){
		return lambdaScheduler;
	}

	public void addJob(LambdaJob job){
		lambdaScheduler.add(job);
	}

	// Greedy algorithm
	public ArrayList<LambdaJob> scheduleJobs(){
		Collections.sort(lambdaScheduler);
		ArrayList<LambdaJob> resultSet = new ArrayList<>();
		resultSet.add(lambdaScheduler.get(0));
		int previous = 0;
		for(int current = 1; current < lambdaScheduler.size(); current++){
			if((lambdaScheduler.get(current).getStart()).compareTo(lambdaScheduler.get(previous).getFinish()) >= 0){
				resultSet.add(lambdaScheduler.get(current));
				previous = current;
			}
		}
		return resultSet;
	}
}