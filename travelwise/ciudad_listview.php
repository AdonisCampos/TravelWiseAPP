<?php
include 'conexion.php';

$idPais = $_POST['id_pais']; // Suponiendo que recibes el ID del país desde alguna solicitud POST

$queryCiudades = "SELECT id, nombre_ciudad FROM ciudades WHERE id_pais = $idPais";

$resultadoCiudades = mysqli_query($conexion, $queryCiudades);

if ($resultadoCiudades) {
    $ciudades = array();
    while ($filaCiudad = mysqli_fetch_assoc($resultadoCiudades)) {
        $ciudad = array(
            'id' => $filaCiudad['id'],
            'nombre_ciudad' => $filaCiudad['nombre_ciudad']
        );
        $ciudades[] = $ciudad;
    }

    echo json_encode($ciudades);
} else {
    $respuesta = array(
        "exito" => false,
        "mensaje" => "ID de país no recibido"
    );
    echo json_encode($respuesta);
}

mysqli_close($conexion);
?>
