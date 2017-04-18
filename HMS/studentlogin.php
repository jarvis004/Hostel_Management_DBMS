<?php
$headers = apache_request_headers();
if(!isset($headers['HoSTelMaNAGeMentSyStmAPP'])|| $headers['HoSTelMaNAGeMentSyStmAPP']!="HoSTelMaNAGeMentSyStmAPPCustmHdr" || 
   !isset($_POST['username']) && !isset($_POST['password'])){
    header('HTTP/1.0 404 Not Found');
    exit;
}
session_start();
require_once 'lib/hms.inc.php';
include_once 'lib/conn.php';
$username=mysql_real_escape_string($_POST['username']);
$password=hash('md4',$_POST['password']);
$session_id=0;
$loginReasonPassword=false;
$loginReasonUsername=false;
$student_name="";
$query='select student_id from student
        where username="'.$username.'"';
$result=mysql_query($query,$db) or die(mysql_error($db));
if(mysql_num_rows($result)==1){
    $q='select student_id from student
        where username="'.$username.'" and
              password="'.$password.'"';
    $rs=mysql_query($q,$db) or die(mysql_error($db));
    if(mysql_num_rows($rs)==1){
        $row=mysql_fetch_array($result);
        $loginSucessful=true;
        $session_id=rand(1000000000,9999999999);
        $query='update student
                set session_id='.$session_id.'
                where username="'.$username.'"';
        mysql_query($query,$db) or die(mysql_error($db));
        $query='select name from student
                where username="'.$username.'"';
        $rs=mysql_query($query,$db) or die(mysql_error($db));
        $row=mysql_fetch_array($rs);
        $student_name=$row['name'];
    }
    else{
        $loginSucessful=false;
        $loginReasonPassword=true;
    }
}
else{
    $loginSucessful=false;
    $loginReasonUsername=true;
}

$login=array(
     'login'=>$loginSucessful,
     'login_reason_username'=>$loginReasonUsername,
     'login_reason_password'=>$loginReasonPassword,
     'session_id'=>$session_id,
     'student_name'=>$student_name
 );
echo json_encode($login);
?>