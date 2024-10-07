import React, { useState } from 'react';
import { notification } from 'antd';
import Form from '../login/Form';
import ElementForm from '../login/ElementForm';
import { agregarEjercicioARutina } from '../api/Api'; 
import { useNavigate, useLocation } from 'react-router-dom';

const CrearEjercicio = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const { rutinaID } = location.state || {}; 

    const [nombre, setNombre] = useState('');
    const [descripcion, setDescripcion] = useState('');
    const [repeticiones, setRepeticiones] = useState(0);
    const [peso, setPeso] = useState(0);
    const [musculo, setMusculo] = useState('');

    const handlerSubmit = (e) => {
        e.preventDefault(); 
        if (!nombre || !descripcion || repeticiones <= 0 || peso < 0 || !musculo) {
            notification.error({
                message: 'Campos inválidos',
                description: 'Por favor complete todos los campos correctamente',
                placement: 'topRight',
            });
            return;
        }

        agregarEjercicioARutina(rutinaID,{ nombre, descripcion, repeticiones, peso, musculo })
            .then(() => {
                notification.success({
                    message: 'Ejercicio creado',
                    description: 'El ejercicio se ha creado y agregado a la rutina correctamente.',
                    placement: 'topRight',
                });
                navigate(`/es/home`); 
            })
            .catch((e) => {
                console.log(e)
                notification.error({
                    message: 'Error al crear o agregar ejercicio',
                    description: 'Hubo un error al intentar crear el ejercicio o agregarlo a la rutina',
                    placement: 'topRight',
                });
            });
    };

    return (
        <div className='max-size-vh flx center'>
            <Form name='Nuevo Ejercicio' btnName='Guardar' handlerSubmit={handlerSubmit}>
                <ElementForm
                    title='Nombre'
                    type='text'
                    id='nombre'
                    name='nombre'
                    setText={setNombre}
                    required={true}
                />
                <ElementForm
                    title='Descripción'
                    type='text'
                    id='descripcion'
                    name='descripcion'
                    setText={setDescripcion}
                    required={true}
                />
                <ElementForm
                    title='Repeticiones'
                    type='number'
                    id='repeticiones'
                    name='repeticiones'
                    setText={setRepeticiones}
                    required={true}
                />
                <ElementForm
                    title='Peso'
                    type='number'
                    id='peso'
                    name='peso'
                    setText={setPeso}
                    required={true}
                />
                <ElementForm
                    title='Músculo'
                    type='text'
                    id='musculo'
                    name='musculo'
                    setText={setMusculo}
                    required={true}
                />
            </Form>
        </div>
    );
};

export default CrearEjercicio;
