import { IAction, LOGGED_IN } from './types';

export function loggedIn(name: string): IAction<{ name: string }> {
  return {
    type: LOGGED_IN,
    payload: { name },
  };
}

// probably use typesafe-actions here, example
// export const loggedIn= (profile: Profile) => action(LOGGED_IN, profile)
