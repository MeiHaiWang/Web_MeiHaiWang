package business.util;

import business.service.IBaseController;

public class ControllerFactory {

	IBaseController baseController =null;

	public ControllerFactory() {

		// TODO 自動生成されたコンストラクター・スタブ
	}


	public Class<IBaseController> getController(String ControllerName){
		  Class<IBaseController> c=null;
		  try {
			c = (Class<IBaseController>) Class.forName(ControllerName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}
		return  c;
	}
}
