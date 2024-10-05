import React, { useState } from 'react';
import { actualizarEjercicio } from '../api/Api';  
import { notification, Modal, Input, Form } from 'antd';  
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { useLogin } from '../context/LoginContext'; 
import "../styles/ejercicio.css"

const Ejercicio = ({ ejercicio }) => { 
    const [completado, setCompletado] = useState(ejercicio.completado);
    const { user, actualizarPerfilUsuario } = useLogin(); 
    const [isModalVisible, setIsModalVisible] = useState(false); 
    const [editedFields, setEditedFields] = useState(ejercicio); 
    const [isUpdating, setIsUpdating] = useState(false);
    const [form] = Form.useForm();  

    const handleCheckboxChange = async () => {
        const updatedEjercicio = {
            ...ejercicio,
            completado: !completado
        };

        setCompletado(!completado);

        try {
            await actualizarEjercicio(updatedEjercicio);
            const usuarioActualizado = {
                ...user,
                rutinasSeguidas: user.rutinasSeguidas.map(rutina => ({
                    ...rutina,
                    ejercicios: rutina.ejercicios.map(ej => 
                        ej.id === ejercicio.id ? updatedEjercicio : ej
                    )
                }))
            };
            
            await actualizarPerfilUsuario(usuarioActualizado);
        } catch (error) {
            console.error('Error actualizando ejercicio:', error);
            notification.error({
                message: 'Error al actualizar',
                description: `No se pudo actualizar el estado del ejercicio "${ejercicio.nombre}".`,
                placement: 'topRight',
            });
        }
    };

    const showEditModal = () => {
        setIsModalVisible(true);
    };

    const handleCancel = () => {
        setIsModalVisible(false);
    };

    const handleFieldChange = (changedValues) => {
        setEditedFields(prevFields => ({
            ...prevFields,
            ...changedValues
        }));
    };

    const handleSaveChanges = async () => {
        try {
            await form.validateFields(); 
            setIsUpdating(true);
            
            await actualizarEjercicio(editedFields);
            const usuarioActualizado = {
                ...user,
                rutinasSeguidas: user.rutinasSeguidas.map(rutina => ({
                    ...rutina,
                    ejercicios: rutina.ejercicios.map(ej => 
                        ej.id === ejercicio.id ? editedFields : ej
                    )
                }))
            };
            
            await actualizarPerfilUsuario(usuarioActualizado);
            setIsModalVisible(false);
            notification.success({
                message: '¡Éxito!',
                description: `El ejercicio "${editedFields.nombre}" ha sido actualizado exitosamente.`,
                placement: 'topRight',
            });
        } catch (error) {
            console.error('Error al actualizar ejercicio:', error);
            notification.error({
                message: 'Error al actualizar',
                description: `No se pudo actualizar el ejercicio.`,
                placement: 'topRight',
            });
        } finally {
            setIsUpdating(false);
        }
    };

    const isFormInvalid = () => {
        const fieldsTouched = form.isFieldsTouched();
        const hasErrors = form.getFieldsError().some(({ errors }) => errors.length > 0);
        return !fieldsTouched || hasErrors;
    };

    return (
        <div className='exercise-container'>
            <div className="exercise-header">
                <h3>{ejercicio.nombre}</h3>
            </div>
            <div className="exercise-body">
                <p>{ejercicio.descripcion}</p>
            </div>
            <div className="exercise-details">
                <p><strong>Repeticiones:</strong> {ejercicio.repeticiones}</p>
                <p><strong>Peso ideal:</strong> {ejercicio.peso} kg</p>
                <p><strong>Músculo:</strong> {ejercicio.musculo}</p>
            </div>
            <div className="exercise-footer">
                <label className="checkbox-container">
                    <input
                        type="checkbox"
                        checked={completado}
                        onChange={handleCheckboxChange}
                    />
                    <span className="checkmark"></span>
                    {' '}Completado
                </label>
                {user.esAdmin && (
                    <FontAwesomeIcon icon={faPenToSquare} className="icon edit-icon" onClick={showEditModal} />
                )}
            </div>
    
            <Modal
                title={`Editar ejercicio: ${ejercicio.nombre}`}
                open={isModalVisible}
                onCancel={handleCancel}
                onOk={handleSaveChanges}
                confirmLoading={isUpdating}
                okButtonProps={{ disabled: isFormInvalid() }}  
            >
                <Form
                    layout="vertical"
                    initialValues={editedFields}
                    onValuesChange={(_, changedValues) => handleFieldChange(changedValues)}
                    form={form}
                >
                    <Form.Item
                        label="Nombre"
                        name="nombre"
                        rules={[
                            {
                                validator: (_, value) => {
                                    if (value && value.trim() === "") {
                                        return Promise.reject(new Error('El nombre no puede estar vacío.'));
                                    }
                                    return Promise.resolve();
                                }
                            },
                        ]}
                    >
                        <Input />
                    </Form.Item>

                    <Form.Item
                        label="Descripción"
                        name="descripcion"
                        rules={[
                            {
                                validator: (_, value) => {
                                    if (value && value.trim() === "") {
                                        return Promise.reject(new Error('La descripción no puede estar vacía.'));
                                    }
                                    return Promise.resolve();
                                }
                            }
                        ]}
                    >
                        <Input />
                    </Form.Item>

                    <Form.Item
                        label="Repeticiones"
                        name="repeticiones"
                        rules={[
                            {
                                validator: (_, value) => {
                                    if (value !== undefined && value !== null) {
                                        if (value <= 0) {
                                            return Promise.reject(new Error('Las repeticiones deben ser mayores a 0.'));
                                        }
                                    }
                                    return Promise.resolve();
                                }
                            }
                        ]}
                    >
                        <Input type="number" />
                    </Form.Item>
                    <Form.Item
                        label="Peso"
                        name="peso"
                        rules={[
                            {
                                validator: (_, value) => {
                                    if (value !== undefined && value !== null) {
                                        if (value < 0) {
                                            return Promise.reject(new Error('El peso no puede ser negativo.'));
                                        }
                                    }
                                    return Promise.resolve();
                                }
                            }
                        ]}
                    >
                        <Input type="number" />
                    </Form.Item>
                    <Form.Item
                        label="Músculo"
                        name="musculo"
                        rules={[
                            {
                                validator: (_, value) => {
                                    if (value && value.trim() === "") {
                                        return Promise.reject(new Error('El músculo no puede estar vacío.'));
                                    }
                                    return Promise.resolve();
                                }
                            }
                        ]}
                    >
                        <Input />
                    </Form.Item>
                    <Form.Item
                        label="Equipo"
                        name="equipo"
                        rules={[
                            {
                                validator: (_, value) => {
                                    if (value && value.trim() === "") {
                                        return Promise.reject(new Error('El equipo no puede estar vacío.'));
                                    }
                                    return Promise.resolve();
                                }
                            }
                        ]}
                    >
                        <Input />
                    </Form.Item>
                    <Form.Item
                        label="Instrucciones"
                        name="instrucciones"
                        rules={[
                            {
                                validator: (_, value) => {
                                    if (value && value.trim() === "") {
                                        return Promise.reject(new Error('Las instrucciones no pueden estar vacías.'));
                                    }
                                    return Promise.resolve();
                                }
                            }
                        ]}
                    >
                        <Input />
                    </Form.Item>
                </Form>
            </Modal>
        </div>
    );
};

export default Ejercicio;
