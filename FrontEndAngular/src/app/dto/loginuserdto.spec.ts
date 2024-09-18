import { Loginuserdto } from './loginuserdto';

describe('Loginuserdto', () => {
  it('should create an instance', () => {
    expect(new Loginuserdto('username',
    'password')).toBeTruthy();
  });
});
