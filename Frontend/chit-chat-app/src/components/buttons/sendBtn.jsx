import React from "react";
import { AXIOS } from "../services/properties";
 

function SendButton(props){
    const[sent, isSent] = React.useState(false);
    console.log("props " + props);
    const buttonStyle = {
        backgroundColor:  "#2196F3",
        color : "white",
        cursor: "pointer",
        border: "none",
        borderRadius: "5px",
        width: "10%",
        padding: "4px 20px",
    };

    async function sendMessage() {
        try {
            await AXIOS.post('/send', {
                email: props.user,
                message: props.message
            }, {
                headers: {
                    'Authorization': 'Bearer ' + props.token
                }
            });
            isSent(true);
        } catch (error) {
            console.error('Error while sending message:', error);
        }
    }

    React.useEffect(()=>{
        if(sent){
            callGetMessageAPI();
            props.onSent();
            isSent(false);
        }
    },[sent]);
    
    async function callGetMessageAPI(){
        await AXIOS.get('/messages',{
            headers:{
                'Authorization' : 'Bearer ' + props.token,
            }
        }).then((response)=>{
            const data = response.data
            console.log("data: " + data);
        })
        .catch(error => console.error('Error Getting in:', error));
    }

    async function handleClick(){
        await sendMessage();
    }

    return (
        <button onClick={handleClick} style={buttonStyle}><i class="material-icons">send</i></button>
    );

}

export default SendButton;