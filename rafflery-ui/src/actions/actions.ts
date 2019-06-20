import {LOGGED_IN, LoginActionTypes} from './types';

export function loggedIn(email: string): LoginActionTypes {
    return {
        type: LOGGED_IN,
        email,
    };
}
// probably use typesafe-actions here, example
// export const loggedIn= (profile: Profile) => action(LOGGED_IN, profile)
