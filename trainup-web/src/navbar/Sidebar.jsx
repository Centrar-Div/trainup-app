import React from 'react'
import '../styles/sidebar.css'
import { useLogin } from '../context/LoginContext'

const Sidebar = () => {

    const {login} = useLogin()

  return (
    <>
        {
            login && 
            <div className='sidebar'>
                <ul>
                    <li>
                        <button className='default-btn'>Home</button>
                    </li>
                    <li>
                        <button className='default-btn'>Explorar</button>
                    </li>
                    <li>
                        <button className='default-btn'>Favoritos</button>
                    </li>
                </ul>
            </div>
        }
    </>


  )
}

export default Sidebar