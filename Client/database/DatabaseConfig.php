<?php
session_start();
class DataBaseConfig{
    public $servername;
    public $username;
    public $password;
    public $databasename;

    public function __construct(){
        $this->servername = 'iqueue123.laygolan.com';
        $this->username = 'u769315562_helikopter';
        $this->password = 'NoW4yHome';
        $this->databasename = 'u769315562_iqueue123';
    }
}
?>