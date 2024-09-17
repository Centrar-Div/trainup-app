import React from 'react';
import '../styles/rutinas.css';
import { useNavigate } from 'react-router-dom';
import { useLogin } from '../context/LoginContext';
import { completarRutina } from '../api/Api';
import NotRutins from '../utils/NotRutins';

const ListRutinas = ({ rutinas, esCompletada}) => {
  const navigate = useNavigate();
  const { user } = useLogin();

  const handlerClick = (rutina) => {
    navigate('/es/home/rutina', { state: { ejercicios: rutina.ejercicios, nombre: rutina.nombre } });
  };

  const marcarComoCompletada = async (rutinaId) => {
    await completarRutina(user.id, rutinaId);
  };

  return (
    <div className='container-boxinfo'>
      {
        rutinas && rutinas.length > 0 ? (
          rutinas.map(rutina => (
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
                <p className={`status ${esCompletada ? 'completed' : 'incomplete'}`}>
                  {esCompletada ? 'Completada' : 'No Completada'}
                </p>
                <p>{rutina.fechaDeCreacion}</p>
              </div>

              {!esCompletada && (
                <button 
                  className={`complete-btn ${rutina.completada ? 'completed' : ''}`} 
                  onClick={(e) => {
                    e.stopPropagation();
                    marcarComoCompletada(rutina.id);
                  }}
                >
                  {rutina.completada ? '✓' : 'O'}
                </button>
              )}
            </div>
          ))
        ) : (
          <NotRutins/>
        )
      }
    </div>
  );
};

export default ListRutinas;
