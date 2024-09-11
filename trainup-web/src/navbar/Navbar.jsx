import React from 'react'
import '../styles/navbar.css'

const Navbar = () => {
  return (
    <nav>
        <button className='default-btn rounded-login-btn secondary-btn'></button>
        <ul>
            <li>Home</li>
            <li>About</li>
            <li>Contact</li>
        </ul>
    </nav>
  )
}

export default Navbar