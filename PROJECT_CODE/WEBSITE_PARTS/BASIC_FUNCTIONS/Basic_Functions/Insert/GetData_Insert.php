<?php
/* Attempt MySQL server connection. Assuming you are running MySQL
server with default setting (user 'root' with no password) */
$link = mysqli_connect("localhost", "root", "", "reservation_system");
 
// Check connection
if($link === false){
    die("ERROR: Could not connect. " . mysqli_connect_error());
}
 
// Escape user inputs for security
$last_name = mysqli_real_escape_string($link, $_REQUEST['sor']);
$email = mysqli_real_escape_string($link, $_REQUEST['des']);
 
// attempt insert query execution
$sql = "INSERT INTO f_table (Source,Destination) VALUES ( '$last_name', '$email')";
if(mysqli_query($link, $sql)){
    echo "Records added successfully.";
} else{
    echo "ERROR: Could not able to execute $sql. " . mysqli_error($link);
}
 
// close connection
mysqli_close($link);
?>


