<?php
include 'conexion.php';

$idDestino = $_GET['id_destino']; // Obtener el ID del destino mediante POST

$query = "SELECT dc.id as idDestinoCiudad, c.nombre_ciudad AS nombre_ciudad
          FROM destinos_ciudades dc
          INNER JOIN ciudades c ON dc.id_ciudad = c.id
          WHERE dc.id_destino = $idDestino"; // Agregar la cláusula WHERE

$resultado = mysqli_query($conexion, $query);

if ($resultado) {
    $ciudades = array();
    while ($fila = mysqli_fetch_assoc($resultado)) {
        $ciudad = array(
            'idDestinoCiudad' => $fila['idDestinoCiudad'],
            'nombre_ciudad' => $fila['nombre_ciudad']
        );
        $ciudades[] = $ciudad;
    }

    echo json_encode($ciudades);
} else {
    $respuesta = array(
        "exito" => false,
        "mensaje" => "Error al obtener datos de ciudades: " . mysqli_error($conexion)
    );
    echo json_encode($respuesta);
}

mysqli_close($conexion);

?>
