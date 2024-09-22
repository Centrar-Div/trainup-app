import React, { useState } from 'react'
import Form from '../login/Form'
import ElementForm from '../login/ElementForm'
import { crearRutina } from '../api/Api'
import { useNavigate } from 'react-router-dom'


const CrearRutina = () => {

    const navigate = useNavigate()

    const [nombre, setNombre] = useState('')
    const [descripcion, setDescripcion] = useState('')
    const [categoria, setCategoria] = useState('')


    const handlerSubmit = (e) => {
        e.preventDefault(); // no eliminar
        crearRutina({nombre, descripcion, categoria}).then(({ data }) => {
            navigate('/es/home')
        }).catch((error) => {
            console.error(error);
        });
    }

  return (
    <div className='max-size-vh flx center '>
        <Form name='Crear Rutina' btnName='Crear Rutina' handlerSubmit={handlerSubmit}> 
        
            <ElementForm
                title='Titulo' 
                type='text' 
                id='titulo' 
                name='titulo' 
                setText={setNombre}
            />
            <ElementForm
                title='Descripcion' 
                type='text' 
                id='descripcion' 
                name='descripcion' 
                setText={setDescripcion}
            />
            <ElementForm
                title='Categoria' 
                type='text' 
                id='categoria' 
                name='categoria' 
                setText={setCategoria}
            />
        </Form>
    </div>
  )
}

export default CrearRutina