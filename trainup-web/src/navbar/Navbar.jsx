import React, { useState } from 'react';
import '../styles/navbar.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars } from '@fortawesome/free-solid-svg-icons';

const Navbar = () => {
  const [isOpen, setIsOpen] = useState(false);

  const toggleMenu = () => {
    setIsOpen(!isOpen); 
  };

  return (
    <nav className="navbar">
      <button 
        className='default-btn rounded-login-btn secondary-btn'
        onClick={toggleMenu} 
      >
        <FontAwesomeIcon icon={faBars} />
      </button>

      <div className={`nav-container ${isOpen ? 'open' : ''}`}>
        <ul className={`nav-links ${isOpen ? 'open' : ''}`}>
          <li><a href="/init">Home</a></li>
          <li><a href="#">About</a></li>
          <li><a href="#services">Services</a></li>
          <li><a href="#contact">Contact</a></li>
        </ul>
      </div>
    </nav>
  );
};

export default Navbar;
