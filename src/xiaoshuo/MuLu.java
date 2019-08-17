package xiaoshuo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class MuLu {
	boolean sure=false;
    String novelname;
	JFrame mulu=new JFrame();
	JLabel lable_z=new JLabel("章节目录");
	public void mu(String [] a) {
		mulu.setLayout(new BorderLayout());
		mulu.setSize(300, 600);
		mulu.setLocation(500, 300);
		JScrollPane jScrollPane1 = new JScrollPane();    //滚动条panel
        jScrollPane1.setPreferredSize(new java.awt.Dimension(288, 164));
        ListModel jList1Model =  new DefaultComboBoxModel(a);
        JList myJlist = new JList();
        myJlist.setModel(jList1Model);            //设置数据
        jScrollPane1.setViewportView(myJlist);    //不能直接add
        mulu.add(lable_z,BorderLayout.NORTH);
        mulu.add(jScrollPane1, BorderLayout.CENTER);
      //  mulu.add(sure, BorderLayout.SOUTH);
         mulu.setVisible(true);
    myJlist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                   
                    JList myList = (JList) e.getSource();
                    int index = myList.getSelectedIndex();    //已选项的下标
                    Object obj = myList.getModel().getElementAt(index);  //取出数据
                   sure=true;
                   novelname=obj.toString();
                    //System.out.println(obj.toString());
                }
            }
        });
        
	}

}
