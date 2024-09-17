import { BrowserRouter, Navigate, Route, Routes, useLocation } from 'react-router-dom'
import './App.css'
import './styles/buttons.css'
import './styles/default.css'
import './styles/textbar.css'
import LandingPage from './landingPage/LandingPage'
import Login from './login/Login'
import { LoginProvider } from './context/LoginContext'
import Navbar from './navbar/Navbar'
import TemplatePage from './TemplatePage'
import Sidebar from './navbar/Sidebar'
import HomePage from './home/HomePage'
import Rutina from './home/Rutina'
import InProgress from './utils/InProgress'
import Profile from './home/Profile'
import RutinasCompletadas from './home/RutinasCompletadas'

const Layout = () => {
  const location = useLocation()
  const hideNavbarRoutes = ['/login', '/init']
  const shouldShowSide   = !hideNavbarRoutes.includes(location.pathname)
  const shouldShowNavbar = !location.pathname.startsWith('/es');


  return (
    <>
      {shouldShowNavbar && <Navbar />}
      {shouldShowSide   && <Sidebar />}
    </>
  )
}

function App() {
  return (
    <BrowserRouter>
      <LoginProvider>
      <Layout/>
      <Routes>
        <Route path='/' element={ <Navigate to='/init'/>}/>
        <Route path='/init' element={<LandingPage/>} />
        <Route path='/es' element={<TemplatePage/>} >
          <Route path='home' element={<HomePage/>} />
          <Route path='home/rutina' element={<Rutina/>}/>
          <Route path='home/profile' element={<Profile/>}/>
          <Route path='home/completadas' element={<RutinasCompletadas/>}/>
          <Route path='home/inProgress' element={<InProgress/>}/>
        </Route>
        <Route path='/login' element={<Login/>} />
        <Route path='*' element={<Navigate to='/init'/>}/>
      </Routes>
      </LoginProvider>
    </BrowserRouter>
  )
}

export default App
