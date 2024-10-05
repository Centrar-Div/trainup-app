import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import { isFollowing } from '../api/Api';
import FollowBtn from './FollowBtn';
import Loader from '../utils/Loader';
import Ejercicio from './Ejercicio';  

const Rutina = () => {
    const location = useLocation();
    const { rutinaID, ejercicios, nombre } = location.state || {};
    const [isFollowed, setIsFollowed] = useState(false);
    const [isLoading, setIsLoading] = useState(true);

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
                        <Ejercicio key={ejercicio.id} ejercicio={ejercicio} />
                    ))}
                </div>
            </>
    );
};

export default Rutina;
