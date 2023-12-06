<?php
include 'conexion.php';
$usu_usuario = $_POST['usuario'];
$usu_password = $_POST['password'];

//$usu_usuario="admin";
//$usu_password="123";

$sentencia = $conexion->prepare("SELECT * FROM usuarios WHERE usuario=? AND password=?");
$sentencia->bind_param('ss', $usu_usuario, $usu_password);
$sentencia->execute();

$resultado = $sentencia->get_result();
if ($fila = $resultado->fetch_assoc()) {
    echo json_encode($fila, JSON_UNESCAPED_UNICODE);
} else {
    // Si no hay resultados, se envía un JSON vacío o con un mensaje específico
    echo json_encode(array('message' => 'No se encontraron resultados'), JSON_UNESCAPED_UNICODE);
}
$sentencia->close();
$conexion->close();

?>

