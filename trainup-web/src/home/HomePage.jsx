import React from 'react'
import '../styles/rutinas.css'
import { Route, useNavigate } from 'react-router-dom'
import Rutina from './Rutina'

const HomePage = () => {
  const navigate = useNavigate()

  const rutinasMock = [
    {
      id: 'r_1',
      nombre: 'Rutina 1',
      descripcion: 'Esta es la rutina 1',
      categoria: 'Categoria 1',
      fechaDeCreacion: '2021-09-01',
      ejercicios: [
        {
          id: 'e_1',
          nombre: 'Jumping Jacks',
          descripcion: 'Este es el ejercicio 1',
          repeticiones: 10,
          peso: 10,
          musculos: 'Biceps'
        }
      ]
    },
    {
      id: 'r_2',
      nombre: 'Rutina 2',
      descripcion: 'Esta es la rutina 2',
      categoria: 'Categoria 2',
      fechaDeCreacion: '2021-09-02',
      ejercicios: [
        {
          id: 'e_2',
          nombre: 'Sentadillas',
          descripcion: 'Este es el ejercicio 2',
          repeticiones: 20,
          peso: 20,
          musculos: 'Triceps'
        }
      ]
    },
    {
      id: 'r_3',
      nombre: 'Rutina 3',
      descripcion: 'Esta es la rutina 3',
      categoria: 'Categoria 3',
      fechaDeCreacion: '2021-09-03',
      ejercicios: [
        {
          id: 'e_3',
          nombre: 'Flexiones',
          descripcion: 'Este es el ejercicio 3',
          repeticiones: 30,
          peso: 30,
          musculos: 'Pectorales'
        }
      ]
    }
  ]

  const handlerClick = (rutina) => {
    navigate('/es/home/rutina', { state: { ejercicios: rutina.ejercicios } })
  }

  return (
    <div>
      <h1>Home</h1>
      <div className='rutinas'>
        {
          rutinasMock.map(rutina => (
            <div key={rutina.id} onClick={handlerClick} className='rutina'>
              <h2>{rutina.nombre}</h2>
              <p>{rutina.descripcion}</p>
              <p>{rutina.categoria}</p>
              <p>{rutina.fechaDeCreacion}</p>
            </div>
          ))
        }
      </div>
    </div>
  )
}

export default HomePage
