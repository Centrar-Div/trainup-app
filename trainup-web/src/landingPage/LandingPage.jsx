import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Contact from '../home/Contact';
import "./landingPage.css"

const LandingPage = () => {
  const navigate = useNavigate();

  useEffect(() => {
    localStorage.removeItem('id');
  }, []);

  
  
  return (
    <div className='trainUp-center'>
      <header className='landing-header'>
        <h1>TRAIN UP</h1>
        <h3>TU MEJOR APLICACIÓN DE ENTRENAMIENTO</h3>
        <div className='mt-10 flx jc-center gap-s'>
          <button className='default-btn modern-btn' onClick={() => navigate('/login')}>Iniciar sesión</button>
          <button className='default-btn modern-btn'  onClick={() => navigate('/register')}>Empieza tu Entrenamiento</button>
        </div>
      </header>
      <section className='landing-features' id="services">
        <h2>¿Qué ofrece TRAIN UP?</h2>
        <div className='landing-com'>
          <div className='feature-item'>
            <h3>Planes Personalizados</h3>
            <p>Entrenamientos adaptados a tu nivel y metas.</p>
          </div>
          <div className='feature-item'>
            <h3>Seguimiento de Progreso</h3>
            <p>Mide tu evolución con estadísticas detalladas.</p>
          </div>
          <div className='feature-item'>
            <h3>Entrenadores Certificados</h3>
            <p>Conéctate con expertos para mejorar tu rendimiento.</p>
          </div>
        </div>
      </section>
      <Contact/>
      <footer className='footer'>
        <p>&copy; 2024 TRAIN UP. Todos los derechos reservados.</p>
      </footer>
    </div>
  );
}

export default LandingPage;
