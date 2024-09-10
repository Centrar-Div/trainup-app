import React from 'react'
import { useNavigate } from 'react-router-dom'

const LandingPage = () => {
    const navigate = useNavigate()

  return (
    <div className='trainUp-center'>
       <h1>TRAIN UP</h1>
       <h3>TU MEJOR APLICACION DE ENTRENAMIENTO</h3>
       <div className='flx gap-xl'>
         <button className='default-btn primary-btn' onClick={()=>navigate('/login')}>Iniciar sesion</button>
         <button className='default-btn primary-btn'>Registrarse</button>
       </div>
     </div>
  )
}

export default LandingPage