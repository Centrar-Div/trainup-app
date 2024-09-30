import React, { useEffect, useState } from 'react'

const ElementForm = ({ name, type, id, title, setText, initialValue = '', required = false }) => {

  const [valueText, setValueText] = useState('')
  const [error, setError] = useState('');


  useEffect(() => {
    if (initialValue) {
      setValueText(initialValue);
    }
  }, [])


  const handlerChange = (e) => {
    const value = e.target.value;
    setValueText(value);
    setText(value);

    if (required && value.trim() === '') {
      setError(`El campo ${name} no puede estar vacío`);
    } else {
      setError('');
    }
  };



  return (
    <div className='default-box none-mp column-box'>
      <label htmlFor={type} className='bold'>{title}</label>
      <input
        className={`primary-textbar textbar-xxl ${error ? 'error-input' : ''} `}
        type={type}
        name={name}
        value={valueText}
        placeholder={name}
        id={id}
        onChange={handlerChange} />
      {error && <span className='error-message'>{error}</span>}
    </div>
  )
}

export default ElementForm