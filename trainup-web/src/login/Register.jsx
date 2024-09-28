import React, { useState } from 'react';
import '../styles/register.css';
import { notification } from 'antd';
import { crearUsuario } from '../api/Api'

const Register = () => {
  const [formData, setFormData] = useState({
    username: '',
    password: '',
    nombreCompleto: '',
    fecNacimiento: '',
    telefono: '',
    genero: '',
    altura: '',
    peso: '',
    objetivo: ''
  });

  const [errors, setErrors] = useState({});

  const validateRequired = (value) => {
    return value && typeof value === 'string' && value.trim() !== '';
  };

  const calculateEdad = (fechaNacimiento) => {
    const today = new Date();
    const birthDate = new Date(fechaNacimiento);
    let age = today.getFullYear() - birthDate.getFullYear();
    const monthDiff = today.getMonth() - birthDate.getMonth();
    if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthDate.getDate())) {
      age--;
    }
    return age;
  };

  const handlerSubmit = async (e) => {
    e.preventDefault();
  
    const newErrors = {};
    if (!validateRequired(formData.username)) newErrors.username = 'El nombre de usuario es obligatorio';
    if (!validateRequired(formData.password)) newErrors.password = 'La contraseña es obligatoria';
    if (!validateRequired(formData.nombreCompleto)) newErrors.nombreCompleto = 'El nombre completo es obligatorio';
  
    if (Object.keys(newErrors).length > 0) {
      setErrors(newErrors);
      return;
    }
  
    const edad = calculateEdad(formData.fecNacimiento);
    const usuarioDTO = {
      username: formData.username,      
      password: formData.password,       
      nombre: formData.nombreCompleto,        
      edad: edad,                          
      fecNacimiento: formData.fecNacimiento, 
      telefono: formData.telefono,       
      genero: formData.genero,           
      altura: formData.altura,           
      peso: formData.peso,               
      objetivo: formData.objetivo,       
    };
  
    try {
      await crearUsuario(usuarioDTO);
      notification.success({ 
        message: 'Registro exitoso',
        description: 'El usuario ha sido creado exitosamente.',
      });
      setFormData({
        username: '',
        password: '',
        nombreCompleto: '',
        fecNacimiento: '',
        telefono: '',
        genero: '',
        altura: '',
        peso: '',
        objetivo: ''
      });
      setErrors({}); 
    } catch (error) {
      notification.error({ 
        message: 'Error al crear usuario',
        description: error.response?.data?.message || 'Ocurrió un error inesperado.',
      });
    }
  };

  

  const handleChange = (key, value) => {
    setFormData((prev) => ({
      ...prev,
      [key]: value,
    }));
    setErrors((prevErrors) => ({
      ...prevErrors,
      [key]: '', 
    }));
  };

  return (
    <div className="register-container">
      <form className="register-form" onSubmit={handlerSubmit}>
        <h2 className='register-title'>Registrarse</h2>

        <div className="form-row">
          <div className='default-box none-mp column-box'>
            <label htmlFor="username" className='bold'>Nombre de Usuario</label>
            <input
              className='primary-textbar textbar-xxl'
              type="text"
              name="username"
              value={formData.username}
              placeholder="Nombre de Usuario"
              id="username"
              onChange={(e) => handleChange('username', e.target.value)}
            />
            {errors.username && <span className="error-message">{errors.username}</span>}
          </div>

          <div className='default-box none-mp column-box'>
            <label htmlFor="password" className='bold'>Contraseña</label>
            <input
              className='primary-textbar textbar-xxl'
              type="password"
              name="password"
              value={formData.password}
              placeholder="Contraseña"
              id="password"
              onChange={(e) => handleChange('password', e.target.value)}
            />
            {errors.password && <span className="error-message">{errors.password}</span>}
          </div>
        </div>

        <div className="form-row">
          <div className='default-box none-mp column-box'>
            <label htmlFor="nombreCompleto" className='bold'>Nombre Completo</label>
            <input
              className='primary-textbar textbar-xxl'
              type="text"
              name="nombreCompleto"
              value={formData.nombreCompleto}
              placeholder="Nombre Completo"
              id="nombreCompleto"
              onChange={(e) => handleChange('nombreCompleto', e.target.value)}
            />
            {errors.nombreCompleto && <span className="error-message">{errors.nombreCompleto}</span>}
          </div>

          <div className='default-box none-mp column-box'>
            <label htmlFor="fecNacimiento" className='bold'>Fecha de Nacimiento</label>
            <input
              className='primary-textbar textbar-xxl'
              type="date"
              name="fecNacimiento"
              value={formData.fecNacimiento}
              id="fecNacimiento"
              onChange={(e) => handleChange('fecNacimiento', e.target.value)}
            />
            {errors.fecNacimiento && <span className="error-message">{errors.fecNacimiento}</span>}
          </div>
        </div>

        <div className="form-row">
          <div className='default-box none-mp column-box'>
            <label htmlFor="telefono" className='bold'>Teléfono</label>
            <input
              className='primary-textbar textbar-xxl'
              type="tel"
              name="telefono"
              value={formData.telefono}
              placeholder="Teléfono"
              id="telefono"
              onChange={(e) => handleChange('telefono', e.target.value)}
            />
            {errors.telefono && <span className="error-message">{errors.telefono}</span>}
          </div>

          <div className='default-box none-mp column-box'>
            <label htmlFor="genero" className='bold'>Género</label>
            <input
              className='primary-textbar textbar-xxl'
              type="text"
              name="genero"
              value={formData.genero}
              placeholder="Género"
              id="genero"
              onChange={(e) => handleChange('genero', e.target.value)}
            />
            {errors.genero && <span className="error-message">{errors.genero}</span>}
          </div>
        </div>
        
        <div className="form-row">
          <div className='default-box none-mp column-box'>
            <label htmlFor="altura" className='bold'>Altura (cm)</label>
            <input
              className='primary-textbar textbar-xxl'
              type="number"
              name="altura"
              value={formData.altura}
              placeholder="Altura"
              id="altura"
              min="0"
              onChange={(e) => handleChange('altura', e.target.value)}
            />
            {errors.altura && <span className="error-message">{errors.altura}</span>}
          </div>

          <div className='default-box none-mp column-box'>
            <label htmlFor="peso" className='bold'>Peso (kg)</label>
            <input
              className='primary-textbar textbar-xxl'
              type="number"
              name="peso"
              value={formData.peso}
              placeholder="Peso"
              id="peso"
              min="0"
              onChange={(e) => handleChange('peso', e.target.value)}
            />
            {errors.peso && <span className="error-message">{errors.peso}</span>}
          </div>
        </div>

        <div className="form-row">
          <div className='default-box none-mp column-box full-width'> 
            <label htmlFor="objetivo" className='bold'>Objetivo</label>
            <input
              className='primary-textbar full-width'
              type="text"
              name="objetivo"
              value={formData.objetivo}
              placeholder="Objetivo"
              id="objetivo"
              onChange={(e) => handleChange('objetivo', e.target.value)}
            />
            {errors.objetivo && <span className="error-message">{errors.objetivo}</span>}
          </div>
        </div>

        <button className="submit-btn" type="submit">Crear Cuenta</button>
      </form>
    </div>
  );
};

export default Register;
