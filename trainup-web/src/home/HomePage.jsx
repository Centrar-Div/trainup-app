import React from 'react';
import '../styles/home.css'; 
import ListRutinas from './ListRutinas';
import { useLogin } from '../context/LoginContext';
import Loader from '../utils/Loader';
import Favoritas from './Favoritas';

const HomePage = () => {
  const { user } = useLogin();

  if (!user || !user.rutinasSeguidas) {
    return (
      <div className="home-container">
        <h1 className="home-title">Bienvenido a tu página de inicio</h1>
        <Loader/>
      </div>
    );
  }

  return (
    <div className="home-container">
      <h1 className="home-title">Bienvenido a tu página de inicio</h1>
      <p className="home-subtitle">Aquí encontrarás las rutinas que sigues. ¡Explora y mantente activo!</p>
      <div className="list-rutinas-container">
        <Favoritas rutinas={user.rutinasFavoritas}/>
        <ListRutinas rutinas={user.rutinasSeguidas} esCompletada={false}/>
      </div>
    </div>
  );
};

export default HomePage;
