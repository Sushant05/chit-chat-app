import React from "react";
import SendButton from "../buttons/sendBtn";
import Bubble from "../bubble";
import LogoutButton from "../buttons/logoutBtn";
import { Navigate, useLocation } from "react-router-dom";
import { AXIOS } from "../services/properties";
import { useEffect } from "react";


function ChatScreen(props){
    const [messageData, setMessageData] = React.useState([]);
    const location = useLocation();
    const[typedMessage, setMessage] = React.useState("");
    const loggedInUser = location.state.data.logInUser;

    useEffect(() => {
        const intervalId = setInterval(() => {
         callGetMessageAPI();
          console.log('Function called after interval');
        }, 1000); // Call the function every 1 seconds
    
        // Clean up the interval on component unmount
        return () => clearInterval(intervalId);
    }, []); // Run the effect only once on component mount

    if(!location.state.data.authorized){
        return <Navigate to="/login"/>
    }

    document.body.classList.remove('bodyStyle');

    function showChats(userData){
        console.log(userData);
        var isLoggedInUser =  false;
        if(userData.email===loggedInUser){
            isLoggedInUser = true;
        }
        return (
            <Bubble message={userData.message} sender={userData.email} isLoggedInUser={isLoggedInUser}/>
        );
    }

    async function callGetMessageAPI(){
        await AXIOS.get('/messages',{
            headers:{
                'Authorization' : 'Bearer ' + location.state.data.jwt,
            }
        }).then((response)=>{
            const data = response.data
            setMessageData(data);
        })
        .catch(error => console.error('Error Getting in:', error));
    }
     
    function clearInputValue(){
        setMessage("");
    }

    function handleChange(event){
        setMessage(event.target.value);
    }

    return (
        <div>
            <div className="navbar">
                <img src={process.env.PUBLIC_URL + '/images/chit-chat-logo.png'} alt="Chit Chat App Logo"></img>
                <span>Private Chat Room</span>
                <LogoutButton></LogoutButton>
            </div>
            <div style={{height:"100px"}}></div>
            {messageData.map(showChats)}
            <div style={{height:"100px"}}></div>
            <div className="bottom-bar">
                <div style={{width:"100%"}}>
                    <input onChange={handleChange} value={typedMessage} type="text" placeholder="Type your message..."/>
                    <SendButton onSent={clearInputValue} user={loggedInUser} message={typedMessage} token={location.state.data.jwt}/>
                </div>
            </div>
        </div>
    );

}

export default ChatScreen;