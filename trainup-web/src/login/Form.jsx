import React from 'react'
import '../styles/boxes.css'


const Form = ({children, name, btnName, handlerSubmit}) => {
  
  return (
    <div className='default-box secondary-box' >
      <form className='flx column-box gap-s jc-center' onSubmit={handlerSubmit} action="">
        <h2 className='ta-center'>{name}</h2>
            {children}
        <button 
          className='default-btn primary-btn' 
          type='submit'>{btnName}</button>
      </form>
    </div>
  )
}

export default Form