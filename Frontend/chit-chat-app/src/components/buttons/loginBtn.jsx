import React from "react";
import { useNavigate} from 'react-router-dom'; 
import {AXIOS } from "../services/properties";
import { useEffect } from "react";


function LoginBtn(props){

    const navigate = useNavigate();
    const [state, setState] = React.useState("false");
    const [token, setToken] = React.useState("");
    const [logInResponse, setLogInResponse] = React.useState("");
    const [signInResponse, setSignInResponse] = React.useState("");
    const [loggedInUser, setLoggedInUser] = React.useState("");

    const buttonStyle = {
        backgroundColor: state ? "#FFBD59" : "white",
        color : state ? "white" : "#FFBD59",
        cursor: "pointer",
        border: "0",
        borderRadius: "4px",
        fontWeight: "600",
        fontSize: "20px",
        margin: "0 10px",
        width: "300px",
        padding: "10px 0",
        boxShadow: "0 0 5px #4a361f",
        transition: "0.4s"
    };

    const text = props.type === "login" ? "Log In" : "Sign In";

    function handeMouseOver(){
        setState(true);
    }

    function handleMouseOut(){
        setState(false);
    }


    async function callSignIn(userDetails){
        
        await AXIOS.post('/register', {name:userDetails.name, email:userDetails.email, password:userDetails.password})
            .then((response)=>{
                setSignInResponse("success");
            })
            .catch(error => {console.error('Error while sign in:', error);});
    }

    async function callLogIn(userDetails){
        await AXIOS.post('/login', {email:userDetails.email, password:userDetails.password})
            .then((response)=>{
                const accessToken = response.data.jwtToken;
                const email = response.data.username;
                setToken(accessToken);
                setLoggedInUser(email);
                setLogInResponse("success");
            })
            .catch(error => {console.error('Error while login in:', error); setLogInResponse("failed")});

       
    }

    

    useEffect(()=>{
       if(logInResponse==="success"){
            console.log("user"+loggedInUser);
            const data = {
                authorized: true, logInUser: loggedInUser, jwt:token
            };
            navigate('/chats', {state: {data: data}});
       }
    }, [logInResponse]);

    useEffect(()=>{
        if(signInResponse==="success"){
            navigate('/login');
        }
    }, [signInResponse]);

    async function handleClick() {
      
        //if current screen is home then navigate to either login screen or signin screen
        if(props.currentScreen==="home"){
            navigate("/"+props.type);
        }
        else if(props.currentScreen==="signin"){
            await callSignIn(props.userDetails);
        }
        else if(props.currentScreen==="login"){
            await callLogIn(props.userDetails);
            if(logInResponse==="success"){
                document.getElementById("dangerText").innerText = "";  
            }
            else if(logInResponse==="failed"){
                document.getElementById("dangerText").innerText = "Unable to Login, Invalid credentials";
            }
        }
    }

    return (
        <div style={{marginBottom: "20px"}}>
            <button onClick={handleClick} onMouseOver={handeMouseOver} onMouseOut={handleMouseOut} style={buttonStyle}>{text}</button>
        </div>
    );
}


export {LoginBtn};