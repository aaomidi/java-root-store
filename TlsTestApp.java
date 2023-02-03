import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.SSLHandshakeException;

public class TlsTestApp {

  private static final String USER_AGENT = "Mozilla/5.0";

  private static final String[] tests = {
    "https://good.gsr4.demo.pki.goog/",
    "https://good.gtsr1.demo.pki.goog/",
    "https://good.gtsr2.demo.pki.goog/",
    "https://good.gtsr3.demo.pki.goog/",
    "https://good.gtsr4.demo.pki.goog/",
    "https://good.gtsr1x.demo.pki.goog/"
  };

  public static void main(String[] args) throws IOException {

    for (int i = 0; i < tests.length; i++) {
      System.out.println("Sending " + tests[i]);
      try {
        sendGET(tests[i]);
      } catch(SSLHandshakeException ex) {
        System.out.println("\tBAD BAD BAD");
        continue;
      }

      System.out.println("\tDone " + tests[i]);
    }
  }

  private static void sendGET(String url) throws IOException {
    URL obj = new URL(url);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    con.setRequestMethod("GET");
    con.setRequestProperty("User-Agent", USER_AGENT);
    int responseCode = con.getResponseCode();
    System.out.println("GET Response Code :: " + responseCode);
    if (responseCode == HttpURLConnection.HTTP_OK) { // success
      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuffer response = new StringBuffer();

      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();

    } else {
      System.out.println("GET request did not work.");
    }

  }

}
