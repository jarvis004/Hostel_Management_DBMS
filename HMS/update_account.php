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
$rent=mysql_real_escape_string($_POST['rentBox']);
$mess=mysql_real_escape_string($_POST['messBox']);
$roll=mysql_real_escape_string($_POST['roll']);
$year=mysql_real_escape_string($_POST['year']);

if($rent[0]=="1") $janRent="paid"; else $janRent="due";
if($rent[1]=="1") $aprRent="paid"; else $aprRent="due";
if($rent[2]=="1") $julRent="paid"; else $julRent="due";
if($rent[3]=="1") $octRent="paid"; else $octRent="due";

if($mess[0]=="1") $janMess="paid"; else $janMess="due";
if($mess[1]=="1") $febMess="paid"; else $febMess="due";
if($mess[2]=="1") $marMess="paid"; else $marMess="due";
if($mess[3]=="1") $aprMess="paid"; else $aprMess="due";

if($mess[4]=="1") $mayMess="paid"; else $mayMess="due";
if($mess[5]=="1") $junMess="paid"; else $junMess="due";
if($mess[6]=="1") $julMess="paid"; else $julMess="due";
if($mess[7]=="1") $augMess="paid"; else $augMess="due";

if($mess[8]=="1") $sepMess="paid"; else $sepMess="due";
if($mess[9]=="1") $octMess="paid"; else $octMess="due";
if($mess[10]=="1") $novMess="paid"; else $novMess="due";
if($mess[11]=="1") $decMess="paid"; else $decMess="due";


$q='update '.$roll.'_accounts 
    set jan_mar="'.$janRent.'",
        apr_jun="'.$aprRent.'",
        jul_sep="'.$julRent.'",
        oct_dec="'.$octRent.'",
            
        jan="'.$janMess.'",
        feb="'.$febMess.'",
        mar="'.$marMess.'",
        apr="'.$aprMess.'",
          
        may="'.$mayMess.'",
        jun="'.$junMess.'",
        jul="'.$julMess.'",
        aug="'.$augMess.'",
            
        sep="'.$sepMess.'",
        oct="'.$octMess.'",
        nov="'.$novMess.'",
        december="'.$decMess.'"
        
    where year="'.$year.'"';
$result=mysql_query($q,$db) or die(mysql_error($db));
  $add=array(
      'error'=>false
  );
    echo json_encode($add);

?>