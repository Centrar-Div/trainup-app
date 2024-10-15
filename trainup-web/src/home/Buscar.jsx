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
    // console.log(catego)
    setSearch('')
    obtenerRutinasPorCategoria(catego).then(({ data }) => setRutinas(data))
  }

  const handleSearch = () => {
    console.log(search);
    if (search !== '') {
      buscarRutina(search, dificultad).then(({ data }) => setRutinas(data))
    }

  }

  const handleKeyDown = (e) => {
    if (e.key === 'Enter') {
      handleSearch();
    }
  }

  const handleDificultadChange = (e) => {
    setDificultad(e.target.value);
  }

  const eliminarFiltro = () => {
    setDificultad('');
  }

  console.log(dificultad)


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
            <option value="" disabled>Dificultad</option>)
            {dificultades.map((dificultad) =>
              <option value={dificultad} id={dificultad}>{dificultad}</option>)}
          </select>
          {dificultad !== "" && (
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
        {rutinas.length > 0 ?
          rutinas.map((rutina) => <CardRutinaSimple key={rutina.id} rutina={rutina} />) :
          <NotRutins
            titulo="¿Vacio?"
            mensaje="Por favor, busca rutinas"
            showButton={false}
          />
        }
      </div>
    </div>
  )
}

export default Buscar