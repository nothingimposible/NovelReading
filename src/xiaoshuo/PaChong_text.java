package xiaoshuo;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class PaChong_text {

	public String text(String url) {  //获得小说内容
		String q = "";
      try {
		Document doc=Jsoup.connect(url).get();
	//	 Elements ele = doc.select(link).select("div").select("div.read-content.j_readContent"); 
		Elements ele=doc.getElementsByClass("read-content j_readContent");
		Elements ele1=doc.getElementsByClass("j_chapterName");
		String x1=ele1.text();
  	String x=ele.text();
		String [] d= x.split(" ");
		q=q+x1+"\n";
		for(String e:d){
			q=q+"  "+e+"\n";
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return q;
	}
	
	public String title(String url,String num,String page){    //获得小说标题
		url="https://www.readnovel.com/search?kw="+url+"&pageNum="+page;
		String q = null;
		try {
			Document doc=Jsoup.connect(url).get();
		Elements ele=doc.select("#result-list").select("div.book-img-text").select("ul").select("li:nth-child("+num+")").select("div.book-mid-info").select("h4").select("a");
		q=ele.text();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return q;
	}
	
	public String information(String url,String num,String page){
		url="https://www.readnovel.com/search?kw="+url+"&pageNum="+page;
		String q = "";
		try {
			Document doc=Jsoup.connect(url).get();
		Elements ele=doc.select("#result-list").select("div.book-img-text").select("ul").select("li:nth-child("+num+")").select("div.book-mid-info");//.select("p.intro");
		q=ele.text();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return q;
	}
	public String author(String url,String num,String page){  //获得作者名
		url="https://www.readnovel.com/search?kw="+url+"&pageNum="+page;
		String q = null;
		try {
			Document doc=Jsoup.connect(url).get();
		Elements ele=doc.select("#result-list").select("div.book-img-text").select("ul").select("li:nth-child("+num+")").select("div.book-mid-info").select("p.author").select("a.name.default");
		q=ele.text();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return q;
	}
	public String type(String url,String num,String page){  //获得小说类型
		url="https://www.readnovel.com/search?kw="+url+"&pageNum="+page;
		String q = null;
		try {
			Document doc=Jsoup.connect(url).get();
		Elements ele=doc.select("#result-list").select("div.book-img-text").select("ul").select("li:nth-child("+num+")").select("div.book-mid-info").select("p.author").select("a:nth-child(4)");
		q=ele.text();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return q;
	}
	
	public String state(String url,String num,String page){   //获得小说更新状态
		url="https://www.readnovel.com/search?kw="+url+"&pageNum="+page;
		String q = null;
		try {
			Document doc=Jsoup.connect(url).get();
		Elements ele=doc.select("#result-list").select("div.book-img-text").select("ul").select("li:nth-child("+num+")").select("div.book-mid-info").select("p.author").select("span");
		q=ele.text();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return q;
	}
}
