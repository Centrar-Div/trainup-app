import React, { useEffect, useState } from 'react';
import { notification } from 'antd';
import '../styles/profile.css';
import { actualizarUsuario } from "../api/Api";
import { useLogin } from '../context/LoginContext';

const Profile = () => {
  const { user, setUser } = useLogin();  
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
    objetivo: '',
    rutinasSeguidas: [],
    rutomasCompletadas: []
  });

  const [editField, setEditField] = useState(null);
  const [editData, setEditData] = useState(profileData);
  const [errors, setErrors] = useState({});
  const showButtons = true;

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
        objetivo: user.objetivo || '',
        rutinasSeguidas: user.rutinasSeguidas || [],
        rutinasCompletadas: user.rutinasCompletadas || []

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
        [name]: value,
      }));

      if (name === 'edad') {
        const yearOfBirth = new Date().getFullYear() - Math.max(0, Math.min(99, value));
        setEditData(prevData => ({
          ...prevData,
          fecNacimiento: `${yearOfBirth}-01-01`, // Se establece un año de nacimiento aproximado
        }));
      }
    }
  };

  const handleEditClick = (field) => {
    setEditField(field);
  };

  const handleCancel = () => {
    setEditData(profileData);
    setEditField(null);
  };

  const validateFields = () => {
    const newErrors = {};
    if (!editData.username.trim()) newErrors.username = 'El nombre de usuario no puede estar vacío';
    if (!editData.password.trim()) newErrors.password = 'La contraseña no puede estar vacía';
    if (!editData.nombre.trim()) newErrors.nombre = 'El nombre no puede estar vacío';
    if (!/^\d+$/.test(editData.telefono)) newErrors.telefono = 'El teléfono solo puede contener números';
    if (!['masculino', 'femenino'].includes(editData.genero.toLowerCase())) newErrors.genero = 'El género debe ser masculino o femenino';
    if (editData.edad < 13 || editData.edad > 99) newErrors.edad = 'La edad debe estar entre 13 y 99 años';
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSave = async () => {
    if (!validateFields()) {
      notification.error({
        message: 'Error de Validación',
        description: 'Por favor corrija los errores antes de continuar.',
        placement: 'topRight',
      });
      return;
    }

    try {
      await actualizarUsuario(editData);
      setProfileData(editData);
      setEditField(null);
      setUser(editData);
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
      {errors[fieldName] && <span className="error-message">{errors[fieldName]}</span>}
      {showButtons && (
        <>
          {editField === fieldName ? (
            <>
              <button className='cancel-btn' onClick={handleCancel}>Cancelar</button>
              <button className='save-btn' onClick={handleSave}>Guardar</button>
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
          {renderField('username', 'Usuario')}
          {renderField('password', 'Contraseña')}
          {renderField('nombre', 'Nombre')}
          {renderField('edad', 'Edad')}
          {renderField('fecNacimiento', 'Fecha de Nacimiento')}
          {renderField('telefono', 'Teléfono')}
          {renderField('genero', 'Género')}
          {renderField('altura', 'Altura')}
          {renderField('peso', 'Peso')}
          {renderField('objetivo', 'Objetivo')}
        </div>
      </div>
    </div>
  );
};

export default Profile;


