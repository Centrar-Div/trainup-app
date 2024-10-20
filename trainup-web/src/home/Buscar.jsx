import React, { useEffect, useState } from 'react'
import { buscarRutina, obtenerCategorias, obtenerRutinasPorCategoria } from '../api/Api'
import CardRutinaSimple from './CardRutinaSimple'
import NotRutins from '../utils/NotRutins'
import { faArrowDownAZ, faArrowDownZA, faMagnifyingGlass, faXmark } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'

const Buscar = () => {

  const [categorias, setCategorias] = useState([])
  const [rutinas, setRutinas] = useState([])
  const [ordenAsc, setOrdenAsc] = useState(true)
  const [search, setSearch] = useState('')
  const [dificultad, setDificultad] = useState('')
  const [isFirstVisit, setIsFirstVisit] = useState(true)
  const dificultades = ['Principiante', 'Intermedio', 'Avanzado']

  useEffect(() => {
    obtenerCategorias().then(({ data }) => setCategorias(data))
  }, [])

  const handlerOrdenar = () => {
    console.log(rutinas)

    const sorted = [...rutinas].sort((a, b) => {
      if (ordenAsc) {
        return a.nombre.localeCompare(b.nombre)
      } else {
        return b.nombre.localeCompare(a.nombre)
      }
    })

    setRutinas(sorted)
    setOrdenAsc(!ordenAsc)
  }

  const handlerGetRutina = (catego) => {
    setSearch('')
    setIsFirstVisit(false)
    obtenerRutinasPorCategoria(catego).then(({ data }) => setRutinas(data))
  }

  const handleSearch = () => {
    setIsFirstVisit(false)
    console.log(search)
    if (search !== '') {
      buscarRutina(search, dificultad).then(({ data }) => setRutinas(data))
    }
  }

  const handleKeyDown = (e) => {
    if (e.key === 'Enter') {
      handleSearch()
    }
  }

  const handleDificultadChange = (e) => {
    const selectedDificultad = e.target.value;
    setDificultad(selectedDificultad)
    setIsFirstVisit(false)
    buscarRutina('', selectedDificultad).then(({ data }) => setRutinas(data))
  }

  const eliminarFiltro = () => {
    setDificultad('')
    setSearch('')
    setRutinas([])
    setIsFirstVisit(true)
  }

  return (
    <div>
      <div>
        <div className='search-header'>
          <input
            type="search"
            className='primary-textbar textbar-xxl'
            placeholder='Buscar'
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            onKeyDown={handleKeyDown} />
          <button className='default-btn-2' onClick={handleSearch}>
            <FontAwesomeIcon icon={faMagnifyingGlass} className="icon size-m" />
          </button>
          <select value={dificultad} onChange={handleDificultadChange} className='modern-btn primary-btn'>
            <option value="" disabled>Dificultad</option>
            {dificultades.map((dificultad) =>
              <option value={dificultad} key={dificultad}>{dificultad}</option>)}
          </select>
          {(dificultad !== "" || search !== '') && (
            <button className='default-btn-2' onClick={eliminarFiltro} >
              <FontAwesomeIcon icon={faXmark} />
            </button>
          )}

          <button className='default-btn-2' onClick={handlerOrdenar}>{
            ordenAsc ?
              <FontAwesomeIcon icon={faArrowDownAZ} className="icon size-m" /> :
              <FontAwesomeIcon icon={faArrowDownZA} className="icon size-m" />
          }</button>
        </div>
        <div></div>
      </div>
      <div className='category-box flx gap-m jc-center'>
        {categorias.map((categoria) => <button
          key={categoria}
          className='default-btn modern-btn'
          onClick={() => handlerGetRutina(categoria)}>
          {categoria}
        </button>)}
      </div>
      <div className='temp-content-body mt-10' >
        {rutinas.length > 0 ? (
          rutinas.map((rutina) => (
            <CardRutinaSimple rutina={rutina} key={rutina.id} />
          ))
        ) : (
          <NotRutins
            titulo={isFirstVisit ? "¡Bienvenido!" : "¿Vacio?"}
            mensaje={isFirstVisit ? "Comienza buscando una rutina o selecciona una categoría." : "No encontramos ninguna rutina con ese nombre. Intenta con otro título."}
            showButton={false}
          />
        )}
      </div>

    </div>
  )
}

export default Buscar
