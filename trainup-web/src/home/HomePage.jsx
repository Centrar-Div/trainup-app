import React from 'react';
import '../styles/rutinas.css'
import DataLoader from '../utils/DataLoader';
import { useNavigate } from 'react-router-dom'
import { obtenerRutinas } from "../api/Api"

const HomePage = () => {
  const navigate = useNavigate();

  const handlerClick = (rutina) => {
    console.log(rutina);
    navigate('/es/home/rutina', { state: { ejercicios: rutina.ejercicios, nombre: rutina.nombre } });
  }

  return (
    <DataLoader fetchData={obtenerRutinas}>
      {(rutinas) => (
        <div>
          <h1>Home</h1>
          <div className='container-boxinfo'>
            {
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
                    <p>{rutina.fechaDeCreacion}</p>
                  </div>
                </div>
              ))
            }
          </div>
        </div>
      )}
    </DataLoader>
  );
}

export default HomePage;
