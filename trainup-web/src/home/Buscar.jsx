import React, { useEffect, useState } from 'react'
import { obtenerCategorias, obtenerRutinasPorCategoria } from '../api/Api'
import CardRutinaSimple from './CardRutinaSimple'
import NotRutins from '../utils/NotRutins'

const Buscar = () => {

    const [categorias, setCategorias] = useState([])
    const [rutinas, setRutinas] = useState([])

    useEffect(() => {
        obtenerCategorias().then(({data}) => setCategorias(data))
    }, [])

    
    const handlerGetRutina = (catego) => {
      console.log(catego)
      obtenerRutinasPorCategoria(catego).then(({data}) => setRutinas(data))
    }
    

  return (
    <div>
      <div></div>
      <div className='category-box flx gap-m jc-center'>
          {categorias.map((categoria) => <button 
                                            key={categoria} 
                                            className='default-btn modern-btn' 
                                            onClick={() => handlerGetRutina(categoria)}>
                                              {categoria}
                                            </button>)}
      </div>
      <div className='temp-content-body mt-10' >       
        { rutinas.length > 0 ?
            rutinas.map((rutina) => <CardRutinaSimple key={rutina.id} rutina={rutina} />):
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