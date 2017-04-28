<?php
require 'lib/hms.inc.php';
$db=mysql_connect(MYSQL_HOST,MYSQL_USER,MYSQL_PASS) or die('Unable to connect');
$query='create database if not exists hms';
mysql_query($query,$db) or die(mysql_error($db));
include_once 'lib/conn.php';
echo "Database created";
mysql_select_db(MYSQL_DB,$db) or die(mysql_error($db));

$query='create table if not exists admin(
        admin_id bigint(12) unsigned not null auto_increment,
        username varchar(20) not null unique,
        password char(41) not null,
        session_id bigint(12) not null default 0,
        PRIMARY KEY(admin_id)
        )ENGINE=MyISAM';
mysql_query($query,$db) or die(mysql_error($db));
echo "<br><br>Admin Table created";
$demo_pass=hash('md4',"jarvis");
$query='insert into admin
         (username,password)
        values
          ("admin","'.$demo_pass.'")';
mysql_query($query,$db) or die(mysql_error($db));
echo "<br><br>Admin Table Populated";

$query='create table if not exists student(
        student_id bigint(12) unsigned not null auto_increment,
        username varchar(20) not null unique,
        password char(41) not null,
        pic_name varchar(30),
        session_id bigint(12) not null default 0,
        name varchar(100),
        roll_no varchar(12) unique,
        email varchar(60) unique,
        address1 varchar(200),
        address2 varchar(200),
        city varchar(30),
        pincode varchar(6),
        batch varchar (10),
        stream varchar(5),
        room_no integer(3),
        allot_date Date,
        PRIMARY KEY(student_id)
        )ENGINE=MyISAM';
mysql_query($query,$db) or die(mysql_error($db));
$query='create table if not exists room(
        room_no integer(3) unsigned not null,
        1st_seat1 varchar(12) default "",
        1st_seat2 varchar(12) default "",
        2nd_seat1 varchar(12) default "",
        2nd_seat2 varchar(12) default "",
        3rd_seat1 varchar(12) default "",
        3rd_seat2 varchar(12) default "",
        4th_seat1 varchar(12) default "",
        4th_seat2 varchar(12) default "",
        PRIMARY KEY(room_no)
        )ENGINE=MyISAM';
mysql_query($query,$db) or die(mysql_error($db));

for($i=1;$i<=100;$i++){
    $query='insert into room
         (room_no)
        values
          ("'.$i.'")';
mysql_query($query,$db) or die(mysql_error($db));
}

echo "<br><br>Student Table created";
$demo_pass=hash('md4',"student");
$query='insert into student
         (username,password,pic_name,name,roll_no,email,address1,address2,city,pincode,batch,stream)
        values
          ("jarvis","'.$demo_pass.'","NPP.png","Nimish Dusad","IIT2015509","demo@gmail.com","myplace","","Asansol","713301","2015-2019","IT")';
mysql_query($query,$db) or die(mysql_error($db));
echo "<br><br>Student Table Populated";

$query='create table if not exists pb(
        student_id bigint(12) unsigned not null,
        username varchar(20) not null unique,
        password char(41) not null,
        PRIMARY KEY(student_id)
        )ENGINE=MyISAM';
mysql_query($query,$db) or die(mysql_error($db));
echo "<br><br>Password backup Table created";
?>