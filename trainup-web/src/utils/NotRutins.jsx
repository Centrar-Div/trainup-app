import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/notRutins.css';

const NotRutins = () => {
  const navigate = useNavigate();

  return (
    <div className="no-rutinas">
      <div className="no-rutinas-message">
        <h2>No tienes rutinas seguidas en este momento</h2>
        <p>¡No te preocupes! Aquí podrás ver las rutinas que sigues una vez que empieces a seguir algunas.</p>
        <button className="explore-btn" onClick={() => navigate('/es/home/inProgress')}>Explorar Rutinas</button>
      </div>
    </div>
  );
}

export default NotRutins;
