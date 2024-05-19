import React from "react";
import { BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import LogInScreen from "./screens/loginScreen";
import SignInScreen from "./screens/signinScreen";
import ChatScreen from "./screens/chatScreen";
import HomeScreen from "./screens/homeScreen";


function App(){
    return (
        <Router>
            <Routes>
                <Route path="/" element={<HomeScreen/>} />
                <Route path="/login" element={<LogInScreen/>} />
                <Route path="/signin" element={<SignInScreen/>} />
                <Route path="/chats" element={<ChatScreen authorized={false}/>} />
            </Routes>
        </Router>
    );
}

export default App;