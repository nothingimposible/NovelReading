package xiaoshuo;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;


public class Online implements Runnable{
	 int index;
	 String[] fr=new String[100];
		String[] fr_l=new String[100];
	boolean flag=false;
  private int page_z=1;
  private int num_z=1;
  private JFrame online_f=new JFrame("在线阅读");
  private JFrame online_reading=new JFrame("在线阅读");
  JFrame online=new JFrame("在线阅读");
  JLabel lable_online=new JLabel("我的书架");
  
  private Button undo=new Button("撤销");
  private Button search=new Button("搜索");
  private Button last=new Button("上一页");
  private Button next=new Button("下一页");
  private JTextArea text=new JTextArea("请输入要搜索的小说名");
  
  private JButton next_reading=new JButton("下一章");
  private JButton last_reading=new JButton("上一章");
  private JButton list_reading=new JButton("目录");
  private JButton mybook=new JButton("我的书架");
  JTextArea novel_reading =new JTextArea();
  private JPanel book_information=new JPanel();
  private int page=1;
  private int chapter_num1=1;
  private int chapter_num2=1;
  private String now_chapter="";
  private String list_name="";
  
  JScrollPane jScrollPane4 = new JScrollPane();    //滚动条panel
  ListModel jList1Model4;
  JList myJlist4 = new JList();
  
  link_s online_r=new link_s();
 // Thread [] t1;
   JLabel b_s =new JLabel("书籍选择：");
   
	JButton [] reading=new JButton[10];
	JButton [] add_book=new JButton[10];
  
	JTextArea novel =new JTextArea();
	String [] book_name=new String[10];
	String [] book_link=new String[10];
	String [] book_details_link=new String[10];
	JTextArea [] jb=new JTextArea[10];
//	JComboBox jb=new JComboBox(book_name);
 // box b=new box();
  String url;
  String next_url;
  String last_url;

	public void main() {
		// TODO Auto-generated method stub
	 url="https://www.readnovel.com/search?kw=";
	 String novel_name;
	 online_f.setLayout(null);
	 
	Font x = new Font("宋体",0,25);
		novel.setFont(x);
	 novel.setEditable(false);
	 novel.setLineWrap(true);
		JScrollPane s1=new JScrollPane(novel);
		s1.setBounds(20, 100, 1000, 800);
	 online_f.add(s1);
	 
	  online_f.add(text);
	 text.setBounds(20, 20, 200, 30);
	 online_f.add(undo);
	 undo.setBounds(230, 20, 30, 30);
	 online_f.add(search);
	 search.setBounds(260, 20, 30, 30);
	 
	 online_f.add(last);
	 online_f.add(next);
	 last.setBounds(50, 900, 120, 50);
	 next.setBounds(850, 900, 120, 50);
	 
	 online_reading.setSize(1400, 1000);
	 online_reading.setLayout(null);
	// online_reading.setLocation(700, 500);
	 JScrollPane s2=new JScrollPane(novel_reading);
	 online_reading.add(s2);
	 novel_reading.setEditable(false);
	 novel_reading.setCaretPosition(0);
		//textArea.setBounds(14, 13, 925, 582);
	 novel_reading.setFont(new Font("宋体",0,25));
	 novel_reading.setLineWrap(true);	
		s2.setBounds(20, 20, 1360, 750);
	// novel_reading.setBounds(20, 20, 1360, 750);
	 online_reading.add(next_reading);
	 last_reading.setBounds(20, 800, 200, 75);
	 
		online_reading.add(last_reading);
		next_reading.setBounds(1160, 800, 200, 75);
		
		online_f.add(mybook);
		mybook.setBounds(1050, 650, 200, 50);
		//online_reading.add(list_reading);
		//list_reading.setBounds(590,800, 200, 75);
		online_reading.setVisible(false);
	 for(int i=0;i<10;i++){
		 jb[i]=new JTextArea();
		 jb[i].setFont(new Font("宋体",0,20));
		 reading[i]=new JButton("开始阅读");
		 add_book[i]=new JButton("加入书架");
		 online_f.add(jb[i]);
		 online_f.add(reading[i]);
		 online_f.add(add_book[i]);
		 
			 jb[i].setBounds(1030, 100+i*50, 250, 50);
			 reading[i].setBounds(1280, 100+i*50, 100, 50);
			 add_book[i].setBounds(1380, 100+i*50, 100, 50);
	 }

	 mybook.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try {
				online_reading1();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		 
	 });
	  next_reading.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			String l=new PaChong_link().next(now_chapter);
			String s=new PaChong_text().text(l);
			if(s!=null){
				chapter_num2++;
			
			novel_reading.setText(s);
			novel_reading.setCaretPosition(0);
			now_chapter=l;
			}
			
		}
		  
	  });
	  
	  last_reading.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			
				if(chapter_num2>1){
				String l=new PaChong_link().last(now_chapter);
				String s=new PaChong_text().text(l);
					chapter_num2--;
				
				novel_reading.setText(s);
				novel_reading.setCaretPosition(0);	
				now_chapter=l;
				}
				
			}
			  
		  });
	  
	  list_reading.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			  
		  });
	  
	  
		reading[0].addActionListener(new ActionListener(){
      
			@Override
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				novel_reading.setText(new PaChong_text().text(book_link[0]));
			//	System.out.println(book_link[0]);
				now_chapter=book_link[0];
				online_reading.setVisible(true);
			}
			
		});
		
		reading[1].addActionListener(new ActionListener(){
		      
			@Override
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				novel_reading.setText(new PaChong_text().text(book_link[1]));
				now_chapter=book_link[1];
				online_reading.setVisible(true);
			}
			
		});
		
		reading[2].addActionListener(new ActionListener(){
		      
			@Override
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				novel_reading.setText(new PaChong_text().text(book_link[2]));
				now_chapter=book_link[2];
				online_reading.setVisible(true);
			}
			
		});
		
		reading[3].addActionListener(new ActionListener(){
		      
			@Override
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				novel_reading.setText(new PaChong_text().text(book_link[3]));
				now_chapter=book_link[3];
				online_reading.setVisible(true);
			}
			
		});
		
		reading[4].addActionListener(new ActionListener(){
		      
			@Override
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				novel_reading.setText(new PaChong_text().text(book_link[4]));
				now_chapter=book_link[4];
				online_reading.setVisible(true);
			}
			
		});
		
		reading[5].addActionListener(new ActionListener(){
		      
			@Override
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				novel_reading.setText(new PaChong_text().text(book_link[5]));
				now_chapter=book_link[5];
				online_reading.setVisible(true);
			}
			
		});
		
		reading[6].addActionListener(new ActionListener(){
		      
			@Override
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				novel_reading.setText(new PaChong_text().text(book_link[6]));
				now_chapter=book_link[6];
				online_reading.setVisible(true);
			}
			
		});
		
		reading[7].addActionListener(new ActionListener(){
		      
			@Override
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				novel_reading.setText(new PaChong_text().text(book_link[7]));
				now_chapter=book_link[7];
				online_reading.setVisible(true);
			}
			
		});
		
		reading[8].addActionListener(new ActionListener(){
		      
			@Override
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				novel_reading.setText(new PaChong_text().text(book_link[8]));
				now_chapter=book_link[8];
				online_reading.setVisible(true);
			}
			
		});
		
		reading[9].addActionListener(new ActionListener(){
		      
			@Override
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				novel_reading.setText(new PaChong_text().text(book_link[9]));
				now_chapter=book_link[9];
				online_reading.setVisible(true);
			}
			
		});
		
		add_book[0].addActionListener(new ActionListener(){
		      
			@Override
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					online_add(book_link[0],jb[0].getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		add_book[1].addActionListener(new ActionListener(){
		      
			@Override
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					online_add(book_link[1],jb[1].getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		add_book[2].addActionListener(new ActionListener(){
		      
			@Override
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					online_add(book_link[2],jb[2].getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		add_book[3].addActionListener(new ActionListener(){
		      
			@Override
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					online_add(book_link[3],jb[3].getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		add_book[4].addActionListener(new ActionListener(){
		      
			@Override
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					online_add(book_link[4],jb[4].getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		add_book[5].addActionListener(new ActionListener(){
		      
			@Override
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					online_add(book_link[5],jb[5].getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		add_book[6].addActionListener(new ActionListener(){
		      
			@Override
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					online_add(book_link[6],jb[6].getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		add_book[7].addActionListener(new ActionListener(){
		      
			@Override
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					online_add(book_link[7],jb[7].getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		add_book[8].addActionListener(new ActionListener(){
		      
			@Override
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					online_add(book_link[8],jb[8].getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		add_book[9].addActionListener(new ActionListener(){
		      
			@Override
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					System.out.println(book_link[9]+"\n"+jb[9].getText());
					online_add(book_link[9],jb[9].getText());
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
	 undo.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			text.setText("");
		}
		 
	 });
	 search.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		/*	PaChong_text t=new PaChong_text();
			PaChong_link t1=new PaChong_link();
			String a = "";
			String sss="";
			for(int i=1;i<=10;i++){
				String w1=String.valueOf(i);
				String w2=String.valueOf(page);
			//	book_name[i-1]="《"+t.title(text.getText(), w1, w2)+"》";
			//	a=a+book_name[i-1]+"\n";		
				//a=a+t.author(text.getText(), w1, w2)+" | "+t.type(text.getText(), w1, w2)+" | "+t.state(text.getText(), w1, w2)+"\n";
				book_details_link[i-1]=t1.details(text.getText(), w1, w2);
				book_link[i-1]=t1.free_reading(book_details_link[i-1]);
				a=t.information(text.getText(), w1, w2)+"\n\n";
				String [] st=a.split(" ");
				jb[i-1].setText("《"+st[0]+"》");
				a="";
				for(int u=0;u<st.length;u++){
					if(u==0)
						sss=sss+"《"+st[u]+"》"+"\n";
					else
						sss=sss+st[u]+"\n";
				}
						
			}
        for(String r:book_details_link)
				System.out.println(r);*/
			 list_name="";
			print_list();
	//	System.out.println("==="+list_name);
		}
		 
	 });
	 
	 next.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			page_z++;
			list_name="";
			print_list();
			//System.out.println(page);
	/*		PaChong_text t=new PaChong_text();
			PaChong_link t1=new PaChong_link();
			String a ="";
			String sss="";
			for(int i=1;i<=10;i++){
				String w1=String.valueOf(i);
				String w2=String.valueOf(page);
				book_details_link[i-1]=t1.details(text.getText(), w1, w2);
				book_link[i-1]=t1.free_reading(book_details_link[i-1]);
				a=t.information(text.getText(), w1, w2)+"\n\n";
				String [] st=a.split(" ");
				jb[i-1].setText("《"+st[0]+"》");
				a="";
				for(int u=0;u<st.length;u++){
					if(u==0)
						sss=sss+"《"+st[u]+"》"+"\n";
					else
						sss=sss+st[u]+"\n";
				}
			
		}
			for(String r:book_details_link)
				System.out.println(r);
			 
		 novel.setText(sss);
		 novel.setCaretPosition(0);*/
			
		}
	 });
	 
	 last.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(page_z>1){
				page_z--;
				list_name="";
				print_list();
				//System.out.println(page);
			/*	PaChong_text t=new PaChong_text();
				PaChong_link t1=new PaChong_link();
				String a = "";
				String sss="";
				for(int i=1;i<=10;i++){
					String w1=String.valueOf(i);
					String w2=String.valueOf(page);
					book_details_link[i-1]=t1.details(text.getText(), w1, w2);
					book_link[i-1]=t1.free_reading(book_details_link[i-1]);
					a=t.information(text.getText(), w1, w2)+"\n\n";
					String [] st=a.split(" ");
					jb[i-1].setText("《"+st[0]+"》");
					a="";
					for(int u=0;u<st.length;u++){
						if(u==0)
							sss=sss+"《"+st[u]+"》"+"\n";
						else
							sss=sss+st[u]+"\n";
					}
				
			}
				
				novel.setText(sss);
				 novel.setCaretPosition(0);*/
				}
			}
		 });
	 
     online_f.setSize(1500, 1000);  //大小
     online_f.setVisible(true);
     
     
	}
   
	
	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub
		num_z++;
		int p=page_z;
		int n=num_z;
		PaChong_text t=new PaChong_text();
		PaChong_link t1=new PaChong_link();
		String a = "";
		String sss="";
			String w1=String.valueOf(n);
			String w2=String.valueOf(p);
			book_details_link[n-1]=t1.details(text.getText(), w1, w2);
			book_link[n-1]=t1.free_reading(book_details_link[n-1]);
			a=t.information(text.getText(), w1, w2)+"\n\n";
			String [] st=a.split(" ");
			jb[n-1].setText("《"+st[0]+"》");
			a="";
			for(int u=0;u<st.length;u++){
				if(u==0)
					sss=sss+"《"+st[u]+"》"+"\n";
				else
					sss=sss+st[u]+"\n";
			}
		//	System.out.println("sss="+sss);
			list_name+=sss;
		//	System.out.println("list_name="+list_name);
			   novel.setText(list_name);
    
    System.out.println("n="+n);
	
	}
	void print_list(){
		//num_z=1;
		Thread [] t1 = new Thread[11];
		num_z=0;
	    for(int i=1;i<=10;i++){
	    	//   num_z=i;   
	    	t1[i]=new Thread(this);
	    	t1[i].start();
	    	System.out.println("num_z="+num_z);
	       }
	    
	    for(String r:book_details_link)
			System.out.println(r);
	    
	  
	    System.out.println("name="+list_name);
		   novel.setCaretPosition(0);
	}
	
	public void online_add(String link,String book_name) throws SQLException{
		String sql="insert into online_reading values(?,?)";
		online_r.sq.pstmt=online_r.sq.conn.prepareStatement(sql);
		online_r.sq.pstmt.setString(1, link);
		online_r.sq.pstmt.setString(2, book_name);
		online_r.sq.pstmt.executeUpdate();
		
	}
	
	public void online_reading1() throws SQLException {
		
		online_r.sq.rs=online_r.sq.stmt.executeQuery("SELECT * from online_reading");
		int i=0;
	while(online_r.sq.rs.next()){
	fr_l[i]= online_r.sq.rs.getString(1);
	fr[i]=online_r.sq.rs.getString(2);
	i++;				
	}
	
	online.getContentPane().setLayout(new BorderLayout());
	online.setSize(300, 600);
	online.setLocation(500, 300);
		jList1Model4 =  new DefaultComboBoxModel(fr);
		jScrollPane4.setPreferredSize(new java.awt.Dimension(288, 200));
        myJlist4.setModel(jList1Model4);            //设置数据
        jScrollPane4.setViewportView(myJlist4);    //不能直接add
		myJlist4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    JList myList = (JList) e.getSource();
                     index = myList.getSelectedIndex();    //已选项的下标
                  //  chapter_num=index;
                    Object obj = myList.getModel().getElementAt(index);  //取出数据
                    String b_name=obj.toString();
   			//	System.out.println(b_name+" "+index+"\n"+fr_l[index]);
              //     System.out.println(new PaChong_text().text(fr_l[index]));
   		
   					novel_reading.setText(new PaChong_text().text(fr_l[index]));
   					now_chapter=fr_l[index];
   					online_reading.setVisible(true);
   				
                   online.setVisible(false);
                }
            }
        });
		System.out.println(flag);
		online.getContentPane().add(lable_online,BorderLayout.NORTH);
		online.getContentPane().add(jScrollPane4, BorderLayout.CENTER);
		online.setVisible(true);
	}

}
