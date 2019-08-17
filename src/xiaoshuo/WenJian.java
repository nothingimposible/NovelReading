package xiaoshuo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WenJian {

    String dizhi;
	public WenJian(String dizhi){
		this.dizhi=dizhi;
	}
	
	public String main() {
		// TODO Auto-generated method stub
		
		File file =new File(dizhi);
		String a = null;
		  try {
			FileReader fis=new FileReader(file);
			char[] zi=new char[1024];
		  int hashdata=0;
		  try {
			while((hashdata=fis.read(zi))>0){
				  //System.out.println(new String(zi,0,hashdata));
				a+=new String(zi,0,hashdata);
				a+="\n";
			  }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		  
  return a;
	}

}
