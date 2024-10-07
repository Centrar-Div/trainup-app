import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { useLogin } from '../context/LoginContext';
import FollowBtn from './FollowBtn';
import Loader from '../utils/Loader';
import Ejercicio from './Ejercicio';
import { Button } from 'antd'; 

const Rutina = () => {
    const location = useLocation();
    const navigate = useNavigate(); 
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

    const handleCreateExercise = () => {
        navigate('/es/home/crear/ejercicio', { state: { rutinaID } }); // Pasar rutinaID al navegar
    };

    return (
        <>
            <h1>Ejercicios de {nombre}</h1>
            <div className="rutina-header">
                <FollowBtn initFollow={isFollowed} rutinaID={rutinaID} />
                <Button onClick={handleCreateExercise} type="primary" style={{ marginLeft: '10px' }}>
                    Crear Ejercicio
                </Button>
            </div>
            <div className='container-boxinfo'>
                {ejercicios.map(ejercicio => (
                    <Ejercicio key={ejercicio.id} ejercicio={ejercicio} />
                ))}
            </div>
        </>
    );
};

export default Rutina;
