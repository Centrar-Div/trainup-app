import React from 'react'
import { useLocation } from 'react-router-dom'

const Rutina = ({}) => {
    const location = useLocation()
    const { ejercicios } = location.state || {}

  return (
    <div className='rutinas'>
        {
            ejercicios.map(ejercicio => (
                <div key={ejercicio.id} className='rutina'>
                    <h3>{ejercicio.nombre}</h3>
                    <p>{ejercicio.descripcion}</p>
                    <p>{ejercicio.repeticiones}</p>
                    <p>{ejercicio.peso}</p>
                    <p>{ejercicio.musculos}</p>
                </div>
            ))
        }
    </div>
  )
}

export default Rutina