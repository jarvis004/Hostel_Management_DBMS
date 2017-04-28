/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hostelmanagementsystem.lib;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JTextPane;

/**
 *
 * @author Ankit
 */
public class MyTextPane extends JTextPane {

    public MyTextPane(int a){
        super();
        setFont(new Font("Century Gothic",Font.BOLD,18));
       if (a==1){
    	   setText(" Day    \t \t Breakfast \t \t Lunch \t \t   Dinner \n\n Monday \t Aloo paratha \t \t butter paneer \t butter chicken\n Tuesday \t idli sambhar \t \t AlooBhindi \t Chinese\n Wednesday \t Bread Cutlet \t \t fish curry \t aloo gobhi \n Thusrday \t Aloo paratha \t \t pav Bhaji \t butter paneer\n Friday \t\t Eggs/Omelette \t \t chole bhature \t Lauki\n Saturday \t Poha Jalebi \t \t butter paneer \t Nan chicken\n Sunday \t\t Maggi/Noodles \t \t idli sambhar \t butter chicken\n ");
       }
        if(a==2){
        setText(" \nAs the name itself specifies -The Hostel Management System is a software developed for managing various activities in the hostel. This particular project deals with the problems on managing a hostel registration and mess registration as well as fee management for both and avoids the problems which occur when carried manually.It also provides students with an option to check their present fee account status and due dates for payment of fees. They can also select type of meal they want for the day or if they want to take meal off. An option for application for fee extension date is also provided which will be sent to admin and he needs to review and approve it.\n" +
        "Implemented in Java, MySQL for database connectivity a Hostel Management System is an automated and optimized system developed to facilitate the functioning of the hostel managing authorities. The project consists of client part and server. Server needs to store all the databases and client part can be run on any computer on LAN or INTERNET depending on institution's requirement.\n" +
        "The system is feasible considering the technical feasibility, operational feasibility, scheduling feasibility. It utilises the utilities of Java Swings for designing the Graphical User Interface. The software can be implemented in any academic institution for different tasks to be managed for hostel. The costs incurred in the development of the system is quite affordable considering the ultimate success of the modules involved.");
        }
        setOpaque(false);

        // this is needed if using Nimbus L&F - see http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6687960
        setBackground(new Color(178, 102, 255));
        setForeground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // set background green - but can draw image here too

        g.setColor(new Color(72, 61, 139));
        g.fillRect(0, 0, getWidth(), getHeight());

        // uncomment the following to draw an image
        //String img = "img.png";
        //g.drawImage("img.png", 0, 0, this);
        super.paintComponent(g);
    }
}
