import React from "react";
import { useNavigate } from "react-router-dom";

function LogoutButton(){

    const navigate = useNavigate();

    const logoutButtonStyle = {
        color: "#FFBD59",
        backgroundColor: "white",
        fontWeight: "bold",
        fontSize: "20px",
        borderRadius: "50px",
        border: "0",
        cursor: "pointer",
        margin: "0 10px",
        width: "100px",
        padding: "10px 0",
        marginRight: "50px",
        marginLeft: "auto",
        
    }

    function handleClick(){
        navigate('/login');
    }

    return (
        
        <button style={logoutButtonStyle} onClick={handleClick}>Logout</button>
        
    );

}

export default LogoutButton;