package co.coconut.api;

import org.apache.http.HttpHost;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import java.util.Map;

public class CoconutAPI {

	private static final String USER_AGENT = "Coconut/2.0.0 (Java)";
	private static final String COCONUT_DOMAIN = "api.coconut.co";
	private static final String COCONUT_URL = "https://api.coconut.co";

	protected String apiKey;
	protected String configContent;

  /**
   * Class constructor specifying the config content, api key.
   */
	public CoconutAPI(String configContent, String apiKey) {
		this.apiKey = apiKey;
		this.configContent = configContent;
	}

  	public CoconutObject submit() throws CoconutException {
  		return request("/v1/job", "POST", this.configContent).parseObject();
  	}

  	public static CoconutObject submit(String configContent, String apiKey) throws CoconutException {
  		return new CoconutAPI(configContent, apiKey).submit();
  	}

  	public static CoconutObject submit(String configContent) throws CoconutException {
  		Map<String, String> env = System.getenv();

  		String apiKey = env.get("COCONUT_API_KEY");
  		return new CoconutAPI(configContent, apiKey).submit();
  	}

	private Executor createExecutor() {
		return Executor.newInstance()
		        .auth(new HttpHost(CoconutAPI.COCONUT_DOMAIN), this.apiKey, "");
	}

	private CoconutResponse request(String resource, String method, String body) throws CoconutException {
		return createRequest(resource, method, body);
	}

	private CoconutResponse createRequest(String resource, String method, String body) throws CoconutException {
		String response = "";
		try {
			Executor executor = createExecutor();

			if(method.equals("POST")) {
				response = executor.execute(Request.Post(CoconutAPI.COCONUT_URL + "/" + resource)
						.addHeader("Accept", "application/json")
						.addHeader("Content-Type", "text/plain")
						.addHeader("User-Agent", CoconutAPI.USER_AGENT)
						.bodyString(body, ContentType.DEFAULT_TEXT))
				        .returnContent().asString();
			} else if(method.equals("PUT")) {
				response = executor.execute(Request.Put(CoconutAPI.COCONUT_URL + "/" + resource)
						.addHeader("Accept", "application/json")
						.addHeader("Content-Type", "text/plain")
						.addHeader("User-Agent", CoconutAPI.USER_AGENT)
						.bodyString(body, ContentType.DEFAULT_TEXT))
				        .returnContent().asString();
			}

			String jsonString = response;
			return new CoconutResponse(jsonString);
		} catch(Exception e) {
			throw new CoconutException(e);
		}
	}
}
