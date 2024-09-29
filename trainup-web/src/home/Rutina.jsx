import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import { isFollowing } from '../api/Api';
import FollowBtn from './FollowBtn';

const Rutina = () => {
    const location = useLocation();
    const { rutinaID, ejercicios, nombre } = location.state || {};
    const [isFollowed, setIsFollowed] = useState(false)

    useEffect(() => {
        isFollowing(localStorage.getItem('id'), rutinaID).then(({ data }) => {
            setIsFollowed(data);
            console.log(data)
        });
    }, [rutinaID]);

    return (
        <>
            <h1>Ejercicios de {nombre}</h1>
            <FollowBtn initFollow={isFollowed} />
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
