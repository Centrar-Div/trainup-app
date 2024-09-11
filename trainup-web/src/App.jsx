import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom'
import './App.css'
import './styles/buttons.css'
import './styles/default.css'
import './styles/textbar.css'
import LandingPage from './landingPage/LandingPage'
import Login from './login/Login'
import { LoginProvider, useLogin } from './context/LoginContext'
import Navbar from './navbar/Navbar'
import TemplatePage from './TemplatePage'
import { useEffect, useState } from 'react'
import Sidebar from './navbar/Sidebar'
import HomePage from './home/HomePage'
import Rutina from './home/Rutina'

function App() {
  const [login , setLogin] = useState ('')
  useEffect(() => {
    setLogin(localStorage.getItem('id'))
  }, [login])

  return (
    <BrowserRouter>
      <LoginProvider>
      <Navbar/>
      <Sidebar/>
      <Routes>
        <Route path='/' element={ login ? <Navigate to={'/es/home'}/> : <Navigate to='/init'/>}/>
        <Route path='/init' element={login ? <Navigate to={'/es/home'}/> : <LandingPage/>} />
        <Route path='/es' element={<TemplatePage/>} >
          <Route path='home' element={<HomePage/>} />
          <Route path='home/rutina' element={<Rutina/>}/>
        </Route>
        <Route path='/login' element={<Login/>} />
        <Route path='*' element={login ? <Navigate to={'/es/home'}/> : <Navigate to='/init'/>}/>
      </Routes>
      </LoginProvider>
    </BrowserRouter>
  )
}

export default App
