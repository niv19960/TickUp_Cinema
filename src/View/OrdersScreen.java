package View;

import Controller.Manager;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrdersScreen extends MoviesFrame {
    private JTextArea textArea = new JTextArea("", 6, 20);
    JButton back = new JButton("Back to Main Menu");

    public OrdersScreen(){
        JLabel title = new JLabel("Orders", SwingConstants.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 40));
        title.setBounds(0, 0, 999, 60);
        title.setForeground(textColor);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);

        JPanel top = new JPanel();
        top.setBounds(0, 0, 999, 80);
        top.setLayout(new BorderLayout());
        top.add(title);
        top.setBackground(backgroundColor);

        ArrayList<String> ordersPhones = Manager.getOrdersPhones();

        JPanel ordersPanel = new JPanel();
        ordersPanel.setBounds(0, 80, 500, 373);
        ordersPanel.setBackground(backgroundColor);

        JPanel orderInfo = new JPanel();
        orderInfo.setBounds(500, 80, 499, 373);
        orderInfo.setBackground(backgroundColor);

        JPanel bottom = new JPanel();
        bottom.setBounds(0, 450, 999, 110);
        bottom.setBackground(backgroundColor);

        back.setBounds(350, 480, 299, 50);
        back.setFont(new Font("Tahoma", Font.BOLD, 22));;
        back.setBackground(buttonColor);
        back.setForeground(textColor);
        back.setFocusable(false);
        back.setHorizontalAlignment(SwingConstants.CENTER);
        back.setVerticalAlignment(SwingConstants.CENTER);

        bottom.add(back);

        JList list = new JList(ordersPhones.toArray());
        list.setBackground(buttonColor);
        list.setForeground(textColor);
        list.setFont(new Font("Tahoma", Font.PLAIN, 20));

        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(400, 350));

        textArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setBackground(backgroundColor);
        textArea.setForeground(textColor);

        JScrollPane contentScroller = new JScrollPane(textArea);
        contentScroller.setPreferredSize(new Dimension(400, 350));
        contentScroller.getViewport().setOpaque(false);
        contentScroller.setOpaque(false);
        contentScroller.setBorder(null);

        orderInfo.add(contentScroller);
        ordersPanel.add(listScroller);
        this.add(top);
        this.add(ordersPanel);
        this.add(orderInfo);
        this.add(bottom);

        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                JList source = (JList)event.getSource();
                int selectedOrder = source.getSelectedIndex();
                String content = Manager.getOrderInfo(selectedOrder);
                textArea.setText(content);
            }
        });

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Manager.backToMainScreen();
            }
        });
    }
}
