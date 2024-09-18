import {Userrole} from "../enums/userrole";


export class User {
  id: number;
  fullName: string;
  username: string;
  password: string;
  email: string;
  role: Userrole;


  constructor(
    id: number,
    fullName: string,
    username: string,
    password: string,
    email: string,
    role: Userrole,

  ) {
    this.id = id;
    this.fullName = fullName;
    this.username = username;
    this.password = password;
    this.email = email;
    this.role = role;

  }
}
