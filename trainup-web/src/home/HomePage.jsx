import React from 'react';
import '../styles/rutinas.css'
import { useNavigate } from 'react-router-dom';
import { useLogin } from '../context/LoginContext';

const HomePage = () => {
  const navigate = useNavigate();
  const { user } = useLogin();

  const handlerClick = (rutina) => {
    navigate('/es/home/rutina', { state: { ejercicios: rutina.ejercicios, nombre: rutina.nombre } });
  };

  return (
    <div>
      <h1>Home</h1>
      <div className='container-boxinfo'>
        {
          user?.rutinasSeguidas && user.rutinasSeguidas.length > 0 ? (
            user.rutinasSeguidas.map(rutina => (
              <div 
                key={rutina.id} 
                onClick={() => handlerClick(rutina)} 
                className='boxinfo'>
                <div className="card-header">
                  <h2>{rutina.nombre}</h2>
                </div>

                <div className="card-body">
                  <p>{rutina.descripcion}</p>
                </div>

                <div className="card-footer">
                  <p className="category">{rutina.categoria}</p>
                  <p>{rutina.fechaDeCreacion}</p>
                </div>
              </div>
            ))
          ) : (
            <p>No tienes rutinas seguidas en este momento.</p> 
          )
        }
      </div>
    </div>
  );
};

export default HomePage;
