import React from "react";
import {LoginBtn} from "../buttons/loginBtn";
import Image from "../image";

function SignInScreen(){
    document.body.classList.add('bodyStyle');
    const [userName, setUserName] = React.useState("");
    const [userEmail, setEmail] = React.useState("");
    const [userPassword, setPassword] = React.useState("");
    const userData = {
        name: userName,
        email: userEmail,
        password: userPassword
    }

    function handleChange(event){
        const eventName = event.target.name;
        const eventValue = event.target.value;
        if(eventName==="name"){
            setUserName(eventValue);
        }else if(eventName==="email"){
            setEmail(eventValue);
        }else if(eventName==="password"){
            setPassword(eventValue);
        }
    }

    return (
        <div>
            <Image/>
            <h2 style={{color:"#FFBD59"}}>SignIn New User</h2>
            <form>
                <div style={{marginBottom: "20px"}}>
                    <label>Name</label><br/>
                    <input onChange={handleChange} value={userName} type="text" placeholder="John Doe" name="name"/><br/>
                </div>
                <div style={{marginBottom: "20px"}}>
                    <label>Email</label><br/>
                    <input onChange={handleChange} value={userEmail} type="email" placeholder="ABC@Email.com" name="email"/><br/>
                </div>
                <div style={{marginBottom: "20px"}}>
                    <label >Password</label><br/>
                    <input onChange={handleChange} value={userPassword} type="password" placeholder="12345@" name="password"/>
                </div>
            </form>
            <LoginBtn currentScreen="signin" type="signin" userDetails={userData}/>
            <div id="dangerText" class="danger-text"></div>
        </div>
    );

}

export default SignInScreen;