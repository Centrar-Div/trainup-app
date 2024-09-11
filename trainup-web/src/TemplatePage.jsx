import React from 'react'
import './styles/templatepage.css'
import { Outlet } from 'react-router-dom'

const TemplatePage = () => {
  return (
    <div className='temp-init'>
        <div className='temp-navbar'></div>
        <div className='temp-body'>
            <div className='temp-sidebar'></div>
            <div className='temp-content'>
                {<Outlet/>}
            </div>
        </div>
    </div>
  )
}

export default TemplatePage