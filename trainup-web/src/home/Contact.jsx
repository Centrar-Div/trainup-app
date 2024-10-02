import React, { useState } from 'react';
import { notification } from 'antd';
import "../landingPage/landingPage.css"

const Contact = () => {
  
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        phone: '',
        message: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        const { name, email, phone, message } = formData;
        if (!name || !email || !phone || !message) {
            notification.error({
                message: 'Error',
                description: 'Todos los campos son obligatorios.',
                placement: 'topRight',
            });
            return;
        }

        setFormData({
            name: '',
            email: '',
            phone: '',
            message: ''
        });

        notification.success({
            message: 'Éxito',
            description: '¡Formulario enviado correctamente!',
            placement: 'topRight',
        });
    };
 

    return (
        <section className='landing-contact' id="contact">
            <h2>Contacto</h2>
            <form className='contact-form' onSubmit={handleSubmit}>
                <label htmlFor="name">Nombre</label>
                <input 
                    type="text" 
                    id="name" 
                    name="name" 
                    value={formData.name} 
                    onChange={handleChange} 
                    required 
                />
                
                <label htmlFor="email">Correo Electrónico</label>
                <input 
                    type="email" 
                    id="email" 
                    name="email" 
                    value={formData.email} 
                    onChange={handleChange} 
                    required 
                />
                
                <label htmlFor="phone">Teléfono</label>
                <input 
                    type="tel" 
                    id="phone" 
                    name="phone" 
                    value={formData.phone} 
                    onChange={handleChange} 
                    required 
                />
                
                <label htmlFor="message">Mensaje</label>
                <textarea 
                    id="message" 
                    name="message" 
                    rows="4" 
                    value={formData.message} 
                    onChange={handleChange} 
                    required 
                ></textarea>
                
                <button type="submit" className='default-btn modern-btn'>Enviar</button>
            </form>
        </section>
    );
}

export default Contact