package edu.nyu.cs9053.homework8;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.HashMap;

public class LambdaWeightedScheduler extends LambdaScheduler{
	private final ArrayList<LambdaJob> weightedScheduler;

	public LambdaWeightedScheduler(){
		super();
		weightedScheduler = new ArrayList<>();
	}

	public ArrayList<LambdaJob> getWeightedScheduler(){
		return weightedScheduler;
	}

	@Override public void addJob(LambdaJob job){
		weightedScheduler.add(job);
	}

	public ArrayList<LambdaJob> scheduleWeightedJobs(){
		Collections.sort(weightedScheduler);

		double[] table = new double[weightedScheduler.size()];
		table[0] = weightedScheduler.get(0).getProfit();

		HashMap<Double, Integer> hashMap = new HashMap<>();
		hashMap.put(weightedScheduler.get(0).getProfit(), 0); 

		double maxProfit = 0d; 
		for(int i = 1; i < weightedScheduler.size(); i++){
			double currProfit = weightedScheduler.get(i).getProfit();
			int latestNonConflictJob = findLatestNonConflict(weightedScheduler, i);
			if(latestNonConflictJob != -1){
				currProfit += table[latestNonConflictJob];
			}
			maxProfit = Math.max(currProfit, table[i - 1]);
			table[i] = maxProfit;

			if(!hashMap.containsKey(maxProfit)){
				hashMap.put(maxProfit, i);
			}
		}

		ArrayList<LambdaJob> resultSet = getOptimalJobs(weightedScheduler, hashMap, maxProfit);
		Collections.sort(resultSet);
		return resultSet;
	}

	private ArrayList<LambdaJob> getOptimalJobs(ArrayList<LambdaJob> scheduler, HashMap<Double, Integer> hashMap, Double maxProfit){
		ArrayList<LambdaJob> resultSet = new ArrayList<>();
		while(hashMap.get(maxProfit) != null){
			LambdaJob temp = scheduler.get(hashMap.get(maxProfit));
			resultSet.add(temp);
			maxProfit -= temp.getProfit();
		}
		return resultSet;
	}

	private int findLatestNonConflict(ArrayList<LambdaJob> jobList, int index){
		for(int j = index - 1; j >= 0; j--){
			if(jobList.get(j).getFinish().compareTo(jobList.get(index).getStart()) <= 0){
				return j;
			}
		}
		return -1;
	}

}