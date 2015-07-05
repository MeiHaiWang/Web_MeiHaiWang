package common.util;

import java.util.List;

import common.model.AreaInfo;

public final class TreeUtil {

	@SuppressWarnings("unused")
	public static AreaNode createTreeObject(List<AreaInfo> treePathList) {
		AreaNode root = new AreaNode(null, null);
		//debug
		System.out.println("===========Tree create start.===============");
		for (AreaInfo aInfo : treePathList){
			int parentId = aInfo.getParent();
			if (aInfo == null) {
				continue;
			}
			if(parentId <= 0){
				// 親ノードがないとき、追加候補はroot直下
				boolean exists = false;
				for (AreaNode child : root.children) {
					if (aInfo.getAreaName().equals(child.name)) {
						// ルート直下に既に存在するので、追加しない
						exists = true;
					}
				}
				if (!exists) {
					AreaNode child = new AreaNode(aInfo, root);
					root.add(child);
					//debug
					System.out.println("ROOTADD: "+root.name +"->"+child.name+" *** Parent is "+child.areaInfo.getParent());
				}
			} else {
				// 親ノードがあるとき、その親ノード直下に追加
				appendAreaNodeAsChild(parentId, aInfo, root);
			}
			parentId = aInfo.getAreaId();
		}
	return root;
	}
	
	private static void appendAreaNodeAsChild(int parentId,
			AreaInfo areaInfo, AreaNode areaNode) {
		//debug
		System.out.println("--: "+areaNode.name +"->"+areaInfo.getAreaName()+"? *** pId:"+parentId+":--");
		// AreaNode#nameがnullはrootなのでここでは対象外
		if (areaNode.name != null && parentId==areaNode.areaInfo.getAreaId()) {
			// 追加すべきノードが見つかったか
			boolean exists = false;
			for (AreaNode child : areaNode.children) {
				if (areaNode.name.equals(child.name)) {
					// 存在するので追加しない
					exists = true;
				}
			}
			if (!exists) {
				AreaNode child = new AreaNode(areaInfo, areaNode);
				areaNode.add(child);
				//debug
				System.out.println("ADD: "+areaNode.name +"->"+child.name+" *** Parent is "+child.areaInfo.getParent());
			}
		} else {
			for (AreaNode child : areaNode.children) {
				// 追加すべきノードを探す
				appendAreaNodeAsChild(parentId, areaInfo, child);
			}
		}
	}
	
}