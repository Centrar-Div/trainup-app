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
    const { rutinaID, nombre } = location.state || {};
    const { user } = useLogin();
    const [isFollowed, setIsFollowed] = useState(false);
    const [isLoading, setIsLoading] = useState(true);
    const [listaDeEjercicios, setListaDeEjercicios] = useState([])
    const [dificultad, setDificultad] = useState('')
    const [descripcion, setDescripcion] = useState('')
    const [imgSrc, setImgSrc] = useState('')

    useEffect(() => {
        obtenerRutinaPorId(rutinaID).then(({ data }) => {
            setListaDeEjercicios(data.ejercicios)
            setDescripcion(data.descripcion)
            setDificultad(data.dificultad)
            setImgSrc(data.img)
        }).catch((error) => {
            console.log(error)
        })

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

    const deleteEjercicio = (ejercicio) => {
        const ejercicios = listaDeEjercicios.filter(e => e.id !== ejercicio.id);
        setListaDeEjercicios(ejercicios);
    }

    const updateEjercicio = (ejercicio) => {
        const ejercicios = listaDeEjercicios.map(e => {
            if (e.id === ejercicio.id) {
                return ejercicio;
            }
            return e;
        });
        setListaDeEjercicios(ejercicios);
    }

    return (
        <>
            <div className="rutina-container">
                <div className="rutina-info">
                    <h1 className="rutina-title">Ejercicios de {nombre}</h1>
                    <div className="card-footer">
                        <p className={'category ' + `${dificultad.toLowerCase()}`}>{dificultad}</p>
                    </div>
    
                    {user.esAdmin ? (
                        <Button onClick={handleCreateExercise} type="primary" className="create-btn">
                            Crear Ejercicio
                        </Button>
                    ) : (
                        <FollowBtn initFollow={isFollowed} rutinaID={rutinaID} />
                    )}
                </div>
    
                {imgSrc && (
                    <img src={imgSrc} className="rutina-img" alt={`imagen de rutina ${nombre}`} />
                )}
            </div>
    
            <div className="container-boxinfo">
                {listaDeEjercicios.map(ejercicio => (
                    <Ejercicio key={ejercicio.id} updateEjercicio={updateEjercicio} deleteEjercicio={deleteEjercicio} ejercicio={ejercicio} rutinaID={rutinaID} />
                ))}
            </div>
        </>
    );
    
};

export default Rutina;
