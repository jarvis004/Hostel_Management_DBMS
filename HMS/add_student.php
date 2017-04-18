<?php
$headers = apache_request_headers();
if(!isset($headers['HoSTelMaNAGeMentSyStmAPP'])|| $headers['HoSTelMaNAGeMentSyStmAPP']!="HoSTelMaNAGeMentSyStmAPPCustmHdr"){
    header('HTTP/1.0 404 Not Found');
    exit;
}
session_start();
require_once 'lib/hms.inc.php';
include_once 'lib/conn.php';

$q='select session_id from admin
    where username="'.$_POST['username'].'"';
$result=mysql_query($q,$db) or die(mysql_error($db));
if(mysql_num_rows($result)>0){
    $row=mysql_fetch_array($result);
    if($row['session_id']!=0 && $row['session_id']!=$_POST['session_id']){
        $add=array(
            'error'=>true,
            'reason'=>"session expire"
        );
        echo json_encode($add);
        exit;
    }
}
else{
    $add=array(
        'error'=>true,
        'reason'=>"session expire"
    );
    echo json_encode($add);
    exit;
}
$firstname=mysql_real_escape_string($_POST['firstname']);
$lastname=mysql_real_escape_string($_POST['lastname']);
$full_name=$firstname.' '.$lastname;
$batch=mysql_real_escape_string($_POST['batch']);
$stream=mysql_real_escape_string($_POST['stream']);
$hostel=mysql_real_escape_string($_POST['hostel']);
$roll=mysql_real_escape_string($_POST['roll']);
$email=mysql_real_escape_string($_POST['email']);
$address1=mysql_real_escape_string($_POST['address1']);
$address2=mysql_real_escape_string($_POST['address2']);
$city=mysql_real_escape_string($_POST['city']);
$pincode=mysql_real_escape_string($_POST['pincode']);

$date=date('Y-m-d');

function generate_username(){
    $str1='abcdefghijklmnopqrstuvwxyz';
    $str2='ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    $str3='1234567890';
    $str4='@$_!()';
    $index1=rand(0,25);
    $index11=rand(0,25);
    $index2=rand(0,25);
    $index22=rand(0,25);
    $index3=rand(0,9);
    $index33=rand(0,9);
    $index4=rand(0,5);
    $index44=rand(0,5);
    $unm=substr($str1,$index1,1).substr($str3,$index3,1).substr($str4,$index4,1).substr($str2,$index2,1).
         substr($str3,$index33,1).substr($str4,$index44,1).substr($str1,$index11,1).substr($str2,$index22,1);
    return $unm;
}
function generate_password(){
    $str1='abcdefghijklmnopqrstuvwxyz';
    $str2='ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    $str3='1234567890';
    $str4='@$_!()';
    $index1=rand(0,25);
    $index11=rand(0,25);
    $index2=rand(0,25);
    $index22=rand(0,25);
    $index3=rand(0,9);
    $index33=rand(0,9);
    $index4=rand(0,6);
    $index44=rand(0,6);
    
    $ps=substr($str1,$index1,1).substr($str3,$index3,1).substr($str4,$index4,1).substr($str2,$index2,1).
         substr($str3,$index33,1).substr($str4,$index44,1).substr($str1,$index11,1).substr($str2,$index22,1);
    return $ps;
}                       //checking if roll exist
$q='select student_id from student
    where roll_no="'.$roll.'"';
$result=mysql_query($q,$db) or die(mysql_error($db));
if(mysql_num_rows($result)>0){
    $add=array(
      'error'=>true,
      'reason'=>"roll exist"
    );
    echo json_encode($add);
    exit;
}                       //checking if email exist
$q='select student_id from student
    where email="'.$email.'"';
$result=mysql_query($q,$db) or die(mysql_error($db));
if(mysql_num_rows($result)>0){
    $add=array(
      'error'=>true,
      'reason'=>"email exist"
    );
    echo json_encode($add);
    exit;
}
$nl=NULL;
$query='select room_no,'.$hostel.'_seat1,'.$hostel.'_seat2 from room
        where '.$hostel.'_seat1="" or
              '.$hostel.'_seat2="" limit 1';

$result=mysql_query($query,$db) or die(mysql_error($db));
if(mysql_num_rows($result)>0){
    $row=mysql_fetch_assoc($result);
    if($row[$hostel.'_seat1']==$nl)
        $seat="seat1";
    else
        $seat="seat2";
    $room_no=$row['room_no'];
}
else{
   $add=array(
      'error'=>true,
      'reason'=>"seat not available"
    );
    echo json_encode($add);
    exit; 
}

$pic_name="NPP.png";
if(isset($_FILES['studentpic'])){
    $pic_dir="Student_Pic/";
    if (is_uploaded_file($_FILES['studentpic']['tmp_name'])){
        $unsupported=false;
        list($width, $height, $type, $attr)=getimagesize($_FILES['studentpic']['tmp_name']);
        switch($type){
            case IMAGETYPE_GIF: 
                 $ext='.gif';
                 break;
            case IMAGETYPE_JPEG:
                 $ext='.jpg';
                 break;
            case IMAGETYPE_PNG:
                 $ext='.png';
                 break;
             default:
                 $unsupported=true;
        }
        if(!$unsupported){
          move_uploaded_file ($_FILES['studentpic'] ['tmp_name'],$pic_dir.$roll.$ext);
          $pic_name=$roll.$ext;
        }
        else{
            $add_student_pic_failed=array(
                'error'=>true,
                'reason'=>"Photograph is not a valid"
            );
            echo json_encode($add_student_pic_failed);
            exit;
        }
        
    }
    else{
        $add_student_pic_failed=array(
            'error'=>true,
            'reason'=>"Photograph could not be uploaded"
        );
        echo json_encode($add_student_pic_failed);
        exit;
    }
}


    while(1){
        $username=generate_username();
        $q='select * from student
            where username="'.$username.'"';
        $result=mysql_query($q,$db) or die(mysql_error($db));
        if(mysql_num_rows($result)==0)
            break;
    }
    $password=generate_password();
    $encp=hash('md4',$password);
    $query='update room set
             '.$hostel.'_'.$seat.'="'.$roll.'"
            where room_no="'.$room_no.'"';
    mysql_query($query,$db) or die(mysql_error($db));
    $query='insert into student
             (username,password,pic_name,name,roll_no,email,address1,address2,city,pincode,batch,stream,allot_date,room_no)
            values
             ("'.$username.'","'.$encp.'","'.$pic_name.'","'.$full_name.'","'.$roll.'","'.$email.'",
              "'.$address1.'","'.$address2.'","'.$city.'","'.$pincode.'","'.$batch.'","'.$stream.'","'.$date.'","'.$room_no.'")';
    mysql_query($query,$db) or die(mysql_error($db));
    $id=mysql_insert_id();
    $query='insert into pb
             (student_id,username,password)
            values
             ("'.$id.'","'.$username.'","'.$password.'")';
    mysql_query($query,$db) or die(mysql_error($db));
    
    //creating each users accounts bill table
    
    $query='create table if not exists '.$roll.'_accounts(
            year int(4) unsigned not null,
            jan_mar varchar(5) default "due",
            apr_jun varchar(5) default "due",
            jul_sep varchar(5) default "due",
            oct_dec varchar(5) default "due",
            jan varchar(5) default "due",
            feb varchar(5) default "due",
            mar varchar(5) default "due",
            apr varchar(5) default "due",
            may varchar(5) default "due",
            jun varchar(5) default "due",
            jul varchar(5) default "due",
            aug varchar(5) default "due",
            sep varchar(5) default "due",
            oct varchar(5) default "due",
            nov varchar(5) default "due",
            december varchar(5) default "due",
            jan_bill integer(5) default 0,
            feb_bill integer(5) default 0,
            mar_bill integer(5) default 0,
            apr_bill integer(5) default 0,
            may_bill integer(5) default 0,
            jun_bill integer(5) default 0,
            jul_bill integer(5) default 0,
            aug_bill integer(5) default 0,
            sep_bill integer(5) default 0,
            oct_bill integer(5) default 0,
            nov_bill integer(5) default 0,
            dec_bill integer(5) default 0,
            PRIMARY KEY(year)
           )ENGINE=MyISAM';
    mysql_query($query,$db) or die(mysql_error($db));
    $startYear=(int)date('Y');
    $end=explode('-',$batch);
    $endyear=(int)$end[1];
    
    for($i=$startYear;$i<=$endyear;$i++){
        $query='insert into '.$roll.'_accounts
             (year)
            values
              ("'.$i.'")';
        mysql_query($query,$db) or die(mysql_error($db));
    }
    
    //creating each users meal table
    
     $query='create table if not exists '.$roll.'_meals(
            day integer(2) unsigned not null,
            lunch varchar(3) default "on",
            dinner varchar(3) default "on",
            PRIMARY KEY(day)
           )ENGINE=MyISAM';
    mysql_query($query,$db) or die(mysql_error($db));
    for($i=1;$i<=31;$i++){
        $query='insert into '.$roll.'_meals
             (day)
            values
              ("'.$i.'")';
        mysql_query($query,$db) or die(mysql_error($db));
    }

/*
$email_body='<html>
               <head>
                   <style>
                      body{
                         margin-bottom: 0;
                         margin-top: 0;
                         margin-right: 0;
                         margin-left: 0;
                       }
                    </style>
                </head>
              <body>
                   <table style="width:100%;height:10%;background: #445555"><tr><td style="width:5%;"></td><td style="vertical-align: middle;color:#ffffff;font-size: 16px;font-weight:900; ">Dear &nbsp; '.$firstname.'</td></tr></table>
                   <table style="width:100%;height:90%;background: #efefef;"><tr>
                      <td style="text-align: left;vertical-align: top;height:10px;color:#334475;font-weight: 900;">  Your account details<br> 
                        Roll-No : '.$rollno.'<br> 
                        Email-id : '.$email.'<br>
                       </td></tr>
                    </table>
              </body>
                </html>';
  
        $headers = 'MIME-Version: 1.0' . "\r\n";
        $headers .= 'Content-type: text/html; charset=iso-8859-1' . "\r\n";
        $headers .= 'From: admin@aecmania.com' . "\r\n";
        echo 'arayn';  //used to distinguish the mail error from others
        mail($email,"Reset Your Password",$email_body,$headers);
 */

$add=array(
     'error'=>false,
     'username'=>$username,
     'password'=>$password
 );
echo json_encode($add);
?>