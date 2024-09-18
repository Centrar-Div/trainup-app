import React, { useEffect, useState } from 'react';
import { notification } from 'antd';
import '../styles/profile.css';
import { actualizarUsuario } from "../api/Api";
import { useLogin } from '../context/LoginContext';

const Profile = () => {
  const { user } = useLogin();  
  const [profileData, setProfileData] = useState({
    id: '',
    username: '',
    password: '',
    nombre: '',
    edad: '',
    fecNacimiento: '',
    telefono: '',
    genero: '',
    altura: '',
    peso: '',
    objetivo: ''
  });

  const [editField, setEditField] = useState(null);
  const [editData, setEditData] = useState(profileData);
  
  // TEMPORAL EL SIGUIENTE SPRINT LO SACAN! 
  const showButtons = false;

  
  useEffect(() => {
    if (user) {
      const newProfileData = {
        id: user.id || '',
        username: user.username || '',
        password: user.password || '',
        nombre: user.nombre || '', 
        edad: user.edad || '',   
        fecNacimiento: user.fecNacimiento || '', 
        telefono: user.telefono || '', 
        genero: user.genero || '', 
        altura: user.altura || '', 
        peso: user.peso || '',  
        objetivo: user.objetivo || '' 
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
      await actualizarUsuario(editData); 
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
      {showButtons && (
        <>
          {editField === fieldName ? (
            <>
              <button className='cancel-btn' onClick={handleCancel}>Cancelar</button>
              <button className='save-btn' onClick={handleSaveProfileData}>Guardar</button>
            </>
          ) : (
            <button className='edit-btn' onClick={() => handleEditClick(fieldName)}>Editar</button>
          )}
        </>
      )}
    </div>
  );

  return (
    <div className='profile-container'>
      <div className='profile-header'>
        <h1>Perfil</h1>
      </div>
      <div className='profile-card'>
        <div className='profile-info'>
          {renderField('nombre', 'Nombre')}
          {renderField('edad', 'Edad')}
          {renderField('fecNacimiento', 'Nacimiento')}
          {renderField('telefono', 'Teléfono')}
          {renderField('genero', 'Género')}
          {renderField('altura', 'Altura')}
          {renderField('peso', 'Peso')}
          {renderField('objetivo', 'Objetivo')}
        </div>
        <div className='profile-actions'>
          {showButtons && (
            <button className='save-btn' onClick={handleSave}>Guardar Cambios</button>
          )}
        </div>
      </div>
    </div>
  );
};

export default Profile;
