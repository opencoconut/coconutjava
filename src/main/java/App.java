import co.coconut.api.CoconutAPI;
import co.coconut.api.CoconutException;
import co.coconut.api.CoconutObject;

public class App {

	public static void main(String[] args) throws CoconutException {
		try {
			String config = "set source = https://s3-eu-west-1.amazonaws.com/files.coconut.co/test.mp4\n"
					+ "set webhook = http://mysite.com/webhook\n"
					+ "-> mp4 = s3://a:s@bucket/video.mp4\n";

			CoconutObject job = CoconutAPI.submit(config);
			System.out.println("Job: " + job.get("id"));

		} catch(CoconutException e) {
			System.out.println("Error " + e.getMessage());
		}
	}

}
