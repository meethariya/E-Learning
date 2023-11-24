import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { User } from '../../../models/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class GeneralService {
  constructor(private http: HttpClient) {}

  // server path
  private serverPath: string = environment.apiGateway;

  /**
   * Generates JWT token based on user credentials.
   *
   * @param email user email
   * @param password user password
   * @returns JWT token
   */
  generateToken(email: string, password: string) {
    let token: string = window.btoa(email + ':' + password);
    return this.http.get<string>(
      this.serverPath + '/api/userservice/generateToken',
      { headers: this.generateHeaders(token), responseType: 'text' as 'json' }
    );
  }

  /**
   * Generate headers.
   * @param token email and password encoded
   * @returns HttpHeaders
   */
  private generateHeaders(token: string): HttpHeaders {
    return new HttpHeaders({
      Authorization: 'Basic ' + token,
    });
  }

  /**
   * Save JWT token in browser cache.
   * @param token JWT
   */
  public setToken(token: string) {
    window.localStorage.setItem('token', token);
  }

  /**
   * Get JWT token from browser cache.
   * @returns JWT token
   */
  public getToken(): string {
    let token = window.localStorage.getItem('token');
    if (token == null) return '';
    else return token;
  }

  /**
   * Get user details.
   * @param email user email
   * @returns user details
   */
  public getUser(email: string): Observable<User> {
    return this.http.get<User>(
      this.serverPath + '/api/userservice/email/' + email
    );
  }

  /**
   * set user's role in browser's cache.
   * @param role user's role
   */
  public setRole(role: string): void {
    window.localStorage.setItem('role', role);
  }

  /**
   * get user's role from browser's cache
   * @returns role
   */
  public getRole(): string {
    let role = window.localStorage.getItem('role');
    if (role == null) return '';
    else return role;
  }

  /**
   * set user email to browser cache.
   * @param email user email
   */
  public setEmail(email: string): void {
    window.localStorage.setItem('email', email);
  }

  /**
   * get email from localstorage.
   * @returns email
   */
  public getEmail(): string {
    let email = window.localStorage.getItem('email');
    if (email == null) return '';
    else return email;
  }
}
