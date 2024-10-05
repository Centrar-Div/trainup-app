import React, { useState } from 'react';
import { actualizarEjercicio, actualizarUsuario } from '../api/Api';  
import { notification } from 'antd';  
import { useLogin } from '../context/LoginContext'; 

const Ejercicio = ({ ejercicio }) => {
    const [completado, setCompletado] = useState(ejercicio.completado);
    const { user } = useLogin(); 

    const handleCheckboxChange = async () => {
        const updatedEjercicio = {
            id: ejercicio.id,
            nombre: ejercicio.nombre,
            descripcion: ejercicio.descripcion,
            repeticiones: ejercicio.repeticiones,
            peso: ejercicio.peso,
            musculo: ejercicio.musculo,
            series: ejercicio.series,
            descansoSegundos: ejercicio.descansoSegundos,
            equipo: ejercicio.equipo,
            instrucciones: ejercicio.instrucciones,
            completado: !completado 
        };
        
        setCompletado(!completado);
    
        try {
            await actualizarEjercicio(updatedEjercicio);
            
            const usuarioActualizado = {
                ...user,
                rutinasSeguidas: user.rutinasSeguidas.map(rutina => ({
                    ...rutina,
                    ejercicios: rutina.ejercicios.map(ej => 
                        ej.id === ejercicio.id ? updatedEjercicio : ej
                    )
                }))
            };

            await actualizarUsuario(usuarioActualizado); 
            notification.success({
                message: 'Ejercicio actualizado',
                description: `El ejercicio "${ejercicio.nombre}" ha sido marcado como ${!completado ? 'completado' : 'incompleto'}.`,
                placement: 'topRight',
            });
        } catch (error) {
            console.error('Error actualizando ejercicio:', error);
            notification.error({
                message: 'Error al actualizar',
                description: `No se pudo actualizar el estado del ejercicio "${ejercicio.nombre}".`,
                placement: 'topRight',
            });
        }
    };
    

    return (
        <div className='boxinfo'>
            <div className="card-header">
                <h3>{ejercicio.nombre}</h3>
            </div>
            <div className="card-body">
                <p>{ejercicio.descripcion}</p>
            </div>
            <div className="card-details">
                <p><strong>Repeticiones:</strong> {ejercicio.repeticiones}</p>
                <p><strong>Peso ideal:</strong> {ejercicio.peso} kg</p>
                <p><strong>Músculo:</strong> {ejercicio.musculo}</p>
            </div>
            <div className="card-footer">
                <label>
                    <input
                        type="checkbox"
                        checked={completado}
                        onChange={handleCheckboxChange}
                    />
                    {' '}Completado
                </label>
            </div>
        </div>
    );
};

export default Ejercicio;
