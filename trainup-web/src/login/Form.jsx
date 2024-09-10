import React from 'react'
import '../styles/boxes.css'


const Form = ({children, name, btnName}) => {
  return (
    <div className='default-box secondary-box'>
      <form className='flx column-box gap-s' action="">
        <h2 className='ta-center'>{name}</h2>
            {children}
        <button className='default-btn primary-btn' type='submit'>{btnName}</button>
      </form>
    </div>
  )
}

export default Form