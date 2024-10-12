import React, { useState } from 'react'
import { useLocation } from 'react-router-dom';
import { useLogin } from '../context/LoginContext';
import { actualizarEjercicio } from '../api/Api';

const EjercicioDetalle = () => {
    const location = useLocation();
    const { ejercicio } = location.state || {};
    const user = useLogin();
    const [completado, setCompletado] = useState(ejercicio.completado);


    const handleCheckboxChange = async () => {


        const updatedEjercicio = {
            ...ejercicio,
            completado: !completado
        };

        setCompletado(!completado);

        actualizarEjercicio(updatedEjercicio).then(() => {
            completarONoEjercicio(user.id, rutinaID, ejercicio.id)
        }).catch(() => {
            notification.error({
                message: 'Error al actualizar',
                description: `No se pudo actualizar el estado del ejercicio "${ejercicio.nombre}".`,
                placement: 'topRight',
            });
        })
    };

    console.log(ejercicio);
    return (
        <div>
            <div>
                <h1>{ejercicio.nombre}</h1>
                {/* 
                {ejercicio && !user.esAdmin ?
                    <label className="checkbox-container">
                        <input
                            type="checkbox"
                            checked={completado}
                            onChange={handleCheckboxChange}
                        />
                        <span className="checkmark"></span>
                        {' '}Completado
                    </label>
                    :
                    <></>} */}
            </div>
            <p className='home-subtitle'>{ejercicio.descripcion}</p>
            <div className='exercise-container'>
                <div className="exercise-details">
                    <p><strong>Instrucciones:</strong> {ejercicio.instrucciones}</p>
                    <p><strong>Músculo:</strong> {ejercicio.musculo}</p>
                    <p><strong>Repeticiones:</strong> {ejercicio.repeticiones}</p>
                    <p><strong>Equipo:</strong> {ejercicio.equipo}</p>
                    <p><strong>Peso ideal:</strong> {ejercicio.peso} kg</p>
                    <p><strong>Series:</strong> {ejercicio.series}</p>
                    <p><strong>Segundos de descanso:</strong> {ejercicio.descansoSegundos}</p>

                </div>
            </div>


        </div>

    )
}

export default EjercicioDetalle