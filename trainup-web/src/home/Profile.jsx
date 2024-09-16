import React, { useEffect, useState } from 'react';
import { notification } from 'antd';
import '../styles/profile.css';
import { actualizarUsuario } from "../api/Api";
import { useLogin } from '../context/LoginContext';

const Profile = () => {
  const { user } = useLogin();
  console.log("UserDTO", user);

  const [profileData, setProfileData] = useState({
    name: '',
    age: '',
    birthDate: '',
    address: '',
    phone: '',
    gender: '',
    height: '',
    weight: '',
    goal: ''
  });

  const [editField, setEditField] = useState(null);
  const [editData, setEditData] = useState(profileData);

  useEffect(() => {
    if (user) {
      const newProfileData = {
        name: user.nombre || '',
        age: user.edad || '',
        birthDate: user.fecNacimiento || '',
        address: user.domicilio || '',
        phone: user.telefono || '',
        gender: user.genero || '',
        height: user.altura || '',
        weight: user.peso || '',
        goal: user.objetivo || ''
      };
      setProfileData(newProfileData);
      setEditData(newProfileData);
    }
  }, [user]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (editField) {
      setEditData(prevData => ({
        ...prevData,
        [name]: value
      }));
    }
  };

  const handleEditClick = (field) => {
    setEditField(field);
  };

  const handleCancel = () => {
    setEditData(profileData);
    setEditField(null);
  };

  const handleSaveProfileData = () => {
    setProfileData(editData);
    setEditField(null);
    notification.success({
      message: 'Datos Guardados',
      description: 'Tus datos han sido guardados localmente.',
      placement: 'topRight',
    });
  };

  const handleSave = async () => {
    setProfileData(editData);
    setEditField(null);

    try {
      await actualizarUsuario({
        id: user.id,
        ...editData
      });
      notification.success({
        message: 'Actualización Exitosa',
        description: 'Tus datos se han actualizado correctamente.',
        placement: 'topRight',
      });
    } catch (error) {
      notification.error({
        message: 'Error al Actualizar',
        description: 'No se pudo actualizar tus datos. Inténtalo de nuevo más tarde.',
        placement: 'topRight',
      });
    }
  };


  const renderField = (fieldName, label) => (
    <div className='profile-field'>
      <strong>{label}:</strong>
      <input
        type='text'
        name={fieldName}
        value={editData[fieldName] || ''}
        onChange={handleChange}
        disabled={editField !== fieldName && editField !== null}
        className={editField === fieldName ? 'editable' : 'disabled'}
      />
      {editField === fieldName ? (
        <>
          <button className='cancel-btn' onClick={handleCancel}>Cancelar</button>
          <button className='save-btn' onClick={handleSaveProfileData}>Guardar</button>
        </>
      ) : (
        <button className='edit-btn' onClick={() => handleEditClick(fieldName)}>Editar</button>
      )}
    </div>
  );

  return (
    <div className='profile-container'>
      <div className='profile-header'>
        <h1>Perfil</h1>
      </div>
      <div className='profile-card default-box'>
        <div className='profile-info default-box column-box'>
          {renderField('name', 'Nombre')}
          {renderField('age', 'Edad')}
          {renderField('birthDate', 'Nacimiento')}
          {renderField('address', 'Domicilio')}
          {renderField('phone', 'Teléfono')}
          {renderField('gender', 'Género')}
          {renderField('height', 'Altura')}
          {renderField('weight', 'Peso')}
          {renderField('goal', 'Objetivo')}
        </div>
        <div className='profile-actions'>
          <button className='save-btn' onClick={handleSave}>Guardar Cambios</button>
        </div>
      </div>
    </div>
  );
};

export default Profile;
