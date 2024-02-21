import {useEffect, useState} from "react";

export default function AudioComp({src}) {
    const [audioSrc, setAudioSrc] = useState(src);

    function handlePlay() {
        setAudioSrc(src)
    }

    function handlePause() {
        setAudioSrc('')
    }

    useEffect(() => {
        if (!audioSrc) {
            setAudioSrc(src);
        }
    }, [audioSrc, src]);

    console.log(audioSrc)

    return (
        <div>
            <audio preload={"none"} src={audioSrc} onPause={handlePause} onPlay={handlePlay} controls/>
        </div>
    );
}