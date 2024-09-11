import React, { useState } from 'react'

const ElementForm = ({name, type, id, title, setText}) => {
  
  const [valueText, setValueText] = useState('')

  const handlerChange = (e) => {
    const value = e.target.value
    setValueText(value)
    setText(value)
  }

  return (
    <div className='default-box none-mp column-box'>
        <label htmlFor={type} className='bold'>{title}</label>
        <input
        className='primary-textbar textbar-xxl' 
        type={type} 
        name={name} 
        value={valueText}
        placeholder={name}
        id={id}
        onChange={handlerChange} />
    </div>
  )
}

export default ElementForm