import React, { createContext, useContext, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { obtenerUsuarioPorUsername, handleError } from '../api/Api'; 
import { notification } from 'antd';
import 'antd/dist/reset.css';

const LoginContext = createContext()

export const LoginProvider = ({children}) => {
    
    const [isLogin, setIsLogin] = useState(false)
    const [user, setUser] = useState(null);
    const navigate = useNavigate()

    const validateLogin = async (username, password) => {
        try {
            const userData = await obtenerUsuarioPorUsername(username);
            if (userData && userData.password === password) {
                setIsLogin(true);
                setUser(userData);
                localStorage.setItem('user', JSON.stringify(userData));
                navigate('/es/home');
            } else {
                notification.error({
                    message: 'Error de Autenticación',
                    description: 'El nombre de usuario o la contraseña son incorrectos.',
                    placement: 'topRight',
                });
            }
        } catch (error) {
            handleError(error);
        }
    };

    const restartLogin = () => {
        setIsLogin(false);
        setUser(null);
        localStorage.removeItem('user'); 
        navigate('/init');
    };
  
    return (
        <LoginContext.Provider value={{ isLogin, user, restartLogin, validateLogin }}>
            {children}
        </LoginContext.Provider>
    );
}

export const useLogin = () => useContext(LoginContext)

