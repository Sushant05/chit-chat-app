import React from "react";

function Bubble(props){
   
    const bubbleStyle = {
        maxWidth: "15%",
        color: "#fff", 
        backgroundColor: props.isLoggedInUser ? "#4CAF50" : "#2196F3", 
        padding: "10px 15px", 
        borderRadius: props.isLoggedInUser ? "25px 25px 0 25px" :"0 25px 25px 25px", 
        marginBottom: "10px", 
        marginLeft :  props.isLoggedInUser ? "auto" : "0",
    };

    const senderStyle = {
        maxWidth: "15%",
        marginBottom: "10px", 
        marginLeft :  props.isLoggedInUser ? "auto" : "0",
    };

    return (
        <div>
            <p style={senderStyle}> {props.isLoggedInUser ? "" : props.sender}</p>
            <div style={bubbleStyle}>{props.message}</div>
        </div> 
    );
}

export default Bubble;