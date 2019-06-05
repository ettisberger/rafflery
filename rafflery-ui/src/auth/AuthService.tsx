import {Auth0DecodedHash, WebAuth} from 'auth0-js';
import history from '../history/History';

/**
 * Authentication service
 */
export default class AuthService {

    // auth0 web auth instance
    private auth0: WebAuth = new WebAuth({
        clientID: process.env.REACT_APP_AUTH_CLIENT_ID as string, // why do I need as string
        domain: process.env.REACT_APP_AUTH_DOMAIN_ADDRESS as string,
        responseType: 'token id_token',
        redirectUri: process.env.REACT_APP_AUTH_REDIRECT_URL,
        audience: `https://${process.env.REACT_APP_AUTH_DOMAIN_ADDRESS}/userinfo`,
        scope: 'openid',
    });

    public get authenticated(): boolean {
        const expiresAt = JSON.parse(localStorage.getItem('expires_at')!);
        return new Date().getTime() < expiresAt;
    }

    public logout(): void {
        // clear local storage
        localStorage.removeItem('access_token');
        localStorage.removeItem('id_token');
        localStorage.removeItem('expires_at');
        history.push('/login');
    }

    public login(): void {
        this.auth0.authorize();
    }

    public handleAuthentication(): void {
        this.auth0.parseHash((err, authResult) => {
            if (authResult && authResult.accessToken && authResult.idToken) {
                this.setSession(authResult);
            } else if (err) {
                // TODO error display
                // tslint:disable-next-line:no-console
                console.error(err);
                alert(`Error: ${err.error}. Check the console for further details.`);
            }
        });
    }

    public setSession(authResult: Auth0DecodedHash): void {
        const {accessToken, expiresIn, idToken} = authResult;
        const expiresAt = JSON.stringify(expiresIn! * 1000 + new Date().getTime());
        localStorage.setItem('access_token', accessToken!);
        localStorage.setItem('id_token', idToken!);
        localStorage.setItem('expires_at', expiresAt);
        history.push('/login');
    }
}
