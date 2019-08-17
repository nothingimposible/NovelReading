package xiaoshuo;

import java.io.File;

public class ceshi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	/*	String url="¿ñÉñ";
		int w=1;
	for(int u=1;u<=99;u++){
		for(int i=1;i<=10;i++){
				String w1=String.valueOf(i);
				String w2=String.valueOf(u);
			String ss=new PaChong_link().details(url,w1,w2);
		  System.out.print("¡¶"+ss+"¡·"+" ");
		}
		 System.out.println("\n");
	}*/
		// new Online().main();
		String url = "https://wenku.baidu.com/view/c4eff7ab988fcc22bcd126fff705cc1754275f5f.html";
		System.out.println(new PaChong_text1().text(url));
	//	String ss=new PaChong_link().directory(url);
	/*	String qq=new PaChong_text().information(url, "1", "1");
		String[] a=qq.split(" ");
		for(String e:a){
			System.out.println(e);
		}*/
	}

}
