

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PhoneBill {
	public static double calPhoneBill(String s) {
		String [] splitStrforNewLine = s.split("\\r?\\n");
		double [] totalCost= new double[splitStrforNewLine.length];
		String [] newphnNumber= new String[splitStrforNewLine.length];
		String [] finalphnNumber= new String[splitStrforNewLine.length];
		double [] finalCost= new double[splitStrforNewLine.length];
		String singleData="";
		String callDuration="";
		String phnNumber="";
		int hourTime = 0;
		int minuteTime = 0;
		int secondTime = 0;
		double totalTime=0;
		
		for(int i=0; i<splitStrforNewLine.length;i++) {
			singleData =  splitStrforNewLine[i];
			String[] splitForComma = singleData.split(",");
			callDuration = splitForComma[0];
			phnNumber = splitForComma[1];
			String[] splitForColon = callDuration.split(":");
			hourTime = Integer.parseInt(splitForColon[0])*3600;
			minuteTime = Integer.parseInt(splitForColon[1])*60;
			secondTime = Integer.parseInt(splitForColon[2]);
			totalTime = hourTime+minuteTime+secondTime;
			
			phnNumber = phnNumber.replaceAll("-", "");
			
			if(totalTime<300) {
				totalCost[i] = totalTime*3;
				
			}else {
				totalCost[i] = Math.ceil(totalTime/60) * 150;
			}
			
			newphnNumber[i] = phnNumber;	
		}
		
		Set<String> set = new HashSet<>();
		int k = 0;
		double sum;
		for(int i=0;i<splitStrforNewLine.length;i++) {
			if(set.contains(newphnNumber[i])) continue;
			else {
				set.add(newphnNumber[i]);
				sum =0;
				for(int j=0;j<splitStrforNewLine.length;j++) {			
					if(newphnNumber[i].equals(newphnNumber[j])) {
						sum = sum + totalCost[j];
					}
				}
			}
			finalphnNumber[k]=newphnNumber[i];
			finalCost[k]= sum;
			k++;
		}
		double maxCostTosingleNumber = Arrays.stream(finalCost).max().getAsDouble(); 
		double totalFinalCost=Arrays.stream(finalCost).sum();
		
		totalFinalCost = totalFinalCost - maxCostTosingleNumber;
		return totalFinalCost;
	}
	
	public static void main(String [] args) {
		String test ="00:01:07,400-234-090\n00:05:01,701-080-080\n00:05:00,400-234-090";
		System.out.println(calPhoneBill(test));
	}

}
