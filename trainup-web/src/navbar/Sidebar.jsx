import React from 'react'
import '../styles/sidebar.css'
import { useNavigate } from 'react-router-dom'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faHome, faCompass, faCheck, faUser, faSignOutAlt, faPlus } from '@fortawesome/free-solid-svg-icons'
import { useLogin } from '../context/LoginContext';


const Sidebar = () => {
    const navigate = useNavigate()
    const { restartLogin } = useLogin()

    return (
        <div className='sidebar'>
            <ul>
                <li>
                    <button className='sidebar-btn' onClick={() => navigate('/es/home/crear/rutina')}>
                        <FontAwesomeIcon icon={faPlus} className="icon" /> Crear Rutina
                    </button>
                </li>
                <li>
                    <button className='sidebar-btn' onClick={() => navigate('/es/home')}>
                        <FontAwesomeIcon icon={faHome} className="icon" /> Home
                    </button>
                </li>
                <li>
                    <button className='sidebar-btn' onClick={() => navigate('/es/home/inProgress')}>
                        <FontAwesomeIcon icon={faCompass} className="icon" /> Explorar
                    </button>
                </li>
                <li>
                    <button className='sidebar-btn' onClick={() => navigate('/es/home/completadas')}>
                        <FontAwesomeIcon icon={faCheck} className="icon" /> Completadas
                    </button>
                </li>
                <li>
                    <button className='sidebar-btn' onClick={() => navigate('/es/home/profile')}>
                        <FontAwesomeIcon icon={faUser} className="icon" /> Perfil
                    </button>
                </li>
                <li className='sidebar-logout'>
                    <button className='sidebar-btn' onClick={restartLogin}>
                        <FontAwesomeIcon icon={faSignOutAlt} className="icon" /> Cerrar Sesión
                    </button>
                </li>
            </ul>
        </div>
    )
}

export default Sidebar
