/**クラス名： PropertiesManager.java
 *説明： javaのプロパティファイルを読み込むクラスです。例外が発生した場合は引数不正の為呼び出し元クラスにthrowします。
 *作成者:蝦名弘紀
 *作成日：2014/09/23
 *最終更新日:2014/09/23
 *備考:Memoryz Ph1.0新規開発
 */

package common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

 public class PropertiesManager {

	static private Properties conf=null;

	/**
	 * @param configファイルのパス（ファイル名含む、プロジェクトからの相対パス)
	 * @return なし
	 * @author Hiroki Ebina
	 * @throws 引数のファイルパスが不正なのでtry&catchはせず呼び出し元クラスにthrowします
	 */

	public PropertiesManager(String[] configPaths) throws FileNotFoundException, IOException {

		conf = new Properties();
		InputStream inStream;

		for(String configPath :configPaths){
			inStream = PropertiesManager.class.getClassLoader().getResourceAsStream(configPath);
			conf.load(inStream);
		}
	}

	/**
	 * @param なし
	 * @return Properties
	 * @author Hiroki Ebina
	 */
	public Properties getProperties(){
		return conf;
	}
}
