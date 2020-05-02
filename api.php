<?php
if($_GET['type']==1)
{

    $name=$_GET['name'];
    $phone=$_GET['phone'];
    $email=$_GET['email'];
    $password=$_GET['password'];
    $public_key=$_GET['public_key'];
    $conn = mysqli_connect('localhost','root','','smart_msg') or die('unable to connect');

    $result=mysqli_query($conn,"insert into user (name,phone,email,password,public_key) values ('$name','$phone','$email','$password','$public_key')");
    if($result)
        echo "0";else echo "-1";
}
if($_GET['type']==2)
{

    $email=$_GET['email'];
    $password=$_GET['password'];
    $conn = mysqli_connect('localhost','root','','smart_msg') or die('unable to connect');

    $result=mysqli_query($conn,"select * from user where email='$email' and password='$password'");
    if(mysqli_num_rows($result)>0)
            {
            $users=mysqli_query($conn,"SELECT name,f.friend as `email`,phone,public_key FROM user u ,friend_list f WHERE u.email='$email' and (u.email=f.user) ");
            $a=array();

            while($row1= mysqli_fetch_assoc($users))
            {
                array_push($a, $row1);
             }
             echo json_encode($a);
        }

    else "-1";
}
if($_GET['type']==3)
{
    $conn = mysqli_connect('localhost','root','','smart_msg') or die('unable to connect');


    $friends=mysqli_query($conn,"select * from user");
    $a=array();

    while($row1= mysqli_fetch_assoc($friends))
    {
        array_push($a, $row1);
    }
    echo json_encode($a);
}
if($_GET['type']==4)
{
    $user=$_GET['cuser'];
    $friend=$_GET['fuser'];
    $conn = mysqli_connect('localhost','root','','smart_msg') or die('unable to connect');

    $result=mysqli_query($conn,"insert into friend_list (user,friend) values ('$user','$friend')");
    if($result)
        echo "0";else echo "-1";
}
if ($_GET['type']==5)
{
    $email=$_GET['email'];
    $conn = mysqli_connect('localhost','root','','smart_msg') or die('unable to connect');


    $users=mysqli_query($conn,"SELECT name,email,phone,public_key FROM user u ,friend_list f WHERE u.email=f.user and f.user='$email'");
    $a=array();

    while($row1= mysqli_fetch_assoc($users))
    {
        array_push($a, $row1);
    }
    echo json_encode($a);
}
