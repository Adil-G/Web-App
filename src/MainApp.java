import SentenceGenerator.GoogleCSAPI;
import general.TestChatBotMain;
import general.graph.theory.WikipediaInfoBoxModel2OldJune14_PERSONAL;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.SystemDefaultCredentialsProvider;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by corpi on 2016-07-11.
 */
public class MainApp {
    private static final String USER_AGENT = "Mozilla/5.0";
    static String urlParameters  = "hi there";
    static byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
    static int    postDataLength = postData.length;
    public static void main(String[] args) throws Exception {
        //ArrayList<String> results = new ArrayList<>(GoogleCSAPI.getLinks(("elephants").replaceAll("\\s+", "+"), false));//.subList(0,3));
        //ArrayList<String> results2 = new ArrayList<>(GoogleCSAPI.getLinks(("elephants").replaceAll("\\s+", "+"), true));//.subList(0,3));

         //System.out.println(general.Client1.extractConcept("wolf"));


        dostuff("GET","what is an elephant".replaceAll("\\s","+"),0);
    }
    public static String dostuff(String action,String query,int filename) throws IOException {
        String url = "http://untitled1-2.rbjbakp2fm.us-west-2.elasticbeanstalk.com/MyServlet?" +
       // String url = "http://localhost:8080/unnamed2/MyServlet?" +
            "user="+query.replaceAll("\\s+","+")+"&password="+filename+"";

        /*List<NameValuePair> params = new LinkedList<NameValuePair>();


        params.add(new BasicNameValuePair("q","hi there"));

        String paramString = URLEncodedUtils.format(params, "utf-8");

        //url += "/"+paramString;
        */
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //con.set
        // optional default is GET

        con.setRequestMethod(action);

        //add request header
        //con.setRequestProperty("User-Agent", USER_AGENT);
        //con.addRequestProperty("q","yolo");
        //con.setRequestProperty( "q","yolo");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
    return response.toString();
    }
}
