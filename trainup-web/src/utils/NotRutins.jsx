import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/notRutins.css';

const NotRutins = ({ titulo, mensaje, showButton }) => {
  const navigate = useNavigate();

  return (
    <div className="no-rutinas">
      <div className="no-rutinas-message">
        <h2>{titulo}</h2>
        <p>{mensaje}</p>

        <button className={`explore-btn ${showButton ? '' : 'none'}`} onClick={() => navigate('/es/home/explorador')}>Explorar Rutinas</button>
      </div>
    </div>
  );
}

export default NotRutins;
