import React, { createContext, useContext, useState } from 'react'
import { useNavigate } from 'react-router-dom'

const LoginContext = createContext()

export const LoginProvider = ({children}) => {
    
    const [isLogin, setIsLogin] = useState(false)
    const [login, setLogin] = useState(isLogin || localStorage.getItem('id'))
    const navigate = useNavigate()
    const emailTest = 'usuario@gmail.com'
    const passwordTest = '123456'
    

    const validateLogin = (email, password) => {
        const validateCredentials = email === emailTest && password === passwordTest
        if(validateCredentials){
            setIsLogin(true)
            setLogin(isLogin)
            localStorage.setItem('id', '1')
            navigate('/es/home')
        }
    }

    const restartLogin = () => {
        setIsLogin(false)
        localStorage.removeItem('id')
        setLogin(isLogin)
        navigate('/init')
    }
  
    return (
    <LoginContext.Provider value={{restartLogin, validateLogin, login}}>
        {children}
    </LoginContext.Provider>    
  )
}

export const useLogin = () => useContext(LoginContext)

