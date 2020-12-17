import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SpeedTestService {

  constructor(private http : HttpClient) { }

  async checkSpeed () {
    let headers = new HttpHeaders()
    headers.append('Content-Type', 'application/json')
    return await this.http.get <any> ('http://localhost:3000/speed-test/speed')
      .toPromise()
  }

  getHistory () {
    let headers = new HttpHeaders()
    headers.append('Content-Type', 'application/json')
    return this.http.get <any> ('http://localhost:3000/speed-test/history')
  }
}
