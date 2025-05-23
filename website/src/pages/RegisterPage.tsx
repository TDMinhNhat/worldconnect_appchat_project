import { useState } from "react";
import { useSelector } from "react-redux";
import type { RootState } from "../stores/stores";
import AuthenticateAPI from "../apis/authenticate";
import { useNavigate } from "react-router";

function RegisterPage() {

    const language = useSelector((state: RootState) => state.language.register_page);
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [sex, setSex] = useState(false);
    const [address, setAddress] = useState("");
    const [phone, setPhone] = useState("");
    const [email, setEmail] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [repeatPassword, setRepeatPassword] = useState("");
    const navigate = useNavigate();

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();

        // const auth = new AuthenticateAPI(firstName, lastName, sex, address, phone, email, username, password);
        // auth.registerAccount().then((response) => {
        //     console.log("Registration successful:", response);
        // }).catch((error) => {
        //     console.error("Registration failed:", error);
        // });
        
        navigate("/login");
    }

    return (
        <div className="w-100 h-100 d-flex flex-column justify-content-center align-items-center">
            <div className="bg-success w-50 h-50 d-flex flex-column justify-content-center align-items-center p-3">
                <div className="w-100">
                    <h1 className="text-center">{language.title}</h1>
                </div>
                <div className="w-100 mt-2 d-flex flex-row align-items-end justify-content-between">
                    <label className="col-3 form-label">{language.first_name.label}:</label>
                    <div className="w-100 d-flex flex-column justify-content-center">
                        <label className="text-danger">{language.first_name.error}</label>
                        <input className="form-control form-input" type="text" placeholder={language.first_name.placeholder} required onChange={(e) => setFirstName(e.target.value)}/>
                    </div>
                </div>
                <div className="w-100 mt-2 d-flex flex-row align-items-end justify-content-between">
                    <label className="col-3 form-label">{language.last_name.label}:</label>
                    <div className="w-100 d-flex flex-column justify-content-center">
                        <label className="text-danger">{language.last_name.error}</label>
                        <input className="form-control form-input" type="text" placeholder={language.last_name.placeholder} required onChange={(e) => setLastName(e.target.value)}/>
                    </div>
                </div>
                <div className="w-100 mt-2 d-flex flex-row align-items-center justify-content-between">
                    <label className="col-3 form-label">{language.sex.label}:</label>
                    <div className="d-flex flex-row align-items-center justify-content-around w-100">
                        <div>
                            <input type="radio" name="sex" id="male" onChange={() => setSex(false)} checked={!sex} />
                            <label className="ms-2" htmlFor="male">{language.sex.male_label}</label>
                        </div>
                        <div>
                            <input type="radio" name="sex" id="female" onChange={() => setSex(true)} checked={sex} />
                            <label className="ms-2" htmlFor="female">{language.sex.female_label}</label>
                        </div>
                    </div>
                </div>
                <div className="w-100 mt-2 d-flex flex-row align-items-center justify-content-between">
                    <label className="col-3 form-label">{language.address.label}:</label>
                    <div className="w-100 d-flex flex-column justify-content-center">
                        <input className="form-control form-input" type="text" placeholder={language.address.placeholder} required onChange={(e) => setAddress(e.target.value)}/>
                    </div>
                </div>
                <div className="w-100 mt-2 d-flex flex-row align-items-end justify-content-between">
                    <label className="col-3 form-label">{language.phone.label}:</label>
                    <div className="w-100 d-flex flex-column justify-content-center">
                        <label className="text-danger">{language.phone.error}</label>
                        <input className="form-control form-input" type="number" placeholder={language.phone.placeholder} required onChange={(e) => setPhone(e.target.value)}/>
                    </div>
                </div>
                <div className="w-100 mt-2 d-flex flex-row align-items-end justify-content-between">
                    <label className="col-3 form-label">{language.email.label}:</label>
                    <div className="w-100 d-flex flex-column justify-content-center">
                        <label className="text-danger">{language.email.error}</label>
                        <input className="form-control form-input" type="email" placeholder={language.email.placeholder} required onChange={(e) => setEmail(e.target.value)}/>
                    </div>
                </div>
                <div className="w-100 mt-2 d-flex flex-row align-items-end justify-content-between">
                    <label className="col-3 form-label">{language.username.label}:</label>
                    <div className="w-100 d-flex flex-column justify-content-center">
                        <label className="text-danger">{language.username.error}</label>
                        <input className="form-control form-input" type="text" placeholder={language.username.placeholder} required onChange={(e) => setUsername(e.target.value)}/>
                    </div>
                </div>
                <div className="w-100 mt-2 d-flex flex-row align-items-end justify-content-between">
                    <label className="col-3 form-label">{language.password.label}:</label>
                    <div className="w-100 d-flex flex-column justify-content-center">
                        <label className="text-danger">{language.password.error}</label>
                        <input className="form-control form-input" type="password" placeholder={language.password.placeholder} required onChange={(e) => setPassword(e.target.value)}/>
                    </div>
                </div>
                <div className="w-100 mt-2 d-flex flex-row align-items-end justify-content-between">
                    <label className="col-3 form-label">{language.confirm_password.label}:</label>
                    <div className="w-100 d-flex flex-column justify-content-center">
                        <label className="text-danger">{language.confirm_password.error}</label>
                        <input className="form-control form-input" type="password" placeholder={language.confirm_password.placeholder} required onChange={(e) => setRepeatPassword(e.target.value)}/>
                    </div>  
                </div>
                <div className="w-100 mt-2 d-flex flex-row align-items-center justify-content-between">
                    <button className="btn btn-primary mt-2" onClick={(e) => handleSubmit(e)}>{language.btn_register}</button>
                </div>
            </div>
        </div>
    )
}

export default RegisterPage;