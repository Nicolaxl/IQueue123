<?php
require "DataBase.php";
$db = new DataBase();
if(isset($_POST['Username']) && isset($POST['Password'])) {
    if ($db->dbConnect()){
        if ($db->logInAd("Admin", $_POST['Username'], $_POST['Password'])) echo "Login success";
        else echo "Wrong username or password";
		$db->closeCon();
    } else echo "Error: Database connection";
}
?>