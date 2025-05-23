import axios from "axios";

class AuthenticateAPI {

    private firstName: string;
    private lastName: string;
    private sex: boolean;
    private address: string;
    private phone: string;
    private email: string;
    private username: string;
    private password: string;

    constructor(
        firstName?: string,
        lastName?: string,
        sex?: boolean,
        address?: string,
        phone?: string,
        email?: string,
        username?: string,
        password?: string
    ) {
        this.firstName = firstName ?? '';
        this.lastName = lastName ?? '';
        this.sex = sex ?? false;
        this.address = address ?? '';
        this.phone = phone ?? '';
        this.email = email ?? '';
        this.username = username ?? '';
        this.password = password ?? '';
    }

    public async registerAccount(): Promise<object> {
        return axios({
            method: "post",
            url: `${process.env.API_HOST}/api/v1/authenticate/register`,
            data: {
                first_name: this.firstName,
                last_name: this.lastName,
                sex: this.sex,
                address: this.address,
                phone: this.phone,
                email: this.email,
                username: this.username,
                password: this.password
            }
        }).then((response) => response.data).catch((error) => {
            console.error("Error during registration:", error);
            throw new Error("Registration failed");
        })
    }

    public async login(): Promise<object> {
        return axios({
            method: "post",
            url: `${process.env.API_HOST}/api/v1/authenticate/login`,
            params: {
                username: this.username,
                password: this.password
            }
        }).then((response) => response.data).catch((error) => {
            console.error("Error during login:", error);
            throw new Error("Login failed");
        })
    }
}

export default AuthenticateAPI;