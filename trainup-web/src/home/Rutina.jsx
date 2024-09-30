import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import { isFollowing } from '../api/Api';
import FollowBtn from './FollowBtn';
import Loader from '../utils/Loader';

const Rutina = () => {
    const location = useLocation();
    const { rutinaID, ejercicios, nombre } = location.state || {};
    const [isFollowed, setIsFollowed] = useState(false)
    const [isLoading, setIsLoading] = useState(true)

    useEffect(() => {
        isFollowing(rutinaID).then(({ data }) => {
            setIsFollowed(data);
            setIsLoading(false);
        });
    }, [rutinaID]);


    return (
        isLoading ?
            <div className="rutinas-completadas-container">
                <Loader />
            </div>
            :
            <>
                <h1>Ejercicios de {nombre}</h1>
                <FollowBtn initFollow={isFollowed} rutinaID={rutinaID} />
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
