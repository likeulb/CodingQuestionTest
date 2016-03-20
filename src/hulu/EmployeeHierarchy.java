package hulu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class EmployeeHierarchy {
	public static void buildEmployeeRelation(String s, BufferedWriter writer) throws IOException{
		HashMap<String, String> employeeInfo = new HashMap<>();
		HashMap<String, List<String>> bossToEmp = new HashMap<>();
		String[] e = s.split("--");
		String ceo = "";
		for(int i=0;i<e.length;i++){
			String cur[] = e[i].split(",");
			String empName = cur[0];
			String bossName = cur[1];
			if(bossName.equals("NULL")){
				ceo=empName;
			}
			if(!bossToEmp.containsKey(bossName)){
				bossToEmp.put(bossName, new ArrayList<String>());
			}
			bossToEmp.get(bossName).add(empName);
			employeeInfo.put(empName, " ("+cur[2]+") "+cur[3]);
		}
		
		writeEmployeeRelation(employeeInfo, bossToEmp, ceo, "", writer);
		
		
	}
	public static void writeEmployeeRelation(HashMap<String, String> employeeInfo, HashMap<String, List<String>> bossToEmp, String curEmp, String dash, BufferedWriter writer) throws IOException{
		writer.write(dash+curEmp+employeeInfo.get(curEmp)+"\n");
		if(bossToEmp.containsKey(curEmp)){
			List<String> nextLevel = bossToEmp.get(curEmp);
			Collections.sort(nextLevel);
			for(String s:nextLevel){
				writeEmployeeRelation(employeeInfo, bossToEmp, s, dash+"-",writer);
			}
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("/Users/yingying/Desktop/org_chart.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/yingying/Desktop/org_chart_out"));
		int numCases = Integer.parseInt(reader.readLine());
		for(int i=1;i<=numCases;i++){
			String cur = reader.readLine();
			writer.write("Case #"+i+"\n");
			buildEmployeeRelation(cur, writer);
		}
		writer.close();
		reader.close();
		
	}

}
