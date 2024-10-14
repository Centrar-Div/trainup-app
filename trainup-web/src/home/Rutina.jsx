import React from 'react';
import { useLocation } from 'react-router-dom';

const Rutina = () => {
    const location = useLocation();
    const { ejercicios, nombre } = location.state || {};

    return (
        <>
            <h1>Ejercicios de {nombre}</h1>
            <div className='container-boxinfo'>
                {ejercicios.map(ejercicio => (
                    <div key={ejercicio.id} className='boxinfo'>
                        <div className="card-header">
                            <h3>{ejercicio.nombre}</h3>
                        </div>
                        <div className="card-body">
                            <p>{ejercicio.descripcion}</p>
                        </div>
                        <div className="card-details">
                            <p><strong>Repeticiones:</strong> {ejercicio.repeticiones}</p>
                            <p><strong>Peso ideal:</strong> {ejercicio.peso} kg</p>
                            <p><strong>Ejercicio:</strong> {ejercicio.musculos}</p>
                        </div>
                    </div>
                ))}
            </div>
        </>
    );
};

export default Rutina;
