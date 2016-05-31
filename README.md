# Java client Library for encoding Videos with Coconut

## Submitting the job

Use the [API Request Builder](https://app.coconut.co/job/new) to generate a config file that match your specific workflow.

Example of `coconut.conf`:

```ini
var s3 = s3://accesskey:secretkey@mybucket

set source  = http://yoursite.com/media/video.mp4
set webhook = http://mysite.com/webhook/coconut

-> mp4  = $s3/videos/video.mp4
-> webm = $s3/videos/video.webm
-> jpg:300x = $s3/previews/thumbs_#num#.jpg, number=3
```

Here is the java code to submit the config file:

```java
import co.coconut.api.CoconutAPI;
import co.coconut.api.CoconutException;
import co.coconut.api.CoconutObject;

public class App {
  public static void main(String[] args) throws CoconutException {
    try {
      String config = "set source = https://s3-eu-west-1.amazonaws.com/files.coconut.co/test.mp4\n"
          + "-> mp4 = s3://a:s@bucket/video.mp4\n";

      CoconutObject job = CoconutAPI.submit(config, "api-key");
      System.out.println("Job: " + job.get("id"));

    } catch(CoconutException e) {
      System.out.println("Error " + e.getMessage());
    }
  }
}
```

Note that you can use the environment variable `COCONUT_API_KEY` to set your API key.

*Released under the [Apache license](http://www.apache.org/licenses/LICENSE-2.0.txt).*

---

* Coconut website: http://coconut.co
* API documentation: http://coconut.co/docs
* Contact: [support@coconut.co](mailto:support@coconut.co)
* Twitter: [@OpenCoconut](http://twitter.com/opencoconut)
