import React, { useEffect, useState } from 'react'

const FollowBtn = ({ initFollow }) => {

    const [isFollowing, setIsFollowing] = useState(initFollow);


    const handleClick = () => {
        setIsFollowing(!isFollowing);
    }

    useEffect(() => {
        setIsFollowing(initFollow);
    }, [initFollow]);

    console.log(isFollowing)

    return (
        <button className={`default-btn modern-btn ${isFollowing ? 'unf' : ''}`} onClick={handleClick} >
            {
                isFollowing ?
                    'Dejar de seguir' :
                    'Seguir'
            }
        </button >
    )
}

export default FollowBtn