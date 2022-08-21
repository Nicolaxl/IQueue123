<?php
require "DataBaseConfig.php";
session_start();

class DataBase{
    public $connect;
    public $data;
    private $sql;
    protected $servername;
    protected $username;
    protected $password;
    protected $databasename;

    public function __construct(){
		$dbc = new DataBaseConfig();
        $this->servername = $dbc->servername;
        $this->username = $dbc->username;
        $this->password = $dbc->password;
        $this->databasename = $dbc->databasename;
        $this->connect = mysqli_connect("p:" . $this->servername, $this->username, $this->password, $this->databasename);
        $this->data = null;
        $this->sql = null;
    }

    function dbConnect(){
        return $this->connect;
    }
	
	function closeCon(){
		return mysqli_close($this->connect);
	}

    function prepareData($data){
        return mysqli_real_escape_string($this->connect, stripslashes(htmlspecialchars($data)));
    }

    function logIn($table, $username, $password){
        $username = $this->prepareData($username);
        $password = $this->prepareData($password);
        $this->sql = 
			"SELECT * FROM `$table` WHERE Username = '$username' AND Password = '$password'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if(mysqli_num_rows($result) != 0){
            $dbusername = $row['Username'];
            $dbpassword = $row['Password'];
            if ($dbusername == $username && $dbpassword == $password) $login = true;
            else $login = false;
        } else $login = false;

        return $login;
    }

    function logInAd($table, $username, $password){
        $username = $this->prepareData($username);
        $password = $this->prepareData($password);
        $this->sql = 
			"SELECT * FROM `$table` WHERE Username = '$username' AND Password = '$password'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if(mysqli_num_rows($result) != 0){
            $dbusername = $row['Email'];
            $dbpassword = $row['Password'];
            if ($dbusername == $username && $dbpassword == $password) $login = true;
            else $login = false;
        } else $login = false;

        return $login;
    }
}