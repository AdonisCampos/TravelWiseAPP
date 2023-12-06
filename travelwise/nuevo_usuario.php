<?php
include 'conexion.php';

$usu_correo = $_POST['correo'];
$usu_usuario = $_POST['usuario'];
$usu_password = $_POST['password'];

$sentencia = $conexion->prepare("INSERT INTO usuarios (correo, usuario, password) VALUES (?, ?, ?)");

$sentencia->bind_param('sss', $usu_correo, $usu_usuario, $usu_password);

if ($sentencia->execute()) {
    $respuesta = array(
        "exito" => true,
        "mensaje" => "Usuario registrado exitosamente"
    );
    echo json_encode($respuesta);
} else {
    $respuesta = array(
        "exito" => false,
        "mensaje" => "Error al registrar usuario: " . $sentencia->error
    );
    echo json_encode($respuesta);
}

$sentencia->close();
$conexion->close();
?>
