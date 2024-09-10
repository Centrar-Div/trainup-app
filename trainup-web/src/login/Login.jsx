import React from 'react'
import '../styles/boxes.css'
import Form from './Form'
import ElementForm from './ElementForm'

const Login = () => {

  return (
    <Form name='Iniciar sesion' btnName='Iniciar sesion'>
      <ElementForm title='Correo electronico' type='email' id='email' name='email'/>
      <ElementForm title='Contraseña' type='password' id='password' name='password'/>
    </Form>
  )
}

export default Login