package common.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtilities {

	//A sentence separates by a comma.
	public List<String> separateData(String str){
		List<String> returnDataList = new ArrayList<String>();
		String str2 = str;
    	int i = 0;
    	while(i<=str.length()){
    		int idx = str.indexOf(',', i);
    		if(idx>i){
		    	str2 = str.substring(i, idx);		    			
    		}else{
    			str2 = str.substring(i, str.length());
    		}
    		returnDataList.add(str2);
	    	i+=(str2.length()+1);
    	}
    	//Debug
    	/*
    	for(String oneData: returnDataList)
    		System.out.println("Debug_separateList: " + oneData);
    		*/
    	return returnDataList;
	}
	
	//List<String> -> List<Integer>
	public List<Integer> convertList_str_int(List<String> strList){
		List<Integer> retList = new ArrayList<Integer>();
		for(String oneStr :strList)
			retList.add(Integer.parseInt(oneStr));
		//Debug
		/*
    	for(int oneData: retList)
    		System.out.println("Debug_convertList: " + oneData);
		 */
    	return retList;
	}

}
