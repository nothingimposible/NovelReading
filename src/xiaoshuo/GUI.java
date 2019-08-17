package xiaoshuo;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;


public class GUI extends Thread{
	boolean run=true;
	JFrame mulu=new JFrame("kk小说阅读");
	JFrame books=new JFrame("kk小说阅读");
	JFrame local=new JFrame("kk小说阅读");
	
	JLabel lable_book=new JLabel("本地书库");
	JLabel lable_z=new JLabel("章节目录");
	JLabel lable_local=new JLabel("最近阅读");
	private JFrame frame;
	private JButton button_1;
	private JButton button_2;
	private JPanel panel_1;
	private JButton button_3;
	private JButton button_4;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_1;
	private int speed=10;
	Font x = new Font("宋体",0,20);
	JTextArea textArea = new JTextArea();
	int chapter_num=0;
	String chapter_name;
    private String [] list=new String[2000];
    private String select_novel; 
	boolean reading=false;
	Thread thread;
	Thread sp;
	
	ButtonGroup buttongroup=new ButtonGroup();
	JScrollPane jScrollPane1 = new JScrollPane();    //滚动条panel
    ListModel jList1Model;
    JList myJlist = new JList();
    
    JScrollPane jScrollPane2 = new JScrollPane();    //滚动条panel
    ListModel jList1Model2;
    JList myJlist2 = new JList();
    
    JScrollPane jScrollPane3 = new JScrollPane();    //滚动条panel
    ListModel jList1Model3;
    JList myJlist3 = new JList();
    link_s local_r =new link_s();
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				  window.frame.setName("kk小说阅读");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
	
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void mu() {
		mulu.getContentPane().setLayout(new BorderLayout());
		mulu.setSize(300, 600);
		mulu.setLocation(500, 300);
		jList1Model =  new DefaultComboBoxModel(list);
		jScrollPane1.setPreferredSize(new java.awt.Dimension(288, 200));
        myJlist.setModel(jList1Model);            //设置数据
        jScrollPane1.setViewportView(myJlist);    //不能直接add
		myJlist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    JList myList = (JList) e.getSource();
                    int index = myList.getSelectedIndex();    //已选项的下标
                    chapter_num=index;
                    Object obj = myList.getModel().getElementAt(index);  //取出数据
                    chapter_name=obj.toString();
                   print_novel(chapter_name);
                   
                   try {
					detection(select_novel.substring(6), chapter_name,select_novel+"\\"+chapter_name);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                   mulu.setVisible(false);
                }
            }
        });
        mulu.getContentPane().add(lable_z,BorderLayout.NORTH);
        mulu.getContentPane().add(jScrollPane1, BorderLayout.CENTER);
         mulu.setVisible(true);
	}
	
	public void local_reading() throws SQLException {
		String[] fr=new String[100];
		String[] fr_l=new String[100];
		String[] fr_z=new String[100];
		local_r.sq.rs=local_r.sq.stmt.executeQuery("SELECT * from local_reading");
		int i=0;
	while(local_r.sq.rs.next()){
	fr_l[i]= local_r.sq.rs.getString(1);
	fr[i]=local_r.sq.rs.getString(2);
	fr_z[i]=local_r.sq.rs.getString(3);
	fr[i]=fr[i]+"\\"+fr_z[i];
	i++;				
	}
		//System.out.println(fr[0]);
		local.getContentPane().setLayout(new BorderLayout());
		local.setSize(300, 600);
		local.setLocation(500, 300);
		jList1Model3 =  new DefaultComboBoxModel(fr);
		jScrollPane3.setPreferredSize(new java.awt.Dimension(288, 200));
        myJlist3.setModel(jList1Model3);            //设置数据
        jScrollPane3.setViewportView(myJlist3);    //不能直接add
		myJlist3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    JList myList = (JList) e.getSource();
                    int index = myList.getSelectedIndex();    //已选项的下标
                    chapter_num=index;
                    Object obj = myList.getModel().getElementAt(index);  //取出数据
                    chapter_name=obj.toString();
                    
                    
                    System.out.println(chapter_name);
                   print_novel_z(chapter_name);
                   
                
                   local.setVisible(false);
                }
            }
        });
		local.getContentPane().add(lable_local,BorderLayout.NORTH);
		local.getContentPane().add(jScrollPane3, BorderLayout.CENTER);
		local.setVisible(true);
	}
	
	public void book() {
		File f=new File("D:\\小说");
		String [] book_list=f.list();
		books.getContentPane().setLayout(new BorderLayout());
		books.setSize(300, 600);
		books.setLocation(500, 300);
		jList1Model2 =  new DefaultComboBoxModel(book_list);
		jScrollPane2.setPreferredSize(new java.awt.Dimension(288, 200));
        myJlist2.setModel(jList1Model2);            //设置数据
        jScrollPane2.setViewportView(myJlist2);    //不能直接add
		myJlist2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    JList myList2 = (JList) e.getSource();
                    int index = myList2.getSelectedIndex();    //已选项的下标
                  //  chapter_num=index;
                    Object obj = myList2.getModel().getElementAt(index);  //取出数据
                   select_novel="D:\\小说\\"+obj.toString();
                System.out.println(select_novel);
                
                File file=new File(select_novel);	
				list=file.list();
			    mu();
                   books.setVisible(false);
                }
            }
        });
		books.getContentPane().add(lable_book,BorderLayout.NORTH);
		books.getContentPane().add(jScrollPane2, BorderLayout.CENTER);
		books.setVisible(true);
	}
	
	public void print_novel(String name){
		
		String pp=select_novel+"\\"+name;
		System.out.println(pp);
		 WenJian w=new WenJian(pp);
					String wenjian=w.main();
					textArea.setText(wenjian);
					textArea.setCaretPosition(0);
					
					textArea.setFont(x);
					String b=select_novel.substring(6);
					lblNewLabel_1.setText("《"+b+"》");
                    
	}
	
public void print_novel_z(String name){
		
		String pp="D:\\小说\\"+name;
		System.out.println(pp);
		 WenJian w=new WenJian(pp);
					String wenjian=w.main();
					textArea.setText(wenjian);
					textArea.setCaretPosition(0);
					
					textArea.setFont(x);
					String b=select_novel.substring(6);
					lblNewLabel_1.setText("《"+b+"》");
                    //System.out.println(obj.toString());
	}
	
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 1274, 714);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 303, 667);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		button_1 = new JButton("在线阅读");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Online().main();
			}
		});
		button_1.setBounds(14, 106, 278, 64);
		panel.add(button_1);
		
		button_2 = new JButton("最近阅读");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					local_reading();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_2.setBounds(14, 168, 278, 58);
		panel.add(button_2);
		
		btnNewButton_1 = new JButton("本地阅读");
		
		
		btnNewButton_1.setBounds(14, 224, 278, 64);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("正在阅读：");
		lblNewLabel.setBounds(14, 434, 92, 44);
		panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(102, 434, 190, 44);
		panel.add(lblNewLabel_1);
		
		String [] size=new String[3];
		size[0]="小";
		size[1]="中";
		size[2]="大";
		JComboBox comboBox = new JComboBox(size);
		
		comboBox.setBounds(162, 502, 134, 37);
		panel.add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("字体大小");
		lblNewLabel_2.setBounds(14, 502, 134, 37);
		panel.add(lblNewLabel_2);
		
		JRadioButton radioButton = new JRadioButton("是");
		radioButton.setBounds(134, 311, 51, 44);
		
		panel.add(radioButton);
		
		JLabel lblNewLabel_3 = new JLabel("自动阅读");
		lblNewLabel_3.setBounds(14, 311, 100, 44);
		panel.add(lblNewLabel_3);
		
		JRadioButton radioButton_1 = new JRadioButton("否",true);
		radioButton_1.setBounds(191, 311, 63, 44);
		panel.add(radioButton_1);
	
		
		panel_1 = new JPanel();
		panel_1.setBounds(303, 608, 953, 59);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		button_3 = new JButton("《上一章");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jList1Model =  new DefaultComboBoxModel(list);
				myJlist.setModel(jList1Model); 
				if(chapter_num>0){
					chapter_num--;
					try {
						detection(select_novel.substring(6), chapter_name,select_novel+"\\"+chapter_name);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
					
				 Object obj = myJlist.getModel().getElementAt(chapter_num);  //取出数据
				 print_novel(obj.toString());
				
			}
		});
		button_3.setBounds(86, 13, 165, 33);
		panel_1.add(button_3);
		
		button_4 = new JButton("下一章》");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jList1Model =  new DefaultComboBoxModel(list);
				myJlist.setModel(jList1Model); 
				if(chapter_num<list.length-1){
					chapter_num++;
					try {
						detection(select_novel.substring(6), chapter_name,select_novel+"\\"+chapter_name);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				 Object obj = myJlist.getModel().getElementAt(chapter_num);  //取出数据
				 print_novel(obj.toString());
			}
		});
		button_4.setBounds(701, 13, 172, 33);
		panel_1.add(button_4);
		
		JButton button_5 = new JButton("章节目录");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mu();
				
			}
		});
		button_5.setBounds(399, 13, 172, 33);
		panel_1.add(button_5);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(303, 0, 953, 608);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(314, 239, 2, 2);
		panel_2.add(scrollPane);
		
		
		textArea.setEditable(false);
		//textArea.setBounds(14, 13, 925, 582);
		
		textArea.setLineWrap(true);
		JScrollPane s1=new JScrollPane(textArea);
		s1.setBounds(14, 13, 925, 582);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 book();
		
			}
		});
		buttongroup.add(radioButton_1);
		buttongroup.add(radioButton);
		
		JLabel label = new JLabel("\u81EA\u52A8\u9605\u8BFB\u901F\u5EA6");
		label.setBounds(14, 360, 113, 37);
		panel.add(label);
		
		String [] select_speed =new String[3];
		select_speed[0]="快";
		select_speed[1]="中";
		select_speed[2]="慢";
		JComboBox comboBox_1 = new JComboBox(select_speed);
		comboBox_1.setBounds(131, 360, 134, 31);
		panel.add(comboBox_1);
		
		JLabel lblNewLabel_4 = new JLabel("  kk小说阅读");
		lblNewLabel_4.setFont(new Font("宋体",0,40));
		lblNewLabel_4.setBounds(14, 0, 275, 106);
		panel.add(lblNewLabel_4);
		comboBox_1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(comboBox_1.getSelectedItem().equals("快")){
					 c_speed(10);
				}
				else if(comboBox_1.getSelectedItem().equals("中")){
					 c_speed(50);
				}
				else if(comboBox_1.getSelectedItem().equals("慢")){
					 c_speed(100);
				
				}
			}
			
		});
		
		comboBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(comboBox.getSelectedItem().equals("小")){
					    x = new Font("宋体",0,20);				   
					textArea.setFont(x);
				}
				else if(comboBox.getSelectedItem().equals("中")){
					 x = new Font("宋体",0,30);
					textArea.setFont(x);
				}
				else if(comboBox.getSelectedItem().equals("大")){
					  x = new Font("宋体",0,40);
					textArea.setFont(x);
				}
			}
			
		});
		
		radioButton.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				open();
			
				System.out.println("线程："+thread.isAlive());
			
			}
			
		});
	
		radioButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
	      run=false;
                 close();
			}
			
		});
		panel_2.add(s1);
	}

	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub
		while(run){
			synchronized(this)  {
				for(int i=textArea.getCaretPosition();i<textArea.getDocument().getLength();i++){
					if(run==false)
						break;
					textArea.setCaretPosition(i);
					System.out.println(i);
				try {
				Thread.sleep(speed);
				} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}		
			 }
					
			}
		
		}
	}
	
	void c_speed(int i){
		this.speed=i;
	}
	
	void close(){	
		run=false;
		System.out.println(this.speed);
	}
	void open(){
		 thread=new Thread(this);	
		 thread.start();
		run=true;
	}
	
	public int querystudent(String book_name) throws SQLException{
		int a=0;	  
		String sql="select * from local_reading";
				local_r.sq.rs=local_r.sq.stmt.executeQuery(sql);
				String a1;
				while(local_r.sq.rs.next()){
				a1=local_r.sq.rs.getString(2);
				
					if(a1.equals(book_name))
				     {					
						a=1;
				     }
			}						
		return a;
	}
	
	public void update(String book_name,String chapter_name,String path) throws SQLException{
		String sql="update local_reading set lujing=?,zhangjie=? where book_name=?";
		local_r.sq.pstmt=local_r.sq.conn.prepareStatement(sql);
		local_r.sq.pstmt.setString(1, path);
		local_r.sq.pstmt.setString(2, chapter_name);
		local_r.sq.pstmt.setString(3, book_name);
		local_r.sq.pstmt.executeUpdate();
	}
	
	public void add(String book_name,String chapter_name,String path) throws SQLException{
		String sql="insert into local_reading values(?,?,?)";
		local_r.sq.pstmt=local_r.sq.conn.prepareStatement(sql);
		local_r.sq.pstmt.setString(1, path);
		local_r.sq.pstmt.setString(2, book_name);
		local_r.sq.pstmt.setString(3, chapter_name);
		local_r.sq.pstmt.executeUpdate();
		
	}
	
	public void detection(String book_name,String chapter_name,String path) throws SQLException{
		if(this.querystudent( book_name)==1){
			update(book_name,chapter_name,path);
		}
		else
			add(book_name,chapter_name,path);
		
	}
	
	}
