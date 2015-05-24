/** クラス名： JsonParse.java
 * 説明： Jsonデータを処理するためのコントローラです．
 * 作成者：玉井伸幸
 * 作成日：2014/11/15
 * 最終更新日：2014/11/15
 * 
 */
package common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class JsonParse {
	private String json = "";
	private String responseJsonString = "";
	private static JsonParse jsonparse;

	private JsonParse (){
		jsonparse = null;
	}

	public static Logger myLog = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public Object parseJsonData(JsonParser parser, String key) {
		Object data = "empty";
		
		try {
			while (parser.nextToken() != null) {
			    String name = parser.getCurrentName();
			    
			    System.out.println("key: " + key + ", name: " + name);
			    if(name != null) {
			        if(name.equals(key)) {
			            parser.nextToken();	          
			            data = parser.getText();
			            break;
			        }else{
			            //想定外のものは無視して次へ
			            //parser.skipChildren();
			        }
			    }
			}
		} catch (JsonParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		return data;
	}

	public void setJsonData(HttpServletRequest request) {
	    // フォームデータより名前を取得
		json = request.getParameter("json");
		myLog.info(json);
	}

	public String getJsonData () {
		return this.json;
	}

	public static JsonParse getInstance() {
		// メソッド呼び出しがあったときに、初めてインスタンスを生成する
		if (jsonparse == null) {
			// マルチスレッド環境下で、1つ目のスレッドがこの位置にいるときに、別のスレッドが上のif文の条件式を評価してしまうと、
			// 2つ以上のスレッドがこのブロック内に入り込めてしまう
			jsonparse = new JsonParse();
		}
	return jsonparse;
	}
}
