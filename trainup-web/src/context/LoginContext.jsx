import React, { createContext, useContext, useState } from 'react'
import { useNavigate } from 'react-router-dom'

const LoginContext = createContext()

export const LoginProvider = ({children}) => {
    
    const [login, setLogin] = useState(false)
    const navigate = useNavigate()
    const emailTest = 'usuario@gmail.com'
    const passwordTest = '123456'
    

    const validateLogin = (email, password) => {
        const correctLogin = email === emailTest && password === passwordTest
        setLogin(correctLogin)
        correctLogin && navigate('/home')
    }
  
    return (
    <LoginContext.Provider value={{validateLogin, login}}>
        {children}
    </LoginContext.Provider>    
  )
}

export const useLogin = () => useContext(LoginContext)

