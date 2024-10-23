import React from 'react'
import NotRutins from '../utils/NotRutins'
import CardRutinaSimple from './CardRutinaSimple'

const Favoritas = ({rutinas}) => {

    

  return (
    <div className='container-boxinfo'>

        {
            rutinas && rutinas.length > 0 ? (rutinas.map(rutina => (<CardRutinaSimple rutina={rutina} />))) 
            : <NotRutins
                titulo="No tienes rutinas favoritas en este momento"
                mensaje="Solo debes buscar una rutina y hacer click en la estrella para agregarla a tus favoritos."
                showButton={true}
            />
        }


        
  </div>
  )
}

export default Favoritas