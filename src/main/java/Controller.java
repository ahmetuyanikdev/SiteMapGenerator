import org.jsoup.Jsoup;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.lang.*;


/**
 *
 * Created by Ahmet Uyanik on 2015-04-13.
 */
public class Controller {

    public static String getLevel(int depthCounter){  /* figure for depth level */
        String l ="";
        for(int i=0;i<depthCounter;i++){
            l=l+" -> ";
        }
        return l;
    }


    public static void getLinks(String url,int depth,int depthCounter){  /*extract links (within domain) for each depth */
        try {
            Document doc = Jsoup.connect(url).get();
            depthCounter++;
            if(depthCounter<=depth) {
                Elements links = doc.select("a[href]");
                for (Element link : links) {
                    if(link.attr("abs:href").toString().contains("www.wexford.com")) {
                        System.out.println(getLevel(depthCounter) + link.attr("abs:href") + " \n");
                        getLinks(link.attr("abs:href").toString(), depth, depthCounter);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String []args){
            int depth = 3;
            String domainUrl = "http://www.wexford.com";
            getLinks(domainUrl,depth,0);
    }

}
