<?php
include 'conexion.php';

// Recibimos los datos del formulario o petición
$id_usuario = $_POST["id_usuario"];
$correo_usuario = $_POST["correo_usuario"];
$password_usuario = $_POST["password_usuario"];

// Verificamos si la contraseña no está vacía para incluirla en la actualización
if (!empty($password_usuario)) {
    $query = "UPDATE usuarios SET correo = '$correo_usuario', password = '$password_usuario' WHERE id = $id_usuario";
} else {
    $query = "UPDATE usuarios SET correo = '$correo_usuario' WHERE id = $id_usuario";
}

$resultado = mysqli_query($conexion, $query);

if ($resultado) {
    $respuesta = array(
        "exito" => true,
        "mensaje" => "Usuario actualizado correctamente"
    );
    echo json_encode($respuesta);
} else {
    $respuesta = array(
        "exito" => false,
        "mensaje" => "Error al actualizar usuario: " . mysqli_error($conexion)
    );
    echo json_encode($respuesta);
}

mysqli_close($conexion);
?>
