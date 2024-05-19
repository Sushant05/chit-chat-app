import React from "react";
import Image from "../image";
import {LoginBtn} from "../buttons/loginBtn";

function HomeScreen(){
    document.body.classList.add('bodyStyle');
    return (
        <div>
            <Image/>
            <LoginBtn currentScreen="home" type="login"/>
            <LoginBtn currentScreen="home" type="signin"/>
        </div>
    );

}

export default HomeScreen;