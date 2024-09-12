import React, { useState } from 'react'
import '../styles/boxes.css'
import Form from './Form'
import ElementForm from './ElementForm'
import { useLogin } from '../context/LoginContext'

const Login = () => {

  const {validateLogin} = useLogin()
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')

  const handlerSubmit = (e) => {
    validateLogin(email, password)
  }

  return (
    <div className='max-size-vh flx center '>
      <Form name='Iniciar sesion' btnName='Iniciar sesion' handlerSubmit={handlerSubmit}>
        <ElementForm 
          title='Correo electronico' 
          type='email' 
          id='email' 
          name='email' 
          setText={setEmail}/>
        <ElementForm 
          title='Contraseña' 
          type='password' 
          id='password' 
          name='password'
          setText={setPassword}/>
      </Form>
    </div>
  )
}

export default Login