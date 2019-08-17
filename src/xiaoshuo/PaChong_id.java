package xiaoshuo;

import java.io.IOException;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
class PaChong_text1 {

		public String text(String url) {  //获得小说内容
			String q = "";
	      try {
			Document doc=Jsoup.connect(url).get();
		//	 Elements ele = doc.select(link).select("div").select("div.read-content.j_readContent"); 
			Elements ele=doc.select("#pageNo-1").select("div").select("div").select("div").select("div").select("div").select("p:nth-child(22)");
			//Elements ele1=doc.getElementsByClass("j_chapterName");
		//	String x1=ele.text();
	  	q=doc.text();
	 
			
		//	q=q+x1+"\n";
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return q;
		}
	}
 class PaChong_id {

	public String text(String url) {
		String q = null;
		String id = null;		
      try {
		Document doc=Jsoup.connect(url).get();
		Elements ele=doc.getElementsByClass("read-content j_readContent");
		q=ele.text();
	//	id=ele.attr("id");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//	return "#" + id; 
      return q;
	}
	
	
}
