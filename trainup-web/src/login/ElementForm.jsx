import React from 'react'

const ElementForm = ({name, type, id, title}) => {
  return (
    <div className='default-box none-mp column-box'>
        <label htmlFor={type} className='bold'>{title}</label>
        <input
        className='primary-textbar textbar-xxl' 
        type={type} 
        name={name} 
        id={id} />
    </div>
  )
}

export default ElementForm