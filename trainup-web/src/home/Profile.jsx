import React, { useState } from 'react';
import '../styles/profile.css';

const Profile = () => {
  const [profileData, setProfileData] = useState({
    name: 'Juan Pérez',
    age: '30 años',
    birthDate: '15 de marzo de 1994',
    address: 'Calle Ficticia 123, Ciudad Imaginaria',
    phone: '+54 11 1234-5678',
    gender: 'Masculino',
    height: '1.80 m',
    weight: '75 kg',
    goal: 'Mantenerse en forma'
  });

  const [editField, setEditField] = useState(null);
  const [editData, setEditData] = useState(profileData);

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

  const handleSave = () => {
    setProfileData(editData);
    setEditField(null);
  };

  const renderField = (fieldName, label) => (
    <div className='profile-field'>
      <strong>{label}:</strong>
      <input
        type='text'
        name={fieldName}
        value={editData[fieldName]}
        onChange={handleChange}
        disabled={editField !== fieldName && editField !== null}
        className={editField === fieldName ? 'editable' : 'disabled'}
      />
      {editField === fieldName ? (
        <>
          <button className='cancel-btn' onClick={handleCancel}>Cancelar</button>
          <button className='save-btn' onClick={handleSave}>Guardar</button>
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
      <div className='profile-card'>
        <div className='profile-info'>
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
