import jwt from 'jsonwebtoken'

export interface JwtPayload {
  name: string;
}

export const hmacAuth = {

  getToken(): string {
    return localStorage.getItem('id_token') as string;
  },

  setToken(username: string): string {
    const token = jwt.sign({
      name: username,
      iss: 'issuer',
      aud: 'clientId'
    }, 'secret');

    localStorage.setItem('id_token', token);
    return token
  },

  getLoggedInUser(): string | undefined {
    const token = this.getToken();

    if (!token) {
      return undefined;
    }

    return this.decode(token).name;
  },

  decode(token: string): JwtPayload {
    return jwt.decode(token) as JwtPayload;
  },

  clearToken() {
    localStorage.removeItem('id_token');
  }

};


