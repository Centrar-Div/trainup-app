import React, { useState } from 'react';
import '../styles/sidebar.css';
import { useNavigate } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHome, faCompass, faCheck, faUser, faSignOutAlt, faPlus, faSearch } from '@fortawesome/free-solid-svg-icons';
import { useLogin } from '../context/LoginContext';

const Sidebar = () => {
    const navigate = useNavigate();
    const { user, restartLogin } = useLogin();
    const [showSidebar, setShowSidebar] = useState(false);

    return (

        <div className='nes'>
        <button className='nes1' onClick={() => setShowSidebar(!showSidebar)}>click</button>
        <div className={`sidebar ${showSidebar ? `s-show` : `s-noshow`}`}>
            
            <ul>
                {user?.esAdmin && (
                    <li>
                        <button className='sidebar-btn' onClick={() => navigate('/es/home/crear/rutina')}>
                            <FontAwesomeIcon icon={faPlus} className="icon" /> <span>Nueva Rutina</span>
                        </button>
                    </li>
                )}
                <li>
                    <button className='sidebar-btn' onClick={() => {
                        navigate('/es/home')
                        setShowSidebar(!showSidebar)
                    }}>
                        <FontAwesomeIcon icon={faHome} className="icon" /> <span>Home</span>
                    </button>
                </li>
                <li>
                    <button className='sidebar-btn' onClick={() => {
                        setShowSidebar(!showSidebar)
                        navigate('/es/home/explorador')}}>
                        <FontAwesomeIcon icon={faCompass} className="icon" /> <span>Explorar</span>
                    </button>
                </li>
                <li>
                    <button className='sidebar-btn' onClick={() => {
                        setShowSidebar(!showSidebar)
                        navigate('/es/home/buscar')}}>
                        <FontAwesomeIcon icon={faSearch} className="icon" /> <span>Buscar</span>
                    </button>
                </li>
                <li>
                    <button className='sidebar-btn' onClick={() => {
                        setShowSidebar(!showSidebar)
                        navigate('/es/home/completadas')}}>
                        <FontAwesomeIcon icon={faCheck} className="icon" /> <span>Completadas</span>
                    </button>
                </li>
                <li>
                    <button className='sidebar-btn' onClick={() => {
                        setShowSidebar(!showSidebar)
                        navigate('/es/home/profile')}}>
                        <FontAwesomeIcon icon={faUser} className="icon" /> <span>Perfil</span>
                    </button>
                </li>
                <li className='sidebar-logout'>
                    <button className='sidebar-btn' onClick={restartLogin}>
                        <FontAwesomeIcon icon={faSignOutAlt} className="icon" /> <span>Cerrar sesion</span>
                    </button>
                </li>
            </ul>
        </div>
        </div>
    );
}

export default Sidebar;
