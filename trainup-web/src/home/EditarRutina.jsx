import React, { useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom'
import { actualizarRutina } from '../api/Api';
import Form from '../login/Form';
import ElementForm from '../login/ElementForm';

const EditarRutina = () => {
    const location = useLocation()
    const { rutinaId, rutinaNombre, rutinaDescripcion, rutinaCategoria } = location.state;
    const navigate = useNavigate()

    const [nombre, setNombre] = useState(rutinaNombre)
    const [descripcion, setDescripcion] = useState(rutinaDescripcion)
    const [categoria, setCategoria] = useState(rutinaCategoria)


    const handlerSubmit = (e) => {
        e.preventDefault();
        actualizarRutina(rutinaId, { nombre, descripcion, categoria }).then(({ data }) => {
            navigate('/es/home/explorador')
        }).catch((error) => {
            console.error(error);
        });
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

export default EditarRutina