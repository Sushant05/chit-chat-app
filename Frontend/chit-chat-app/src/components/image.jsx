import React from "react";


function Image(){

    const imgageStyle = {
        width: "70%",
        height: "70%"
    };

    return (
        <div style={{marginBottom: "20px"}}>
            <img style={imgageStyle} src={process.env.PUBLIC_URL + '/images/chit-chat-logo.png'} alt="Chit Chat App Logo"></img>
        </div>    
    );
}

export default Image;