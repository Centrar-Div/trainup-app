import React from 'react'
import '../styles/sidebar.css'
import { useLogin } from '../context/LoginContext'
import { useNavigate } from 'react-router-dom'

const Sidebar = () => {

    const navigate = useNavigate()

  return (
    <div className='sidebar'>
        <ul>
            <li>
                <button className='default-btn' onClick={() => navigate('/es/home')}>Home</button>
            </li>
            <li>
                <button className='default-btn'>Explorar</button>
            </li>
            <li>
                <button className='default-btn'>Completadas</button>
            </li>
        </ul>
    </div>
  )
}

export default Sidebar