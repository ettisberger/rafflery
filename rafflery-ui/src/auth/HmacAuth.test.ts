import { hmacAuth } from './HmacAuth';

describe('Hmac Auth', () => {
  afterEach(() => localStorage.clear());

  describe('when user "Andy" is selected', () => {
    it('should generate a signed token with the correct payload and add it to localstorage', () => {
      hmacAuth.setToken('Andy');
      const token = localStorage.getItem('id_token') as string;

      const payload = hmacAuth.decode(token);
      expect(payload.name).toBe('Andy');
    });

    it('should remove token from localstorage', () => {
      hmacAuth.setToken('Gomph');
      hmacAuth.clearToken();

      expect(localStorage.getItem('id_token')).toBe(null);
    });
  });
});
