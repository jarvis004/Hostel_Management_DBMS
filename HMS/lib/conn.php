<?php  
    $db=mysql_connect(MYSQL_HOST,MYSQL_USER,MYSQL_PASS) or die('Unable to connect');
    mysql_select_db(MYSQL_DB,$db) or die(mysql_error($db));
?>
