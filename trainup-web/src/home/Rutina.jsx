import React from 'react'
import { useLocation } from 'react-router-dom'

const Rutina = ({}) => {
    const location = useLocation()
    const { ejercicios, nombre } = location.state || {}

  return (
    <>
        <h1>Ejercicios de { nombre }</h1>
        <div className='container-boxinfo'>
            {
                ejercicios.map(ejercicio => (
                    <div key={ejercicio.id} className='boxinfo'>
                        <h3>{ejercicio.nombre}</h3>
                        <p>{ejercicio.descripcion}</p>
                        <p>{ejercicio.repeticiones}</p>
                        <p>{ejercicio.peso}</p>
                        <p>{ejercicio.musculos}</p>
                    </div>
                ))
            }
        </div>
    </>
  )
}

export default Rutina