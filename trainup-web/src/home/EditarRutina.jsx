import React, { useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom'
import { actualizarRutina } from '../api/Api';
import Form from '../login/Form';
import ElementForm from '../login/ElementForm';

const EditarRutina = () => {
    const location = useLocation()
    const { rutinaId, rutinaNombre, rutinaDescripcion, rutinaCategoria, rutinaDificultad } = location.state;
    const navigate = useNavigate()

    const [nombre, setNombre] = useState(rutinaNombre)
    const [descripcion, setDescripcion] = useState(rutinaDescripcion)
    const [categoria, setCategoria] = useState(rutinaCategoria)
    const [dificultad, setDificultad] = useState(rutinaDificultad)
    const dificultades = ['Principiante', 'Intermedio', 'Avanzado']


    const handleDificultadChange = (e) => {
        const selectedDificultad = e.target.value;
        setDificultad(selectedDificultad)
    }

    const handlerSubmit = (e) => {
        e.preventDefault();
        if (rutinaId && nombre && descripcion && categoria && dificultad) {
            const body = {
                id: rutinaId,
                nombre,
                descripcion,
                categoria,
                dificultad,
            };

            actualizarRutina(body).then(({ data }) => {
                navigate('/es/home/explorador')
            }).catch((error) => {
                console.error(error);
            });
        }

    }

    return (
        <div className='max-size-vh flx center '>
            <Form name='Editar Rutina' btnName='Guardar' handlerSubmit={handlerSubmit}>

                <ElementForm
                    title='Titulo'
                    type='text'
                    id='titulo'
                    name='titulo'
                    setText={setNombre}
                    initialValue={nombre}
                    required={true}
                />
                <ElementForm
                    title='Descripcion'
                    type='text'
                    id='descripcion'
                    name='descripcion'
                    setText={setDescripcion}
                    initialValue={descripcion}
                    required={true}
                />
                <ElementForm
                    title='Categoria'
                    type='text'
                    id='categoria'
                    name='categoria'
                    setText={setCategoria}
                    initialValue={categoria}
                    required={true}
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

export default EditarRutina