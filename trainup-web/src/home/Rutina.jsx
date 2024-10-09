import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { useLogin } from '../context/LoginContext';
import FollowBtn from './FollowBtn';
import Loader from '../utils/Loader';
import Ejercicio from './Ejercicio';
import { Button } from 'antd'; 
import { obtenerRutinaPorId } from '../api/Api';

const Rutina = () => {
    const location = useLocation();
    const navigate = useNavigate(); 
    const { rutinaID, ejercicios, nombre } = location.state || {};
    const { user } = useLogin();
    const [isFollowed, setIsFollowed] = useState(false);
    const [isLoading, setIsLoading] = useState(true);
    const [listaDeEjercicios, setListaDeEjercicios] = useState([])

    
    useEffect(() => {
        obtenerRutinaPorId(rutinaID).then(({ data })=>{
            console.log(data)
           // setListaDeEjercicios(data.ejercicios)
        })
        if (user) {
            const sigueRutina = user.rutinasSeguidas.some(rutina => rutina.id === rutinaID);
            setIsFollowed(sigueRutina);
            setIsLoading(false);
        }
    }, [user, rutinaID, listaDeEjercicios]);


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
                {listaDeEjercicios.map(ejercicio => (
                    <Ejercicio key={ejercicio.id} ejercicio={ejercicio} rutinaID={rutinaID} />
                ))}
            </div>
        </>
    );
};

export default Rutina;
