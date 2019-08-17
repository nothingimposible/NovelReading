package xiaoshuo;

import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class PaChong_link {

	public String text(String url) {
		String q = null;
		String lianjie = null;
		// TODO Auto-generated method stub
      try {
		Document doc=Jsoup.connect(url).get();
		Elements ele = doc.select("#j-catalogWrap").select("div.volume-wrap").select("div:nth-child(4)").select("ul").select("li:nth-child(1)").select("a");
		lianjie=ele.attr("href");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return lianjie;
   
	}
  
	public String details(String url,String num,String page) {  //�鼮����
		String q = null;
		String lianjie = null;
		url="https://www.readnovel.com/search?kw="+url+"&pageNum="+page;
		// TODO Auto-generated method stub
      try {
		Document doc=Jsoup.connect(url).get();
		Elements ele=doc.select("#result-list").select("div.book-img-text").select("ul").select("li:nth-child("+num+")").select("div.book-right-info").select("p").select("a.pink-btn");
		lianjie=ele.attr("href");
		//lianjie=ele.text();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return "https:"+ lianjie;
   
	}
	
	public String free_reading(String url) {  //����Ķ�
		String q = null;
		String lianjie = null;
		// TODO Auto-generated method stub
      try {
		Document doc=Jsoup.connect(url).get();
	
		Elements ele=doc.select("#readBtn");
		lianjie=ele.attr("href");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return "https:"+ lianjie;
   
	}
	
	public String next(String url) {  //��һ��
		String q = null;
		String lianjie = null;
		// TODO Auto-generated method stub
      try {
		Document doc=Jsoup.connect(url).get();
		Elements ele=doc.select("#j_chapterNext");
		lianjie=ele.attr("href");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return "https:"+ lianjie;
   
	}
	
	public String last(String url) {  //��һ��
		String q = null;
		String lianjie = null;
		// TODO Auto-generated method stub
      try {
		Document doc=Jsoup.connect(url).get();
		Elements ele=doc.select("#j_chapterPrev");
		lianjie=ele.attr("href");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return "https:"+ lianjie;
   
	}
	
	public String directory(String url) {  //Ŀ¼
		String q = null;
		String lianjie = null;
		// TODO Auto-generated method stub
      try {
		Document doc=Jsoup.connect(url).get();
		Elements ele=doc.select("#j-catalogWrap").select("div.volume-wrap").select("div:nth-child(2)").select("ul").select("li:nth-child(99)").select("a");
		lianjie=ele.attr("src");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return "https:"+ lianjie;
   
	}
}
