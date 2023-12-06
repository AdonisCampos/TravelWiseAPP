<?php
include 'conexion.php';

$idPais = $_POST['id_pais'];
$tituloDestino = $_POST['titulo_destino'];
$recomendaciones = $_POST['recomendaciones'];
$ciudades = explode(',', $_POST['ciudades_seleccionadas']);
$nombreUsuario = $_POST['nombre_usuario'];

// Obtener el ID del usuario usando el nombre de usuario
$sqlUsuario = "SELECT id FROM usuarios WHERE usuario  = '$nombreUsuario'";
$resultUsuario = mysqli_query($conexion, $sqlUsuario);

if ($rowUsuario = mysqli_fetch_assoc($resultUsuario)) {
    $idUsuario = $rowUsuario['id']; // ID del usuario obtenido

    // Generar el token MD5 basado en los datos
    $token = md5($tituloDestino . $idPais . $idUsuario . implode('', $ciudades));

    // Insertar datos en la tabla destinos
    $sqlDestinos = "INSERT INTO destinos (titulo, id_pais, creacion, ultima_modifiacion, id_usuario, token) 
                    VALUES ('$tituloDestino', $idPais, NOW(), NOW(), $idUsuario, '$token')";

    if (mysqli_query($conexion, $sqlDestinos)) {
        // Obtener el ID del último destino insertado usando el token generado
        $sqlObtenerDestinoID = "SELECT id FROM destinos WHERE token = '$token'";
        $resultado = mysqli_query($conexion, $sqlObtenerDestinoID);
        if ($fila = mysqli_fetch_assoc($resultado)) {
            $lastDestinoID = $fila['id'];

            // Insertar datos en la tabla destinos_ciudades para cada ciudad
            foreach ($ciudades as $ciudad) {
                $sqlCiudad = "SELECT id FROM ciudades WHERE nombre_ciudad = '$ciudad'";
                $resultCiudad = mysqli_query($conexion, $sqlCiudad);
                if ($rowCiudad = mysqli_fetch_assoc($resultCiudad)) {
                    $idCiudad = $rowCiudad['id'];
                    $sqlDestinosCiudades = "INSERT INTO destinos_ciudades (id_ciudad, id_destino, creacion, ultima_modifiacion) 
                                            VALUES ($idCiudad, $lastDestinoID, NOW(), NOW())";
                    mysqli_query($conexion, $sqlDestinosCiudades);
                }
            }

            // Insertar datos en la tabla destinos_recomendaciones con las recomendaciones
            $sqlDestinosRecomendaciones = "INSERT INTO destinos_recomendaciones (recomendacion, id_destino, creacion, ultima_modifiacion) 
                                           VALUES ('$recomendaciones', $lastDestinoID, NOW(), NOW())";
            mysqli_query($conexion, $sqlDestinosRecomendaciones);

            $respuesta = array(
                "exito" => true,
                "mensaje" => "Datos insertados correctamente"
            );
            echo json_encode($respuesta);
        } else {
            $respuesta = array(
                "exito" => false,
                "mensaje" => "Error al obtener el ID del destino"
            );
            echo json_encode($respuesta);
        }
    } else {
        $respuesta = array(
            "exito" => false,
            "mensaje" => "Error al insertar datos en destinos: " . mysqli_error($conexion)
        );
        echo json_encode($respuesta);
    }
} else {
    $respuesta = array(
        "exito" => false,
        "mensaje" => "Error al obtener el ID del usuario"
    );
    echo json_encode($respuesta);
}

mysqli_close($conexion);
?>
