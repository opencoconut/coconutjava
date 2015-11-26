package co.coconut.api;

import org.json.JSONException;

public class CoconutResponse {
	public String response;
	public CoconutResponse(String jsonString) {
		this.response = jsonString;
	}

	public CoconutObject parseObject() {
		if(this.response.startsWith("{")) {
			try {
				return new CoconutObject(this.response);
			} catch (JSONException e) {
	            e.printStackTrace();
	            return new CoconutObject();
			}
		} else {
			return new CoconutObject();
		}
	}
}