import "./App.css";
import { Routes, Route, BrowserRouter } from "react-router-dom";
import UserAuthPage from "./Components/UserAuth";
import MainPage from "./Pages/MainPage";
import Header from "./Layout/Header";
import Footer from "./Layout/Footer";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Header></Header>
        <Routes>
          <Route path="/" Component={UserAuthPage}></Route>
          <Route path="/main" Component={MainPage}></Route>
        </Routes>
        <Footer></Footer>
      </BrowserRouter>
    </div>
  );
}

export default App;
