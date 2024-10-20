import React, { useState } from 'react'
import { notification } from 'antd';
import Form from '../login/Form'
import ElementForm from '../login/ElementForm'
import { crearRutina } from '../api/Api'
import { useNavigate } from 'react-router-dom'


const CrearRutina = () => {

    const navigate = useNavigate()

    const [nombre, setNombre] = useState('')
    const [descripcion, setDescripcion] = useState('')
    const [categoria, setCategoria] = useState('')
    const [dificultad, setDificultad] = useState('')
    const dificultades = ['Principiante', 'Intermedio', 'Avanzado']


    const handlerSubmit = (e) => {
        e.preventDefault(); // no eliminar
        crearRutina({ nombre, descripcion, categoria, dificultad }).then(({ data }) => {
            navigate('/es/home')
        }).catch((error) => {
            notification.error({
                message: 'Campos vacios',
                description: 'Por favor complete todos los campos',
                placement: 'topRight',
            });
        });
    }

    const handleDificultadChange = (e) => {
        const selectedDificultad = e.target.value;
        setDificultad(selectedDificultad)
    }

    return (
        <div className='max-size-vh flx center '>
            <Form name='Nueva Rutina' btnName='Guardar' handlerSubmit={handlerSubmit}>

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
                <select value={dificultad} onChange={handleDificultadChange} className='modern-btn primary-btn'>
                    <option value="" disabled>Dificultad</option>
                    {dificultades.map((dif) =>
                        <option value={dif} key={dif}>{dif}</option>)}
                </select>
            </Form>
        </div>
    )
}

export default CrearRutina