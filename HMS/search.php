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
 

if(isset($_POST['year']))
    $year=intval($_POST['year']);
else
    $year=date('Y');
$query=mysql_real_escape_string($_POST['query']);
$type=mysql_real_escape_string($_POST['type']);
if($type=="roll"){
    

    $q='select * from student
        where roll_no="'.$query.'"';
    $result=mysql_query($q,$db) or die(mysql_error($db));
    if(mysql_num_rows($result)>0){
        $row=mysql_fetch_array($result);
        $search=array(
            'result'=>"yes",
            'name'=>$row['name'],
            'roll'=>$row['roll_no'],
            'batch'=>$row['batch'],
            'stream'=>$row['stream'],
            'room_no'=>$row['room_no'],
            'pic_name'=>$row['pic_name'],
            'allot_date'=>$row['allot_date'],
            'mess_account'=>array(),
            'rent_account'=>array()
        );
        $query='select * from '.$query.'_accounts
            where year="'.$year.'"';
        $rs=mysql_query($query,$db) or die(mysql_error($db));
        $rw=mysql_fetch_array($rs);
        
       $allot_date=$row['allot_date'];
       $allot_date=explode("-",$allot_date);
       $allot_month=$allot_date[1];
       $allot_year=$allot_date[2];
       $jan_mar=$rw['jan_mar']=="due"?false:true;
       $apr_jun=$rw['apr_jun']=="due"?false:true;
       $jul_sep=$rw['jul_sep']=="due"?false:true;
       $oct_dec=$rw['oct_dec']=="due"?false:true;
       $jan=$rw['jan']=="due"?false:true;$feb=$rw['feb']=="due"?false:true;  $mar=$rw['mar']=="due"?false:true;$apr=$rw['apr']=="due"?false:true;
       $may=$rw['may']=="due"?false:true;$jun=$rw['jun']=="due"?false:true;$jul=$rw['jul']=="due"?false:true;$aug=$rw['aug']=="due"?false:true;
       $sep=$rw['sep']=="due"?false:true;$oct=$rw['oct']=="due"?false:true;  $nov=$rw['nov']=="due"?false:true;$dec=$rw['december']=="due"?false:true;
     /*  if($allot_year<=$year){
           if($allot_month>1)
               $jan="NA";
           if($allot_month>2)
               $feb="NA";
           if($allot_month>3){
               $jan_mar="NA";
               $mar="NA";
           }
           if($allot_month>4)
               $apr="NA";
           if($allot_month>5)
               $may="NA";
           if($allot_month>6){
               $apr_jun="NA";
               $jun="NA";
           }
           if($allot_month>7)
               $jul="NA";
           if($allot_month>8)
               $aug="NA";
           if($allot_month>9){
               $jul_sep="NA";
               $sep="NA";
           }
           if($allot_month>10)
               $oct="NA";
           if($allot_month>11)
               $nov="NA";
       }
       else{   
           $jan_mar="NA";$apr_jun="NA";$jul_sep="NA";$oct_dec="NA";
           $jan="NA";$feb="NA";$mar="NA";$apr="NA";$may="NA";$jun="NA";
           $jul="NA";$aug="NA";$sep="NA";$oct="NA";$nov="NA";$dec="NA";
       }
       */ 
        $search['rent_account'][0]=array(
            'jan_mar'=>$jan_mar,'apr_jun'=>$apr_jun,
            'jul_sep'=>$jul_sep,'oct_dec'=>$oct_dec
        );
        $search['mess_account'][0]=array(
            'jan'=>$jan,'feb'=>$feb,'mar'=>$mar,'apr'=>$apr,'may'=>$may,'jun'=>$jun,
            'jul'=>$jul,'aug'=>$aug,'sep'=>$sep,'oct'=>$oct,'nov'=>$nov,'dec'=>$dec,
        );
        $due_mess_bill=intval($rw['jan_bill'])+intval($rw['feb_bill'])+intval($rw['mar_bill'])+intval($rw['apr_bill'])+intval($rw['may_bill'])+intval($rw['jun_bill'])+intval($rw['jul_bill'])+intval($rw['aug_bill'])+intval($rw['sep_bill'])+intval($rw['oct_bill'])+intval($rw['nov_bill'])+intval($rw['dec_bill']);
        $search['due_mess_bil']=$due_mess_bill;
        echo json_encode($search);
        exit;
    }  
    else{
        $search=array(
            'result'=>"no"
        );
        echo json_encode($search);
        exit;
    }
}



else if($type=="name"){
    $q='select * from student
        where name LIKE "'.$query.'%"';
    $result=mysql_query($q,$db) or die(mysql_error($db));
    if(mysql_num_rows($result)>0){
        $search=array(
                'result'=>"yes",
                'data'=> array()
         );
        $i=0;
        
        while($row=mysql_fetch_array($result)){
            $search['data'][$i]=array(
                'name'=>$row['name'],
                'roll'=>$row['roll_no'],
                'batch'=>$row['batch'],
                'stream'=>$row['stream'],
                'room_no'=>$row['room_no'],
                'pic_name'=>$row['pic_name']
            );
            $i++;
        }
        echo json_encode($search);
        exit;
    }
    else{
        $search=array(
            'result'=>"no"
        );
        echo json_encode($search);
        exit;
    }
}

?>