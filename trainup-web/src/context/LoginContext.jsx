import React, { createContext, useContext, useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import { obtenerUsuarioPorUsername, handleError, logearUsuario } from '../api/Api'; 
import { notification } from 'antd';
import 'antd/dist/reset.css';

const LoginContext = createContext()

export const LoginProvider = ({children}) => {
    
    const [isLogin, setIsLogin] = useState(false)
    const [user, setUser] = useState(null);
    const navigate = useNavigate()
    
    useEffect(() => {

        const username = localStorage.getItem('username');
        const password = localStorage.getItem('password');
        console.log(user);
        if (username && password) {
            logearUsuario(username, password).then(({data}) => {
                setUser(data);
            })
        } else {
            navigate('/init');
        }
    }, []);

    const validateLogin = (username, password) => {
        logearUsuario(username, password).then(({data}) => {
            localStorage.setItem('username', data.username);
            localStorage.setItem('password', data.password);
            setUser(data);
            navigate('/es/home')
        }).catch((err) => {
            notification.error({
                message: 'Error de Autenticación',
                description: 'El nombre de usuario o la contraseña son incorrectos.',
                placement: 'topRight',
            });
        })
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

