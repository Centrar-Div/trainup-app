import React, { useEffect, useState } from 'react'
import { seguirRutina } from '../api/Api';
import { useLogin } from '../context/LoginContext';

const FollowBtn = ({ initFollow, rutinaID }) => {

    const [isFollowing, setIsFollowing] = useState(initFollow);
    const { user, setUser } = useLogin();

    const handleClick = () => {
        setIsFollowing(!isFollowing);
        seguirRutina(rutinaID).then(({ data }) => {
            setUser(data);
        });
    }

    return (
        <button className={`default-btn modern-btn ${isFollowing ? '' : 'primary-btn'}`} onClick={handleClick} >
            {
                isFollowing ?
                    'Dejar de seguir' :
                    'Seguir'
            }
        </button >
    )
}

export default FollowBtn