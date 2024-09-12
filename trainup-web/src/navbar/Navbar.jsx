
import React, { useEffect, useState } from 'react'
import '../styles/navbar.css'
import { useLogin } from '../context/LoginContext'

const Navbar = () => {


  return (
    <nav>
        <button className='default-btn rounded-login-btn secondary-btn'></button>
        <ul>
            <li>
              <button className='default-btn primary-btn'>perfil</button>
            </li>
            <li>
              <button className='default-btn primary-btn'>about</button>
            </li>
            <li>
              <button className='default-btn primary-btn'>contact</button>
            </li>
        </ul>
    </nav>   
  )
}


export default Navbar;
