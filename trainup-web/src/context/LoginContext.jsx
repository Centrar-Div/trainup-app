import React, { createContext, useContext, useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { logearUsuario, actualizarUsuario, obtenerUsuarioPorID } from '../api/Api';
import { notification } from 'antd';
import 'antd/dist/reset.css';

const LoginContext = createContext();

export const LoginProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [authChecked, setAuthChecked] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    const username = localStorage.getItem('username');
    const password = localStorage.getItem('password');
    const userId = localStorage.getItem('id');
    
    if (username && password && userId) {
      obtenerUsuarioPorID(userId)
        .then(({ data }) => {
          setUser(data);
        })
        .catch(() => {
          localStorage.clear(); 
          navigate('/init'); 
        })
        .finally(() => {
          setAuthChecked(true); 
        });
    } else {
      setAuthChecked(true); 
    }
  }, [navigate]);

  const validateLogin = (username, password) => {
    logearUsuario(username, password)
      .then(({ data }) => {
        localStorage.setItem('id', data.id);
        localStorage.setItem('username', data.username);
        localStorage.setItem('password', data.password);
        setUser(data);
        navigate('/es/home');
      })
      .catch(() => {
        notification.error({
          message: 'Error de Autenticación',
          description: 'El nombre de usuario o la contraseña son incorrectos.',
          placement: 'topRight',
        });
      });
  };

  const restartLogin = () => {
    setUser(null);
    localStorage.clear();
    navigate('/init');
    notification.success({
      message: 'Sesión Cerrada',
      description: 'Has cerrado la sesión correctamente.',
      placement: 'topRight',
    });
  };

  const actualizarPerfilUsuario = async (datos) => {
    try {
      const response = await actualizarUsuario(datos);
      setUser(response.data);
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

  return (
    <LoginContext.Provider value={{ user, setUser, restartLogin, validateLogin, actualizarPerfilUsuario, authChecked }}>
      {children}
    </LoginContext.Provider>
  );
};

export const useLogin = () => useContext(LoginContext);
