import React, { useEffect, useState } from 'react'
import { obtenerRutinas } from '../api/Api'
import CardRutinaSimple from './CardRutinaSimple'
import NotRutins from '../utils/NotRutins'

const ExploradorPage = () => {

    const [rutinas, setRutinas] = useState([])

    useEffect(() => {
        obtenerRutinas().then(({data}) =>  setRutinas(data))
    }, [rutinas])

  return (
    <div className='temp-content-body '>
        { rutinas.length > 0 ?
            rutinas.map((rutina) => <CardRutinaSimple rutina={rutina} />):
            <NotRutins 
                titulo="No existen rutinas todavia" 
                mensaje="Nadie ha creado rutinas todavia, se el primero en crear una"
                showButton={false}
            />
        }
    </div>
  )
}

export default ExploradorPage