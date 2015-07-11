package common.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import common.model.AreaInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings("serial")
public class AreaNode implements Serializable {

	AreaInfo areaInfo = new AreaInfo();
	public String name;	
	public AreaNode parent;
	public List<AreaNode> children = new ArrayList<AreaNode>();
	
	public AreaNode() {
	}
	
	public AreaNode(AreaInfo areaInfo, AreaNode parent) {
		this.areaInfo = areaInfo;
		this.name = areaInfo!=null? areaInfo.getAreaName(): null;
		this.parent = parent;
	}
	
	public void add(AreaNode child) {
		this.children.add(child);
	}
	
	public JSONObject output() {
		JSONObject retJson = new JSONObject();
		//retJson.put("t_area_id", areaInfo.getAreaId());
		//retJson.put("t_area_name", areaInfo.getAreaName());
		JSONArray jsonArray = new JSONArray();
		//debug
		//System.out.println("=====output start=================");
		
		if(children.size()>0){
			for(AreaNode an : children){
				JSONObject childJson = childJson(an);
				if(childJson != null){
					jsonArray.add(childJson);					
				}
			}
		}
		retJson.put("area",jsonArray);
		//debug
		//System.out.println("COMPLETE:" + jsonArray);
	
		return retJson;
	}

	public JSONObject childJson(AreaNode node){
		JSONObject retJson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		//debug
		//System.out.println("NODE INFO: "+node);
		retJson.put("t_area_id", node.areaInfo.getAreaId());
		retJson.put("t_area_name", node.areaInfo.getAreaName());			
		if(node.children.size()>0){
			for(AreaNode an : node.children){
				JSONObject childJson = childJson(an);
				if(childJson != null){
					jsonArray.add(childJson);		
					//debug
					//System.out.println("2:" +jsonArray);
				}
			}
		retJson.put("area_slave",jsonArray);
		}
		
		return retJson;
	}

	@Override
	public String toString() {
		return String.format("%s->%s", name, children);
	}
}
