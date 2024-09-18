import { RegisterUserDto } from './register-user-dto';

describe('RegisterUserDto', () => {
  it('should create an instance', () => {
    expect(new RegisterUserDto('username', 'email@example.com', 'password', 'USER')).toBeTruthy();
  });
});
