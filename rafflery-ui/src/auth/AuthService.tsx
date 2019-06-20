import {Auth0DecodedHash, WebAuth} from 'auth0-js';
import history from '../history/History';

/**
 * Authentication service
 */
class AuthService {

    private auth0: WebAuth;

    constructor() {
        this.auth0 = new WebAuth({
            clientID: process.env.REACT_APP_AUTH_CLIENT_ID || '',
            domain: process.env.REACT_APP_AUTH_DOMAIN_ADDRESS || '',
            responseType: 'token id_token',
            redirectUri: process.env.REACT_APP_AUTH_REDIRECT_URL,
            audience: `https://${process.env.REACT_APP_AUTH_DOMAIN_ADDRESS}/userinfo`,
            scope: 'openid email profile',
        });
    }

    public get authenticated(): boolean {
        const expiresAt = JSON.parse(localStorage.getItem('expires_at')!);
        return new Date().getTime() < expiresAt;
    }

    public logout(): void {
        // clear local storage
        localStorage.removeItem('access_token');
        localStorage.removeItem('id_token');
        localStorage.removeItem('expires_at');
        localStorage.removeItem('email');
        history.push('/login');
    }

    public loginWithAuth0(): void {
        this.auth0.authorize();
    }

    /**
     * This only works when the browser doesnt block 3rd party cookies
     * https://auth0.com/docs/cross-origin-authentication#limitations-of-cross-origin-authentication
     */
    public login(username: string, password: string) {
        return new Promise((resolve, reject) => this.auth0.login({
            realm: 'Username-Password-Authentication',
            username,
            password,
            }, (err: any, authResult) => {
                this.handleAuthResult(authResult, err);
            }));
    }

    public handleAuthentication(): void {
        this.auth0.parseHash((err, authResult) => {
            this.handleAuthResult(authResult, err);
        });
    }

    private handleAuthResult(authResult: any, err: any) {
        if (authResult && authResult.accessToken && authResult.idToken) {
            this.setSession(authResult);
        } else if (err) {
            // TODO error display
            alert(`Error: ${err.error}. Check the console for further details.`);
        }
    }

    public setSession(authResult: Auth0DecodedHash): void {
        const {accessToken, expiresIn, idToken, idTokenPayload} = authResult;
        const expiresAt = JSON.stringify(expiresIn! * 1000 + new Date().getTime());
        localStorage.setItem('access_token', accessToken!);
        localStorage.setItem('id_token', idToken!);
        localStorage.setItem('expires_at', expiresAt);
        localStorage.setItem('email', idTokenPayload.email);
    }

    public getProfile = (callBack: (err?: any, result?: any) => void) => {
        const accessToken = this.getAccessToken();
        // let self = this;
        this.auth0.client.userInfo(accessToken, (err, profile) => {
            callBack(err, profile);
        });
    }

    public getAccessToken() {
        const accessToken = localStorage.getItem('access_token');
        if (!accessToken) {
            throw new Error('No access token found');
        }
        return accessToken;
    }
}

const auth0Service = new AuthService();

export default auth0Service;
