import React, { useEffect, useState } from 'react'
import { obtenerRutinas } from '../api/Api'
import CardRutinaSimple from './CardRutinaSimple'

const ExploradorPage = () => {

    const [rutinas, setRutinas] = useState([])

    useEffect(() => {

        obtenerRutinas().then(({data}) => {
            setRutinas(data)
        })

    }, [rutinas])

  return (
    <div className='temp-content-body '>
        {
            rutinas.map((rutina) => <CardRutinaSimple rutina={rutina} />)
        }
    </div>
  )
}

export default ExploradorPage