import React, { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import { useLogin } from '../context/LoginContext';
import FollowBtn from './FollowBtn';
import Loader from '../utils/Loader';
import Ejercicio from './Ejercicio';

const Rutina = () => {
    const location = useLocation();
    const { rutinaID, ejercicios, nombre } = location.state || {};
    const { user } = useLogin();
    const [isFollowed, setIsFollowed] = useState(false);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        if (user) {
            const sigueRutina = user.rutinasSeguidas.some(rutina => rutina.id === rutinaID);
            setIsFollowed(sigueRutina);
            setIsLoading(false);
        }
    }, [user, rutinaID]);

    if (isLoading) {
        return (
            <div className="rutinas-completadas-container">
                <Loader />
            </div>
        );
    }

    return (
        <>
            <h1>Ejercicios de {nombre}</h1>
            <FollowBtn initFollow={isFollowed} rutinaID={rutinaID} />
            <div className='container-boxinfo'>
                {ejercicios.map(ejercicio => (
                    <Ejercicio key={ejercicio.id} ejercicio={ejercicio} />
                ))}
            </div>
        </>
    );
};

export default Rutina;
