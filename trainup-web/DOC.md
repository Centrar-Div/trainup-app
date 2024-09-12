# DOCUMENT

## Componente `Form` y `ElementForm`:

Este componente permite crear una preestructura de un formulario para completar campos. Es recomendable utilizarlo en el desarrollo de logins, registros o similar. 

`<Form>` posee los atributos `name` (nombre del form), `btnName` (nombre del boton submit), y `handlerSubmit` (funcion que posee la logica del boton), 

El componente `<Form>` lo se complementa junto con el componente `<ElementForm>` que representa el input del form. Éste componente posee el atributo `title` (titulo del campo), `type` (tipo de input), `id` (nombre unico del input), `name` (nombre del input), `setText` (set para obtener la informacion del campo)

**Ejemplo de uso**


```jsx

import React, { useState } from 'react'
import Form from './Form'
import ElementForm from './ElementForm'

const Element = () => {


    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')

    const handlerSubmit = (e) => {
        /*logica para validar el login y logearse */
    }

    return (
        <Form 
            name='Iniciar sesion' 
            btnName='Iniciar sesion' 
            handlerSubmit={handlerSubmit}>

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
    )
}

export default Element

```